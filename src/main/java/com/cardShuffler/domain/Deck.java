package com.cardShuffler.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.cardShuffler.constants.Constants;

@Entity
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "deck_seq_gen")
	@SequenceGenerator(name = "deck_seq_gen", sequenceName = "DECK_ID_SEQ", initialValue = 1000)
	@Column(name = "DECK_ID")
	private Long id;
	
	@Column(length = 1000)
	private String cards = Constants.CARDS;
	
	@Column(unique = true)
	private String DeckName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeckName() {
		return DeckName;
	}

	public void setDeckName(String deckName) {
		DeckName = deckName;
	}

	public String getCards() {
		return cards;
	}

	public void setCards(String cards) {
		this.cards = cards;
	}

	
}
