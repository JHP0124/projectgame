package kr.io.playdata.service;

import kr.io.playdata.domain.User;

public interface UserSerivce {

	User getUser(User user); // 유저가 있는지 확인하는 메서드
	
	boolean insertUser(User user); // 유저를 등록하는 메서드
	
	boolean updateuser(User user);
	
	boolean deleteuser(User user);
	
	/*void updatepassword(User user);
	void updatemail(User user);
	void updatename(User user);
	void updatenickname(User user);
	
	
	
	*/
	
	
}
