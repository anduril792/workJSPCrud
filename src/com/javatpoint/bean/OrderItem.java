package com.javatpoint.bean;

public class OrderItem {
	private Order order;// �q��
	private Product p; // �ӫ~
	private int buynum; // �ʪ��ƶq
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	
}