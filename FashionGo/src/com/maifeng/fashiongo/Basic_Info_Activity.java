package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.VolleyError;
import com.maifeng.fashiongo.base.GetPersonalDetailsData;
import com.maifeng.fashiongo.base.GetPersonalDetailsType;
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Basic_Info_Activity extends Activity implements OnClickListener {

	//初始化顶部导航栏控件
		private View topbar;
		private LinearLayout ll_returnbtn;
		private TextView tv_title;
		private RelativeLayout relativemessage;
		private RelativeLayout replace_accuont;
		
		private TextView tv_view_account;
		private TextView tv_view_username;
		private GetPersonalDetailsData data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_center_basic_info);
		
		Click();
	}
	private void Click(){
		topbar =findViewById(R.id.topbar);
		// 顶部导航栏控件id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		//顶部导航栏控件相关设置
		tv_title.setText("基本信息 ");
		topbar.findViewById(R.id.tv_name_function).setVisibility(View.INVISIBLE);
		
		tv_view_account=(TextView)findViewById(R.id.tv_view_account);
		tv_view_username =(TextView)findViewById(R.id.tv_view_username);
		
		relativemessage=(RelativeLayout)findViewById(R.id.relativemessage);
		replace_accuont=(RelativeLayout)findViewById(R.id.replace_accuont);
		
		ll_returnbtn.setOnClickListener(this);
		relativemessage.setOnClickListener(this);
		replace_accuont.setOnClickListener(this);
		//取出用户账号
		SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
		String userName = pref.getString("username", "");
		tv_view_account.setText(userName);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll_returnbtn:
			finish();
			break;
		case R.id.relativemessage:
			Intent intent = new Intent(getApplicationContext(),Edit_BasiinformationAcitivity.class);
//			String imageString = data.getImage();
//			String nameString = data.getpName();
//			String ageString = data.getAge();
//			String sexString = data.getSex();
//			String qqString = data.getQq();
//			String emailString = data.getEmail();
//			String pnameString = data.getpName();
//			String cnameString = data.getcName();
//			String areaString = data.getArea();
//			String addressString = data.getAddress();
//			intent.putExtra("imageString", data.getImage());
//			intent.putExtra("nameString", data.getName());
//			
//			intent.putExtra("sexString", data.getSex());
//			intent.putExtra("ageString", data.getAge());
//			
//			intent.putExtra("qqString", data.getQq());
//			intent.putExtra("emailString", data.getEmail());
//			intent.putExtra("pnameString", data.getpName());
//			intent.putExtra("cnameString", data.getcName());
//			intent.putExtra("areaString", data.getArea());
//			intent.putExtra("addressString", data.getAddress());
			startActivity(intent);
			finish();
			break;
		case R.id.replace_accuont:
			Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
			startActivity(intent1);
			finish();
			break;
		default:
			break;
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		vollePost();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	private void vollePost(){
		//登录标识
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		String accessToken = pref.getString("accessToken", "");
			//组装请求数据
			Map<String,String> map = new HashMap<String, String>();
			map.put("accessToken", accessToken);
			VolleyRequest.RequestPost(this,Urls.PERSONAL_DETAILS,"PERSONAL_DETAILS", map,
					new VolleyAbstract(this,VolleyAbstract.listener,VolleyAbstract.errorListener) {
						@Override
						public void onMySuccess(String result) {
							data = JsonUtil.parseJsonToBean(result, GetPersonalDetailsType.class).getData();
							//设置显示的昵称
							tv_view_username.setText(data.getName());
							
						}
						@Override
						public void onMyError(VolleyError error) {
						}
					});
		}
}
