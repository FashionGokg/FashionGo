package com.maifeng.fashiongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.maifeng.fashiongo.adapter.GoodsAddress_Adapter;
import com.maifeng.fashiongo.adapter.Goods_GetAddress_Adapter;
import com.maifeng.fashiongo.base.GoodsAddressType;
import com.maifeng.fashiongo.base.Goods_AddNew_AddressType;
import com.maifeng.fashiongo.base.Goods_GetAddressData;
import com.maifeng.fashiongo.base.Goods_GetAddressType;
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;
import com.maifeng.fashiongo.volleyhandle.Volleyhandle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Goods_Address_Activity extends Activity {

	//初始化顶部导航栏控件
	private View topbar;
	private LinearLayout ll_returnbtn;
	private LinearLayout ll_functionbtn;
	private TextView tv_title;
	private TextView tv_name_function;
	
	private ListView lv_goodsaddress;
	//测试
	private Goods_GetAddress_Adapter adapter;
	private Goods_GetAddressData type;
	private List<Goods_GetAddressData> data;
	
	private List<Goods_GetAddressData> listaddress;
	private List<Goods_GetAddressData> listid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_center_goodsaddress);
		topbar =findViewById(R.id.topbar);
		// 顶部导航栏控件id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		ll_functionbtn = (LinearLayout)topbar.findViewById(R.id.ll_functionbtn);
		tv_name_function = (TextView)topbar.findViewById(R.id.tv_name_function);
		//顶部导航栏控件相关设置
		tv_title.setText("收货地址");
		tv_name_function.setText("新增");
		lv_goodsaddress = (ListView)findViewById(R.id.lv_goodsaddress);
		basiClick();
	}

	private void basiClick(){
		ll_returnbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		ll_functionbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Goods_Address_Activity.this,New_Goods_Address_Activity.class);
				startActivity(intent);
			}
		});
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		volleyPost();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//实例化请求队列
		RequestQueue queue = Volleyhandle.getInstance(getApplicationContext()).getRequestQueue();
		//活动销毁时取消请求，减少内存消耗
		queue.cancelAll("GET_RECEIVE_ADDRESS");
	}
	private void volleyPost(){
		//组装请求数据
		Map<String,String> map = new HashMap<String, String>();
		//登录标识
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		String accessToken = pref.getString("accessToken", "");
		map.put("accessToken", accessToken);
		VolleyRequest.RequestPost(this,UrlAddress.GET_RECEIVE_ADDRESS,"GET_RECEIVE_ADDRESS", map,
				new VolleyAbstract(this,VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						Goods_GetAddressType gType = gson.fromJson(result,Goods_GetAddressType.class);
						listaddress=gType.getData();
						listid=gType.getData();//存放id
						Goods_GetAddressData addressData = new Goods_GetAddressData();
						Goods_GetAddressData idaddressData = new Goods_GetAddressData();
						idaddressData.getId();
						addressData.getName();
						addressData.getPhone();
						addressData.getProvince();
						addressData.getCity();
						addressData.getArea();
						addressData.getAddress();
						listaddress.add(addressData);
						listid.add(idaddressData);
						System.out.println("-------》"+result);
						Goods_GetAddress_Adapter adapter1 = new Goods_GetAddress_Adapter(getApplicationContext(), listaddress);
						lv_goodsaddress.setAdapter(adapter1);
						lv_goodsaddress.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent, View view, int position,
									long id) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(getApplicationContext(),Edit_GoodsAddress_Activity.class);
								SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
								String accessToken = pref.getString("accessToken", "");
								String idString = listid.get(position).getId();
								String names = listaddress.get(position).getName();
								String phones = listaddress.get(position).getPhone();
								String provinces = listaddress.get(position).getProvince();
								String citys = listaddress.get(position).getCity();
								String areas = listaddress.get(position).getArea();
								String addresss = listaddress.get(position).getAddress();
								intent.putExtra("accessToken",accessToken);
								intent.putExtra("id", idString);
								intent.putExtra("name",names);
								intent.putExtra("phone", phones);
								intent.putExtra("province",provinces);
								intent.putExtra("city", citys);
								intent.putExtra("area",areas);
								intent.putExtra("address", addresss);
								startActivity(intent);
							}
						});
					}
					
					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						
					}
				});
		
	}
}
