package com.maifeng.fashiongo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GoodDetailActivity extends Activity {
	private TextView layout_title;
	private LinearLayout layout_left;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//���ر�����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_good_detail);
		
		layout_left=(LinearLayout) findViewById(R.id.layout_left);
		layout_title=(TextView) findViewById(R.id.layout_title);
		layout_title.setText("��Ʒ����");
		layout_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
