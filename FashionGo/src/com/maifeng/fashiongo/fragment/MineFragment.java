package com.maifeng.fashiongo.fragment;

import com.maifeng.fashiongo.Basic_Info_Activity;
import com.maifeng.fashiongo.Goods_Address_Activity;
import com.maifeng.fashiongo.LoginActivity;
import com.maifeng.fashiongo.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MineFragment extends Fragment {
	String Tag = "MineFragment";
	//��ʼ�������������ؼ�
	private View topbar;
	private LinearLayout ll_returnbtn;
	private TextView tv_title;
	private TextView tv_name_function;
	//��ʼ���ײ��ؼ�
	private RelativeLayout relayout_message;
	private RelativeLayout relayout_address;
	private RelativeLayout relayout_myorder;
	private RelativeLayout relayout_collect;
	private RelativeLayout relayout_share;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		View view = inflater.inflate(R.layout.mine_fragment, container,false);
		
//		SharedPreferences pref = getSharedPreferences("myPref",getActivity().MODE_PRIVATE); 
//		if (pref.getString("accessToken", null).equals(null)) {
//		
//			Intent intent = Intent(getActivity(),LoginActivity.class);
//			startActivity(intent);
//			
//		}
		topbar = view.findViewById(R.id.topbar);
		// �����������ؼ�id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		tv_name_function = (TextView)topbar.findViewById(R.id.tv_name_function);
		//�����������ؼ��������
		ll_returnbtn.setVisibility(View.INVISIBLE);
		tv_title.setText("��������");
		tv_name_function.setVisibility(View.INVISIBLE);
		//�ײ��ؼ�id
		relayout_message = (RelativeLayout)view.findViewById(R.id.relayout_message);
		relayout_address = (RelativeLayout)view.findViewById(R.id.relayout_address);
		relayout_myorder = (RelativeLayout)view.findViewById(R.id.relayout_myorder);
		relayout_collect = (RelativeLayout)view.findViewById(R.id.relayout_collect);
		relayout_share = (RelativeLayout)view.findViewById(R.id.relayout_share);
		
		basiClick();
		
		return view;
	}
	private Intent Intent(FragmentActivity activity, Class<LoginActivity> class1) {
		// TODO Auto-generated method stub
		return null;
	}
	private SharedPreferences getSharedPreferences(String string,
			int mode_PRIVATE) {
		// TODO Auto-generated method stub
		return null;
	}
	private void basiClick(){
		relayout_message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),Basic_Info_Activity.class);
				startActivity(intent);
			}
		});
		relayout_address.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),Goods_Address_Activity.class);
				startActivity(intent);
			}
		});
		relayout_myorder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(getActivity(),GoodsAddress_Activity.class);
//				startActivity(intent);
			}
		});
		relayout_collect.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(getActivity(),GoodsAddress_Activity.class);
//				startActivity(intent);
			}
		});
		relayout_share.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(getActivity(),GoodsAddress_Activity.class);
//				startActivity(intent);
			}
		});
	}
}
