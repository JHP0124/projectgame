package kr.io.playdata.service;

import java.util.Optional;

import javax.transaction.Transactional;

import kr.io.playdata.dao.UserRepository;
import kr.io.playdata.domain.User;

public class UserSerivceImpl implements UserSerivce {
	
	private UserRepository userrepo;

	public User getUser(User user) {  //// 유저가 있는지 확인하는 메서드
		Optional<User> finduser = userrepo.findById(user.getId());
		if(finduser.isPresent()) {
			return finduser.get();
		}else {
		return null;
		}
	}
	
	@Transactional
	public boolean insertUser(User user) { // 유저를 등록하는 메서드
		
		boolean result =false;
		
		if(getUser(user)==null) {
			userrepo.save(user);
			result = true;
		}
		return result;
	}
	
	
	public boolean updateuser(User user) {
		boolean result =false;
		User finduser = userrepo.findById(user.getId()).get();
			
		if(finduser !=null) {
			finduser.setId(user.getId());
			finduser.setPw(user.getPw());
			finduser.setMail(user.getMail());
			finduser.setName(user.getName());
			finduser.setNickname(user.getNickname());
	
			userrepo.save(finduser);
			result = true;
			}
			
		return result;
	}
	
	public boolean deleteuser(User user) {
		boolean result =false;
		User finduser = userrepo.findById(user.getId()).get();
		if(finduser !=null) {
			userrepo.deleteById(user.getId());
			result = true;
		}
		
		return result;
	}
	
	

	
	
	/*void updatepassword(User user);
	void updatemail(User user);
	void updatename(User user);
	void updatenickname(User user);
	void deleteuser(User user);*/
	
	
}
