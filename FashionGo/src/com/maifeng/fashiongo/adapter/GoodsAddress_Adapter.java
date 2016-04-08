package com.maifeng.fashiongo.adapter;

import java.util.List;

import com.maifeng.fashiongo.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GoodsAddress_Adapter extends BaseAdapter {

	private Context context;
	private List<String> list;
	public GoodsAddress_Adapter(Context context,List<String> list){
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
		//ImageView tv_img_go = (ImageView)view.findViewById(R.id.tv_img_go);
		tv_name.setText(list.get(position));
		tv_phone.setText(list.get(position));
		tv_address.setText(list.get(position));
		return view;
	}

}
