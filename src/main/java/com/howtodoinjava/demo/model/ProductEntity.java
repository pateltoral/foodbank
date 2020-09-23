package com.howtodoinjava.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

@Entity
@Where(clause = "is_deleted = false")
@Table(name = "TBL_PRODUCT")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true)
	private String productName;

	@Column(name = "weight")
	private float productWeight;

	@Column(name = "weight_type")
	private String productWeightType;

	@Column(name = "description")
	private String productDescription;

	@Column(name = "quantity")
	private int productQuantity;

	@Column(name = "is_available")
	private Boolean isAvailable;

	@Column(name = "threshold")
	private int productThreshold;

	@Column(name = "product_status")
	private Boolean productStatus;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cat_id")
	private CategoryEntity categoryEntity;

	@Transient
	private Long categoryId;

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<IngredientsEntity> ingredientsEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(float productWeight) {
		this.productWeight = productWeight;
	}

	public String getProductWeightType() {
		return productWeightType;
	}

	public void setProductWeightType(String productWeightType) {
		this.productWeightType = productWeightType;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getProductThreshold() {
		return productThreshold;
	}

	public void setProductThreshold(int productThreshold) {
		this.productThreshold = productThreshold;
	}

	public Boolean getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Boolean productStatus) {
		this.productStatus = productStatus;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public CategoryEntity getCategoryEntity() {
		return categoryEntity;
	}

	public void setCategoryEntity(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<IngredientsEntity> getIngredientsEntity() {
		return ingredientsEntity;
	}

	public void setIngredientsEntity(List<IngredientsEntity> ingredientsEntity) {
		this.ingredientsEntity = ingredientsEntity;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", productName=" + productName + ", productWeight=" + productWeight
				+ ", productWeightType=" + productWeightType + ", productDescription=" + productDescription
				+ ", productQuantity=" + productQuantity + ", isAvailable=" + isAvailable + ", productThreshold="
				+ productThreshold + ", productStatus=" + productStatus + ", isDeleted=" + isDeleted + ", category="
				+ categoryEntity.getCategoryName() + /* ", ingredients=" + Arrays.asList(ingredientsEntity) + */ "]";
	}
}
