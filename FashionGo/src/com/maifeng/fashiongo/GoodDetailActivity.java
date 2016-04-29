package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.VolleyError;
import com.maifeng.fashiongo.banner.GoodDetailImgnagViewPager;
import com.maifeng.fashiongo.base.ClassifyThreeData;
import com.maifeng.fashiongo.base.ClassifyThreeType;
import com.maifeng.fashiongo.base.GoodDetailData;
import com.maifeng.fashiongo.base.GoodDetailType;
import com.maifeng.fashiongo.base.GoodSizeData;
import com.maifeng.fashiongo.base.GoodsImageListData;
import com.maifeng.fashiongo.base.GoodsSpecificationsData;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 产品详情页界面
 * 
 * @author liekkas
 * 
 */

public class GoodDetailActivity extends Activity {
	private GoodDetailImgnagViewPager pager;
	private List<ClassifyThreeData> threeList;
	private List<GoodDetailData> detail;
	private GoodDetailType detai;
	private List<GoodsImageListData> imageList;
	private List<GoodsSpecificationsData> specifications;
	private List<GoodSizeData> sizes;
	private TextView layout_title;
	private LinearLayout layout_left;
	private String goodsCode;
	private TextView tv_goodname, tv_about, tv_goodcode, tv_isPackage,
			tv_originalPrice, tv_price, tv_goodnum;
	private TextView tv_color, tv_size;

	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_good_detail);

		intent = this.getIntent();
		goodsCode = intent.getStringExtra("goodsCode");

		initView();
		detaildata();

		layout_left.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private void detaildata() {
		System.out.println("解析了这里");
		//组装请求数据
		Map<String, String> map = new HashMap<String, String>();
		//登录标识
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		String accessToken = pref.getString("accessToken", "");
		map.put("accessToken", accessToken);
		map.put("goodsCode", goodsCode);
		System.out.println("---id"+goodsCode);
		System.out.println("accessToken"+accessToken);
		VolleyRequest.RequestPost(getApplicationContext(), "http://172.16.40.80/shop/index.php/home/Goods/getGoodsDetails", "GoodDetail",
				map, new VolleyAbstract(this, VolleyAbstract.listener,
						VolleyAbstract.errorListener) {

					@Override
					public void onMySuccess(String result) {
						detail = JsonUtil.parseJsonToBean(result,GoodDetailType.class).getData();
						Toast.makeText(getApplicationContext(),detai.getMessage(), Toast.LENGTH_SHORT).show();
						System.out.println("------>"+result);
//						tv_goodname.setText(((GoodDetailData) detail).getGoodsName());
//						tv_about.setText(((GoodDetailData) detail).getGoodsInfo());
//						tv_goodcode.setText(((GoodDetailData) detail).getGoodsCode());
//						tv_originalPrice.setText(((GoodDetailData) detail).getOriginalPrice());
//						tv_price.setText(((GoodDetailData) detail).getPrice());
//						if (((GoodDetailData) detail).getIsPackage() == 0) {
//							tv_isPackage.setText("否");
//						} else if (((GoodDetailData) detail).getIsPackage() == 1) {
//							tv_isPackage.setText("是");
//						}
					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub

						System.out.println("解析失败了");
					}
				});
	}

	private void initView() {
		// TODO Auto-generated method stub
		layout_left = (LinearLayout) findViewById(R.id.layout_left);
		layout_title = (TextView) findViewById(R.id.layout_title);
		tv_goodname = (TextView) findViewById(R.id.tv_goodname); // 商品名称
		tv_about = (TextView) findViewById(R.id.tv_about);// 商品简介
		tv_goodcode = (TextView) findViewById(R.id.tv_goodcode);// 商品编号
		tv_isPackage = (TextView) findViewById(R.id.tv_isPackage);// 是否包邮
		tv_originalPrice = (TextView) findViewById(R.id.tv_originalPrice);// 市场价
		tv_price = (TextView) findViewById(R.id.tv_price);// 优惠价
		tv_goodnum = (TextView) findViewById(R.id.tv_goodnum);// 总库存

		layout_title.setText("产品详情");
		pager = (GoodDetailImgnagViewPager) findViewById(R.id.pager);

	}
}
