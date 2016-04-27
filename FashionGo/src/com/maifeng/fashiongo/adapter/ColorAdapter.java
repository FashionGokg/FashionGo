package com.maifeng.fashiongo.adapter;

import java.util.List;

import org.w3c.dom.Text;

import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.base.GoodsSpecificationsData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ColorAdapter extends BaseAdapter {

	private Context context;
	private List<GoodsSpecificationsData> list;

	public ColorAdapter(Context context, List<GoodsSpecificationsData> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_color, null);
			holder = new ViewHolder();
			holder.tv_color = (TextView) convertView
					.findViewById(R.id.tv_color);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	private class ViewHolder {
		TextView tv_color;
	}

}
