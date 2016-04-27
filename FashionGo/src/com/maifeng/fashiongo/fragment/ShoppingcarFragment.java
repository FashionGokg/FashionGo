package com.maifeng.fashiongo.fragment;

import java.util.ArrayList;
import java.util.List;

import com.maifeng.fashiongo.LoginActivity;
import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.adapter.ShoppingcarAdapter;
import com.maifeng.fashiongo.base.ShoppingcarType;


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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ShoppingcarFragment extends Fragment {
	private View topbar;
	private LinearLayout ll_returnbtn;
	private LinearLayout ll_functionbtn;
	private TextView tv_title,tv_name_function;
	private ListView listView;
	private List<ShoppingcarType> listdate;
	private ShoppingcarType sType;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		SharedPreferences pref = getSharedPreferences("myPref",getActivity().MODE_PRIVATE); 
//		if (pref.getString("accessToken", null).isEmpty()) {
//		
//			Intent intent = Intent(getActivity(),LoginActivity.class);
//			startActivity(intent);
//			
//		}
		
		View view = inflater.inflate(R.layout.shoppingcar_fragment, container,false);
		
		//顶部导航栏
        topbar = view.findViewById(R.id.topbar);
		topbar.findViewById(R.id.ll_returnbtn).setVisibility(View.INVISIBLE);
 //       ll_returnbtn = (LinearLayout) topbar.findViewById(R.id.ll_returnbtn);
        topbar.findViewById(R.id.ll_returnbtn).setVisibility(View.INVISIBLE);
        ll_functionbtn= (LinearLayout) topbar.findViewById(R.id.ll_functionbtn);
        tv_title = (TextView) topbar.findViewById(R.id.tv_title);
        tv_name_function = (TextView) topbar.findViewById(R.id.tv_name_function);
        tv_title.setText("购物车");
        tv_name_function.setText("编辑");

//        ll_returnbtn.setVisibility(View.INVISIBLE);
        ll_functionbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "点击了编辑按钮", Toast.LENGTH_SHORT).show();
			}
		});
        
        listView = (ListView) view.findViewById(R.id.list_shoppingcar);
        
        //数据源
        listdate = new ArrayList<ShoppingcarType>();
        for (int i = 0; i < 10; i++) {
        	 int id =i;
        	 String	goodsName ="测试商品"+i;
        	 int	goodsImage=R.drawable.img_png3;
        	 Double	price=200.00;
        	 int	number=2;
        	 String	model="蓝色";
        	 String	size="M号";
        	 sType=new ShoppingcarType(id, goodsName, goodsImage, price, number, model, size);
        	 listdate.add(sType);
        }
        
        ShoppingcarAdapter myAdapter =new ShoppingcarAdapter(getActivity(),listdate);
        listView.setAdapter(myAdapter);
		
		
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
}
