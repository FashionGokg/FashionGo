package com.maifeng.fashiongo.volleyhandle;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache{

	private LruCache<String, Bitmap> mCache;
	
	public BitmapCache(){
		//��ȡӦ�ó����������ڴ�
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		// ʹ���������ڴ�ֵ��1/8��Ϊ����Ĵ�С��
		int cacheSize = maxMemory / 8; 
		mCache = new LruCache<String, Bitmap>(cacheSize){
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// ��д�˷���������ÿ��ͼƬ�Ĵ�С��Ĭ�Ϸ���ͼƬ������
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
		};
	}
	
	
	@Override
	public Bitmap getBitmap(String url) {
		// TODO Auto-generated method stub
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO Auto-generated method stub
		mCache.put(url, bitmap);
	}

}