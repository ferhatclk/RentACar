package com.kodlamaio.rentAcar.core.services.findex;

import org.springframework.stereotype.Service;

@Service
public class FindexAdapter implements FindexCheckService{

	@Override
	public int checkFindexScore(String nationalIdentity) {
		FindexScore findexScore = new FindexScore();
		int result = findexScore.findexScore(nationalIdentity);
		return result;
	}

}
