package com.tweltar.TrelloClone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweltar.TrelloClone.models.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

}
