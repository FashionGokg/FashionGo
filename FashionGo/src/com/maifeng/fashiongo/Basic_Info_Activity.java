package com.maifeng.fashiongo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Basic_Info_Activity extends Activity {

	//初始化顶部导航栏控件
		private View topbar;
		private LinearLayout ll_returnbtn;
		private TextView tv_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_center_basic_info);
		topbar =findViewById(R.id.topbar);
		// 顶部导航栏控件id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		//顶部导航栏控件相关设置
		tv_title.setText("基本信息");
		topbar.findViewById(R.id.tv_name_function).setVisibility(View.INVISIBLE);
		
		Click();
	}
	private void Click(){
		ll_returnbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

}
