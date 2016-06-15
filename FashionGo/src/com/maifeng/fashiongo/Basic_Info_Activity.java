package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.VolleyError;
import com.maifeng.fashiongo.banner.CiecleImageView;
import com.maifeng.fashiongo.base.GetPersonalDetailsData;
import com.maifeng.fashiongo.base.GetPersonalDetailsType;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Basic_Info_Activity extends Activity implements OnClickListener {

	//��ʼ�������������ؼ�
		private View topbar;
		private LinearLayout ll_returnbtn;
		private TextView tv_title;
		private RelativeLayout relativemessage;
		private RelativeLayout replace_accuont;
		
		private TextView tv_view_account;
		private TextView tv_view_username;
		private GetPersonalDetailsData data;
		
		private CiecleImageView head_img_go;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_center_basic_info);
		
		Click();
	}
	private void Click(){
		topbar =findViewById(R.id.topbar);
		// �����������ؼ�id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		//�����������ؼ��������
		tv_title.setText("������Ϣ ");
		topbar.findViewById(R.id.tv_name_function).setVisibility(View.INVISIBLE);
		
		tv_view_account=(TextView)findViewById(R.id.tv_view_account);
		tv_view_username =(TextView)findViewById(R.id.tv_view_username);
		head_img_go=(CiecleImageView) findViewById(R.id.head_img_go);
		
		relativemessage=(RelativeLayout)findViewById(R.id.relativemessage);
		replace_accuont=(RelativeLayout)findViewById(R.id.replace_accuont);
		
		ll_returnbtn.setOnClickListener(this);
		relativemessage.setOnClickListener(this);
		replace_accuont.setOnClickListener(this);
		//ȡ���û��˺�
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		String userName = pref.getString("account_number", "");
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
			startActivity(intent);
			break;
		case R.id.replace_accuont:
			Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
			SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
			Editor editor = pref.edit();
			editor.clear();
			editor.putString("account_number", "");
			editor.putString("accessToken", "");
			editor.commit();
			startActivity(intent1);
			MainActivity.mainActivity.finish();
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
		//��¼��ʶ
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		String accessToken = pref.getString("accessToken", "");
			//��װ��������
			Map<String,String> map = new HashMap<String, String>();
			map.put("accessToken", accessToken);
			VolleyRequest.RequestPost(this,Urls.PERSONAL_DETAILS,"PERSONAL_DETAILS", map,
					new VolleyAbstract(this,VolleyAbstract.listener,VolleyAbstract.errorListener) {
						@Override
						public void onMySuccess(String result) {
							data = JsonUtil.parseJsonToBean(result, GetPersonalDetailsType.class).getData();
							//������ʾ���ǳ�
							tv_view_username.setText(data.getName());
							
						}
						@Override
						public void onMyError(VolleyError error) {
						}
					});
		}
}
