package com.tweltar.TrelloClone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweltar.TrelloClone.models.List;

public interface ListRepository extends JpaRepository<List, Long> {

}
