package kr.io.playdata.service;

import java.util.Optional;

import kr.io.playdata.dao.Cgame1Repository;
import kr.io.playdata.dao.UserRepository;
import kr.io.playdata.domain.Cgame1;
import kr.io.playdata.domain.User;

public class Cgame1ServiceImpl implements Cgame1Service{
	
	private Cgame1Repository repo;


	public boolean insertCgame1(Cgame1 cgame1){
	
		boolean result =false;
		
		if(cgame1!=null) {
			repo.save(cgame1);
			result = true;
		}
		return result;

	}

}
