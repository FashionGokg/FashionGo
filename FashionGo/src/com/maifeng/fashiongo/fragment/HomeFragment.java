package com.maifeng.fashiongo.fragment;

import com.maifeng.fashiongo.FirstClassifyAcitvity;
import com.maifeng.fashiongo.MainActivity;
import com.maifeng.fashiongo.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeFragment extends Fragment {
	private ImageView img_menu;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_fragment, container, false);
		// 关闭
		getActivity().getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		img_menu = (ImageView) view.findViewById(R.id.img_menu);

		img_menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 跳转到商品分类页面
				Intent intent = new Intent(getActivity()
						.getApplicationContext(), FirstClassifyAcitvity.class);
				startActivity(intent);
			}
		});
		return view;

	}
}
