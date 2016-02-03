package com.cardShuffler.serviceTest;

import java.util.Arrays;

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
import com.cardShuffler.strategy.ComplexShuffler;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

/**
 * This test case tests the logic written for handShuffling .
 * @author KARTHEEK YS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
public class ComplexShufflerStrategyTest {
	
	@Autowired
	private DeckRepository theDeckRepository;
	
	@Autowired
	private CardShufflerService theNikeInterviewService;
	
	@Autowired 
	private ComplexShuffler complexShuffler;
	
	/**
	 * Invoking the shuffle from ComplexShuffle Class
	 */
	@Test
	public void complexShufflerTest(){
		theDeckRepository.deleteAll();
		Deck aNonShuffledDeck = theNikeInterviewService.CreateDecks("TestDeck");
        Assert.assertEquals(aNonShuffledDeck.getCards(), Constants.CARDS);
        List<String> shuffledOrder = complexShuffler.shuffleCards(Arrays.asList(aNonShuffledDeck.getCards().split(",")));
        Assert.assertNotEquals(aNonShuffledDeck.getCards(), shuffledOrder.toString());
	}
}
