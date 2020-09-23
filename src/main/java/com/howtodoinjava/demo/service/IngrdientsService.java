package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.IngredientsEntity;
import com.howtodoinjava.demo.repository.IngredientsRepository;

@Service
public class IngrdientsService {

	@Autowired
	IngredientsRepository repository;

	public List<IngredientsEntity> getAllIngredient() {
		List<IngredientsEntity> result = (List<IngredientsEntity>) repository.findAll();

		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<IngredientsEntity>();
		}
	}

	public IngredientsEntity getIngredientById(Long id) throws RecordNotFoundException {
		Optional<IngredientsEntity> ingredient = repository.findById(id);

		if (ingredient.isPresent()) {
			return ingredient.get();
		} else {
			throw new RecordNotFoundException("No ingredient record exist for given id");
		}
	}

	public IngredientsEntity createOrUpdateIngredient(IngredientsEntity entity) {
		if (entity.getId() == null) {
			entity.setIsDeleted(false);
			entity = repository.save(entity);

			return entity;
		} else {
			Optional<IngredientsEntity> ingredient = repository.findById(entity.getId());

			if (ingredient.isPresent()) {
				IngredientsEntity newEntity = ingredient.get();
				newEntity.setIngredientName(entity.getIngredientName());
				newEntity.setIngredientStatus(entity.getIngredientStatus());
				newEntity.setIsDeleted(false);

				newEntity = repository.save(newEntity);

				return newEntity;
			} else {
				entity = repository.save(entity);

				return entity;
			}
		}
	}

	public void deleteIngredientById(Long id) throws RecordNotFoundException {
		Optional<IngredientsEntity> ingredient = repository.findById(id);

		if (ingredient.isPresent()) {
			IngredientsEntity newEntity = ingredient.get();
			newEntity.setIsDeleted(true);
			newEntity = repository.save(newEntity);
		} else {
			throw new RecordNotFoundException("No ingredient record exist for given id");
		}
	}
}
