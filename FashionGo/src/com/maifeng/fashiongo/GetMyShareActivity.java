package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.maifeng.fashiongo.adapter.GetMyShareAdapter;
import com.maifeng.fashiongo.base.GetMyShareData;
import com.maifeng.fashiongo.base.GetMyShareType;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

public class GetMyShareActivity extends Activity {
	//��ʼ�������������ؼ�
	private View topbar;
	private LinearLayout ll_returnbtn;
	private TextView tv_title;
	private TextView tv_name_function;
	
	private List<GetMyShareData> list;
	private GetMyShareAdapter adapter;
	private ListView list_myshare;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.getmyshare);
		list_myshare = (ListView)findViewById(R.id.list_myshare);
		
		topbar();
		sharePost();
	}
	private void topbar(){
		topbar=this.findViewById(R.id.topbar);
		// �����������ؼ�id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		tv_name_function = (TextView)topbar.findViewById(R.id.tv_name_function);
		//�����������ؼ��������
		tv_title.setText("�ҵķ���");
		tv_name_function.setVisibility(View.INVISIBLE);
		ll_returnbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	private void sharePost(){
		//��װ��������
		Map<String,String> map = new HashMap<String, String>();
		//��¼��ʶ
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		final String accessToken = pref.getString("accessToken", "");
		map.put("accessToken", accessToken);
		map.put("page","1");
		VolleyRequest.RequestPost(this,Urls.GET_MY_SHARE,"GET_MY_SHARE", map,
				new VolleyAbstract(this, VolleyAbstract.listener,VolleyAbstract.errorListener) {
					@Override
					public void onMySuccess(String result) {
						// TODO Auto-generated method stub
						list = JsonUtil.parseJsonToBean(result, GetMyShareType.class).getData();
						adapter = new GetMyShareAdapter(getApplicationContext(), list,accessToken);
						list_myshare.setAdapter(adapter);
					}
					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
					}
				});
	}
}
