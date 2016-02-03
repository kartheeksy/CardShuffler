package com.cardShuffler.strategy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Complex shuffler class which has the logic for hand shuffling .
 * @author KARTHEEK YS
 *
 */
@Service
public class ComplexShuffler implements ShuffleStrategy {
	
	/**
	 * This implements the shuffle method as per the strategy pattern .
	 * It splits the Cards into two halfs and does a hand shuffle . 
	 */
	@Override
	public List<String> shuffleCards(List<String> theCurrentOrder) {
		List<String> theFirstHalfDeck = theCurrentOrder.subList(0, theCurrentOrder.size()/2);
		List<String> theSecondHalfDeck = theCurrentOrder.subList(theCurrentOrder.size()/2, theCurrentOrder.size());
		List<String> theShuffledList = new ArrayList<String>();
		for(int i=0 ; i < theCurrentOrder.size()/2 ; i++){
			if(theFirstHalfDeck.get(i) != null){
				theShuffledList.add(theFirstHalfDeck.get(i));
			}
			if(theSecondHalfDeck.get(i) != null){
				theShuffledList.add(theSecondHalfDeck.get(i));
			}
		}
		return theShuffledList;
	}

}
