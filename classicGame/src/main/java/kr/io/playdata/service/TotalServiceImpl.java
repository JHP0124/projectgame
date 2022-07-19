package kr.io.playdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.io.playdata.dao.TotalRepository;
import kr.io.playdata.domain.Total;

@Service
public class TotalServiceImpl implements TotalService {
	
	@Autowired
	private TotalRepository totalrepo ;

	public void updateTotal(Total total) {
		Total befo = totalrepo.findById(total.getNickname()).get();
		
		if(befo.getNickname()!=null&& befo.getScore1() < total.getScore1()) {
			befo.setScore1(total.getScore1());	
		}else if (befo.getNickname()!=null&& befo.getScore2() < total.getScore2()) {
			befo.setScore2(total.getScore2());	
		}else if (befo.getNickname()!=null&& befo.getScore3() < total.getScore3()) {
			befo.setScore3(total.getScore3());	
		}else {
			System.out.println( befo.getNickname()+"님에 대한 어떠한 값 갱신이 없었습니다.");
		}
		
		totalrepo.save(befo);
		
	}
	
	
}
