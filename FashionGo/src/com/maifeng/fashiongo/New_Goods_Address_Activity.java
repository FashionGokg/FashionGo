package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.maifeng.fashiongo.base.Goods_AddNew_AddressType;
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;
import com.maifeng.fashiongo.volleyhandle.Volleyhandle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class New_Goods_Address_Activity extends Activity {

	//��ʼ�������������ؼ�
	private View topbar;
	private LinearLayout ll_returnbtn;
	private LinearLayout ll_functionbtn;
	private TextView tv_title;
	private TextView tv_name_function;
	
	private RelativeLayout relayout_province;
	private RelativeLayout relayout_city;
	private RelativeLayout relayout_area;
	private EditText ed_name;
	private EditText ed_phones;
	private TextView tv_province;
	private TextView tv_city;
	private TextView tv_area;
	private EditText ed_address;
	private String pCodeString;//���ʡ��id
	private String cCodeString;//��ų���id
	
	private List<Goods_AddNew_AddressType> list;
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
		
		viewId();
		basiClick();
	}
	
	private void viewId() {
		// TODO Auto-generated method stub
		relayout_province=(RelativeLayout)findViewById(R.id.relayout_province);
		relayout_city=(RelativeLayout)findViewById(R.id.relayout_city);
		relayout_area=(RelativeLayout)findViewById(R.id.relayout_area);
		ed_name=(EditText)findViewById(R.id.ed_name);
		ed_phones=(EditText)findViewById(R.id.ed_phones);
		tv_province=(TextView)findViewById(R.id.tv_province);
		tv_city=(TextView)findViewById(R.id.tv_city);
		tv_area=(TextView)findViewById(R.id.tv_area);
		ed_address=(EditText)findViewById(R.id.ed_addrss);
		
	}

	private void basiClick() {
		// ����
		ll_returnbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		//ȷ��
		ll_functionbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				volleyPost();
				finish();
			}
		});
		relayout_province.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent(getApplicationContext(),Provice_Activity.class);
				startActivityForResult(intent1, 1);
			}
		});
		relayout_city.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(getApplicationContext(),City_Activity.class);
				intent2.putExtra("pCode",pCodeString);
				startActivityForResult(intent2, 2);
			}
		});
		relayout_area.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent3 = new Intent(getApplicationContext(),Area_Activity.class);
				intent3.putExtra("cCode",cCodeString);
				startActivityForResult(intent3, 3);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode==1) {
			String pNameString = data.getStringExtra("pName");
			tv_province.setText(pNameString);//��ʾʡ��
			pCodeString=data.getStringExtra("pCode");//�õ�ʡ��id
		}
		if (resultCode==2) {
			String cNameString = data.getStringExtra("cName");
			tv_city.setText(cNameString);//��ʾ����
			cCodeString=data.getStringExtra("cCode");//�õ�����id
		}
		if (resultCode==3) {
			String cNameString = data.getStringExtra("aArea");
			tv_area.setText(cNameString);//��ʾ����
		}
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
		//ʵ�����������
				RequestQueue queue = Volleyhandle.getInstance(getApplicationContext()).getRequestQueue();
				//�����ʱȡ�����󣬼����ڴ�����
				queue.cancelAll("ADD_RECEIVE_ADDRESS");
	}
	private void volleyPost(){
		//��װ��������
		Map<String,String> map = new HashMap<String, String>();
		//��¼��ʶ
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		
		String accessToken = pref.getString("accessToken", "");
		System.out.println(accessToken);
		String name = ed_name.getText().toString().trim();//trim()�ǽ�ת������ַ�������ȥ��ǰ��ո�
		String phone = ed_phones.getText().toString().trim();
		String tvprovince = tv_province.getText().toString().trim();
		String tvcity = tv_city.getText().toString().trim();
		String tvarea = tv_area.getText().toString().trim();
		String edaddress = ed_address.getText().toString().trim();
		map.put("accessToken", accessToken);
		map.put("name",name);
		map.put("phone",phone);
		map.put("province",tvprovince);
		map.put("city",tvcity);
		map.put("area",tvarea);
		map.put("address",edaddress);
		VolleyRequest.RequestPost(this,UrlAddress.ADD_RECEIVE_ADDRESS,"ADD_RECEIVE_ADDRESS", map,
				new VolleyAbstract(this,VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						Goods_AddNew_AddressType aType = gson.fromJson(result,Goods_AddNew_AddressType.class);
						//list.add(aType);
						System.out.println("------>"+result);
						if(aType.getErrorcode().equals("0")){
							Toast.makeText(New_Goods_Address_Activity.this, "��ӵĵ�ַΪ"+result, Toast.LENGTH_SHORT).show();
		
						}
						else if(aType.getErrorcode().equals("1")){
							Toast.makeText(New_Goods_Address_Activity.this,aType.getMessage(), Toast.LENGTH_SHORT).show();
						}
					}
					
					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						System.err.println(error);
					}
				});
		
	}
}
