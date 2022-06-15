package com.kodlamaio.rentAcar.core.services.findex;

import java.util.Random;

public class FindexScore {
	
	public int findexScore(String nationalIdantity) {
		Random random = new Random();
		int score = random.nextInt(1900) ;
		System.out.println(score);
		return score;
	}
	
	
}
