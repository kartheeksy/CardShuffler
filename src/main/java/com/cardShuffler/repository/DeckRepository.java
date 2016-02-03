package com.cardShuffler.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cardShuffler.domain.Deck;

/**
 * Repository to Connect to Database and perform DB Operations .
 * @author KARTHEEK YS
 *
 */
@Repository
public interface DeckRepository extends CrudRepository<Deck, Long> {
	
	/**
	 * Method to get the Given Deck by Name form in memory database .
	 * @param aDeckName
	 * @return Deck
	 */
	@Query("select d from Deck d where d.DeckName = ?1")
	public Deck findByName(String aDeckName);

}
