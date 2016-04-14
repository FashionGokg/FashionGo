package com.maifeng.fashiongo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;




import com.android.volley.RequestQueue;

import com.android.volley.VolleyError;

import com.google.gson.Gson;
import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.adapter.ClassifyOneAdapter;
import com.maifeng.fashiongo.adapter.ClassifyTwoAdapter;
import com.maifeng.fashiongo.base.ClassifyTwoData;
import com.maifeng.fashiongo.base.ClassifyTwoType;
import com.maifeng.fashiongo.base.ClassifyOneData;
import com.maifeng.fashiongo.base.ClassifyOneType;
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;
import com.maifeng.fashiongo.volleyhandle.Volleyhandle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;


public class FirstClassifyAcitvity extends Activity {

	private List<ClassifyOneData> onelist;
	private List<ClassifyTwoData> twolist;
	private ListView mListView;
	private GridView mGridView;
	private RequestQueue queue;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_classify);

		mGridView = (GridView) findViewById(R.id.gv_seconed_choice);
		mListView = (ListView) findViewById(R.id.lv_first_choice);


		
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		volleyGet();
		
		//监听列表变化
		mListView.setOnItemClickListener(new OnItemClickListener() {
			
						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							volleyPost(onelist.get(position).getClassifyId());
						}
					});
		
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
				//实例化请求队列  （采用单例模式）
				queue =Volleyhandle.getInstance(this.getApplication()).getRequestQueue();
				
				//活动销毁时 取消请求 减少内存消耗
				queue.cancelAll("GET_CLASSIFY_ONE");
				queue.cancelAll("GET_CLASSIFY_TWO");
				
	}

	/**
	 * 获取一级列表数据
	 */
	private void volleyGet() {
	//发起Get请求
	VolleyRequest.RequestGet(this, UrlAddress.GET_CLASSIFY_ONE,
			"GET_CLASSIFY_ONE", new VolleyAbstract(this,VolleyAbstract.listener,VolleyAbstract.errorListener) {
		
		@Override
		public void onMySuccess(String result) {
			
			try {
				//Gson解析
				Gson gson=new Gson();
				ClassifyOneType cType = gson.fromJson(result, ClassifyOneType.class);
				onelist=cType.getData();
				
				//绑定适配器
				ClassifyOneAdapter oneAdapter = new ClassifyOneAdapter(
						getApplicationContext(), onelist);
				mListView.setAdapter(oneAdapter);
				
				//设置默认
				volleyPost(onelist.get(0).getClassifyId());
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void onMyError(VolleyError error) {
			// TODO Auto-generated method stub
			
		}
	});
		
	}

	/**
	 * 请求二级列表数据
	 * @param classifyId 请求数据
	 */
	private void volleyPost(String classifyId) {
		//组装请求数据classifyId;
		Map<String, String> map = new HashMap<String, String>();
		map.put("classifyId", classifyId);
		//发起Post请求
		VolleyRequest.RequestPost(this, UrlAddress.GET_CLASSIFY_TWO, "GET_CLASSIFY_TWO", map,
				new VolleyAbstract(this, VolleyAbstract.listener,VolleyAbstract.errorListener) {
			
			@Override
			public void onMySuccess(String result) {
				try {
					//Gson解析
					Gson gson=new Gson();
					ClassifyTwoType cType = gson.fromJson(result, ClassifyTwoType.class);
					twolist=cType.getData();
					
					//绑定适配器
					ClassifyTwoAdapter twoAdapter = new ClassifyTwoAdapter(getApplicationContext(), twolist);
					mGridView.setAdapter(twoAdapter);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void onMyError(VolleyError error) {

				
			}
		});
		
	}
}
