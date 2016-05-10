package com.maifeng.fashiongo.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.adapter.ShoppingcarAdapter;
import com.maifeng.fashiongo.adapter.ShoppingcarEditAdapter;
import com.maifeng.fashiongo.base.EditGoodsForCartType;
import com.maifeng.fashiongo.base.ShoppingcarData;
import com.maifeng.fashiongo.base.ShoppingcarType;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.util.LogUtil;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ShoppingcarFragment extends Fragment implements OnClickListener{
	private View topbar;
	private LinearLayout ll_returnbtn;
	private LinearLayout ll_functionbtn;
	private TextView tv_title,tv_name_function;
	private ListView listView;
	public 	static CheckBox selectAll;
	public	static TextView tv_commodityQuantity;
	public 	static TextView tv_Total;
	private List<ShoppingcarData> listdate;
	private ShoppingcarAdapter myAdapter;
	private String accessToken;
	//获取购物车
	private String urlString = "http://172.16.40.47/shop/index.php/home/GoodsToCar/getCartInfo";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.shoppingcar_fragment, container,false);
		
		initView(view);
		
		SharedPreferences pref = getActivity().getSharedPreferences("myPref", getActivity().MODE_PRIVATE);
		accessToken = pref.getString("accessToken", "");
		volleyPost();
		
		
		selectAll.setOnClickListener(this);
		
        ll_functionbtn.setOnClickListener(this);
        
        
		return view;
	}
	
	private void initView(View view) {
		//顶部导航栏
        topbar = view.findViewById(R.id.topbar);
		topbar.findViewById(R.id.ll_returnbtn).setVisibility(View.INVISIBLE);
        topbar.findViewById(R.id.ll_returnbtn).setVisibility(View.INVISIBLE);
        ll_functionbtn= (LinearLayout) topbar.findViewById(R.id.ll_functionbtn);
        tv_title = (TextView) topbar.findViewById(R.id.tv_title);
        tv_name_function = (TextView) topbar.findViewById(R.id.tv_name_function);
        tv_title.setText("购物车");
        tv_name_function.setText("编辑");
        
        listView = (ListView) view.findViewById(R.id.list_shoppingcar);
		selectAll = (CheckBox) view.findViewById(R.id.selectAll);
		tv_commodityQuantity = (TextView) view.findViewById(R.id.tv_commodity_quantity);
		tv_Total = (TextView) view.findViewById(R.id.tv_total);
        
		
	}

	private void volleyPost() {

		
		Map<String, String> map = new HashMap<String, String>();
		map.put("accessToken", accessToken);
		map.put("page", "1");
		VolleyRequest.RequestPost(getActivity(), urlString, "MyCartInfo", map,
				new VolleyAbstract(getActivity(),VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
					listdate = JsonUtil.parseJsonToBean(result, ShoppingcarType.class).getData();
			       
					myAdapter =new ShoppingcarAdapter(getActivity(),listdate);
			        listView.setAdapter(myAdapter);
	
					}
					
					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						
					}
				});
		
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.selectAll:
			if (selectAll.isChecked()) {
				int commodityQuantity =0;
				int number=0;
				float total =0.00f;
				for (int i = 0; i < myAdapter.getCount(); i++) {
					myAdapter.isCheckedMap.put(i, true);
					myAdapter.idSet.add(listdate.get(i).getId());
					commodityQuantity = commodityQuantity+Integer.parseInt(listdate.get(i).getNumber());
					total=total+(Integer.parseInt(listdate.get(i).getPrice())*commodityQuantity);
					number=number+commodityQuantity;
					LogUtil.i("全选", number+"+"+total);
					commodityQuantity =0;
				}
				ShoppingcarAdapter.number =number;
				ShoppingcarAdapter.Total=total;
				tv_commodityQuantity.setText("共"+String.valueOf(number)+"件商品");
				tv_Total.setText("￥"+String.valueOf(total)+"元");
				
			}else {
				for (int i = 0; i < myAdapter.getCount(); i++) {
					myAdapter.isCheckedMap.put(i, false);
				}
				myAdapter.idSet.clear();
				ShoppingcarAdapter.number =0;
				ShoppingcarAdapter.Total=0;
				tv_commodityQuantity.setText("共0件商品");
				tv_Total.setText("￥0.00元");
				
			}
			myAdapter.notifyDataSetChanged();
			
			break;

		case R.id.ll_functionbtn:
			ShoppingcarEditAdapter shoppingcarEditAdapter = new ShoppingcarEditAdapter(getActivity(), listdate);
			if (tv_name_function.getText().equals("编辑")) {
				tv_name_function.setText("完成");
				selectAll.setVisibility(View.GONE);
				listView.setAdapter(shoppingcarEditAdapter);
				
			}else {
				tv_name_function.setText("编辑");
				if (shoppingcarEditAdapter.editlist != null) {
					editCartPost(shoppingcarEditAdapter.editlist);	
				}else {
					volleyPost();
					selectAll.setVisibility(View.VISIBLE);
				}
	

				
			}


			break;
		}
	}

	
	private void editCartPost(List<ShoppingcarData> editlist) {
		List<EditGoodsForCartType> list = new ArrayList<EditGoodsForCartType>();
		for (int i = 0; i < editlist.size(); i++) {
			EditGoodsForCartType eType = new EditGoodsForCartType();
			eType.setId(editlist.get(i).getId());
			eType.setGoodsCode(editlist.get(i).getGoodsCode());
			eType.setSpecificationsId(editlist.get(i).getSpecificationsId());
			eType.setNumber(editlist.get(i).getNumber());
			list.add(eType);
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getNumber());
		}
		
		Map<String, String> map = new HashMap<String, String>();
		Gson gson = new Gson();
		System.out.println("--------->"+gson.toJson(list));
		System.out.println("accessToken--------->"+accessToken);
		map.put("accessToken", accessToken);
		
		map.put("editParam", gson.toJson(list));
		VolleyRequest.RequestPost(getActivity(), "http://172.16.40.47/shop/index.php/home/GoodsToCar/editGoodsForCart", "editGoodsForCart", map,
				new VolleyAbstract(getActivity(),VolleyAbstract.listener,VolleyAbstract.errorListener) {
					
					@Override
					public void onMySuccess(String result) {
						volleyPost();
						selectAll.setVisibility(View.VISIBLE);
					}
					
					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub
						
					}
				});
	}








}
