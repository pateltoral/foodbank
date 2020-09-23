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
import com.howtodoinjava.demo.model.IngredientsEntity;
import com.howtodoinjava.demo.service.IngrdientsService;

@Controller
@RequestMapping("/ingredient")
public class IngredientsMvcController {
	@Autowired
	IngrdientsService service;

	@RequestMapping
	public String getAllIngredients(Model model) {
		List<IngredientsEntity> list = service.getAllIngredient();

		model.addAttribute("ingredients", list);
		return "list-ingredients";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editIngredientById(Model model, @PathVariable("id") Optional<Long> id)
			throws RecordNotFoundException {
		if (id.isPresent()) {
			IngredientsEntity entity = service.getIngredientById(id.get());
			model.addAttribute("ingredient", entity);
		} else {
			model.addAttribute("ingredient", new IngredientsEntity());
		}
		return "add-edit-ingredient";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteIngredientById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteIngredientById(id);
		return "redirect:/ingredient";
	}

	@RequestMapping(path = "/createIngredient", method = RequestMethod.POST)
	public String createOrUpdateIngredient(IngredientsEntity ingredient) {
		service.createOrUpdateIngredient(ingredient);
		return "redirect:/ingredient";
	}
}
