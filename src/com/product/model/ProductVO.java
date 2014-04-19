package com.product.model;
import java.sql.Date;

public class ProductVO implements java.io.Serializable{
	private Integer proNo;
	private String productName;
	private String category;
	private Integer price;
	private Byte[] image_1;
	private Byte[] image_2;
	private Byte[] image_3;
	private Integer quantity;
	private Integer minimumQuantity;
	private Date dateAvailable;
	private Integer status;
	private String keywords;
	private String description;
	private String relatedProducts;
	private Integer priority;
	private Double discount;
	private Integer score;
	
	public Integer getProNo() {
		return proNo;
	}
	public void setProNo(Integer proNo) {
		this.proNo = proNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Byte[] getImage_1() {
		return image_1;
	}
	public void setImage_1(Byte[] image_1) {
		this.image_1 = image_1;
	}
	public Byte[] getImage_2() {
		return image_2;
	}
	public void setImage_2(Byte[] image_2) {
		this.image_2 = image_2;
	}
	public Byte[] getImage_3() {
		return image_3;
	}
	public void setImage_3(Byte[] image_3) {
		this.image_3 = image_3;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getMinimumQuantity() {
		return minimumQuantity;
	}
	public void setMinimumQuantity(Integer minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}
	public Date getDateAvailable() {
		return dateAvailable;
	}
	public void setDateAvailable(Date dateAvailable) {
		this.dateAvailable = dateAvailable;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRelatedProducts() {
		return relatedProducts;
	}
	public void setRelatedProducts(String relatedProducts) {
		this.relatedProducts = relatedProducts;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getScorel() {
		return score;
	}
	public void setScorel(Integer score) {
		this.score = score;
	}
	
}
