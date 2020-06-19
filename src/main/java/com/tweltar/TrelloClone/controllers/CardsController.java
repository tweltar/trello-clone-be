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

import com.tweltar.TrelloClone.models.Card;
import com.tweltar.TrelloClone.repositories.CardRepository;

@RestController
@RequestMapping("/trelloClone/v1/cards")
public class CardsController {
	@Autowired
	CardRepository cardRepository;
	
	@GetMapping
	public List<Card> getAll() {
		return cardRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{id}")
	public Card getById(@PathVariable Long id)  {
		return cardRepository.getOne(id);
	}
	
	@PostMapping
	public Card createCard(@RequestBody final Card card) {
		return cardRepository.saveAndFlush(card);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE) 
	public void delete(@PathVariable Long id) {
		cardRepository.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Card updateCard(@PathVariable final Long id, @RequestBody Card card) {
		Card existingCard = cardRepository.getOne(id);
		BeanUtils.copyProperties(card, existingCard, "id", "position", "status");
		return cardRepository.saveAndFlush(existingCard);
	}
}
