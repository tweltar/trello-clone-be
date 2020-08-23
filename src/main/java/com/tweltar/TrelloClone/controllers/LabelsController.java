package com.tweltar.TrelloClone.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tweltar.TrelloClone.models.Label;
import com.tweltar.TrelloClone.repositories.CardRepository;
import com.tweltar.TrelloClone.repositories.LabelRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/tweltar/trelloClone/labels")
public class LabelsController {
	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	@GetMapping
	public List<Label> getAll() {
		return labelRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Label getById(@PathVariable final Long id) {
		return labelRepository.getOne(id);
	}
	
	@PostMapping
	public Label createLabel(@RequestBody Label label) {
		return labelRepository.saveAndFlush(label);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Label update(@RequestBody Label label, @PathVariable Long id) {
		Label existingLabel = labelRepository.getOne(id);
		BeanUtils.copyProperties(label, existingLabel, "id", "status", "cards");
		return labelRepository.saveAndFlush(existingLabel);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		labelRepository.deleteById(id);
	}
}
