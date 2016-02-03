package com.cardShuffler.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cardShuffler.constants.Constants;
import com.cardShuffler.domain.Deck;
import com.cardShuffler.repository.DeckRepository;
import com.cardShuffler.strategy.ComplexShuffler;
import com.cardShuffler.strategy.SimpleShuffler;

/**
 * Service Class which has the business logic to perform all the CRUD Operations and to shuffle the cards
 * @author KARTHEEK YS
 *
 */
@Service
public class CardShufflerService {

	@Resource
	private DeckRepository theDeckRepo;
	
	@Autowired
	private ComplexShuffler complexShuffler;
	
	@Autowired
	private SimpleShuffler simpleShuffler;
	
	@Value("${cardShuffler.useComplexShuffling}")
	private boolean useComplexShuffler;
	
	/**
	 * Gets the Deck with given ID 
	 * @param aDeskId
	 * @return Deck
	 * @throws Exception
	 */
	public Deck getDeck(Long aDeskId) throws Exception{
		Deck aReturnedDeck =  theDeckRepo.findOne(aDeskId);
		if ( aReturnedDeck == null){
			throw new Exception("Deck with the Given ID Does not exist");
		}
		return aReturnedDeck;
	}
	
	/**
	 * Gets the Deck with given Name 
	 * @param aDeskId
	 * @return Deck
	 * @throws Exception
	 */
	public Deck getDeck(String aDeskName) throws Exception{
		Deck aReturnedDeck =  theDeckRepo.findByName(aDeskName);
		if ( aReturnedDeck == null){
			throw new Exception("Deck with the Given Name :" +aDeskName + "Does not exist");
		}
		return aReturnedDeck;
	}
	
	/**
	 * Gets all the Decks in DB 
	 * @return Iterable<Deck>
	 * @throws Exception
	 */
	public List<Deck> getAllDecks() throws Exception{
		List<Deck> theDeckList = (List<Deck>) theDeckRepo.findAll();
		if(theDeckList.size() == 0 || theDeckList == null){
			throw new Exception("No Decks Exist ! Create One with createDeck API ");
		}
		return theDeckList;
	}
	
	/**
	 * Creates a Deck with given Name 
	 * @param aDeckName
	 * @return Deck
	 */
	public Deck CreateDecks(String aDeckName){
		Deck d = new Deck();
		d.setDeckName(aDeckName);
		theDeckRepo.save(d);
		return d;
	}
	
	/**
	 * Shuffles the Deck according to the shuffle type turned on during deployement
	 * @param aShuffleCount
	 * @param aDeckName
	 * @return Deck
	 * @throws Exception 
	 */
	public Deck shuffleDecks(Long aShuffleCount , String aDeckName) throws Exception{
		
		Deck theExistingDeck = getDeck(aDeckName);
		String[] theCurrentOrder = new String[52];
		if(theExistingDeck != null && theExistingDeck.getCards() != null){
			theCurrentOrder = theExistingDeck.getCards().split(",");
		} else {
			 theCurrentOrder = Constants.CARDS.split(",");
		}
		
		List<String> theCurrentOrderList = Arrays.asList(theCurrentOrder);

		for ( int i = 0 ; i < aShuffleCount; i ++){
			if(useComplexShuffler){
				theCurrentOrderList  = complexShuffler.shuffleCards(theCurrentOrderList);
			} else {
				theCurrentOrderList = simpleShuffler.shuffleCards(theCurrentOrderList);
			}
		}
		theExistingDeck.setCards(theCurrentOrderList.toString());
		theDeckRepo.save(theExistingDeck);
		return theExistingDeck ;
	}
	
	/**
	 * Deletes the Deck with given Id.
	 * @param aDeckId
	 */
	public void deleteDecks(Long aDeckId){
		 theDeckRepo.delete(aDeckId);
	}
	
	/**
	 * Deletes the Deck with given Id.
	 * @param aDeckId
	 * @throws Exception 
	 */
	public void deleteDecks(String aDeckName) throws Exception{
		Deck theDeleatedDeck = getDeck(aDeckName);
		theDeckRepo.delete(theDeleatedDeck.getId());
	}
}
