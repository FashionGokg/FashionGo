package com.maifeng.fashiongo.fragment;

import com.maifeng.fashiongo.Basic_Info_Activity;
import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.R.id;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MineFragment extends Fragment {
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
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mine_fragment, container,false);
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
		
        click();
		
		return view;
	}
	private void click(){
		relayout_message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),Basic_Info_Activity.class);
				startActivity(intent);
			}
		});
	}
}
