package com.maifeng.fashiongo;

import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.maifeng.fashiongo.adapter.Goods_Province_Adapter;
import com.maifeng.fashiongo.base.Goods_ProvinceData;
import com.maifeng.fashiongo.base.Goods_ProvinceType;
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;
import com.maifeng.fashiongo.volleyhandle.Volleyhandle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Provice_Activity extends Activity {

	//初始化顶部导航栏控件
	private View topbar;
	private LinearLayout ll_returnbtn;
	private TextView tv_title;
	private ListView list_province;
	private List<Goods_ProvinceData> data1;
	private List<Goods_ProvinceData> data2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏头部
		super.onCreate(savedInstanceState);
		setContentView(R.layout.province_layout);
		topbar =findViewById(R.id.topbar);
		// 顶部导航栏控件id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		//顶部导航栏控件相关设置
		tv_title.setText("省份");
		//隐藏不要的内容
		topbar.findViewById(R.id.tv_name_function).setVisibility(View.INVISIBLE);
		
		list_province = (ListView)findViewById(R.id.list_province);
		
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
		volleyGet();
		list_province.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("pName",data1.get(position).getpName());
				intent.putExtra("pCode",data2.get(position).getpCode());
				setResult(1,intent);
				finish();
			}
		});
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//实例化请求队列
		RequestQueue queue = Volleyhandle.getInstance(this.getApplicationContext()).getRequestQueue();
		//活动销毁时取消请求减少内存消耗
		queue.cancelAll("GET_PROVINCE_LIST");
	}
	private void volleyGet() {
		VolleyRequest.RequestGet(this, UrlAddress.GET_PROVINCE_LIST,"GET_PROVINCE_LIST",
				new VolleyAbstract(this, VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						Goods_ProvinceType pType = gson.fromJson(result,Goods_ProvinceType.class);
						data1=pType.getData();
						data2=pType.getData();
						//绑定适配器
						Goods_Province_Adapter adapter = new Goods_Province_Adapter(getApplicationContext(), data1);
						list_province.setAdapter(adapter);
						
					}
					
					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						System.out.println("请求失败");
						
					}
				});
	}
}
