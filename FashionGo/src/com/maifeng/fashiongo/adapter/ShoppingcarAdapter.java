package com.maifeng.fashiongo.adapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.base.ShoppingcarType;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ShoppingcarAdapter extends BaseAdapter{
	private Context context;
	private List<ShoppingcarType> listdate;
	public static Map<Integer, Boolean> isCheckedMap;//用于记录复选框的状态
	public static HashSet<Integer> idSet;//用于记录选择的复选框	
	
	public ShoppingcarAdapter(Context context, List<ShoppingcarType> listdate) {
		super();
		this.context = context;
		this.listdate = listdate;
		idSet = new HashSet<Integer>();
		isCheckedMap = new HashMap<Integer, Boolean>();
		for (int i = 0; i < listdate.size(); i++) {
			isCheckedMap.put(i, false);
		}
	}

	@Override
	public int getCount() {
		return listdate.size();
	}

	@Override
	public Object getItem(int position) {
		return listdate.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Viewholder holder=null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_shoppingcar, null);
			holder = new Viewholder();
			holder.checkBox_list = (CheckBox) convertView.findViewById(R.id.checkBox_list);
			holder.iv_shopimg = (ImageView) convertView.findViewById(R.id.iv_shopimg);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_model = (TextView) convertView.findViewById(R.id.tv_model);
			holder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
			holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
			holder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
			convertView.setTag(holder);
		}else {
			holder = (Viewholder) convertView.getTag();
		}
		//获取Map集合里的当前item的复选框状态
		holder.checkBox_list.setChecked(isCheckedMap.get(position));
		holder.iv_shopimg.setImageResource(listdate.get(position).getGoodsImage());
		holder.tv_name.setText(listdate.get(position).getGoodsName());
		holder.tv_model.setText(listdate.get(position).getModel());
		holder.tv_size.setText(listdate.get(position).getSize());
		holder.tv_price.setText("￥"+String.valueOf(listdate.get(position).getPrice()));
		holder.tv_number.setText("x "+listdate.get(position).getNumber());
		
		if (isCheckedMap.get(position)) {
			idSet.add(listdate.get(position).getId());
		}else {
			idSet.remove(listdate.get(position).getId());
		}
		return convertView;
	}
	
	class Viewholder{
		CheckBox checkBox_list;
		ImageView iv_shopimg;
		TextView tv_name;
		TextView tv_model;
		TextView tv_size;
		TextView tv_price;
		TextView tv_number;
		//删除按钮后面再补
	}
	
	

}
