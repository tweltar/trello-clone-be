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

import com.tweltar.TrelloClone.models.Checklist;
import com.tweltar.TrelloClone.repositories.CardRepository;
import com.tweltar.TrelloClone.repositories.ChecklistRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/tweltar/trelloClone/checklists")
public class ChecklistsController {
	@Autowired
	ChecklistRepository checklistRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	@GetMapping
	public List<Checklist> getAll() {
		return checklistRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Checklist getById(@PathVariable Long id) {
		return checklistRepository.getOne(id);
	}
	
	@PostMapping("addTo/{card_id}")
	public Checklist create(@PathVariable Long card_id, @RequestBody Checklist checklist) {
		checklist.setCardId(card_id);
		return checklistRepository.saveAndFlush(checklist);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public Checklist update(@PathVariable Long id, @RequestBody Checklist checklist) {
		Checklist existingChecklist = checklistRepository.getOne(id);
		BeanUtils.copyProperties(checklist, existingChecklist, "id", "cardId", "position", "checked");
		return checklistRepository.saveAndFlush(existingChecklist);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		checklistRepository.deleteById(id);
	}
	
	@PostMapping(value = {"{id}/{checked}"})
	public Checklist checkedUnchecked(@PathVariable Long id, @PathVariable Short checked) {
		Checklist checklist = checklistRepository.getOne(id);
		checklist.setChecked(checked);
		return checklistRepository.saveAndFlush(checklist);
	}
}
