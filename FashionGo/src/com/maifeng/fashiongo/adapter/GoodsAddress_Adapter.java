package com.maifeng.fashiongo.adapter;

import java.util.HashMap;
import java.util.List;

import com.maifeng.fashiongo.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GoodsAddress_Adapter extends BaseAdapter {

	private Context context;
	private List<HashMap<String, Object>> list;
	public GoodsAddress_Adapter(Context context,List<HashMap<String, Object>> list){
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view = View.inflate(context, R.layout.personal_cneter_goodsaddress_style,null);
		TextView tv_name = (TextView)view.findViewById(R.id.tv_name);
		TextView tv_phone = (TextView)view.findViewById(R.id.tv_phone);
		TextView tv_address = (TextView)view.findViewById(R.id.tv_address);
		tv_name.setText((String)list.get(position).get("tv_name"));
		tv_phone.setText((String)list.get(position).get("tv_phone"));
		tv_address.setText((String)list.get(position).get("tv_address"));
		return view;
	}

}
