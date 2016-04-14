package com.maifeng.fashiongo.volleyhandle;

import java.util.Map;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;

public class VolleyRequest {
	public static StringRequest stringRequest;
	//public Context context;
	

	public static void RequestGet(Context context,String url,String tag,
			VolleyAbstract vif){
		Volleyhandle.getInstance(context).getRequestQueue().cancelAll(tag);
		stringRequest = new StringRequest(Method.GET, url, vif.loadingListener(), vif.errorListener());
		stringRequest.setTag(tag);
		Volleyhandle.getInstance(context).getRequestQueue().add(stringRequest);
		
	}
	
	public static void RequestPost(Context context,String url,String tag,final Map<String, String> map,VolleyAbstract vif){
		Volleyhandle.getInstance(context).getRequestQueue().cancelAll(tag);
		stringRequest = new StringRequest(url, vif.loadingListener(), vif.errorListener()){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				return map;
			}
		};
		stringRequest.setTag(tag);
		Volleyhandle.getInstance(context).getRequestQueue().add(stringRequest);
	}
}
