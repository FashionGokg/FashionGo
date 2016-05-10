package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.android.volley.VolleyError;
import com.google.gson.JsonSyntaxException;
import com.maifeng.fashiongo.adapter.GoodListAdapter;
import com.maifeng.fashiongo.base.GoodListData;
import com.maifeng.fashiongo.base.GoodListType;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

/**
 * 商品列表界面
 * 
 * @author liekkas
 * 
 */
public class GoodListActivity extends Activity implements OnClickListener {
	private LinearLayout layout_left;
	private RadioButton tab_relevance, tab_sales, tab_price, tab_new;
	private ListView lv_goodlist;
	private GoodListAdapter goodlistadapter;
	private List<GoodListData> goodList;

//	private String url = "http://172.16.40.47/shop/index.php/home/Goods/getGoodsList";
//	private String url1 = "http://172.16.40.47/shop/index.php/home/goods/getGoodsList";
	private String ClassifyThreeId;
	private String type = "0";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goodlist);
		initView();
		setLight(0);

		Intent intent = getIntent();
		ClassifyThreeId = intent.getStringExtra("ClassifyThreeId");
		volleyPost(ClassifyThreeId, type);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	private void volleyPost(String ClassifyThreeId, String type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", "");
		map.put("ClassifyThreeId", ClassifyThreeId);
		map.put("difference", "0");
		map.put("type", type);
		map.put("page", "1");
		VolleyRequest.RequestPost(getApplicationContext(), Urls.GET_GOODS_LIST,
				"getGoodsList", map, new VolleyAbstract(this,
						VolleyAbstract.listener, VolleyAbstract.errorListener) {

					@Override
					public void onMySuccess(String result) {
						try {
							// 解析
							goodList = JsonUtil.parseJsonToBean(result,
									GoodListType.class).getData();

							// 绑定适配器
							goodlistadapter = new GoodListAdapter(
									getApplicationContext(), goodList);
							lv_goodlist.setAdapter(goodlistadapter);

							lv_goodlist.setOnItemClickListener(new OnItemClickListener() {
										@Override
										public void onItemClick(AdapterView<?> parent,View view, int position, long id) {
											// 向GoodDetailData传递数据
											Intent intent = new Intent(GoodListActivity.this,GoodDetailActivity.class);
											intent.putExtra("goodsCode",goodList.get(position).getGoodsCode());
											startActivity(intent);
										}
									});
						} catch (JsonSyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Log.i("TAG", "解析失败");
						}
					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});

	}

	/**
	 * 设置开关
	 * 
	 * @param i
	 */
	private void setLight(int i) {
		Lightoff();
		switch (i) {
		case 0:
			tab_relevance.setChecked(true);
			break;
		case 1:
			tab_sales.setChecked(true);
			break;
		case 2:
			tab_price.setChecked(true);
			break;
		case 3:
			tab_new.setChecked(true);
			break;
		}
	}

	/**
	 * 开关全暗
	 */
	private void Lightoff() {
		tab_relevance.setChecked(false);
		tab_sales.setChecked(false);
		tab_price.setChecked(false);
		tab_new.setChecked(false);
	}

	private void initView() {
		layout_left = (LinearLayout) findViewById(R.id.layout_left);
		tab_relevance = (RadioButton) findViewById(R.id.tab_relevance);
		tab_sales = (RadioButton) findViewById(R.id.tab_sales);
		tab_price = (RadioButton) findViewById(R.id.tab_price);
		tab_new = (RadioButton) findViewById(R.id.tab_new);
		lv_goodlist = (ListView) findViewById(R.id.lv_goodlist);

		tab_relevance.setOnClickListener(this);
		tab_sales.setOnClickListener(this);
		tab_price.setOnClickListener(this);
		tab_new.setOnClickListener(this);
		layout_left.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_left:
			finish();
			break;
		case R.id.tab_relevance:
			type = "0";
			volleyPost(ClassifyThreeId, type);
			setLight(0);
			break;
		case R.id.tab_sales:
			type = "1";
			volleyPost(ClassifyThreeId, type);
			setLight(1);
			break;
		case R.id.tab_price:
			type = "2";
			volleyPost(ClassifyThreeId, type);
			setLight(2);
			break;
		case R.id.tab_new:
			type = "3";
			volleyPost(ClassifyThreeId, type);
			setLight(3);
			break;

		default:
			break;
		}

	}

}
