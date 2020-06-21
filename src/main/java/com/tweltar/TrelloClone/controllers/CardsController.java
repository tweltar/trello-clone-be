package com.tweltar.TrelloClone.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tweltar.TrelloClone.models.Account;
import com.tweltar.TrelloClone.models.Card;
import com.tweltar.TrelloClone.repositories.AccountRepository;
import com.tweltar.TrelloClone.repositories.CardRepository;
import com.tweltar.TrelloClone.repositories.ListRepository;

@RestController
@RequestMapping("/trelloClone/v1/cards")
public class CardsController {
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	ListRepository listRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
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
		BeanUtils.copyProperties(card, existingCard, "id", "position", "status");
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
}
