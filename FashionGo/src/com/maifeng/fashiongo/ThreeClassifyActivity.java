package com.maifeng.fashiongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.R.id;
import com.maifeng.fashiongo.R.layout;
import com.maifeng.fashiongo.adapter.ClassifyOneAdapter;
import com.maifeng.fashiongo.adapter.ClassifyThreeAdapter;
import com.maifeng.fashiongo.base.ClassifyThreeData;
import com.maifeng.fashiongo.base.ClassifyThreeType;
import com.maifeng.fashiongo.base.ClassifyTwoData;
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 三级分类界面
 * 
 * @author Administrator
 * 
 */
public class ThreeClassifyActivity extends Activity {
	private List<ClassifyThreeData> threelist;
	private List<ClassifyTwoData> twolist;
	private ListView mListView;
	private View topbar;
	private LinearLayout ll_returnbtn;
	private TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_classify_three);
		// Intent intent = getIntent();
		// String ClassifyTwoId = intent.getStringExtra("ClassifyTwoId");
		// Log.i("asdasdasdasdsad", ClassifyTwoId);
		initView();
		// NetWork2(ClassifyTwoId);

		// ThreeDat(ClassifyTwoId);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// 接收FirstClassifyActivity传递过来的数据
		Intent intent = getIntent();
		ThreeData(intent.getStringExtra("ClassifyTwoId"));

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	/**
	 * 获取三级分类数据 http://172.16.40.80/shop/index.php/home/getClassifyThree
	 * http://172.16.40.80/shop/index.php/home/Classify/getClassifyThree
	 * UrlAddress.GET_CLASSIFY_THREE
	 */
	private void ThreeData(String ClassifyTwoId) {
		// 组装请求数据ClassifyTwoId
		Map<String, String> map = new HashMap<String, String>();
		map.put("ClassifyTwoId", ClassifyTwoId);
		// 发起Get请求
		VolleyRequest
				.RequestPost(
						this,
						"http://172.16.40.80/shop/index.php/home/Classify/getClassifyThree",
						"GET_CLASSIFY_THREE", map, new VolleyAbstract(this,
								VolleyAbstract.listener,
								VolleyAbstract.errorListener) {

							@Override
							public void onMySuccess(String result) {
								try {
									// GSon解析
									Gson gson = new Gson();
									ClassifyThreeType threeType = gson
											.fromJson(result,
													ClassifyThreeType.class);
									threelist = threeType.getData();

									// 绑定适配器
									ClassifyThreeAdapter threeAdapter = new ClassifyThreeAdapter(
											getApplicationContext(), threelist);
									mListView.setAdapter(threeAdapter);
									mListView
											.setOnItemClickListener(new OnItemClickListener() {

												@Override
												public void onItemClick(
														AdapterView<?> parent,
														View view,
														int position, long id) {
													// TODO Auto-generated
													// method stub
													Intent intent = new Intent(
															ThreeClassifyActivity.this,
															GoodListActivity1.class);
													intent.putExtra(
															"ClassifyThreeId",
															threelist
																	.get(position)
																	.getClassifyThreeId());
													startActivity(intent);

												}
											});

								} catch (JsonSyntaxException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							@Override
							public void onMyError(VolleyError error) {
								// TODO Auto-generated method stub

							}
						});
	}

	// private void NetWork2(final String t) {
	// RequestQueue requestQueue = Volley
	// .newRequestQueue(ThreeClassifyActivity.this);
	// StringRequest request = new StringRequest(Method.POST,
	// "http://112.124.118.133:9065/ssgApp/getClassifyThree",
	// new Listener<String>() {
	//
	// @Override
	// public void onResponse(String response) {
	// // TODO Auto-generated method stub
	// System.out.println("三级数据--------->" + response);
	// try {
	// JSONObject jsonObject = new JSONObject(response);
	// JSONArray array = jsonObject.getJSONArray("data");
	// list = new ArrayList<String>();// 存放名称
	// list2 = new ArrayList<String>();// 存放id
	// for (int i = 0; i < array.length(); i++) {
	// String classifyName = array.getJSONObject(i)
	// .getString("classifyName");
	// list.add(classifyName);
	// String ClassifyThreeId = array.getJSONObject(i)
	// .getString("ClassifyThreeId");
	// list2.add(ClassifyThreeId);
	// System.out.println(ClassifyThreeId);
	// }
	// System.out.println(list.size());
	// ClassifyThreeAdapter adapter = new ClassifyThreeAdapter(
	// getApplicationContext(), list);
	// mListView.setAdapter(adapter);
	//
	// mListView
	// .setOnItemClickListener(new OnItemClickListener() {
	//
	// @Override
	// public void onItemClick(
	// AdapterView<?> parent,
	// View view, int position, long id) {
	// Intent intent = new Intent(
	// ThreeClassifyActivity.this,
	// GoodListActivity.class);
	// Log.i("ClassifyThreeId------->",
	// list2.get(position));
	// intent.putExtra("ClassifyThreeId",
	// list2.get(position));
	// startActivity(intent);
	//
	// }
	// });
	//
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// }, new Response.ErrorListener() {
	//
	// @Override
	// public void onErrorResponse(VolleyError error) {
	// // TODO Auto-generated method stub
	//
	// }
	// }) {
	// @Override
	// protected Map<String, String> getParams() throws AuthFailureError {
	// HashMap<String, String> map = new HashMap<String, String>();
	// map.put("ClassifyTwoId", t); // ClassifyTwoId是二级分类的值
	// return map;
	// }
	// };
	// requestQueue.add(request);
	// }

	private void initView() {
		// TODO Auto-generated method stub

		mListView = (ListView) findViewById(R.id.lv_classify_three);
		topbar = findViewById(R.id.topbar);
		tv_title = (TextView) topbar.findViewById(R.id.tv_title);
		topbar.findViewById(R.id.tv_name_function)
				.setVisibility(View.INVISIBLE);
		ll_returnbtn = (LinearLayout) topbar.findViewById(R.id.ll_returnbtn);
		tv_title.setText("分类");
		ll_returnbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(),
						FirstClassifyAcitvity.class);
				startActivity(intent);
			}
		});

	}

}
