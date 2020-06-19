package com.tweltar.TrelloClone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweltar.TrelloClone.models.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
