package com.cardShuffler.strategy;

import java.util.List;

/**
 * This is a Strategy interface 
 * @author KARTHEEK YS
 *
 */
public interface ShuffleStrategy {
	
	/**
	 * Has the custom implementation for the shuffle type .
	 * @param theCurrentOrder
	 * @return List<String>
	 */
	public List<String> shuffleCards(List<String> theCurrentOrder);
}
