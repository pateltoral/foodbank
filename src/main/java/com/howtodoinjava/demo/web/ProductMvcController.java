package com.howtodoinjava.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.CategoryEntity;
import com.howtodoinjava.demo.model.ProductEntity;
import com.howtodoinjava.demo.repository.CategoryRepository;
import com.howtodoinjava.demo.repository.IngredientsRepository;
import com.howtodoinjava.demo.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductMvcController {
	@Autowired
	ProductService service;

	@Autowired
	CategoryRepository catRepository;
	@Autowired
	IngredientsRepository ingreRepository;

	@RequestMapping
	public String getAllProducts(Model model) {
		List<ProductEntity> list = service.getAllProducts();

		model.addAttribute("products", list);
		return "list-products";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editProductById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			ProductEntity entity = service.getProductById(id.get());
			entity.setCategoryId(entity.getCategoryEntity().getId());
			model.addAttribute("product", entity);
		} else {
			model.addAttribute("product", new ProductEntity());
		}
		List<CategoryEntity> catEntities = (List<CategoryEntity>) catRepository.findAll();
		model.addAttribute("categoryList", catEntities);
		List<CategoryEntity> ingreEntities = (List<CategoryEntity>) catRepository.findAll();
		model.addAttribute("ingredientsList", ingreEntities);
		return "add-edit-product";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteProductById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteProductById(id);
		return "redirect:/product";
	}

	@RequestMapping(path = "/createProduct", method = RequestMethod.POST)
	public String createOrUpdateProduct(ProductEntity product) {
		Optional<CategoryEntity> entity = catRepository.findById(product.getCategoryId());
		product.setCategoryEntity(entity.get());
		service.createOrUpdateProduct(product);
		return "redirect:/product";
	}
}
