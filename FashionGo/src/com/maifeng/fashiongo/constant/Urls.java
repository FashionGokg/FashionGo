package com.maifeng.fashiongo.constant;

public class Urls {
	/**
	 * Root
	 */
	private static String Root = "http://172.16.40.47/shop/index.php/home/";
//	private static String Root = "http://wemall.minephone.com/develop/ssgshop/";

	/**
	 * 个人注册
	 */
	public static String REGISTER = Root + "index/register";

	/**
	 * 获取验证码
	 * */
	public static String GetVerify = Root + "PhoneCaptcha/getPhoneCaptcha";

	/**
	 * 登录
	 */
	public static String LOGIN = Root +  "index/login";

	/**
	 * 首页.获取banner
	 */
	public static String GET_BANNER_LIST = Root + "BannerList/getBannerList";

	/**
	 * 首页.获取banner下的3个商品广告入口的信息
	 */
	public static String GET_THREE_GOODS_AD_INFO = Root + "BannerList/getThreeGoodsADInfo";

	/**
	 * 首页.获取首页底部精品推荐
	 */
	public static String GET_RECOMMENDATION = Root + "BannerList/getRecommendation";

	/**
	 * 获取一级分类
	 */
	public static String GET_CLASSIFY_ONE = Root + "Classify/getClassifyOne";

	/**
	 * 获取二级分类
	 */
	public static String GET_CLASSIFY_TWO = Root + "Classify/getClassifyTwo";

	/**
	 * 获取三级分类(品牌下)
	 */
	public static String GET_CLASSIFY_THREE = Root + "Classify/getClassifyThree";

	/**
	 * 获取商品列表（包括3个商品广告入口、首页商品搜索）
	 */
	public static String GET_GOODS_LIST = Root + "Goods/getGoodsList";

	/**
	 * 获取商品详情
	 */
	public static String GET_GOODS_DETAILS = Root + "Goods/getGoodsDetails";

	/**
	 * 获取商品的规格
	 */
	public static String GET_GOODS_SPECIFICATIONS = Root + "Goods/getGoodsSpecifications";

	/**
	 * 获取我的购物车
	 */
	public static String GET_MY_CARTINFO = Root + "GoodsToCar/getCartInfo";

	/**
	 * 加入购物车
	 */
	public static String ADD_GOODS_TO_CART = Root + "GoodsToCar/addGoodsToCar";

	/**
	 * 删除购物车的商品
	 */
	public static String DELETE_GOODS_FOR_CART = Root + "GoodsToCar/deleteGoodsForCart";

	/**
	 * 编辑购物车商品信息
	 */
	public static String EDIT_GOODS_FOR_CART = Root + "GoodsToCar/editGoodsForCart";

	/**
	 * 获取我的分享
	 */
	public static String GET_MY_SHARE = Root + "Share/getMyShare";

	/**
	 * 删除我的分享
	 */
	public static String DELETE_MY_SHARE = Root + "Share/deleteMyShare";

	/**
	 * 分享
	 */
	public static String SHARE_GOODS = Root + "Share/shareGoods";

	/**
	 * 添加收藏
	 */
	public static String ADD_COLLECTION = Root + "Collection/addCollection";

	/**
	 * 获取我的收藏列表
	 */
	public static String GET_MY_COLLECTION = Root + "Collection/getMyCollection";

	/**
	 * 删除收藏
	 */
	public static String DELETE_COLLECTION = Root + "Collection/deleteCollection";

	/**
	 * 添加收货地址
	 */
	public static String ADD_RECEIVE_ADDRESS = Root + "Address/addReceiveAddress";

	/**
	 * 删除收货地址
	 */
	public static String DELETE_ADDRESS = Root + "Address/deleteAddress";

	/**
	 * 获取收货地址列表
	 */
	public static String GET_RECEIVE_ADDRESS = Root + "Address/POSTReceiveAddress";

	/**
	 * 获取地址详情
	 */
	public static String GET_ADDRESS_DETAIL = Root + "Address/POSTAddressDetail";

	/**
	 * 编辑收货地址
	 */
	public static String EDIT_ADDRESS = Root + "Address/EditAddress";

	/**
	 * 获取个人详情
	 */
	public static String PERSONAL_DETAILS = Root + "PersonalDetails/PersonalDetails";

	/**
	 * 修改个人详情
	 */
	public static String CHANGE_PEMAL_INFO = Root + "PersonalDetails/changePernalInfo";

	/**
	 * 获取我的支付宝
	 */
	public static String GET_MY_ALIPAY_INFO = Root + "Alipay/GetMyAlipayInfo";

	/**
	 * 获取我的银行卡
	 */
	public static String GET_MY_BANK_CARD_INFO = Root + "Alipay/GetMyBankCardInfo";

	/**
	 * 获取我的订单
	 */
	public static String GET_MY_ORDER = Root + "Order/getMyOrder";

	/**
	 * 确认收货
	 */
	public static String CONFIRM_RECEIVING = Root + "Order/confirmReceiving";

	/**
	 * 删除订单
	 */
	public static String DELETE_ORDER = Root + "Order/deleteOrder";

	/**
	 * 查看订单详情
	 */
	public static String GET_ORDER_DETAIL = Root + "Order/getOrderDetail";

	/**
	 * 确认订单
	 */
	public static String CONFIRM_ORDER = Root + "Order/confirmOrder";

	/**
	 * 获取短信验证码
	 */
	public static String GET_PHONE_CAPTCHA = Root + "PhoneCaptcha/getPhoneCaptcha";

	/**
	 * 获取所有省份
	 */
	public static String GET_PROVINCE_LIST = Root + "Other/GetProvinceList";

	/**
	 * 获取所有（或指定省份下）城市列表
	 */
	public static String GET_CITY_LIST = Root + "Other/GetCityList";

	/**
	 * 获取地区列表
	 */
	public static String GET_AREA_LIST = Root + "Other/GetAreaList";

	/**
	 * 检查更新
	 */
	public static String CHECK_UPDATE = Root + "update/checkUpdate";

	/**
	 * APP程序崩溃提交原因到后台记录
	 */
	public static String SERVER_FEEDBACK = Root + "Feedback/serverFeedback";

	/**
	 * 获取启动页
	 */
	public static final String GETAPPWELCOME = Root + "Other/GetAppWelcome";

	/**
	 * 忘记密码网页链接
	 */
	public static final String FINDPASSWORD = Root + "System/findPassword";

	/**
	 * 关于我们网页链接
	 */
	public static final String ABOUTUS = Root + "System/aboutUs";

	/**
	 * 意见反馈网页链接
	 */
	public static final String TIPS = Root + "System/t";

	/**
	 * 注册协议链接
	 */
	public static final String PROTOCOL = Root + "System/protocol";
}
