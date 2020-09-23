package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.CategoryEntity;
import com.howtodoinjava.demo.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repository;

	public List<CategoryEntity> getAllCategory() {
		List<CategoryEntity> result = (List<CategoryEntity>) repository.findAll();

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<CategoryEntity>();
		}
	}

	public CategoryEntity getCategoryById(Long id) throws RecordNotFoundException {
		Optional<CategoryEntity> category = repository.findById(id);

		if (category.isPresent()) {
			return category.get();
		} else {
			throw new RecordNotFoundException("No category record exist for given id");
		}
	}

	public CategoryEntity createOrUpdateCategory(CategoryEntity entity) {
		if (entity.getId() == null) {
			entity.setIsDeleted(false);
			entity = repository.save(entity);

			return entity;
		} else {
			Optional<CategoryEntity> category = repository.findById(entity.getId());

			if (category.isPresent()) {
				CategoryEntity newEntity = category.get();
				newEntity.setCategoryName(entity.getCategoryName());
				newEntity.setCategoryStatus(entity.getCategoryStatus());
				newEntity.setIsDeleted(false);
				
				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}

	public void deleteCategoryById(Long id) throws RecordNotFoundException {
		Optional<CategoryEntity> category = repository.findById(id);

		if (category.isPresent()) {
			CategoryEntity newEntity = category.get();
			newEntity.setIsDeleted(true);
			newEntity = repository.save(newEntity);
		} else {
			throw new RecordNotFoundException("No category record exist for given id");
		}
	}
}
