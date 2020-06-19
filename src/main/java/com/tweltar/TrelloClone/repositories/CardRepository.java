package com.tweltar.TrelloClone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweltar.TrelloClone.models.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
