package com.maifeng.fashiongo;

import android.app.Activity;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class New_Goods_Address_Activity extends Activity {

	//��ʼ�������������ؼ�
	private View topbar;
	private LinearLayout ll_returnbtn;
	private LinearLayout ll_functionbtn;
	private TextView tv_title;
	private TextView tv_name_function;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_center_newadd_goodsaddress);
		topbar =findViewById(R.id.topbar);
		// �����������ؼ�id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		ll_functionbtn = (LinearLayout)topbar.findViewById(R.id.ll_functionbtn);
		tv_name_function = (TextView)topbar.findViewById(R.id.tv_name_function);
		//�����������ؼ��������
		tv_title.setText("������ַ");
		tv_name_function.setText("ȷ��");
		
		Click();
	}
	private void Click() {
		// TODO Auto-generated method stub
		ll_returnbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		ll_functionbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	

}
