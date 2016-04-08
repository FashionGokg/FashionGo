package com.maifeng.fashiongo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FeedBackAcitvtty extends Activity implements OnClickListener{
	private View topbar;
	private TextView tv_title;
	private LinearLayout ll_returnbtn;
	
	private EditText ed_title,ed_context;
	private Button btn_push;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback_layout);
		
		initView();
		
	}

	private void initView() {
		topbar = findViewById(R.id.topbar);
		ll_returnbtn=(LinearLayout) findViewById(R.id.ll_returnbtn);
		tv_title = (TextView) topbar.findViewById(R.id.tv_title);
		ed_title = (EditText) findViewById(R.id.ed_title);
		ed_context = (EditText) findViewById(R.id.ed_context);
		btn_push = (Button) findViewById(R.id.btn_push);
		
		tv_title.setText("意见反馈");
		topbar.findViewById(R.id.ll_functionbtn).setVisibility(View.INVISIBLE);
		ll_returnbtn.setOnClickListener(this);
		btn_push.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_returnbtn:
			Intent intent = new Intent(getApplicationContext(),MainActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.btn_push:
			String title = ed_title.getText().toString().trim();
			String context = ed_context.getText().toString().trim();
			//提交操作  开线程 耗时网络操作
			Toast.makeText(getApplicationContext(), "正在提交", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}
