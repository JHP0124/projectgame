package kr.io.playdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.io.playdata.domain.User;
import kr.io.playdata.service.UserSerivce;

@SessionAttributes("user")
//@Controller
@RestController
public class UserController {
	
	@Autowired
	private UserSerivce userSerivce;
	
	@PostMapping("/signup")
	public String signup(User user) {
		boolean result = userSerivce.insertUser(user);
		if(result) {
			System.out.println("저장 성공");
		}else {
			System.out.println("저장 실패");			
		}
		
		
		return "forward:회원가입 웰컴페이지or게임페이지"; // 해당 페이지는 WEB-INF/board/회원가입 웰컴페이지or게임페이지.jsp로 이동함.
	}
	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
	@PostMapping("/login")
	public String login(User user, Model model) {
		User finduser=userSerivce.getUser(user);
		
		if(finduser!=null && finduser.getPw().equals(finduser.getPw())) {
			model.addAttribute("user", finduser);
			System.out.println(finduser.getId()+"님의 로그인 성공");
			return "forward:게임리스트 화면"; // WEB-INF/board/게임리스트화면.jsp로 이동함.
		} else {
			System.out.println("로그인 실패");
			return "redirect: 로그인 화면.html";  // 로그인 화면.html로 이동시킴
		}
	}
	
	@PostMapping("/updateuser")
	public String updateuser(User user, Model model) {
		if (user.getId() == null) { // 로그인을 안해서  멤버의 id 값을 없을 수 없으면 
			System.out.println("로그인 필요");
			return "redirect:로그인"; // 로그인.html로 이동 시킴.
		}
		boolean finduser=userSerivce.updateuser(user);
		System.out.println(user.getId()+"의 유저 정보 업데이트 성공");
		return "forward:로그인 후 화면"; // 포워드로 WEB-INF/board/로그인 후 화면.jsp로 이동
	}
	
	@DeleteMapping("/deleteuser")
	public  String deleteuser(User user, Model model) {
		if (user.getId() == null) { // 로그인을 안해서  멤버의 id 값을 없을 수 없으면 
			System.out.println("로그인 필요");
			return "redirect:로그인"; // 로그인.html로 이동 시킴.
		}
		boolean del =userSerivce.deleteuser(user);
		if(del) {
			System.out.println(user.getId()+"님의 유저정보 삭제완료");
			return "redirect:로그인"; // 로그인.html로 이동 시킴.
		}else {
			System.out.println(user.getId()+"님의 삭제 실패");
			return "forward:로그인 후 화면"; // 포워드로 WEB-INF/board/로그인 후 화면.jsp로 이동
		}	
	}	
	
	
	
	@GetMapping("/logout") //주소창에선 /localhost/logout
	public String logout(SessionStatus status) {
		status.setComplete(); //세션 상태를 종료 시킴
		return "redirect: 로그인 화면.html";  // 로그인 화면.html로 이동시킴
	}
	
	
	
	

}
