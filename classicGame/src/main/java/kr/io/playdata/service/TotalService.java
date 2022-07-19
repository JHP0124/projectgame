package kr.io.playdata.service;

import kr.io.playdata.domain.Total;

public interface TotalService {
	
	void updateTotal(Total total); // 기존 점수와 새로운  점수를 비교해서 높은 점수만 남는 메서드

}
