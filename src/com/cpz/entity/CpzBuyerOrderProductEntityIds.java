package com.cpz.entity;
@SuppressWarnings("serial")
public class CpzBuyerOrderProductEntityIds implements java.io.Serializable{
	private String orderno;//订单号
	private String shopproductid;//卖家商品代号
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String morderno) {
		orderno = morderno;
	}
	public String getShopproductid() {
		return shopproductid;
	}
	public void setShopproductid(String mshopproductid) {
		shopproductid = mshopproductid;
	}
}

