package com.howtodoinjava.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Where(clause = "is_deleted = false")
@Table(name = "TBL_INGRDIENTS")
public class IngredientsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String ingredientName;

	@Column(name = "status")
	private Boolean ingredientStatus;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
	private ProductEntity productEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public Boolean getIngredientStatus() {
		return ingredientStatus;
	}

	public void setIngredientStatus(Boolean ingredientStatus) {
		this.ingredientStatus = ingredientStatus;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", ingredientName=" + ingredientName + ", ingredientStatus="
				+ ingredientStatus + ", isDeleted=" + isDeleted + "]";
	}

}
