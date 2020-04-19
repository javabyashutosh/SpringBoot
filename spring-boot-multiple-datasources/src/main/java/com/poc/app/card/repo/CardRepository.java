package com.poc.app.card.repo;

import org.springframework.data.repository.CrudRepository;

import com.poc.app.card.entity.Card;

public interface CardRepository extends CrudRepository<Card,Integer>{

}
