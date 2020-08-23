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

import com.tweltar.TrelloClone.models.Account;
import com.tweltar.TrelloClone.models.Card;
import com.tweltar.TrelloClone.models.Checklist;
import com.tweltar.TrelloClone.models.Label;
import com.tweltar.TrelloClone.repositories.AccountRepository;
import com.tweltar.TrelloClone.repositories.CardRepository;
import com.tweltar.TrelloClone.repositories.ChecklistRepository;
import com.tweltar.TrelloClone.repositories.LabelRepository;
import com.tweltar.TrelloClone.repositories.ListRepository;

@RestController
@CrossOrigin
@RequestMapping("/tweltar/trelloClone/cards")
public class CardsController {
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	ListRepository listRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	LabelRepository labelRepository;
	
	@Autowired
	ChecklistRepository checklistRepository;
	
	@GetMapping
	public List<Card> getAll() {
		return cardRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Card getById(@PathVariable Long id)  {
		return cardRepository.getOne(id);
	}
	
	@PostMapping("addTo/{list_id}")
	public Card createCard(@PathVariable Long list_id, @RequestBody final Card card) {
		com.tweltar.TrelloClone.models.List list = listRepository.getOne(list_id);
		card.setList(list);
		return cardRepository.saveAndFlush(card);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE) 
	public void delete(@PathVariable Long id) {
		cardRepository.deleteById(id);
	}
	
	@RequestMapping(value = "{list_id}/{id}", method = RequestMethod.PUT)
	public Card updateCard(@PathVariable final Long list_id, @PathVariable final Long id, @RequestBody Card card) {
		com.tweltar.TrelloClone.models.List list = listRepository.getOne(list_id);
		card.setList(list);
		Card existingCard = cardRepository.getOne(id);
		BeanUtils.copyProperties(card, existingCard, "id", "position", "status", "checklists", "labels", "accounts");
		return cardRepository.saveAndFlush(existingCard);
	}
	
	@RequestMapping(value = "add/member/{username}/to/card/{id}", method = RequestMethod.PUT)
	public Card addMember(@PathVariable String username, @PathVariable Long id) {
		Account account = accountRepository.getOne(username);
		Card card = cardRepository.getOne(id);
		card.getAccounts().add(account);
		return cardRepository.saveAndFlush(card);
	}
	
	@RequestMapping(value = "remove/member/{username}/from/card/{id}", method = RequestMethod.DELETE)
	public void removeMember(@PathVariable String username, @PathVariable Long id) {
		Account account = accountRepository.getOne(username);
		Card card = cardRepository.getOne(id);
		card.getAccounts().remove(account);
		cardRepository.saveAndFlush(card);
	}
	
	@RequestMapping(value = "add/label/{label_id}/to/card/{id}", method = RequestMethod.PUT)
	public Card addLabel(@PathVariable Long label_id, @PathVariable Long id) {
		Label label = labelRepository.getOne(label_id);
		Card card = cardRepository.getOne(id);
		card.getLabels().add(label);
		return cardRepository.saveAndFlush(card);
	}
	
	@RequestMapping(value = "remove/label/{label_id}/from/card/{id}", method = RequestMethod.DELETE)
	public void removeLabel(@PathVariable Long label_id, @PathVariable Long id) {
		Label label = labelRepository.getOne(label_id);
		Card card = cardRepository.getOne(id);
		card.getLabels().remove(label);
		cardRepository.saveAndFlush(card);
	}
	
	@RequestMapping(value = "add/checklist/{checklist_id}/to/card/{id}", method = RequestMethod.PUT)
	public Card addChecklist(@PathVariable Long checklist_id, @PathVariable Long id) {
		Checklist checklist = checklistRepository.getOne(checklist_id);
		Card card = cardRepository.getOne(id);
		card.getChecklists().add(checklist);
		return cardRepository.saveAndFlush(card);
	}
	
	@RequestMapping(value = "remove/checklist/{checklist_id}/from/card/{id}", method = RequestMethod.DELETE)
	public void removeChecklist(@PathVariable Long checklist_id, @PathVariable Long id) {
		Checklist checklist = checklistRepository.getOne(checklist_id);
		Card card = cardRepository.getOne(id);
		card.getChecklists().remove(checklist);
		cardRepository.saveAndFlush(card);
	}
	
	@RequestMapping(value = "move/card/{id}/to/list/{list_id}", method = RequestMethod.PUT)
	public Card moveCard(@PathVariable Long id, @PathVariable Long list_id) {
		com.tweltar.TrelloClone.models.List list = listRepository.getOne(list_id);
		Card card = cardRepository.getOne(id);
		card.setList(list);
		return cardRepository.saveAndFlush(card);
	}
	
	@RequestMapping(value = "change/card/{id}/status/{status}", method = RequestMethod.PUT)
	public Card changeStatus(@PathVariable Long id, @PathVariable Integer status) {
		Card card = cardRepository.getOne(id);
		card.setStatus(status);
		return cardRepository.saveAndFlush(card);
	}
}
