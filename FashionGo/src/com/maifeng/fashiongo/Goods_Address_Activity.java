package com.maifeng.fashiongo;

import java.util.List;

import com.maifeng.fashiongo.adapter.GoodsAddress_Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
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
	private GoodsAddress_Adapter adapter;
	private List<String> list;
	
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
		
//		lv_goodsaddress = (ListView)findViewById(R.id.lv_goodsaddress);
//		adapter = new GoodsAddress_Adapter(getApplicationContext(),list);
//		lv_goodsaddress.setAdapter(adapter);
		Click();
	}

	private void Click(){
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
}
