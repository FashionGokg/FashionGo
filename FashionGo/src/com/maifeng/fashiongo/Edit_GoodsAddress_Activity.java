package com.maifeng.fashiongo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Edit_GoodsAddress_Activity extends Activity {

	private View topbar;
	private LinearLayout ll_returnbtn;
	private TextView tv_title;
	private LinearLayout ll_functionbtn;
	private TextView tv_name_function;
	
	
	private TextView dl_province;
	private TextView dl_city;
	private TextView dl_area;
	private EditText dl_detailaddress;
	private EditText dl_name;
	private EditText dl_phone;
	
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_goodsaddress);
		intent=this.getIntent();
		//头部相关设置
		topbar =findViewById(R.id.topbar);
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		ll_functionbtn = (LinearLayout)topbar.findViewById(R.id.ll_functionbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		tv_name_function = (TextView)topbar.findViewById(R.id.tv_name_function);
		tv_title.setText("收货地址");
		tv_name_function.setText("删除");
		idGet();
		clickOn();
	}
	private  void idGet(){
		dl_province=(TextView)findViewById(R.id.dl_province);
		
		dl_city=(TextView)findViewById(R.id.dl_city);
		
		dl_area=(TextView)findViewById(R.id.dl_area);
		dl_detailaddress=(EditText)findViewById(R.id.dl_detailaddress);
		dl_name=(EditText)findViewById(R.id.dl_name);
		dl_phone=(EditText)findViewById(R.id.dl_phone);
		//从intent中取出数据
		String name1 = intent.getStringExtra("name");
		String phone1= intent.getStringExtra("phone");
		String province1 = intent.getStringExtra("province");
		String city1 = intent.getStringExtra("city");
		String area1= intent.getStringExtra("area");
		String address1 = intent.getStringExtra("address");
		//设置显示数据
		System.out.println(province1+city1+area1);
		dl_province.setText(province1);
		dl_city.setText(city1);
		dl_area.setText(area1);
		dl_detailaddress.setText(address1);
		dl_name.setText(name1);
		dl_phone.setText(phone1);
		
	}
	private void clickOn(){
		//返回
		ll_returnbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		//删除
		ll_functionbtn.setOnClickListener(new OnClickListener() {
			
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
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	private void volleyPost(){
		
	}
}
