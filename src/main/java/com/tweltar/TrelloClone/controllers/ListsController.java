package com.tweltar.TrelloClone.controllers;

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

import com.tweltar.TrelloClone.models.List;
import com.tweltar.TrelloClone.repositories.ListRepository;

@RestController
@CrossOrigin
@RequestMapping("/tweltar/trelloClone/lists")
public class ListsController {
	@Autowired
	ListRepository listRepository;
	
	@GetMapping
	public java.util.List<List> getAll() {
		return listRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public List getById(@PathVariable Long id) {
		return listRepository.getOne(id);
	}
	
	@PostMapping
	public List createList(@RequestBody final List list) {
		return listRepository.saveAndFlush(list);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteListById(@PathVariable Long id ) {
		listRepository.deleteById(id);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public List update(@RequestBody final List list, @PathVariable Long id) {
		List existingList = listRepository.getOne(id);
		BeanUtils.copyProperties(list, existingList, "id", "position", "status", "cards");
		return listRepository.saveAndFlush(existingList);
	}
	
	@RequestMapping(value = "change/list/{id}/status/{status}", method = RequestMethod.PUT)
	public List changeStatus(@PathVariable Long id, @PathVariable Integer status) {
		List list = listRepository.getOne(id);
		list.setStatus(status);
		return listRepository.saveAndFlush(list);
	}
}
