package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.ProductEntity;
import com.howtodoinjava.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	public List<ProductEntity> getAllProducts() {
		List<ProductEntity> result = (List<ProductEntity>) repository.findAll();

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<ProductEntity>();
		}
	}

	public ProductEntity getProductById(Long id) throws RecordNotFoundException {
		Optional<ProductEntity> product = repository.findById(id);

		if (product.isPresent()) {
			return product.get();
		} else {
			throw new RecordNotFoundException("No product record exist for given id");
		}
	}

	public ProductEntity createOrUpdateProduct(ProductEntity entity) {
		if (entity.getId() == null) {
			entity.setIsDeleted(false);
			entity = repository.save(entity);

			return entity;
		} else {
			Optional<ProductEntity> product = repository.findById(entity.getId());

			if (product.isPresent()) {
				ProductEntity newEntity = product.get();
				newEntity.setProductName(entity.getProductName());
				newEntity.setProductWeight(entity.getProductWeight());
				newEntity.setProductWeightType(entity.getProductWeightType());
				newEntity.setProductDescription(entity.getProductDescription());
				newEntity.setProductQuantity(entity.getProductQuantity());
				newEntity.setIsAvailable(entity.getIsAvailable());
				newEntity.setProductThreshold(entity.getProductThreshold());
				newEntity.setProductStatus(entity.getProductStatus());
				newEntity.setIsDeleted(entity.getIsDeleted());
				newEntity.setCategoryEntity(entity.getCategoryEntity());
				newEntity.setIngredientsEntity(entity.getIngredientsEntity());
				newEntity.setIsDeleted(false);

				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}

	public void deleteProductById(Long id) throws RecordNotFoundException {
		Optional<ProductEntity> product = repository.findById(id);

		if (product.isPresent()) {
			ProductEntity newEntity = product.get();
			newEntity.setIsDeleted(true);
			newEntity = repository.save(newEntity);
		} else {
			throw new RecordNotFoundException("No ingredient record exist for given id");
		}
	}
}
