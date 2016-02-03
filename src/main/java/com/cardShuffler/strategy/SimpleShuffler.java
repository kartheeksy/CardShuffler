package com.cardShuffler.strategy;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * This class has the logic for simple shuffling .
 * @author KARTHEEK YS
 *
 */
@Service
public class SimpleShuffler implements ShuffleStrategy {
	
	/**
	 * Simple shuffling 
	 */
	@Override
	public List<String> shuffleCards(List<String> theCurrentOrder) {
		Collections.shuffle(theCurrentOrder);
		return theCurrentOrder;
	}

}
