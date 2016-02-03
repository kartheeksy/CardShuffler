package com.cardShuffler.serviceTest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cardShuffler.configuration.Main;
import com.cardShuffler.constants.Constants;
import com.cardShuffler.domain.Deck;
import com.cardShuffler.repository.DeckRepository;
import com.cardShuffler.service.CardShufflerService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

/**
 * This class tests the service layer methods . 
 * @author KARTHEEK YS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
public class CardShufflerServiceTest {
	
	@Autowired
	private DeckRepository theDeckRepository;
	
	@Autowired
	private CardShufflerService theNikeInterviewService;
	
	/**
	 * Tests Create Deck 
	 */
	@Test
	public void testCreateDeck(){
		Deck aCreatedDeck = theNikeInterviewService.CreateDecks("TestDeck");
        Assert.assertEquals(aCreatedDeck.getDeckName(), "TestDeck");
	}
	
	/**
	 * Tests the Card Order after creation 
	 */
	@Test
	public void testInitialCardOrder(){
		theDeckRepository.deleteAll();
		Deck aCreatedDeck = theNikeInterviewService.CreateDecks("TestDeck");
        Assert.assertEquals(aCreatedDeck.getCards(), Constants.CARDS);
	}
	
	/**
	 * Tests the get Desk 
	 * @throws Exception
	 */
	@Test
	public void testgetDeck() throws Exception{
		theDeckRepository.deleteAll();
		Deck aCreatedDeck = theNikeInterviewService.CreateDecks("TestDeck");
		Deck aRecievedDeck = theNikeInterviewService.getDeck(aCreatedDeck.getId());
        Assert.assertEquals(aRecievedDeck.getDeckName(), "TestDeck");
	}
	
	/**
	 * Tests the get Desk by Name 
	 * @throws Exception
	 */
	@Test
	public void testgetDeckByName() throws Exception{
		theDeckRepository.deleteAll();
		Deck aCreatedDeck = theNikeInterviewService.CreateDecks("TestDeck");
		Deck aRecievedDeck = theNikeInterviewService.getDeck(aCreatedDeck.getDeckName());
        Assert.assertEquals(aRecievedDeck.getDeckName(), "TestDeck");
	}
	
	/**
	 * Tests the get all Desks 
	 * @throws Exception
	 */
	@Test
	public void testgetAllDecks() throws Exception{
		theDeckRepository.deleteAll();
		theNikeInterviewService.CreateDecks("TestDeck");
		theNikeInterviewService.CreateDecks("TestDeck2");
		theNikeInterviewService.CreateDecks("TestDeck3");
		List<Deck> aRecievedDeck = theNikeInterviewService.getAllDecks();
        Assert.assertEquals(aRecievedDeck.size(), 3);
	}
	
	/**
	 * Tests the shuffle method
	 * @throws Exception
	 */
	@Test
	public void testshuffle() throws Exception{
		theDeckRepository.deleteAll();
		Deck aCreatedDeck = theNikeInterviewService.CreateDecks("TestDeck");
		theNikeInterviewService.shuffleDecks(3L, aCreatedDeck.getDeckName());
		Deck aRecievedDeck = theNikeInterviewService.getDeck(aCreatedDeck.getDeckName());
        Assert.assertNotEquals(aRecievedDeck.getCards(), Constants.CARDS);
	}
	
	/**
	 * Tests the delete Deck method
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testDeleteDeck() throws Exception{
		theDeckRepository.deleteAll();
		Deck aCreatedDeck = theNikeInterviewService.CreateDecks("TestDeck");
		theNikeInterviewService.deleteDecks(aCreatedDeck.getId());
		theNikeInterviewService.getDeck("TestDeck");
	}
	
	/**
	 * Tests the delete deck by name method .
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testDeleteDeckByName() throws Exception{
		theDeckRepository.deleteAll();
		Deck aCreatedDeck = theNikeInterviewService.CreateDecks("TestDeck");
		theNikeInterviewService.deleteDecks(aCreatedDeck.getDeckName());
		theNikeInterviewService.getDeck("TestDeck");
	}

}
