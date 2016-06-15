package com.maifeng.fashiongo.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.maifeng.fashiongo.FirstClassifyAcitvity;
import com.maifeng.fashiongo.GoodDetailActivity;
import com.maifeng.fashiongo.GoodListActivity;
import com.maifeng.fashiongo.R;

import com.maifeng.fashiongo.adapter.RecommendationAdapter;
import com.maifeng.fashiongo.banner.ImageCycleView;
import com.maifeng.fashiongo.banner.ImageCycleView.ImageCycleViewListener;
import com.maifeng.fashiongo.base.BannerData;
import com.maifeng.fashiongo.base.BannerType;
import com.maifeng.fashiongo.base.RecommendationData;
import com.maifeng.fashiongo.base.RecommendationType;
import com.maifeng.fashiongo.base.ThreeGoodsADInfoData;
import com.maifeng.fashiongo.base.ThreeGoodsADInfoType;
import com.maifeng.fashiongo.constant.LazyFragment;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.util.KeyboradUtil;
import com.maifeng.fashiongo.util.ListViewUtils;
import com.maifeng.fashiongo.volleyhandle.MyImageCache;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;
import com.maifeng.fashiongo.volleyhandle.Volleyhandle;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class HomeFragment extends LazyFragment implements OnClickListener{
	private ImageView img_menu;
	private LinearLayout header_layout,footer_layout;
	private ScrollView scrollView;
	int headerHeight;// 顶部布局文件的高度；
	private List<BannerData> bannerlist;
	private List<RecommendationData>recommendationlist;
	private RecommendationAdapter mRecommendationAdapter;
	private ListView listView;
	private List<ThreeGoodsADInfoData> adInfolist;
	
	private boolean isLoading;// 正在加载；
	
	private boolean isRemark;// 标记，当前是在listview最顶端摁下的；
	/**
	 * 摁下的Y值
	 */
	private int startY;// 摁下时的Y值；

	private int state;// 当前的状态；
	private final int NONE = 0;// 正常状态；
	private final int PULL = 1;// 提示下拉状态；
	private final int RELESE = 2;// 提示释放状态；
	private final int REFLASHING = 3;// 刷新状态；
	
	
	private ImageCycleView mAdView;

	private ArrayList<String> mImageUrl = null;
	
	private NetworkImageView left_image,right_image_top,right_image_bottom;
	
	private NetworkImageView[] networkImageViews ;
	
	private EditText et_search;
	
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_fragment, container, false);
		
		//关闭焦点键盘
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		

		initView(view);
		search();
		isPrepared = true;
		lazyLoad();

		
		return view;
		
	}
	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		if (!isPrepared||!isVisible) {
			return;
		}
		volleyGetBanner();
		volleyGetImage(networkImageViews);
		volleyGetRecommendation("1");
		
	}
	@Override
	public void onStop() {
		super.onStop();
		Volleyhandle.getInstance(getActivity()).getRequestQueue().cancelAll("GET_BANNER_LIST");
		Volleyhandle.getInstance(getActivity()).getRequestQueue().cancelAll("GET_THREE_GOODS_AD_INFO");
		Volleyhandle.getInstance(getActivity()).getRequestQueue().cancelAll("GET_RECOMMENDATION");
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mAdView.pushImageCycle();
		
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mAdView.pushImageCycle();
	}
	private void initView(View view) {
		et_search = (EditText) view.findViewById(R.id.et_search);
		img_menu = (ImageView) view.findViewById(R.id.img_menu);
		left_image = (NetworkImageView) view.findViewById(R.id.left_image);
		right_image_top = (NetworkImageView) view.findViewById(R.id.right_image_top);
		right_image_bottom = (NetworkImageView) view.findViewById(R.id.right_image_bottom);
		left_image.setOnClickListener(this);
		right_image_top.setOnClickListener(this);
		right_image_bottom.setOnClickListener(this);
		networkImageViews = new NetworkImageView[]{left_image,right_image_top,right_image_bottom};
		listView = (ListView) view.findViewById(R.id.recommendation_list);
		mAdView = (ImageCycleView)view.findViewById(R.id.ad_view);
		
		scrollView = (ScrollView) view.findViewById(R.id.scrollView);
		header_layout = (LinearLayout)view.findViewById(R.id.header_layout);
		measureView(header_layout);
		headerHeight = header_layout.getMeasuredHeight();
		topPadding(-headerHeight);
		
		footer_layout= (LinearLayout) view.findViewById(R.id.footer_layout);
		footer_layout.setVisibility(View.GONE);
		scrollView.setOnTouchListener(new TouchListenerImpl());
		
		
		img_menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 跳转到商品分类页面
				Intent intent = new Intent(getActivity()
						.getApplicationContext(), FirstClassifyAcitvity.class);
				startActivity(intent);
			}
		});
	}
	/**
	 * 通知父布局，占用的宽，高；
	 * 
	 * @param view
	 */
	private void measureView(LinearLayout view) {
		ViewGroup.LayoutParams p = view.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);
		int height;
		int tempHeight = p.height;
		if (tempHeight > 0) {
			height = MeasureSpec.makeMeasureSpec(tempHeight,
					MeasureSpec.EXACTLY);
		} else {
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		view.measure(width, height);
	}

	/**
	 * 设置header 布局 上边距；
	 * 
	 * @param topPadding
	 */
	private void topPadding(int topPadding) {
		header_layout.setPadding(header_layout.getPaddingLeft(), topPadding,
				header_layout.getPaddingRight(), header_layout.getPaddingBottom());
		header_layout.invalidate();
	}


	private void volleyGetBanner() {
		VolleyRequest.RequestGet(getActivity(), Urls.GET_BANNER_LIST, "GET_BANNER_LIST",
				new VolleyAbstract(getActivity(),VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						System.out.println(result);
						bannerlist = JsonUtil.parseJsonToBean(result, BannerType.class).getData();
						mImageUrl = new ArrayList<String>();
						for (int i = 0; i < bannerlist.size(); i++) {
							mImageUrl.add(bannerlist.get(i).getBannerImage());
						}
						mAdView.setImageResources(mImageUrl, mAdCycleViewListener);
					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						
					}
				});
		
	}
	private void volleyGetImage(final NetworkImageView[] imageViews) {
		VolleyRequest.RequestGet(getActivity(), Urls.GET_THREE_GOODS_AD_INFO, "GET_THREE_GOODS_AD_INFO",
				new VolleyAbstract(getActivity(),VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
					ThreeGoodsADInfoType type =JsonUtil.parseJsonToBean(result, ThreeGoodsADInfoType.class);
					adInfolist = type.getData();
					ImageLoader imageLoader =new ImageLoader(Volleyhandle.getInstance(context).getRequestQueue(), MyImageCache.getImageCache(context));
					
					for (int i = 0; i < adInfolist.size(); i++) {
						imageViews[i].setScaleType(ScaleType.FIT_XY);
						imageViews[i].setImageUrl(adInfolist.get(i).getADImage(), imageLoader);
					}
					
						
					}
					
					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						
					}
				});
	}
	private void volleyGetRecommendation(String page) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("page", page);
		VolleyRequest.RequestPost(getActivity(), Urls.GET_RECOMMENDATION, 
				"GET_RECOMMENDATION", map, new VolleyAbstract(getActivity(),VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						recommendationlist=JsonUtil.parseJsonToBean(result, RecommendationType.class).getData();
						mRecommendationAdapter = new RecommendationAdapter(getActivity(),recommendationlist);
						listView.setAdapter(mRecommendationAdapter);
						ListViewUtils.setListViewHeightBasedOnChildren(listView);
						listView.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								Toast.makeText(getActivity(), recommendationlist.get(position).getGoodsCode(), Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(getActivity(),GoodDetailActivity.class);
								intent.putExtra("Code", "homelist");
								intent.putExtra("goodsCode",recommendationlist.get(position).getGoodsCode());
								startActivity(intent);
							}
						});
					}
					
					@Override
					public void onMyError(VolleyError error) {
						
					}
				});
	}
	
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(int position, View imageView) {
			//  单击图片处理事件
			Intent intent = new Intent(getActivity(),GoodDetailActivity.class);
			intent.putExtra("Code", "home");
			intent.putExtra("goodsCode", bannerlist.get(position).getGoodscode());
			startActivity(intent);
			
		}



		@Override
		public void displayImage(String imageURL, NetworkImageView imageView,com.android.volley.toolbox.ImageLoader imageLoader) {

			imageView.setImageUrl(imageURL, imageLoader);
		}
	};

	public class TouchListenerImpl implements OnTouchListener{

		@Override
		public boolean onTouch(View view, MotionEvent ev) {
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				int scrollY=view.getScrollY();
				if (scrollY==0) {
					isRemark = true;
					startY = (int) ev.getY();
				}

				
				
				break;
			case MotionEvent.ACTION_MOVE:
				onMove(ev);
				int scrollY1=view.getScrollY();
				int height1=view.getHeight();
				int scrollViewMeasuredHeight1=scrollView.getChildAt(0).getMeasuredHeight();
//				if (scrollY==0) {
//					System.out.println("滑动到了顶端 view.getScrollY()="+scrollY);
//				}
				if ((scrollY1+height1)==scrollViewMeasuredHeight1) {
					System.out.println("滑动到了底部 scrollY="+scrollY1);
                    System.out.println("滑动到了底部 height="+height1);
                    System.out.println("滑动到了底部 scrollViewMeasuredHeight="+scrollViewMeasuredHeight1);
                    
                    if (!isLoading) {
                    	isLoading = true;
						footer_layout.setVisibility(View.VISIBLE);
						Handler handler = new Handler();
						handler.postDelayed(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//获取更多数据
								
								//更新listview显示；
								System.out.println("加载数据中");
								//通知listview加载完毕
								loadComplete();
							}
						}, 3000);
						
					}
                    
				}
				break;
			case MotionEvent.ACTION_UP:
				if (state == RELESE) {
					state = REFLASHING;
					// 加载最新数据；
					reflashViewByState();
//					iReflashListener.onReflash();
					Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							//获取最新数据
							
							//通知listview 刷新数据完毕；
							reflashComplete();
						}
					}, 2000);
				} else if (state == PULL) {
					state = NONE;
					isRemark = false;
					reflashViewByState();
					view.scrollBy(0,0);
				}
				break;
			}
			
			return false;
		}
		
	}
	
	/**
	 * 判断移动过程操作；
	 * 
	 * @param ev
	 */
	private void onMove(MotionEvent ev) {
		if (!isRemark) {
			return;
		}
		int tempY = (int) ev.getY();
		int space = tempY - startY;
		int topPadding = space - headerHeight;
		switch (state) {
		case NONE:
			if (space > 0) {
				state = PULL;
				reflashViewByState();
			}
			break;
		case PULL:
			topPadding(topPadding);
			if (space > headerHeight + 20) {
				state = RELESE;
				reflashViewByState();
			}
			break;
		case RELESE:
//			topPadding(topPadding);
			if (space < headerHeight + 30) {
				state = PULL;
				reflashViewByState();
			} else if (space <= 0) {
				state = NONE;
				isRemark = false;
				reflashViewByState();
			}
			break;
		}
	}


	/**
	 * 根据当前状态，改变界面显示；
	 */
	private void reflashViewByState() {
		TextView tip = (TextView) header_layout.findViewById(R.id.tip);
		ImageView arrow = (ImageView) header_layout.findViewById(R.id.arrow);
		ProgressBar progress = (ProgressBar) header_layout.findViewById(R.id.progress);
		RotateAnimation anim = new RotateAnimation(0, 180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim.setDuration(500);
		anim.setFillAfter(true);
		RotateAnimation anim1 = new RotateAnimation(180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		anim1.setDuration(500);
		anim1.setFillAfter(true);
		switch (state) {
		case NONE:
			arrow.clearAnimation();
			topPadding(-headerHeight);
			break;

		case PULL:
			arrow.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tip.setText("下拉可以刷新！");
			arrow.clearAnimation();
			arrow.setAnimation(anim1);
			break;
		case RELESE:
			arrow.setVisibility(View.VISIBLE);
			progress.setVisibility(View.GONE);
			tip.setText("松开可以刷新！");
			arrow.clearAnimation();
			arrow.setAnimation(anim);
			break;
		case REFLASHING:
			topPadding(50);
			arrow.setVisibility(View.GONE);
			progress.setVisibility(View.VISIBLE);
			tip.setText("正在刷新...");
			arrow.clearAnimation();
			break;
		}
	}

	/**
	 * 获取完数据；
	 */
	public void reflashComplete() {
		state = NONE;
		isRemark = false;
		reflashViewByState();
		TextView lastupdatetime = (TextView) header_layout.findViewById(R.id.lastupdate_time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String time = format.format(date);
		lastupdatetime.setText("最近刷新时间"+time);
	}
	
	/**
	 * 加载完毕
	 */
	public void loadComplete(){
		isLoading = false;
		footer_layout.setVisibility(View.GONE);
	}



// 搜索
public void search() {
	et_search.setOnEditorActionListener(new OnEditorActionListener() {

		@Override
		public boolean onEditorAction(TextView v, int actionId,
				KeyEvent event) {
			// 关闭软件盘
			KeyboradUtil.closeKeyborad(getActivity());
			
			if (actionId == EditorInfo.IME_ACTION_SEARCH) {
				
				if (TextUtils.isEmpty(et_search.getText())||et_search.getText().toString().trim().length()==0) {
					Toast.makeText(getActivity(), "搜索内容不能为空", Toast.LENGTH_SHORT).show();
				}else {
					Intent intent = new Intent(getActivity(),GoodListActivity.class);

					String keyword = et_search.getText().toString().trim();
					intent.putExtra("home_keyword", keyword);
					intent.putExtra("Code", "home");
					startActivity(intent);
				}
				
			}
			return false;
		}

	});
}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.left_image:
		Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
		break;
	case R.id.right_image_top:
		Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
		break;
	case R.id.right_image_bottom:
		Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
		break;
	}
	
}




}
