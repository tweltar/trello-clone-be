package com.tweltar.TrelloClone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweltar.TrelloClone.models.Label;

public interface LabelRepository extends JpaRepository<Label, Long> {

}
