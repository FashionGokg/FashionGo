package com.maifeng.fashiongo.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.maifeng.fashiongo.FirstClassifyAcitvity;
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
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.util.ListViewUtils;
import com.maifeng.fashiongo.volleyhandle.MyImageCache;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;
import com.maifeng.fashiongo.volleyhandle.Volleyhandle;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
	private ImageView img_menu;
	private List<BannerData> bannerlist;
	private List<RecommendationData>recommendationlist;
	private RecommendationAdapter mRecommendationAdapter;
	private ListView listView;
	private List<ThreeGoodsADInfoData> adInfolist;
	
	private ImageCycleView mAdView;

	private ArrayList<String> mImageUrl = null;
	
	private NetworkImageView left_image,right_image_top,right_image_bottom;
	
	private NetworkImageView[] networkImageViews ;
	
	private String urlRecommendation = "http://172.16.40.80/shop/index.php/home/BannerList/getRecommendation";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_fragment, container, false);
		
		//关闭焦点键盘
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		
		img_menu = (ImageView) view.findViewById(R.id.img_menu);
		left_image = (NetworkImageView) view.findViewById(R.id.left_image);
		right_image_top = (NetworkImageView) view.findViewById(R.id.right_image_top);
		right_image_bottom = (NetworkImageView) view.findViewById(R.id.right_image_bottom);
		
		networkImageViews = new NetworkImageView[]{left_image,right_image_top,right_image_bottom};
		

		
		listView = (ListView) view.findViewById(R.id.recommendation_list);
		mAdView = (ImageCycleView)view.findViewById(R.id.ad_view);
		
		img_menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 跳转到商品分类页面
				Intent intent = new Intent(getActivity()
						.getApplicationContext(), FirstClassifyAcitvity.class);
				startActivity(intent);
			}
		});
		volleyGetBanner();
		volleyGetImage(networkImageViews);
		volleyGetRecommendation("1");
		
		return view;
		
	}











	private void volleyGetBanner() {
		VolleyRequest.RequestGet(getActivity(), UrlAddress.GET_BANNER_LIST, "GET_BANNER_LIST",
				new VolleyAbstract(getActivity(),VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
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
		VolleyRequest.RequestGet(getActivity(), UrlAddress.GET_THREE_GOODS_AD_INFO, "GET_THREE_GOODS_AD_INFO",
				new VolleyAbstract(getActivity(),VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
					ThreeGoodsADInfoType type =JsonUtil.parseJsonToBean(result, ThreeGoodsADInfoType.class);
					adInfolist = type.getData();
					ImageLoader imageLoader =new ImageLoader(Volleyhandle.getInstance(context).getRequestQueue(), MyImageCache.getImageCache(context));
					
					for (int i = 0; i < adInfolist.size(); i++) {
						//ImageLoader.getInstance().displayImage(adInfolist.get(i).getADImage(), imageViews[i]);
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
		VolleyRequest.RequestPost(getActivity(), urlRecommendation, 
				"Recommendation", map, new VolleyAbstract(getActivity(),VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						recommendationlist=JsonUtil.parseJsonToBean(result, RecommendationType.class).getData();
						mRecommendationAdapter = new RecommendationAdapter(getActivity(),recommendationlist);
						listView.setAdapter(mRecommendationAdapter);
						ListViewUtils.setListViewHeightBasedOnChildren(listView);
					}
					
					@Override
					public void onMyError(VolleyError error) {
						
					}
				});
	}
	
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(int position, View imageView) {
			// TODO 单击图片处理事件
			Toast.makeText(getActivity(), "position->"+position, Toast.LENGTH_LONG).show();
		}



		@Override
		public void displayImage(String imageURL, NetworkImageView imageView,com.android.volley.toolbox.ImageLoader imageLoader) {

			imageView.setImageUrl(imageURL, imageLoader);
		}
	};



}
