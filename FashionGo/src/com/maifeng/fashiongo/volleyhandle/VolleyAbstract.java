package com.maifeng.fashiongo.volleyhandle;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.maifeng.fashiongo.util.LogUtil;

import android.content.Context;

public abstract class VolleyAbstract {

	public Context context;
	public static Listener<String> listener;
	public static ErrorListener errorListener;
	
	public VolleyAbstract(Context context,Listener<String>listener,
			ErrorListener errorListener){
		
		this.context = context;
//		this.listener=listener;
//		this.errorListener=errorListener;
	}
	public abstract void onMySuccess(String result);
	public abstract void onMyError(VolleyError error);
	
	public Listener<String> loadingListener(){
		 listener=new Listener<String>() {

			@Override
			public void onResponse(String response) {
				
				onMySuccess(response);
			//Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
				LogUtil.i("请求成功", response);
			}
		};
		return listener;
	}
	
	public ErrorListener errorListener(){
		errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				onMyError(error);
				//Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
				LogUtil.i("请求失败", error.toString());
			}
		};
		return errorListener;
		
	}
	
}
