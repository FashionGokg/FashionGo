package com.maifeng.fashiongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.android.volley.VolleyError;
import com.maifeng.fashiongo.adapter.ColorAdapter;
import com.maifeng.fashiongo.adapter.MyPagerAdapter;
import com.maifeng.fashiongo.adapter.SizeAdapter;
import com.maifeng.fashiongo.base.AddGoodsToCart;
import com.maifeng.fashiongo.base.Add_Collection;
import com.maifeng.fashiongo.base.GoodDetailData;
import com.maifeng.fashiongo.base.GoodDetailGoodsImage;
import com.maifeng.fashiongo.base.GoodDetailType;
import com.maifeng.fashiongo.base.GoodsSpecificationsData;
import com.maifeng.fashiongo.base.GoodsSpecificationsSize;
import com.maifeng.fashiongo.base.GoodsSpecificationsType;
import com.maifeng.fashiongo.base.ShareGoods;
import com.maifeng.fashiongo.constant.Urls;
import com.maifeng.fashiongo.util.JsonUtil;
import com.maifeng.fashiongo.util.LogUtil;
import com.maifeng.fashiongo.volleyhandle.VolleyAbstract;
import com.maifeng.fashiongo.volleyhandle.VolleyRequest;

/**
 * 产品详情页界面
 * 
 * @author liekkas
 * 
 */
public class GoodDetailActivity extends Activity implements OnClickListener {
	private GoodDetailData detail;
	private List<GoodDetailGoodsImage> urllist;
	private List<GoodsSpecificationsData> specifications;
	private List<GoodsSpecificationsSize> sizes = new ArrayList<GoodsSpecificationsSize>();
	private TextView layout_title;
	private LinearLayout layout_left;
	private String goodsCode;
	private TextView tv_goodname, tv_about, tv_goodcode, tv_isPackage,
			tv_originalPrice, tv_price, tv_goodnum;
	private TextView tv_color, tv_size;
	private Button btn_minus, btn_add;
	private Button btn_join_shoppingcar, btn_pay;
	private EditText et_num;
	private int etnum = 0;
	private ViewPager vPager;
	private ImageView btn_last, btn_next;
	private ImageView layout_right_share, layout_right_collect;
	private int currindex;
	private PopupWindow popupWindow;
	private String accessToken;

	private String text = "";
	private String imageurl = "http://172.16.40.80/shop/public/img/ad/2.jpg";
	private String imageurl2 = "http://pic40.nipic.com/20140411/7041340_205340194000_2.jpg";
	private String title = "FashionGo";
	private String url = "";

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_good_detail);

		intent = this.getIntent();
		goodsCode = intent.getStringExtra("goodsCode");
		// 登录标识
		SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
		accessToken = pref.getString("accessToken", "");

		initView();
		detaildata(accessToken, goodsCode);
	}

	private void detaildata(String accessToken, String goodsCode) {
		// 组装请求数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("accessToken", accessToken);
		map.put("goodsCode", goodsCode);
		System.out.println("goodsCode: " + goodsCode);
		System.out.println("accessToken: " + accessToken);
		VolleyRequest.RequestPost(getApplicationContext(),
				Urls.GET_GOODS_DETAILS, "GET_GOODS_DETAILS", map,
				new VolleyAbstract(this, VolleyAbstract.listener,
						VolleyAbstract.errorListener) {

					@Override
					public void onMySuccess(String result) {

						detail = JsonUtil.parseJsonToBean(result,
								GoodDetailType.class).getData();
						urllist = detail.getGoodsImageList().getGoodsImage();
						System.out.println("------>" + result);
						tv_goodname.setText(detail.getGoodsName());
						tv_about.setText(detail.getGoodsInfo());
						tv_goodcode.setText(detail.getGoodsCode());
						tv_originalPrice.setText(detail.getOriginalPrice());
						tv_price.setText(detail.getPrice());
						if (detail.getIsPackage() == 0) {
							tv_isPackage.setText("否");
						} else if (detail.getIsPackage() == 1) {
							tv_isPackage.setText("是");
						}
						tv_goodnum.setText(detail.getTotalNum() + "");
						etnum = detail.getTotalNum();
						MyPagerAdapter adapter = new MyPagerAdapter(
								getApplicationContext(), urllist);
						vPager.setAdapter(adapter);

						btn_last.setOnClickListener(new OnClickListener() {
							// 点击回到上一张图片
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								if (currindex == 0) {

								} else {
									currindex--;
									setCurrentItem(currindex);
								}
							}
						});

						btn_next.setOnClickListener(new OnClickListener() {
							// 点击到下一张图片
							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								if (currindex == urllist.size() - 1) {

								} else {
									currindex++;
									setCurrentItem(currindex);
								}
							}
						});

						specifications(detail.getGoodsCode());
					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub

						System.out.println("解析失败了");
					}
				});
	}

	private void specifications(String goodsCode) {
		// 组装请求数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("goodsCode", goodsCode);
		VolleyRequest.RequestPost(getApplicationContext(),
				Urls.GET_GOODS_SPECIFICATIONS, "GET_GOODS_SPECIFICATIONS", map,
				new VolleyAbstract(this, VolleyAbstract.listener,
						VolleyAbstract.errorListener) {

					@Override
					public void onMySuccess(String result) {
						specifications = JsonUtil.parseJsonToBean(result,
								GoodsSpecificationsType.class).getData();

					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});

	}

	public void setCurrentItem(int index) {
		vPager.setCurrentItem(index);
	}

	@Override
	public void onClick(View v) {
		int num = 0;
		switch (v.getId()) {
		case R.id.layout_left:
			finish();
			break;
		case R.id.layout_color:
			if (detail == null) {

				return;
			}
			if (popupWindow != null) {
				popupWindow.dismiss();
				popupWindow = null;
			}
			showWindow(v);
			break;
		case R.id.layout_size:
			if (tv_color.getText().equals("请选择")) {
				Toast.makeText(this, "请选择颜色后再操作！", Toast.LENGTH_SHORT).show();
				return;
			}
			if (popupWindow != null) {
				popupWindow.dismiss();
				popupWindow = null;
			}
			showWindow2(v, specifications.get(0).getSizeList()
					.getSpecificationsId());
			break;
		case R.id.btn_minus:
			if (et_num.getText().toString().equals("")) {
				et_num.setText("0");
			}
			num = Integer.valueOf(et_num.getText().toString());
			num--;
			if (num <= 0) {
				num = 1;
			}
			et_num.setText(num + "");
			et_num.setSelection(et_num.getText().length());
			break;
		case R.id.btn_add:
			if (et_num.getText().toString().equals("")) {
				et_num.setText("0");
			}
			num = Integer.valueOf(et_num.getText().toString());
			num++;
			if (num > etnum && etnum != 0) {
				num = etnum;
			}
			et_num.setText(num + "");
			et_num.setSelection(et_num.getText().length());
			break;
		case R.id.btn_join_shoppingcar: // 加入购物车
			System.out.println("点击了加入购物车");
			if (accessToken.equals("")) {
				Toast.makeText(this, "请登录后再操作！", Toast.LENGTH_LONG).show();
				return;
			}
			if (et_num.getText().toString().equals("")
					|| et_num.getText().toString().equals("0")) {
				Toast.makeText(this, "选择商品数量！", Toast.LENGTH_LONG).show();
				return;
			}
			if (tv_size.getText().equals("请选择")) {
				Toast.makeText(this, "请选择商品类型后再操作！", Toast.LENGTH_LONG).show();
				return;
			}
			carInfo(goodsCode, specifications.get(0).getSizeList()
					.getSpecificationsId());

			break;
		case R.id.btn_pay: // 立即支付
			System.out.println("点击了立即支付");
			if (accessToken.equals("")) {
				Toast.makeText(this, "请登录后再操作！", Toast.LENGTH_LONG).show();
				return;
			}
			if (et_num.getText().toString().equals("")
					|| et_num.getText().toString().equals("0")) {
				Toast.makeText(this, "选择商品数量！", Toast.LENGTH_LONG).show();
				return;
			}
			if (tv_size.getText().equals("请选择")) {
				Toast.makeText(this, "请选择商品类型后再操作！", Toast.LENGTH_LONG).show();
				return;
			}
			break;
		case R.id.layout_right_collect: // 收藏
			System.out.println("点击了收藏");
			if (accessToken.equals("")) {
				Toast.makeText(this, "请登录后再操作！", Toast.LENGTH_LONG).show();
				return;
			}
			collect(goodsCode);
			break;
		case R.id.layout_right_share: // 分享
			System.out.println("点击了分享");
			if (accessToken.equals("")) {
				Toast.makeText(this, "请登录后再操作！", Toast.LENGTH_LONG).show();
				return;
			}
			showShare();
			Shared(goodsCode);
			break;
		default:
			break;
		}

	}

	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(title);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		oks.setImageUrl(imageurl);
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// 启动分享GUI
		oks.show(this);

	}

	private void Shared(String goodsCode) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("accessToken", accessToken);
		map.put("goodsCode", goodsCode);
		System.out.println("goodsCode_collect: " + goodsCode);
		System.out.println("accessToken_collect: " + accessToken);
		VolleyRequest.RequestPost(getApplicationContext(), Urls.SHARE_GOODS,
				"SHARE_GOODS", map, new VolleyAbstract(this,
						VolleyAbstract.listener, VolleyAbstract.errorListener) {

					@Override
					public void onMySuccess(String result) {
						LogUtil.e("分享信息", result);
						ShareGoods shareGoods = JsonUtil.parseJsonToBean(
								result, ShareGoods.class);
						switch (shareGoods.getErrorcode()) {
						case 0:
							Toast.makeText(getApplicationContext(),
									shareGoods.getMessage(), Toast.LENGTH_SHORT)
									.show();
							break;

						default:
							Toast.makeText(getApplicationContext(),
									shareGoods.getMessage(), Toast.LENGTH_SHORT)
									.show();
							break;
						}
					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
	}

	private void collect(String goodsCode) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("accessToken", accessToken);
		map.put("goodsCode", goodsCode);
		System.out.println("goodsCode_collect: " + goodsCode);
		System.out.println("accessToken_collect: " + accessToken);
		VolleyRequest.RequestPost(getApplicationContext(), Urls.ADD_COLLECTION,
				"ADD_COLLECTION", map, new VolleyAbstract(this,
						VolleyAbstract.listener, VolleyAbstract.errorListener) {

					@Override
					public void onMySuccess(String result) {
						LogUtil.e("添加收藏信息", result);
						Add_Collection add_Collection = JsonUtil
								.parseJsonToBean(result, Add_Collection.class);
						switch (add_Collection.getErrorcode()) {
						case 0:
							Toast.makeText(getApplicationContext(),
									add_Collection.getMessage(),
									Toast.LENGTH_SHORT).show();
							break;

						default:
							Toast.makeText(getApplicationContext(),
									add_Collection.getMessage(),
									Toast.LENGTH_SHORT).show();
							break;
						}
					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
	}

	private void carInfo(String goodsCode, String specificationsId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("accessToken", accessToken);
		map.put("goodsCode", goodsCode);
		map.put("goodsNum", String.valueOf(et_num.getText()));
		map.put("specificationsId", specificationsId);
		System.out.println("goodsCode_carInfo: " + goodsCode);
		System.out.println("accessToken_carInfo: " + accessToken);
		VolleyRequest.RequestPost(getApplicationContext(),
				Urls.ADD_GOODS_TO_CART, "ADD_GOODS_TO_CART", map,
				new VolleyAbstract(this, VolleyAbstract.listener,
						VolleyAbstract.errorListener) {

					@Override
					public void onMySuccess(String result) {
						LogUtil.e("加入购物车信息", result);
						// TODO Auto-generated method stub
						AddGoodsToCart addGoodsToCart = JsonUtil
								.parseJsonToBean(result, AddGoodsToCart.class);
						switch (addGoodsToCart.getErrorcode()) {
						case 0:
							Toast.makeText(getApplicationContext(),
									addGoodsToCart.getMessage(),
									Toast.LENGTH_SHORT).show();
							break;

						default:
							Toast.makeText(getApplicationContext(),
									addGoodsToCart.getMessage(),
									Toast.LENGTH_SHORT).show();
							break;
						}

					}

					@Override
					public void onMyError(VolleyError error) {
						// TODO Auto-generated method stub

					}
				});
	}

	private void showWindow(View parent) {
		if (popupWindow == null) {
			View view = LayoutInflater.from(this).inflate(
					R.layout.dialog_choice_color, null);
			view.setFocusableInTouchMode(true);
			popupWindow = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
		}
		ListView listView = (ListView) popupWindow.getContentView()
				.findViewById(R.id.lv_color);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				sizes.clear();
				System.out.println("specifications------------>"
						+ specifications.get(position).getModel());
				tv_color.setText(specifications.get(position).getModel());
				sizes.add(specifications.get(position).getSizeList());
			}
		});
		ColorAdapter colorAdapter = new ColorAdapter(this);
		colorAdapter.setData(specifications);
		listView.setAdapter(colorAdapter);
		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);
		// 这个是为了点击“返回Back”也能使其消失，并且并不影响你的背景
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// 显示的位置为:屏幕的宽度的一半——PopupWindow的高度的一半
		popupWindow.showAsDropDown(parent, 0, -5);

	}

	private void showWindow2(View parent, String specificationsId) {
		if (popupWindow == null) {
			View view = LayoutInflater.from(this).inflate(
					R.layout.dialog_choice_color, null);
			view.setFocusableInTouchMode(true);
			popupWindow = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
		}
		ListView listView = (ListView) popupWindow.getContentView()
				.findViewById(R.id.lv_color);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				tv_size.setText(sizes.get(position).getSize());
				tv_goodnum.setText(sizes.get(position).getNum() + "");
				etnum = sizes.get(position).getNum();
				System.out.println("getNum--->" + sizes.get(position).getNum());

			}
		});
		SizeAdapter sizeAdapter = new SizeAdapter(this);

		sizeAdapter.setData(sizes);
		listView.setAdapter(sizeAdapter);
		// 使其聚集
		popupWindow.setFocusable(true);
		// 设置允许在外点击消失
		popupWindow.setOutsideTouchable(true);
		// 这个是为了点击“返回Back”也能使其消失，并且并不影响你的背景
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// 显示的位置为:屏幕的宽度的一半——PopupWindow的高度的一半
		popupWindow.showAsDropDown(parent, 0, -5);

	}

	private void initView() {
		// TODO Auto-generated method stub
		layout_left = (LinearLayout) findViewById(R.id.layout_left);
		layout_title = (TextView) findViewById(R.id.layout_title);
		tv_goodname = (TextView) findViewById(R.id.tv_goodname); // 商品名称
		tv_about = (TextView) findViewById(R.id.tv_about);// 商品简介
		tv_goodcode = (TextView) findViewById(R.id.tv_goodcode);// 商品编号
		tv_isPackage = (TextView) findViewById(R.id.tv_isPackage);// 是否包邮
		tv_originalPrice = (TextView) findViewById(R.id.tv_originalPrice);// 市场价
		tv_price = (TextView) findViewById(R.id.tv_price);// 优惠价
		tv_goodnum = (TextView) findViewById(R.id.tv_goodnum);// 总库存

		tv_color = (TextView) findViewById(R.id.tv_color);
		tv_size = (TextView) findViewById(R.id.tv_size);

		LinearLayout layout_color = (LinearLayout) findViewById(R.id.layout_color);
		LinearLayout layout_size = (LinearLayout) findViewById(R.id.layout_size);

		et_num = (EditText) findViewById(R.id.et_num);
		btn_minus = (Button) findViewById(R.id.btn_minus);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_join_shoppingcar = (Button) findViewById(R.id.btn_join_shoppingcar);// 加入购物车
		btn_pay = (Button) findViewById(R.id.btn_pay);// 立即支付
		layout_right_collect = (ImageView) findViewById(R.id.layout_right_collect); // 收藏
		layout_right_share = (ImageView) findViewById(R.id.layout_right_share); // 分享

		layout_title.setText("产品详情");
		vPager = (ViewPager) findViewById(R.id.vPager);
		btn_last = (ImageView) findViewById(R.id.btn_last); // 上一张图片
		btn_next = (ImageView) findViewById(R.id.btn_next); // 下一张图片

		layout_left.setOnClickListener(this);
		layout_color.setOnClickListener(this);
		layout_size.setOnClickListener(this);
		btn_minus.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		btn_join_shoppingcar.setOnClickListener(this);
		btn_pay.setOnClickListener(this);
		layout_right_collect.setOnClickListener(this);
		layout_right_share.setOnClickListener(this);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ShareSDK.stopSDK(this);
	}

}
