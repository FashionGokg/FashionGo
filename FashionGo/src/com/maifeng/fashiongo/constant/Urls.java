package com.maifeng.fashiongo.constant;

public class Urls {
	/**
	 * Root
	 */
	private static String Root = "http://172.16.40.47/shop/index.php/home/";
//	private static String Root = "http://wemall.minephone.com/develop/ssgshop/";

	/**
	 * ����ע��
	 */
	public static String REGISTER = Root + "index/register";

	/**
	 * ��ȡ��֤��
	 * */
	public static String GetVerify = Root + "PhoneCaptcha/getPhoneCaptcha";

	/**
	 * ��¼
	 */
	public static String LOGIN = Root +  "index/login";

	/**
	 * ��ҳ.��ȡbanner
	 */
	public static String GET_BANNER_LIST = Root + "BannerList/getBannerList";

	/**
	 * ��ҳ.��ȡbanner�µ�3����Ʒ�����ڵ���Ϣ
	 */
	public static String GET_THREE_GOODS_AD_INFO = Root + "BannerList/getThreeGoodsADInfo";

	/**
	 * ��ҳ.��ȡ��ҳ�ײ���Ʒ�Ƽ�
	 */
	public static String GET_RECOMMENDATION = Root + "BannerList/getRecommendation";

	/**
	 * ��ȡһ������
	 */
	public static String GET_CLASSIFY_ONE = Root + "Classify/getClassifyOne";

	/**
	 * ��ȡ��������
	 */
	public static String GET_CLASSIFY_TWO = Root + "Classify/getClassifyTwo";

	/**
	 * ��ȡ��������(Ʒ����)
	 */
	public static String GET_CLASSIFY_THREE = Root + "Classify/getClassifyThree";

	/**
	 * ��ȡ��Ʒ�б�����3����Ʒ�����ڡ���ҳ��Ʒ������
	 */
	public static String GET_GOODS_LIST = Root + "Goods/getGoodsList";

	/**
	 * ��ȡ��Ʒ����
	 */
	public static String GET_GOODS_DETAILS = Root + "Goods/getGoodsDetails";

	/**
	 * ��ȡ��Ʒ�Ĺ��
	 */
	public static String GET_GOODS_SPECIFICATIONS = Root + "Goods/getGoodsSpecifications";

	/**
	 * ��ȡ�ҵĹ��ﳵ
	 */
	public static String GET_MY_CARTINFO = Root + "GoodsToCar/getCartInfo";

	/**
	 * ���빺�ﳵ
	 */
	public static String ADD_GOODS_TO_CART = Root + "GoodsToCar/addGoodsToCar";

	/**
	 * ɾ�����ﳵ����Ʒ
	 */
	public static String DELETE_GOODS_FOR_CART = Root + "GoodsToCar/deleteGoodsForCart";

	/**
	 * �༭���ﳵ��Ʒ��Ϣ
	 */
	public static String EDIT_GOODS_FOR_CART = Root + "GoodsToCar/editGoodsForCart";

	/**
	 * ��ȡ�ҵķ���
	 */
	public static String GET_MY_SHARE = Root + "Share/getMyShare";

	/**
	 * ɾ���ҵķ���
	 */
	public static String DELETE_MY_SHARE = Root + "Share/deleteMyShare";

	/**
	 * ����
	 */
	public static String SHARE_GOODS = Root + "Share/shareGoods";

	/**
	 * ����ղ�
	 */
	public static String ADD_COLLECTION = Root + "Collection/addCollection";

	/**
	 * ��ȡ�ҵ��ղ��б�
	 */
	public static String GET_MY_COLLECTION = Root + "Collection/getMyCollection";

	/**
	 * ɾ���ղ�
	 */
	public static String DELETE_COLLECTION = Root + "Collection/deleteCollection";

	/**
	 * ����ջ���ַ
	 */
	public static String ADD_RECEIVE_ADDRESS = Root + "Address/addReceiveAddress";

	/**
	 * ɾ���ջ���ַ
	 */
	public static String DELETE_ADDRESS = Root + "Address/deleteAddress";

	/**
	 * ��ȡ�ջ���ַ�б�
	 */
	public static String GET_RECEIVE_ADDRESS = Root + "Address/POSTReceiveAddress";

	/**
	 * ��ȡ��ַ����
	 */
	public static String GET_ADDRESS_DETAIL = Root + "Address/POSTAddressDetail";

	/**
	 * �༭�ջ���ַ
	 */
	public static String EDIT_ADDRESS = Root + "Address/EditAddress";

	/**
	 * ��ȡ��������
	 */
	public static String PERSONAL_DETAILS = Root + "PersonalDetails/PersonalDetails";

	/**
	 * �޸ĸ�������
	 */
	public static String CHANGE_PEMAL_INFO = Root + "PersonalDetails/changePernalInfo";

	/**
	 * ��ȡ�ҵ�֧����
	 */
	public static String GET_MY_ALIPAY_INFO = Root + "Alipay/GetMyAlipayInfo";

	/**
	 * ��ȡ�ҵ����п�
	 */
	public static String GET_MY_BANK_CARD_INFO = Root + "Alipay/GetMyBankCardInfo";

	/**
	 * ��ȡ�ҵĶ���
	 */
	public static String GET_MY_ORDER = Root + "Order/getMyOrder";

	/**
	 * ȷ���ջ�
	 */
	public static String CONFIRM_RECEIVING = Root + "Order/confirmReceiving";

	/**
	 * ɾ������
	 */
	public static String DELETE_ORDER = Root + "Order/deleteOrder";

	/**
	 * �鿴��������
	 */
	public static String GET_ORDER_DETAIL = Root + "Order/getOrderDetail";

	/**
	 * ȷ�϶���
	 */
	public static String CONFIRM_ORDER = Root + "Order/confirmOrder";

	/**
	 * ��ȡ������֤��
	 */
	public static String GET_PHONE_CAPTCHA = Root + "PhoneCaptcha/getPhoneCaptcha";

	/**
	 * ��ȡ����ʡ��
	 */
	public static String GET_PROVINCE_LIST = Root + "Other/GetProvinceList";

	/**
	 * ��ȡ���У���ָ��ʡ���£������б�
	 */
	public static String GET_CITY_LIST = Root + "Other/GetCityList";

	/**
	 * ��ȡ�����б�
	 */
	public static String GET_AREA_LIST = Root + "Other/GetAreaList";

	/**
	 * ������
	 */
	public static String CHECK_UPDATE = Root + "update/checkUpdate";

	/**
	 * APP��������ύԭ�򵽺�̨��¼
	 */
	public static String SERVER_FEEDBACK = Root + "Feedback/serverFeedback";

	/**
	 * ��ȡ����ҳ
	 */
	public static final String GETAPPWELCOME = Root + "Other/GetAppWelcome";

	/**
	 * ����������ҳ����
	 */
	public static final String FINDPASSWORD = Root + "System/findPassword";

	/**
	 * ����������ҳ����
	 */
	public static final String ABOUTUS = Root + "System/aboutUs";

	/**
	 * ���������ҳ����
	 */
	public static final String TIPS = Root + "System/t";

	/**
	 * ע��Э������
	 */
	public static final String PROTOCOL = Root + "System/protocol";
}
