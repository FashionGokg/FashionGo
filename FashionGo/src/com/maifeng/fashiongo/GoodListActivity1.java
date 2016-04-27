package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.maifeng.fashiongo.adapter.GoodListAdapter;
import com.maifeng.fashiongo.base.GoodListData;
import com.maifeng.fashiongo.base.GoodListType;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class GoodListActivity1 extends Activity implements OnClickListener {
	private LinearLayout layout_left;
	private RadioButton tab_relevance, tab_sales, tab_price, tab_new;
	private ListView lv_goodlist;
	private GoodListAdapter goodlistadapter;
	private List<GoodListData> goodList;

	private String url = "http://172.16.40.80/shop/index.php/home/Goods/getGoodsList";
	private String url1 = "http://172.16.40.80/shop/index.php/home/goods/getGoodsList";
	private String ClassifyThreeId;
	private String type;

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

	private void volleyPost(String ClassifyThreeId, String type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", "");
		map.put("ClassifyThreeId", ClassifyThreeId);
		map.put("difference", "0");
		map.put("type", type);
		map.put("page", "1");
		VolleyRequest.RequestPost(getApplicationContext(), url1,
				"getGoodsList", map, new VolleyAbstract(this,
						VolleyAbstract.listener, VolleyAbstract.errorListener) {

					@Override
					public void onMySuccess(String result) {
						try {
							Gson gson = new Gson();
							GoodListType goodListType = gson.fromJson(result,
									GoodListType.class);
							goodList = goodListType.getData();

							goodlistadapter = new GoodListAdapter(
									getApplicationContext(), goodList);
							lv_goodlist.setAdapter(goodlistadapter);
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
			Toast.makeText(getApplicationContext(), "相关", Toast.LENGTH_LONG)
					.show();
			setLight(0);
			break;
		case R.id.tab_sales:
			setLight(1);
			Toast.makeText(getApplicationContext(), "销量", Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.tab_price:
			setLight(2);
			Toast.makeText(getApplicationContext(), "价格", Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.tab_new:
			setLight(3);
			Toast.makeText(getApplicationContext(), "新品", Toast.LENGTH_LONG)
					.show();
			break;

		default:
			break;
		}

	}

}
