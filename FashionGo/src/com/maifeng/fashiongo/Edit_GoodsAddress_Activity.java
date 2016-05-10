package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.maifeng.fashiongo.base.Delete_GetAddressType;
import com.maifeng.fashiongo.base.Edit_GetAddressType;
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.util.LogUtil;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;
import com.maifeng.fashiongo.volleyhandle.Volleyhandle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Edit_GoodsAddress_Activity extends Activity {

	private View topbar;
	private LinearLayout ll_returnbtn;
	private TextView tv_title;
	private LinearLayout ll_functionbtn;
	private TextView tv_name_function;
	

	private EditText dl_name;
	private EditText dl_phone;
	private TextView dl_province;
	private TextView dl_city;
	private TextView dl_area;
	private EditText dl_detailaddress;
	
	private Intent intent;
	private Button edit_save;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_goodsaddress);
		intent=this.getIntent();

		initView();
		idGet();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		//头部相关设置
		topbar =findViewById(R.id.topbar);
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		ll_functionbtn = (LinearLayout)topbar.findViewById(R.id.ll_functionbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		tv_name_function = (TextView)topbar.findViewById(R.id.tv_name_function);
		tv_title.setText("收货地址");
		tv_name_function.setText("删除");
	}

	private  void idGet(){
		edit_save=(Button)findViewById(R.id.edit_save);
		dl_name=(EditText)findViewById(R.id.dl_name);
		dl_phone=(EditText)findViewById(R.id.dl_phone);
		dl_province=(TextView)findViewById(R.id.dl_province);
		dl_city=(TextView)findViewById(R.id.dl_city);
		dl_area=(TextView)findViewById(R.id.dl_area);
		dl_detailaddress=(EditText)findViewById(R.id.dl_detailaddress);
		
		//从intent中取出数据
//		String name1 = intent.getStringExtra("name");
//		String phone1= intent.getStringExtra("phone");
//		String province1 = intent.getStringExtra("province");
//		String city1 = intent.getStringExtra("city");
//		String area1= intent.getStringExtra("area");
//		String address1 = intent.getStringExtra("address");
		//设置显示数据
		dl_province.setText(intent.getStringExtra("province"));
		dl_city.setText(intent.getStringExtra("city"));
		dl_area.setText(intent.getStringExtra("area"));
		dl_detailaddress.setText(intent.getStringExtra("address"));
		dl_name.setText(intent.getStringExtra("name"));
		dl_phone.setText(intent.getStringExtra("phone"));
		
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
				deltetPost();
				finish();
			}
		});
		//保存
		edit_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				editPost();
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
		//实例化请求队列
		RequestQueue queue = Volleyhandle.getInstance(this.getApplicationContext()).getRequestQueue();
		//活动销毁时取消请求减少内存消耗
		queue.cancelAll("DELETE_ADDRESS");
		queue.cancelAll("EDIT_ADDRESS");
	}
	//删除
	private void deltetPost(){
		//组装请求数据
		Map<String,String> map = new HashMap<String, String>();
		//登录标识
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		String accessToken = pref.getString("accessToken", "");
		String id = intent.getStringExtra("id");
		map.put("accessToken", accessToken);
		map.put("id", id);
		VolleyRequest.RequestPost(this,Urls.DELETE_ADDRESS, "DELETE_ADDRESS", map,
				new VolleyAbstract(this, VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						// TODO Auto-generated method stub
						Edit_GetAddressType eType = JsonUtil.parseJsonToBean(result,Edit_GetAddressType.class);
						System.out.println("----->"+eType.getMessage());
					}
					
					@Override
					public void onMyError(VolleyError error) {
						System.out.println("解析失败");
					}
				});
	}
	//保存
	private void editPost(){
		//组装请求数据
		Map<String,String> map = new HashMap<String, String>();
		//登录标识
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		String accessToken = pref.getString("accessToken", "");
		String id = intent.getStringExtra("id");
 		String name = dl_name.getText().toString();
		String phone =  dl_phone.getText().toString();
		String province = dl_province.getText().toString().trim();
		String city = dl_city.getText().toString().trim();
		String area = dl_area.getText().toString().trim();
		String address =dl_detailaddress.getText().toString().trim();
		map.put("accessToken", accessToken);
		map.put("id", id);
		map.put("name",name);
		map.put("phone",phone);
		map.put("province",province);
		map.put("city",city);
		map.put("area",area);
		map.put("address",address);
		VolleyRequest.RequestPost(this,Urls.EDIT_ADDRESS, "EDIT_ADDRESS", map,
				new VolleyAbstract(this, VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						// TODO Auto-generated method stub
						Edit_GetAddressType eType = JsonUtil.parseJsonToBean(result,Edit_GetAddressType.class);
						System.out.println("----->"+eType.getMessage());
					}
					
					@Override
					public void onMyError(VolleyError error) {
						System.out.println("解析失败");
					}
				});
	}
	
}
