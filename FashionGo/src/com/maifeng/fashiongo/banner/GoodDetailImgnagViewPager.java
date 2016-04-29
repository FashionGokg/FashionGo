package com.maifeng.fashiongo.banner;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.base.GoodsImageListData;

/**
 * 产品图片导航Image navigation
 * 
 * @author whz
 * 
 */
@SuppressLint("NewApi")
public class GoodDetailImgnagViewPager extends LinearLayout {
	class ViewPagerData {
		String imageurl;
		ImageView imageview;
	}

	private Context context;
	private View fatherview;
	private ViewPager viewpager;
	private MyViewPagerAdapter adapter;
	private OnPageChange listener;
	private OnItemClick clicklistener;
	private ImageView btn_last;
	private ImageView btn_next;
	private ArrayList<ViewPagerData> viewdata;
	private int currindex;
	private int allcurrindex = 0;
	private int mweight;

	public GoodDetailImgnagViewPager(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public GoodDetailImgnagViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public GoodDetailImgnagViewPager(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		this.context = context;

		// 父界面
		fatherview = LayoutInflater.from(this.context).inflate(
				R.layout.layout_gooddetail_imgnagviewpager, null);
		btn_last = (ImageView) fatherview.findViewById(R.id.btn_last);
		btn_next = (ImageView) fatherview.findViewById(R.id.btn_next);
		viewpager = (ViewPager) fatherview.findViewById(R.id.vPager);

		// 固定宽高比例
		getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						// 现在布局全部完成，可以获取到任何View组件的宽度、高度、左边、右边等信息
						if (mweight == 0) {
							mweight = getMeasuredWidth();// 计算平均宽度
							GoodDetailImgnagViewPager.this
									.setLayoutParams(new LayoutParams(mweight,
											mweight));
						}
					}
				});

	}

	public synchronized void setimsges(
			final ArrayList<GoodsImageListData> urls, int defcurrindex) {

		this.removeAllViews();
		allcurrindex = urls.size() - 1;
		viewdata = new ArrayList<ViewPagerData>();

		RadioGroup.LayoutParams redioGroupParams = new RadioGroup.LayoutParams(
				10, 10);
		redioGroupParams.setMargins(5, 0, 5, 0);

		LayoutParams imageViewParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		for (int i = 0; i < urls.size(); i++) {

			final int viewindex = i;
			// ViewPager部分
			ViewPagerData vdata = new ViewPagerData();

			ImageView image = new ImageView(context);
			image.setScaleType(ScaleType.FIT_XY);
			// image.setScaleType(ScaleType.FIT_CENTER);
			image.setLayoutParams(imageViewParams);
			vdata.imageview = image;

			vdata.imageview.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (clicklistener != null) {
						clicklistener.onClick(viewindex);
					}
				}
			});

			vdata.imageurl = urls.get(i).getGoodsImage();

			// 加载图片
			// NetAccess.image(vdata.imageview, vdata.imageurl);
			// viewdata.add(vdata);

			btn_last.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (currindex == 0) {
						// currindex = urls.size()-1;
						// setCurrentItem(currindex);
					} else {
						currindex--;
						setCurrentItem(currindex);
					}

				}
			});

			btn_next.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (currindex == urls.size() - 1) {
						// currindex = 0;
						// setCurrentItem(currindex);
					} else {
						currindex++;
						setCurrentItem(currindex);
					}

				}
			});
		}

		adapter = new MyViewPagerAdapter(viewdata);
		viewpager.setAdapter(adapter);
		viewpager.setOnPageChangeListener(new MyOnPageChangeListener());

		if (defcurrindex != 0) {
			this.setCurrentItem(defcurrindex);
		}

		this.addView(fatherview);

	}

	public void setCurrentItem(int index) {
		viewpager.setCurrentItem(index);
	}

	// clicklistener
	public void setOnclick(OnItemClick clicklistener) {
		this.clicklistener = clicklistener;
	}

	/***
	 * 设置翻页图片切换时的响应事件
	 * 
	 * @param listener
	 */
	public void setOnPageChangeListener(OnPageChange listener) {
		this.listener = listener;
	}

	private class MyViewPagerAdapter extends PagerAdapter {
		private ArrayList<ViewPagerData> data;

		public MyViewPagerAdapter(ArrayList<ViewPagerData> data) {
			this.data = data;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(data.get(position).imageview);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(data.get(position).imageview, 0);
			return data.get(position).imageview;
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	private class MyOnPageChangeListener implements OnPageChangeListener {
		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int index) {
			// 页面转换
			currindex = index;

			// 触发子事件
			if (listener != null) {
				listener.onPageSelected(currindex);
			}

		}

	}

	public interface OnPageChange {
		public void onPageSelected(int currindex);
	}

	public interface OnItemClick {
		public void onClick(int index);
	}

}
