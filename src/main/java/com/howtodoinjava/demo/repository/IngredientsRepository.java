package com.howtodoinjava.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howtodoinjava.demo.model.IngredientsEntity;

public interface IngredientsRepository extends JpaRepository<IngredientsEntity, Long> {

}
