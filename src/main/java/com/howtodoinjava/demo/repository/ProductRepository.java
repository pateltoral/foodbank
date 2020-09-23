package com.howtodoinjava.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.howtodoinjava.demo.model.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
