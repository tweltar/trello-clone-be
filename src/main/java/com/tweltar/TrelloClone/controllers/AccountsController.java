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
import com.tweltar.TrelloClone.repositories.AccountRepository;

@RestController
@RequestMapping("/tweltar/trelloClone/accounts")
public class AccountsController {
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping
	public List<Account> getAll() {
		return accountRepository.findAll();
	}
	
	@GetMapping
	@RequestMapping("{username}")
	public Account getById(@PathVariable String username) {
		return accountRepository.getOne(username);
	}
	
	@PostMapping
	public Account createAccount(@RequestBody Account account) {
		return accountRepository.saveAndFlush(account);
	}
	
	@RequestMapping(value = "{username}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String username) {
		accountRepository.deleteById(username);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Account updateAccount(@RequestBody Account account, @PathVariable String username) {
		Account existingAccount = accountRepository.getOne(username);
		BeanUtils.copyProperties(account, existingAccount, "id", "username", "verified");
		return accountRepository.saveAndFlush(existingAccount);
	}
}
