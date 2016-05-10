package com.maifeng.fashiongo;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Text;

import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.maifeng.fashiongo.base.Goods_AddNew_AddressType;
import com.maifeng.fashiongo.base.Goods_AreaData;
import com.maifeng.fashiongo.base.Goods_CityData;
import com.maifeng.fashiongo.base.Goods_ProvinceData;
import com.maifeng.fashiongo.constant.UrlAddress;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;
import com.maifeng.fashiongo.volleyhandle.Volleyhandle;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Edit_BasiinformationAcitivity extends Activity implements OnClickListener{

	//��ʼ�������������ؼ�
	private View topbar;
	private LinearLayout ll_returnbtn;
	private LinearLayout ll_functionbtn;
	private TextView tv_title;
	private TextView tv_name_function;
	
	private RelativeLayout relative_imageview;
	private RelativeLayout replacesex_relayout;
	private RelativeLayout province_relayout;
	private RelativeLayout city_relayout;
	private RelativeLayout area_relayout;
	
	private ImageView image_head;
	private EditText editname=null;
	private EditText editage=null;
	private TextView textsex=null;
	private EditText editqq=null;
	private EditText editemaill=null;
	private TextView textprovince=null;
	private TextView textcity=null;
	private TextView textarea=null;
	private EditText editaddress=null;
	
	private String pCodeString;//���ʡ��id
	private String cCodeString;//��ų���id
	private String aCodeString;//��ŵ���id
	
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_basiinformation);
		intent = this.getIntent();
		idGet();
	}
	private void idGet(){
		topbar =findViewById(R.id.topbar);
		// �����������ؼ�id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		ll_functionbtn = (LinearLayout)topbar.findViewById(R.id.ll_functionbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		tv_name_function = (TextView)topbar.findViewById(R.id.tv_name_function);
		//�����������ؼ��������
		tv_title.setText("�������� ");
		tv_name_function.setText("����");
		
		relative_imageview=(RelativeLayout)findViewById(R.id.relative_imageview);
		replacesex_relayout=(RelativeLayout)findViewById(R.id.replacesex_relayout);
		province_relayout=(RelativeLayout)findViewById(R.id.province_relayout);
		city_relayout=(RelativeLayout)findViewById(R.id.city_relayout);
		area_relayout=(RelativeLayout)findViewById(R.id.area_relayout);
		
		ll_returnbtn.setOnClickListener(this);
		ll_functionbtn.setOnClickListener(this);
		relative_imageview.setOnClickListener(this);
		replacesex_relayout.setOnClickListener(this);
		province_relayout.setOnClickListener(this);
		city_relayout.setOnClickListener(this);
		area_relayout.setOnClickListener(this);
		
		image_head=(ImageView)findViewById(R.id.image_head);
		editname=(EditText)findViewById(R.id.editname);
		editage=(EditText)findViewById(R.id.editage);
		textsex=(TextView)findViewById(R.id.textsex);
		editqq=(EditText)findViewById(R.id.editqq);
		editemaill=(EditText)findViewById(R.id.editemaill);
		textprovince=(TextView)findViewById(R.id.textprovince);
		textcity=(TextView)findViewById(R.id.textcity);
		textarea=(TextView)findViewById(R.id.textarea);
		editaddress=(EditText)findViewById(R.id.editaddress);
		//������ʾ���ݵ���Ϣ
		//image_head.setImageResource(intent.getStringExtra(""));
//		editname.setText(intent.getStringExtra("nameString"));
//		editage.setText(intent.getStringExtra("ageString"));
//		if (intent.getStringExtra("sexString").equals(0)) {
//			textsex.setText("��");
//		}else {
//			textsex.setText("Ů");
//		}
//		//textsex.setText(intent.getStringExtra("sexString"));
//		editqq.setText(intent.getStringExtra("qqString"));
//		editemaill.setText(intent.getStringExtra("emailString"));
//		textprovince.setText(intent.getStringExtra("pnameString"));
//		textcity.setText(intent.getStringExtra("cnameString"));
//		textarea.setText(intent.getStringExtra("areaString"));
//		editaddress.setText(intent.getStringExtra("addressString"));

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
		queue.cancelAll("CHANGE_PEMAL_INFO");
	}
	private void vollePost(){
		//��¼��ʶ
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		
		String accessToken = pref.getString("accessToken","");
		//String image =image_head.getImageMatrix().toString();
		String name = editname.getText().toString().trim();//trim()�ǽ�ת������ַ�������ȥ��ǰ��ո�
		String age = editage.getText().toString().trim();
		String sex ;
		if (textsex.getText().toString().equals("��")) {
			 sex = "0";
		}else {
			 sex = "1";
		}
		String qq = editqq.getText().toString().trim();
		String email = editemaill.getText().toString().trim();
		
		String pCode =textprovince.getText().toString().trim();
		String cCode = textcity.getText().toString().trim();
		String aCode = textarea.getText().toString().trim();
		String address = editaddress.getText().toString().trim();
		//ͷ���޲�����
		if (TextUtils.isEmpty(name)) {
			Toast.makeText(getApplicationContext(), "��������Ϊ��",Toast.LENGTH_SHORT).show();
			return;
		}if (TextUtils.isEmpty(age)) {
			Toast.makeText(getApplicationContext(), "���䲻��Ϊ��",Toast.LENGTH_SHORT).show();
			return;
		}if (Integer.valueOf(age)<0||Integer.valueOf(age)>200) {
			Toast.makeText(getApplicationContext(), "���䲻��ȷ",Toast.LENGTH_SHORT).show();
			return;
		}if (sex.equals("")) {
			Toast.makeText(getApplicationContext(), "�Ա���Ϊ��",Toast.LENGTH_SHORT).show();
			return;
		}if (TextUtils.isEmpty(qq)) {
			Toast.makeText(getApplicationContext(), "QQ����Ϊ��",Toast.LENGTH_SHORT).show();
			return;
		}if (TextUtils.isEmpty(email)) {
			Toast.makeText(getApplicationContext(), "���䲻��Ϊ��",Toast.LENGTH_SHORT).show();
			return;
		}if (pCode.equals("��ѡ��ʡ��")) {
			Toast.makeText(getApplicationContext(), "ʡ�ݲ���Ϊ��",Toast.LENGTH_SHORT).show();
			return;
		}if (cCode.equals("��ѡ�����")) {
			Toast.makeText(getApplicationContext(), "���в���Ϊ��",Toast.LENGTH_SHORT).show();
			return;
		}if (aCode.equals("��ѡ�����")) {
			Toast.makeText(getApplicationContext(), "��������Ϊ��",Toast.LENGTH_SHORT).show();
			return;
		}else if (TextUtils.isEmpty(address)) {
			Toast.makeText(getApplicationContext(), "��ϸ��ַ����Ϊ��",Toast.LENGTH_SHORT).show();
			return;
		}else {
	
			//��װ��������
			Map<String,String> map = new HashMap<String, String>();
			map.put("accessToken", accessToken);
			map.put("image","");//��ͼƬ
			map.put("name",name);
			map.put("age",age);
			map.put("sex",sex);
			map.put("qq",qq);
			map.put("email",email);
			map.put("pCode", pCodeString);
			map.put("cCode", cCodeString);
			map.put("aCode", aCodeString);
			map.put("address",address);
			VolleyRequest.RequestPost(this,Urls.CHANGE_PEMAL_INFO,"CHANGE_PEMAL_INFO", map,
					new VolleyAbstract(this,VolleyAbstract.listener,VolleyAbstract.errorListener) {
						
						@Override
						public void onMySuccess(String result) {
							JsonUtil.parseJsonToBean(result, Goods_AddNew_AddressType.class);
						
						}
						@Override
						public void onMyError(VolleyError error) {
							// TODO Auto-generated method stub
							
						}
					});
			finish();
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ll_returnbtn:
			finish();
			break;
		//����
		case R.id.ll_functionbtn:
			vollePost();
			Intent intent=new Intent(getApplicationContext(),Basic_Info_Activity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.relative_imageview:
			Toast.makeText(getApplicationContext(),"����ͷ��ͼƬ��Դ", Toast.LENGTH_SHORT).show();
			break;
		case R.id.replacesex_relayout:
			sex();
			break;
		case R.id.province_relayout:
			Intent intent1 = new Intent(getApplicationContext(),Provice_Activity.class);
			startActivityForResult(intent1, 1);
			break;
		case R.id.city_relayout:
			if (pCodeString==null) {
				Toast.makeText(getApplicationContext(),"��ѡ��ʡ�ݺ��ٲ���",Toast.LENGTH_SHORT).show();
				return;
			}else {
				Intent intent2 = new Intent(getApplicationContext(),City_Activity.class);
				intent2.putExtra("pCode", pCodeString);
				startActivityForResult(intent2, 2);
			}
			
			break;
		case R.id.area_relayout:
			if (cCodeString==null) {
				Toast.makeText(getApplicationContext(),"��ѡ����к��ٲ���",Toast.LENGTH_SHORT).show();
				return;
			}else {
				Intent intent3 = new Intent(getApplicationContext(),Area_Activity.class);
				intent3.putExtra("cCode", cCodeString);
				startActivityForResult(intent3, 3);
			}
			
			break;
		default:
			break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode==1) {
			String pNameString = data.getStringExtra("pName");
			textprovince.setText(pNameString);//��ʾʡ��
			pCodeString=data.getStringExtra("pCode");//�õ�ʡ��id
		}
		if (resultCode==2) {
			String cNameString = data.getStringExtra("cName");
			textcity.setText(cNameString);//��ʾ����
			cCodeString=data.getStringExtra("cCode");//�õ�����id
		}
		if (resultCode==3) {
			String cNameString = data.getStringExtra("aArea");
			textarea.setText(cNameString);//��ʾ����
			aCodeString = data.getStringExtra("aCode");
		}
	}
	//����ѡ���Ա�
	private void sex(){
        //    ͨ��AlertDialog.Builder�������ʵ�������ǵ�һ��AlertDialog�Ķ���
        AlertDialog.Builder builder = new AlertDialog.Builder(Edit_BasiinformationAcitivity.this);
        //    ����Title��ͼ��
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("ѡ��һ���Ա�");
        //    ָ�������б����ʾ����
        final String[] cities = {"��", "Ů"};
        //    ����һ���������б�ѡ����
        builder.setItems(cities, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(Edit_BasiinformationAcitivity.this, "ѡ����Ա�Ϊ��" + cities[which], Toast.LENGTH_SHORT).show();
				textsex.setText(cities[which]);
                
            }
        });
        builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                
            }
        });
        builder.show();
	}
}
