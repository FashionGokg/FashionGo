package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.maifeng.fashiongo.adapter.GetMyCollectionAdapter;
import com.maifeng.fashiongo.base.GetMyCollectionData;
import com.maifeng.fashiongo.base.GetMyCollectionType;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

public class GetMyCollectionActivity extends Activity {
	//��ʼ�������������ؼ�
	private View topbar;
	private LinearLayout ll_returnbtn;
	private TextView tv_title;
	private TextView tv_name_function;
	
	private List<GetMyCollectionData> data;
	private GetMyCollectionAdapter adapter;
	private ListView list_mycollection;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getmycollection);
		list_mycollection = (ListView)findViewById(R.id.list_mycollection);
		topbar();
		collectionPost();
		
//		list_mycollection.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0,View arg1, int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				System.out.println("��ת������ҳ"+data.get(arg2).goodsCode);
//				Intent intent = new Intent(getApplicationContext(),GoodDetailActivity.class);
//				intent.putExtra("goodsCode", data.get(arg2).goodsCode);
//				startActivity(intent);
//				finish();
//				
//			}
//		});
		
	}
	private void topbar(){
		topbar=this.findViewById(R.id.topbar);
		// �����������ؼ�id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		tv_name_function = (TextView)topbar.findViewById(R.id.tv_name_function);
		//�����������ؼ��������
		tv_title.setText("�ҵ��ղ�");
		tv_name_function.setVisibility(View.INVISIBLE);
		ll_returnbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
	private void collectionPost(){
		//��װ��������
		Map<String,String> map = new HashMap<String, String>();
		//��¼��ʶ
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		final String accessToken = pref.getString("accessToken", "");
		map.put("accessToken", accessToken);
		map.put("page","1");
		VolleyRequest.RequestPost(this,Urls.GET_MY_COLLECTION,"GET_MY_COLLECTION", map,
				new VolleyAbstract(this, VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						System.out.println("���ص�����̫��"+result);
						data = JsonUtil.parseJsonToBean(result, GetMyCollectionType.class).getData();
						
						adapter = new GetMyCollectionAdapter(getApplicationContext(), data, accessToken);
						list_mycollection.setAdapter(adapter);
						
						System.out.println("��ת������ҳ"+data.get(0).goodsCode);
					}
					@Override
					public void onMyError(VolleyError error) {
						
					}
				});
	}
}
