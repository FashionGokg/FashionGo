package com.maifeng.fashiongo.adapter;

import java.util.List;

import com.maifeng.fashiongo.R;
import com.maifeng.fashiongo.base.ClassifyOneData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ClassifyOneAdapter extends BaseAdapter {
	private List<ClassifyOneData> list;
	private Context context;


	public ClassifyOneAdapter(Context context, List<ClassifyOneData> list) {
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
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.itme_classify_one, null);
			holder = new ViewHolder();
			holder.tv_calssify_name = (TextView) convertView
					.findViewById(R.id.tv_classify_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_calssify_name.setText(list.get(position).getClassifyName());
		return convertView;
	}

	private static class ViewHolder {
		TextView tv_calssify_name;
	}

}
