package com.maifeng.fashiongo.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maifeng.fashiongo.Basic_Info_Activity;
import com.maifeng.fashiongo.GetMyCollectionActivity;
import com.maifeng.fashiongo.GetMyShareActivity;
import com.maifeng.fashiongo.Goods_Address_Activity;
import com.maifeng.fashiongo.LoginActivity;
import com.maifeng.fashiongo.MyOrder;
import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.constant.LazyFragment;

public class MineFragment extends LazyFragment {
	String Tag = "MineFragment";
	//初始化顶部导航栏控件
	private View topbar;
	private LinearLayout ll_returnbtn;
	private TextView tv_title;
	private TextView tv_name_function;
	//初始化底部控件
	private RelativeLayout relayout_message;
	private RelativeLayout relayout_address;
	private RelativeLayout relayout_myorder;
	private RelativeLayout relayout_collect;
	private RelativeLayout relayout_share;
	
	private String accessToken;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mine_fragment, container,false);
		initView(view);
		isPrepared = true;
		lazyLoad();
		basiClick();
		return view;
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		if (!isPrepared||!isVisible) {
			return;
		}
		SharedPreferences pref = getActivity().getSharedPreferences("myPref", getActivity().MODE_PRIVATE);
		accessToken = pref.getString("accessToken", "");
		if (accessToken.equals("")) {
			Intent intent = new Intent(getActivity(),LoginActivity.class);
			startActivity(intent);
			getActivity().finish();
		}
		
		
	}
	private void initView(View view) {
		// TODO Auto-generated method stub
		topbar = view.findViewById(R.id.topbar);
		// 顶部导航栏控件id
		ll_returnbtn = (LinearLayout)topbar.findViewById(R.id.ll_returnbtn);
		tv_title = (TextView)topbar.findViewById(R.id.tv_title);
		tv_name_function = (TextView)topbar.findViewById(R.id.tv_name_function);
		//顶部导航栏控件相关设置
		ll_returnbtn.setVisibility(View.INVISIBLE);
		tv_title.setText("个人中心");
		tv_name_function.setVisibility(View.INVISIBLE);
		//底部控件id
		relayout_message = (RelativeLayout)view.findViewById(R.id.relayout_message);
		relayout_address = (RelativeLayout)view.findViewById(R.id.relayout_address);
		relayout_myorder = (RelativeLayout)view.findViewById(R.id.relayout_myorder);
		relayout_collect = (RelativeLayout)view.findViewById(R.id.relayout_collect);
		relayout_share = (RelativeLayout)view.findViewById(R.id.relayout_share);
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
				intent.putExtra("Code_address", "Mine");
				startActivity(intent);
			}
		});
		relayout_myorder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),MyOrder.class);
				startActivity(intent);
			}
		});
		relayout_collect.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),GetMyCollectionActivity.class);
				startActivity(intent);
			}
		});
		relayout_share.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),GetMyShareActivity.class);
				startActivity(intent);
			}
		});
	}


}
