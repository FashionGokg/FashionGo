package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.maifeng.fashiongo.base.LoginData;
import com.maifeng.fashiongo.base.LoginType;
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

public class LoginActivity extends Activity implements OnClickListener{

	private Button button;
	private TextView tv_register,tv_forgetpwd;
	private TextView tv_name_function,tv_title;

	private EditText et_phonenumber,et_password;
	private LoginData data;
	@Override

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

		initView();

	}

	private void initView() {
		button = (Button) findViewById(R.id.btn_login);
		tv_register = (TextView) findViewById(R.id.tv_register);
		tv_forgetpwd = (TextView) findViewById(R.id.tv_forgetpwd);
		et_phonenumber =(EditText) findViewById(R.id.et_phonenumber);
		et_password = (EditText) findViewById(R.id.et_password);
		tv_name_function = (TextView) findViewById(R.id.tv_name_function);
		tv_name_function.setVisibility(View.GONE);

		tv_title =(TextView) findViewById(R.id.tv_title);
		tv_title.setText("µÇÂ¼");

		button.setOnClickListener(this);
		tv_register.setOnClickListener(this);
		tv_forgetpwd.setOnClickListener(this);
	}



	private void volleyPost(String userName,String password){

		Map<String, String> map = new HashMap<String, String>();
//		map.put("userName", userName);
//		map.put("password", password);
		map.put("userName", "13626214191");
		map.put("password", "1234");		
		map.put("system", "android");
		VolleyRequest.RequestPost(this, UrlAddress.LOGIN, "GetVerify",map,
				new VolleyAbstract(this, VolleyAbstract.listener,VolleyAbstract.errorListener) {

			@Override
			public void onMySuccess(String result) {
				
				try {
					Gson gson=new Gson();
					LoginType LType = gson.fromJson(result, LoginType.class);    
					data=LType.getData();					
					
					if(LType.getErrorcode().equals("0")){
						SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
						Editor editor = pref.edit();
						editor.clear();
						editor.putString("accessToken", data.getAccessToken());
						editor.commit();

						Toast.makeText(LoginActivity.this, "µÇÂ½³É¹¦", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(getApplicationContext(),MainActivity.class);
						startActivity(intent);
						finish();
					}
					else if(LType.getErrorcode().equals("403")){
						Toast.makeText(LoginActivity.this,LType.getMessage() , Toast.LENGTH_SHORT).show();
					}
					else if(LType.getErrorcode().equals("402")){
						Toast.makeText(LoginActivity.this, LType.getMessage(), Toast.LENGTH_SHORT).show();
					}
					else if(LType.getErrorcode().equals("404")){
						Toast.makeText(LoginActivity.this,LType.getMessage(), Toast.LENGTH_SHORT).show();
					}
					else if(LType.getErrorcode().equals("1")){
						Toast.makeText(LoginActivity.this,LType.getMessage(), Toast.LENGTH_SHORT).show();
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			@Override
			public void onMyError(VolleyError error) {

			}
		});

	}	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			String userName = et_phonenumber.getText().toString().trim();
			String password = et_password.getText().toString().trim();
			volleyPost(userName, password);		
			break;
		case R.id.tv_register:
			Intent intent1 = new Intent(getApplicationContext(),RegisterActivity.class);
			startActivity(intent1);
			break;
			
		case R.id.tv_forgetpwd:
			Intent intent2 = new Intent(getApplicationContext(),FindPasswordActivity.class);
			startActivity(intent2);
		default:
			break;
		}

	}
}
