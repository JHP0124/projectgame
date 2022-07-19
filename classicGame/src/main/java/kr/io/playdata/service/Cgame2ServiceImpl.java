package kr.io.playdata.service;

import kr.io.playdata.dao.Cgame2Repository;
import kr.io.playdata.domain.Cgame2;

public class Cgame2ServiceImpl implements Cgame2Service{
	
	
	
	private Cgame2Repository repo;


	public boolean insertCgame2(Cgame2 cgame2){
	
		boolean result =false;
		
		if(cgame2!=null) {
			repo.save(cgame2);
			result = true;
		}
		return result;

	}

}
