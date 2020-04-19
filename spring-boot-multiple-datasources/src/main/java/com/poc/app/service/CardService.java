package com.poc.app.service;

import org.springframework.stereotype.Service;

import com.poc.app.card.entity.Card;
import com.poc.app.card.repo.CardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

	private final CardRepository cardRepository;
	
	public Card saveCardInfo(Card card) {
		return cardRepository.save(card);
	}
}
