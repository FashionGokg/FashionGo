package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.maifeng.fashiongo.adapter.Goods_City_Adapter;
import com.maifeng.fashiongo.base.Goods_CityData;
import com.maifeng.fashiongo.base.Goods_CityType;
import com.maifeng.fashiongo.constant.UrlAddress;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class City_Activity extends Activity {

	//初始化顶部导航栏控件
	private View topbar;
	private LinearLayout ll_returnbtn;
	private TextView tv_title;
	
	private List<Goods_CityData> data1;
	private List<Goods_CityData> data2;
	private ListView list_city;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏头部
		super.onCreate(savedInstanceState);
		setContentView(R.layout.city_layout);
		topbar =findViewById(R.id.topbar);
		// 顶部导航栏控件id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		//顶部导航栏控件相关设置
		tv_title.setText("城市");
		//隐藏不要的内容
		topbar.findViewById(R.id.tv_name_function).setVisibility(View.INVISIBLE);
		
		list_city=(ListView)findViewById(R.id.list_city);
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
		list_city.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(); 
				intent.putExtra("cName",data1.get(position).getcName());
				intent.putExtra("cCode",data2.get(position).getcCode());
				setResult(2,intent);
				finish();
			}
		});
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//实例化请求队列
		RequestQueue queue = Volleyhandle.getInstance(getApplicationContext()).getRequestQueue();
		//活动销毁时取消请求，减少内存消耗
		queue.cancelAll("GET_CITY_LIST");
	}
	private void volleyPost(){
		//组装请求数据pCode
		Map<String,String> map = new HashMap<String, String>();
		String pCode = intent.getStringExtra("pCode");
		map.put("pCode",pCode);
		VolleyRequest.RequestPost(this,UrlAddress.GET_CITY_LIST,"GET_CITY_LIST", map,
				new VolleyAbstract(this,VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						Goods_CityType cType = gson.fromJson(result,Goods_CityType.class);
						data1=cType.getData();//城市名
						data2=cType.getData();//id
						//绑定适配器
						Goods_City_Adapter adapter = new Goods_City_Adapter(getApplicationContext(),data1);
						list_city.setAdapter(adapter);
					}
					
					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						
					}
				});
	}
}
