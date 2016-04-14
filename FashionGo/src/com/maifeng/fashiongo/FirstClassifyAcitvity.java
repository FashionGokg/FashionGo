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
		// ���ر�����
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
		
		//�����б�仯
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
				//ʵ�����������  �����õ���ģʽ��
				queue =Volleyhandle.getInstance(this.getApplication()).getRequestQueue();
				
				//�����ʱ ȡ������ �����ڴ�����
				queue.cancelAll("GET_CLASSIFY_ONE");
				queue.cancelAll("GET_CLASSIFY_TWO");
				
	}

	/**
	 * ��ȡһ���б�����
	 */
	private void volleyGet() {
	//����Get����
	VolleyRequest.RequestGet(this, UrlAddress.GET_CLASSIFY_ONE,
			"GET_CLASSIFY_ONE", new VolleyAbstract(this,VolleyAbstract.listener,VolleyAbstract.errorListener) {
		
		@Override
		public void onMySuccess(String result) {
			
			try {
				//Gson����
				Gson gson=new Gson();
				ClassifyOneType cType = gson.fromJson(result, ClassifyOneType.class);
				onelist=cType.getData();
				
				//��������
				ClassifyOneAdapter oneAdapter = new ClassifyOneAdapter(
						getApplicationContext(), onelist);
				mListView.setAdapter(oneAdapter);
				
				//����Ĭ��
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
	 * ��������б�����
	 * @param classifyId ��������
	 */
	private void volleyPost(String classifyId) {
		//��װ��������classifyId;
		Map<String, String> map = new HashMap<String, String>();
		map.put("classifyId", classifyId);
		//����Post����
		VolleyRequest.RequestPost(this, UrlAddress.GET_CLASSIFY_TWO, "GET_CLASSIFY_TWO", map,
				new VolleyAbstract(this, VolleyAbstract.listener,VolleyAbstract.errorListener) {
			
			@Override
			public void onMySuccess(String result) {
				try {
					//Gson����
					Gson gson=new Gson();
					ClassifyTwoType cType = gson.fromJson(result, ClassifyTwoType.class);
					twolist=cType.getData();
					
					//��������
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
