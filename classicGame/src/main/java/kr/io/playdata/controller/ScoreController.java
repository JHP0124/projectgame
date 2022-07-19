package kr.io.playdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.io.playdata.domain.Cgame1;
import kr.io.playdata.domain.Cgame2;
import kr.io.playdata.domain.Cgame3;
import kr.io.playdata.service.Cgame1Service;
import kr.io.playdata.service.Cgame2Service;
import kr.io.playdata.service.Cgame3Service;

@RestController
public class ScoreController {

	@Autowired
	private Cgame1Service cgame1service;

	@Autowired
	private Cgame2Service cgame2service;

	@Autowired
	private Cgame3Service cgame3service;

	@PostMapping("/insertCgame1")
	public String insertCgame1(Cgame1 cgame1) {
		if(cgame1.getNickname() == null) {
			return "redirect:showerror";
		}
		
		boolean result = cgame1service.insertCgame1(cgame1);
		if(result) {
			System.out.println("---cgame1insert 성공---");
			return "redirect:로그인"; // 로그인.html로 이동 시킴.
		}else {
			System.out.println("---cgame1insert 실패---");
			return "forward:로그인 후 화면"; // 포워드로 WEB-INF/board/로그인 후 화면.jsp로 이동
		}

	}

	@PostMapping("/insertCgame2")
	public String insertCgame2(Cgame2 cgame2) {
		if(cgame2.getNickname() == null) {
			return "redirect:showerror";
		}
		
		boolean result = cgame2service.insertCgame2(cgame2);
		if(result) {
			System.out.println("---cgame2insert 성공---");
			return "redirect:로그인"; // 로그인.html로 이동 시킴.
		}else {
			System.out.println("---cgame2insert 실패---");
			return "forward:로그인 후 화면"; // 포워드로 WEB-INF/board/로그인 후 화면.jsp로 이동
		}

	}


	@PostMapping("/insertCgame3")
	public String insertCgame3(Cgame3 cgame3) {
		if(cgame3.getNickname() == null) {
			return "redirect:showerror";
		}
		
		boolean result = cgame3service.insertCgame3(cgame3);
		if(result) {
			System.out.println("---cgame3insert 성공---");
			return "redirect:로그인"; // 로그인.html로 이동 시킴.
		}else {
			System.out.println("---cgame3insert 실패---");
			return "forward:로그인 후 화면"; // 포워드로 WEB-INF/board/로그인 후 화면.jsp로 이동
		}


	}


}
