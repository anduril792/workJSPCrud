package com.javatpoint.bean;

public class ProductForList 
{
	private String id;
	private String name;
	private double price;
	private int pnum;
	private String category;
	private String description;
	private String img_url;
	
	public ProductForList()	{}
	public ProductForList(String id, String name, double price, String category, int pnum, String img_url,
			String description)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.pnum = pnum;
		this.img_url = img_url;
		this.description = description;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
