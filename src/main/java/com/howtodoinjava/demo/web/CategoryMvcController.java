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
import com.howtodoinjava.demo.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryMvcController {
	@Autowired
	CategoryService service;

	@RequestMapping
	public String getAllEmployees(Model model) {
		List<CategoryEntity> list = service.getAllCategory();

		model.addAttribute("categories", list);
		return "list-categories";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editCategoryById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			CategoryEntity entity = service.getCategoryById(id.get());
			model.addAttribute("category", entity);
		} else {
			model.addAttribute("category", new CategoryEntity());
		}
		return "add-edit-category";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteCategoryById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteCategoryById(id);
		return "redirect:/category";
	}

	@RequestMapping(path = "/createCategory", method = RequestMethod.POST)
	public String createOrUpdateCategory(CategoryEntity category) {
		service.createOrUpdateCategory(category);
		return "redirect:/category";
	}
}
