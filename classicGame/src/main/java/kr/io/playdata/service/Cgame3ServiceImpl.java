package kr.io.playdata.service;

import kr.io.playdata.dao.Cgame3Repository;
import kr.io.playdata.domain.Cgame3;

public class Cgame3ServiceImpl implements Cgame3Service{
	
	private Cgame3Repository repo;


	public boolean insertCgame3(Cgame3 cgame3){
	
		boolean result =false;
		
		if(cgame3!=null) {
			repo.save(cgame3);
			result = true;
		}
		return result;

	}

}
