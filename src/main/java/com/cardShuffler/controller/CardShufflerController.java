
package com.cardShuffler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cardShuffler.constants.Constants;
import com.cardShuffler.domain.Deck;
import com.cardShuffler.service.CardShufflerService;

/**
 * This rest controller has the CRUD API Exposed to create a DECK of Cards and an API to shuffle the DECK.
 * @author KARTHEEK YS
 *
 */
@RestController
public class CardShufflerController {
	
	@Autowired
	public CardShufflerService aNikeCodeInterviewService;
	
	/**
	 * API to Get the Decks by ID 
	 * @param aDeckId
	 * @return Deck
	 * @throws Exception
	 */
    @RequestMapping(method = RequestMethod.GET, value = Constants.GET_DECK_BY_ID)
    @ResponseBody
    Deck getCards(@RequestParam(value = Constants.DECK_ID) Long aDeckId) throws Exception {
    	return aNikeCodeInterviewService.getDeck(aDeckId);
    }
    
    /**
	 * API to Get the Decks by Name 
	 * @param aDeckId
	 * @return Deck
	 * @throws Exception
	 */
    @RequestMapping(method = RequestMethod.GET, value = Constants.GET_DECK_BY_NAME)
    @ResponseBody
    Deck getDeckByName(@RequestParam(value = Constants.DECK_NAME) String aDeckName) throws Exception {
    	return aNikeCodeInterviewService.getDeck(aDeckName);
    }
    
    /**
     * API to get all the Existing Decks
     * @return Iterable<Deck>
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET, value = Constants.GET_All_DECKS)
    @ResponseBody
    List<Deck> getAllCards() throws Exception {
    	return aNikeCodeInterviewService.getAllDecks();
    }
    
    /**
     * API To create a Deck 
     * @param aDeckName
     * @return Deck
     */
    @RequestMapping(method = RequestMethod.PUT, value = Constants.CREATE_DECK)
    @ResponseBody
    Deck updateCards(@RequestParam(value = Constants.DECK_NAME) String aDeckName) {
    	return aNikeCodeInterviewService.CreateDecks(aDeckName);
    }
    
    /**
     * API to Shuffle a DECK .
     * @param aShuffleCount
     * @param aDeckName
     * @return Deck
     * @throws Exception 
     */
    @RequestMapping(method = RequestMethod.POST, value = Constants.SHUFFLE_DECK)
    @ResponseBody
    Deck shuffleCards(@RequestParam(value = Constants.SHUFFLE_COUNT) Long aShuffleCount , @RequestParam(value = Constants.DECK_NAME) String aDeckName) throws Exception {
    	return aNikeCodeInterviewService.shuffleDecks(aShuffleCount,aDeckName);
    }
    
    /**
     * API to Delete a DECK 
     * @param aDeckId
     */
    @RequestMapping(method = RequestMethod.DELETE, value = Constants.DELETE_DECK)
    @ResponseBody
    void deleteDeck(@RequestParam(value = Constants.DECK_ID) Long aDeckId) {
    	 aNikeCodeInterviewService.deleteDecks(aDeckId);
    }
    
    /**
     * API to Delete a DECK 
     * @param aDeckId
     * @throws Exception 
     */
    @RequestMapping(method = RequestMethod.DELETE, value = Constants.DELETE_DECK_BY_NAME)
    @ResponseBody
    void deleteDeckByName(@RequestParam(value = Constants.DECK_NAME) String aDeckName) throws Exception {
    	 aNikeCodeInterviewService.deleteDecks(aDeckName);
    }
}
