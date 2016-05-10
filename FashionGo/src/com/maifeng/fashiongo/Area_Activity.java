package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.maifeng.fashiongo.adapter.Goods_Area_Adapter;
import com.maifeng.fashiongo.adapter.Goods_City_Adapter;
import com.maifeng.fashiongo.base.Goods_AreaData;
import com.maifeng.fashiongo.base.Goods_AreaType;
import com.maifeng.fashiongo.base.Goods_CityData;
import com.maifeng.fashiongo.base.Goods_CityType;
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;
import com.maifeng.fashiongo.volleyhandle.Volleyhandle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Area_Activity extends Activity {

	//��ʼ�������������ؼ�
	private View topbar;
	private LinearLayout ll_returnbtn;
	private TextView tv_title;
	
	private List<Goods_AreaData> data1;
	private List<Goods_AreaData> data2;
	private ListView list_area;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);//����ͷ��
		super.onCreate(savedInstanceState);
		setContentView(R.layout.area_layout);
		topbar =findViewById(R.id.topbar);
		// �����������ؼ�id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		//�����������ؼ��������
		tv_title.setText("��/��");
		//���ز�Ҫ������
		topbar.findViewById(R.id.tv_name_function).setVisibility(View.INVISIBLE);
		
		list_area=(ListView)findViewById(R.id.list_arae);
		intent=this.getIntent();
		clickOn();
	}
	private void clickOn() {
		// TODO Auto-generated method stub
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
		volleyPost();
		list_area.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(); 
				intent.putExtra("aArea",data1.get(position).getArea());
				intent.putExtra("aCode",data2.get(position).getaCode());
				setResult(3,intent);
				finish();
			}
		});
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//ʵ�����������
		RequestQueue queue = Volleyhandle.getInstance(getApplicationContext()).getRequestQueue();
		//�����ʱȡ�����󣬼����ڴ�����
		queue.cancelAll("GET_AREA_LIST");
	}
	private void volleyPost(){
		//��װ��������pCode
		Map<String,String> map = new HashMap<String, String>();
		String cCode = intent.getStringExtra("cCode");
		map.put("cCode",cCode);
		VolleyRequest.RequestPost(this,Urls.GET_AREA_LIST,"GET_CITY_LIST", map,
				new VolleyAbstract(this,VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						Goods_AreaType cType = gson.fromJson(result,Goods_AreaType.class);
						data1=cType.getData();//������
						data2=cType.getData();//id
						//��������
						Goods_Area_Adapter adapter = new Goods_Area_Adapter(getApplication(),data1);
						list_area.setAdapter(adapter);
					}
					
					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						
					}
				});
	}

}
