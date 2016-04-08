package com.maifeng.fashiongo;

import com.maifeng.fashiongo.fragment.MineFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Basic_Info_Activity extends Activity {

	//��ʼ�������������ؼ�
		private View topbar;
		private LinearLayout ll_returnbtn;
		private TextView tv_title;
		private TextView tv_name_function;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_center_basic_info);
		topbar =findViewById(R.id.topbar);
		// �����������ؼ�id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		tv_name_function = (TextView)topbar.findViewById(R.id.tv_name_function);
		//�����������ؼ��������
		tv_title.setText("������Ϣ");
		tv_name_function.setVisibility(View.INVISIBLE);
		
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
