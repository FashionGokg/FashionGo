package com.maifeng.fashiongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.adapter.ClassifyOneAdapter;
import com.maifeng.fashiongo.adapter.ClassifyTwoAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

public class FirstClassifyAcitvity extends Activity {

	private List<String> list;
	private List<String> list2;
	private ListView mListView;
	private GridView mGridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_classify);

		mGridView = (GridView) findViewById(R.id.gv_seconed_choice);
		mListView = (ListView) findViewById(R.id.lv_first_choice);

		RequestQueue requestQueue = Volley
				.newRequestQueue(FirstClassifyAcitvity.this);
		StringRequest request = new StringRequest(Method.POST,
				"http://112.124.118.133:9065/ssgApp/getClassifyone",
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						System.out.println("一级数据--------->" + response);
						try {
							JSONObject jsonObject = new JSONObject(response);
							JSONArray array = jsonObject.getJSONArray("data");
							list = new ArrayList<String>();// 存放名称
							list2 = new ArrayList<String>();// 存放id
							for (int i = 0; i < array.length(); i++) {
								String classifyName = array.getJSONObject(i)
										.getString("classifyName");
								list.add(classifyName);
								String classifyId = array.getJSONObject(i)
										.getString("classifyId");
								list2.add(classifyId);
								System.out.println(classifyId);
							}
							System.out.println(list.size());
							ClassifyOneAdapter oneAdapter = new ClassifyOneAdapter(
									getApplicationContext(), list);
							mListView.setAdapter(oneAdapter);
							NetWork(list2.get(0));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
		requestQueue.add(request);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				NetWork(list2.get(position));

			}
		});
	}

	public void NetWork(final String t) {
		RequestQueue requestQueue = Volley
				.newRequestQueue(FirstClassifyAcitvity.this);
		StringRequest request = new StringRequest(Method.POST,
				"http://112.124.118.133:9065/ssgApp/getClassifyTwo",
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						System.out.println("二级数据------->" + response);
						try {
							JSONObject jsonObject = new JSONObject(response);
							JSONArray array = jsonObject.getJSONArray("data");
							list = new ArrayList<String>();
							for (int i = 0; i < array.length(); i++) {
								String classifyName = array.getJSONObject(i)
										.getString("classifyName");
								list.add(classifyName);
								String ClassifyTwoId = array.getJSONObject(i)
										.getString("ClassifyTwoId");
								System.out.println(ClassifyTwoId);
							}
							System.out.println(list.size());
							ClassifyTwoAdapter twoAdapter = new ClassifyTwoAdapter(
									getApplicationContext(), list);
							mGridView.setAdapter(twoAdapter);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("classifyId", t); // classifyId是一级分类的值
				return map;
			}
		};
		requestQueue.add(request);
	}
}
