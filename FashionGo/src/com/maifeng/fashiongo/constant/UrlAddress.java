package com.maifeng.fashiongo.constant;

public class UrlAddress {
	/**
	 * Root
	 */
	private static String Root ="http://112.124.118.133:9065/ssgApp/";
	
	/**
	 * 个人注册
	 */
	public static String REGISTER="http://172.16.40.80/shop/index.php/home/index/register";
	
	/**
	 * 获取验证码
	 * */
	public static String GetVerify="http://172.16.40.80/shop/index.php/home/PhoneCaptcha/getPhoneCaptcha";
	
	/**
	 * 登录
	 */
	public static String LOGIN="http://172.16.40.80/shop/index.php/home/index/login";
	
	/**
	 * 首页.获取banner
	 */
	public static String GET_BANNER_LIST=Root+"getBannerList";
	
	/**
	 * 首页.获取banner下的3个商品广告入口的信息
	 */
	public static String GET_THREE_GOODS_AD_INFO=Root+"getThreeGoodsADInfo";
	
	/**
	 * 首页.获取首页底部精品推荐
	 */
	public static String GET_RECOMMENDATION=Root+"getRecommendation";
	
	/**
	 * 获取一级分类
	 */
	public static String GET_CLASSIFY_ONE=Root+"getClassifyone";
	
	/**
	 * 获取二级分类
	 */
	public static String GET_CLASSIFY_TWO=Root+"getClassifyTwo";
	
	/**
	 * 获取三级分类(品牌下)
	 */
	public static String GET_CLASSIFY_THREE=Root+"getClassifyThree";
	
	/**
	 * 获取商品列表（包括3个商品广告入口、首页商品搜索）
	 */
	public static String GET_GOODS_LIST=Root+"getGoodsList";
	
	/**
	 * 获取商品详情
	 */
	public static String GET_GOODS_DETAILS=Root+"getGoodsDetails";
	
	/**
	 * 获取商品的规格
	 */
	public static String GET_GOODS_SPECIFICATIONS=Root+"getGoodsSpecifications";
	
	/**
	 * 获取我的购物车
	 */
	public static String GET_MY_CARTINFO=Root+"getMyCartInfo";
	
	/**
	 * 加入购物车
	 */
	public static String ADD_GOODS_TO_CART=Root+"addGoodsToCart";
	
	/**
	 * 删除购物车的商品
	 */
	public static String DELETE_GOODS_FOR_CART=Root+"deleteGoodsForCart";
	
	/**
	 * 编辑购物车商品信息
	 */
	public static String EDIT_GOODS_FOR_CART=Root+"editGoodsForCart";
	
	/**
	 * 获取我的分享
	 */
	public static String GET_MY_SHARE=Root+"getMyShare";
	
	/**
	 * 删除我的分享
	 */
	public static String DELETE_MY_SHARE=Root+"deleteMyShare";
	
	/**
	 * 分享
	 */
	public static String SHARE_GOODS=Root+"shareGoods";
	
	/**
	 * 添加收藏
	 */
	public static String ADD_COLLECTION=Root+"addCollection";
	
	/**
	 * 获取我的收藏列表
	 */
	public static String GET_MY_COLLECTION=Root+"getMyCollection";
	
	/**
	 * 删除收藏
	 */
	public static String DELETE_COLLECTION=Root+"deleteCollection";
	
	/**
	 * 添加收货地址
	 */
	public static String ADD_RECEIVE_ADDRESS=Root+"addReceiveAddress";
	
	/**
	 * 删除收货地址
	 */
	public static String DELETE_ADDRESS=Root+"deleteAddress";
	
	/**
	 * 获取收货地址列表
	 */
	public static String GET_RECEIVE_ADDRESS=Root+"getReceiveAddress";
	
	/**
	 * 获取地址详情
	 */
	public static String GET_ADDRESS_DETAIL=Root+"getAddressDetail";
	
	/**
	 * 编辑收货地址
	 */
	public static String EDIT_ADDRESS=Root+"editAddress";
	
	/**
	 * 获取个人详情
	 */
	public static String PERSONAL_DETAILS=Root+"personalDetails";
	
	/**
	 * 修改个人详情
	 */
	public static String CHANGE_PEMAL_INFO=Root+"changePernalInfo";
	
	/**
	 * 获取我的支付宝
	 */
	public static String GET_MY_ALIPAY_INFO=Root+"getMyAlipayInfo";
	
	/**
	 * 获取我的银行卡
	 */
	public static String GET_MY_BANK_CARD_INFO=Root+"getMyBankCardInfo";
	
	/**
	 * 获取我的订单
	 */
	public static String GET_MY_ORDER=Root+"getMyOrder";
	
	/**
	 * 确认收货
	 */
	public static String CONFIRM_RECEIVING=Root+"confirmReceiving";
	
	/**
	 * 删除订单
	 */
	public static String DELETE_ORDER=Root+"deleteOrder";
	
	/**
	 * 查看订单详情
	 */
	public static String GET_ORDER_DETAIL=Root+"getOrderDetail";
	
	/**
	 * 确认订单
	 */
	public static String CONFIRM_ORDER=Root+"confirmOrder";
	
	/**
	 * 获取短信验证码
	 */
	public static String GET_PHONE_CAPTCHA=Root+"getPhoneCaptcha";
	
	/**
	 * 获取所有省份
	 */
	public static String GET_PROVINCE_LIST=Root+"getProvinceList";
	
	/**
	 * 获取所有（或指定省份下）城市列表
	 */
	public static String GET_CITY_LIST=Root+"getCityList";
	
	/**
	 * 获取地区列表
	 */
	public static String GET_AREA_LIST=Root+"getAreaList";
	
	/**
	 * 检查更新
	 */
	public static String CHECK_UPDATE=Root+"checkUpdate";
	
	/*
	 * APP程序崩溃提交原因到后台记录
	 */
	public static String SERVER_FEEDBACK=Root+"serverFeedback";
	
	
}
