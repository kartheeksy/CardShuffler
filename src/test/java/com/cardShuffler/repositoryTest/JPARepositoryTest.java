package com.cardShuffler.repositoryTest;

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
import com.cardShuffler.domain.Deck;
import com.cardShuffler.repository.DeckRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

/**
 * This Test case is to test the JPARepository for custom written methods
 * @author KARTHEEK YS
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
public class JPARepositoryTest {

	@Autowired
	private DeckRepository theDeckRepository;
	
	@Test
	public void contextLoads() {
	}
	
	/**
	 * Tests the FindDeckByName method .
	 */
	@Test
    public void findDeckByname() {
		Deck a = new Deck();
		a.setDeckName("testDeck");
		theDeckRepository.save(a);
        Deck aReturnedDeck = theDeckRepository.findByName("testDeck");
        Assert.assertEquals(aReturnedDeck.getDeckName(), "testDeck");
    }
}
