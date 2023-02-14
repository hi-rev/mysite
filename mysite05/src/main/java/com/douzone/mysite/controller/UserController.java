package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	// 1. 회원 가입 페이지 이동
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	// 2. 회원 가입
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			// model.addAttribute("userVo", vo);
			model.addAllAttributes(result.getModel());
			return "user/join";
		}

		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	// 3. 회원 가입 성공 페이지 이동
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	// 4. 로그인 페이지 이동
	// -> 로그인 처리는 user/auth로 하도록 함 (spring-servlet에서 Handler Interceptor 등록)
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
	
	// 5. 회원 정보 수정 페이지 이동
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		UserVo userVo = userService.getUser(authUser.getNo());
		
		model.addAttribute("userVo", userVo);
		return "user/update";
	}
	
	// 6. 회원 정보 수정
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo vo) {
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		
		authUser.setName(vo.getName());
		
		return "redirect:/user/update";
	}
	
	@RequestMapping("/auth")
	public void auth() {	
	}
	
	@RequestMapping("/logout")
	public void logout() {	
	}
	
}