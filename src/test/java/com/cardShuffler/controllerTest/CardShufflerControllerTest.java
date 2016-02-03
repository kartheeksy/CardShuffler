package com.cardShuffler.controllerTest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.web.client.RestTemplate;

import com.cardShuffler.configuration.Main;
import com.cardShuffler.constants.Constants;
import com.cardShuffler.domain.Deck;
import com.cardShuffler.repository.DeckRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

/**
 * Integration Tests for testing the Exposed RestFull Micro services .
 * Used RestTemplate to hit the Service and then validated the response .
 * As per Spring 4.1.6 having @WebIntegrationTest would allow the server to run and our services 
 * would be sent as a request to server which would make a valid EndToEnd test .
 * @author KARTHEEK YS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class,TestContext.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
@WebIntegrationTest
public class CardShufflerControllerTest {

	@Autowired
	DeckRepository theDeckrepository;
	
	/**
	 * Test case to test the GetByID Rest API . 
	 * @throws Exception
	 */
	@Test
	public void testGetById() throws Exception{
		Deck newDeck = new Deck();
		newDeck.setDeckName("ControllerTest");
		Deck aCreatedDeck = theDeckrepository.save(newDeck);
		RestTemplate template = new TestRestTemplate();
		Deck aRecievedDeck = template.getForEntity("http://localhost:8080"+Constants.GET_DECK_BY_ID+"?deckId="+aCreatedDeck.getId(), Deck.class).getBody();	
        Assert.assertEquals(aRecievedDeck.getCards(), Constants.CARDS);
	}
	
	/**
	 * Test case to test the GetByName API 
	 * @throws Exception
	 */
	@Test
	public void testGetByName() throws Exception{
		theDeckrepository.deleteAll();
		Deck newDeck = new Deck();
		newDeck.setDeckName("ControllerTest");
		Deck aCreatedDeck = theDeckrepository.save(newDeck);
		RestTemplate template = new TestRestTemplate();
		Deck aRecievedDeck = template.getForEntity("http://localhost:8080"+Constants.GET_DECK_BY_NAME+"?"+Constants.DECK_NAME+"="+aCreatedDeck.getDeckName(), Deck.class).getBody();	
        Assert.assertEquals(aRecievedDeck.getCards(), Constants.CARDS);
	}
	
	/**
	 * Test case to test GetAll API 
	 * @throws Exception
	 */
	@Test
	public void testGetAll() throws Exception{
		theDeckrepository.deleteAll();
		Deck newDeck = new Deck();
		newDeck.setDeckName("ControllerTest");
		theDeckrepository.save(newDeck);
		RestTemplate template = new TestRestTemplate();
		@SuppressWarnings("unchecked")
		List<Deck> aRecievedDeckList = template.getForEntity("http://localhost:8080"+Constants.GET_All_DECKS, List.class).getBody();	
        Assert.assertEquals(aRecievedDeckList.size(), 1);
	}
	
	/**
	 * Test Create Deck API 
	 * @throws Exception
	 */
	@Test
	public void testCreateDeck() throws Exception{
		theDeckrepository.deleteAll();
		RestTemplate template = new TestRestTemplate();
		template.put("http://localhost:8080"+Constants.CREATE_DECK+"?"+Constants.DECK_NAME+"=TestDeck", Deck.class);
		@SuppressWarnings("unchecked")
		List<Deck> aRecievedDeckList = template.getForEntity("http://localhost:8080"+Constants.GET_All_DECKS, List.class).getBody();	
        Assert.assertEquals(aRecievedDeckList.size(), 1);
	}
	
	/**
	 * Test Shuffle Deck API 
	 * @throws Exception
	 */
	@Test
	public void testShuffleDeck() throws Exception{
		theDeckrepository.deleteAll();
		Deck newDeck = new Deck();
		newDeck.setDeckName("TestDeck");
		theDeckrepository.save(newDeck);
		RestTemplate template = new TestRestTemplate();
		template.postForLocation("http://localhost:8080"+Constants.SHUFFLE_DECK+"?"+Constants.SHUFFLE_COUNT+"=1&"+Constants.DECK_NAME+"=TestDeck", Deck.class);
		Deck aRecievedDeck = template.getForEntity("http://localhost:8080"+Constants.GET_DECK_BY_NAME+"?"+Constants.DECK_NAME+"=TestDeck", Deck.class).getBody();	
        Assert.assertNotEquals(aRecievedDeck.getCards(), Constants.CARDS);
	}
	
	/**
	 * Test Delete Deck API 
	 * @throws Exception
	 */
	@Test
	public void testDeleteDeck() throws Exception{
		theDeckrepository.deleteAll();
		Deck newDeck = new Deck();
		newDeck.setDeckName("TestDeck");
		Deck aCreatedDeck = theDeckrepository.save(newDeck);
		RestTemplate template = new TestRestTemplate();
		template.delete("http://localhost:8080"+Constants.DELETE_DECK+"?"+Constants.DECK_ID+"="+aCreatedDeck.getId());
	}
	
}
