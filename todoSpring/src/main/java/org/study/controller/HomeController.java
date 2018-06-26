package org.study.controller;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.model.UserVO;
import org.study.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.info("go login");
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,Model model) {
		if(session.getAttribute("userid")==null) {
			logger.info("[error]로그아웃 실패-로그인되어있지 않음");
			model.addAttribute("error", "먼저 로그인을 하세요");
			return "login";
		}
		session.invalidate();
		logger.info("로그아웃 성공");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/tryLogin", method = RequestMethod.POST)
	public String tryLogin(UserVO user,
			@RequestParam(value="remember",required=false) String remember,
			@RequestParam(value="orgPath",required=false) String orgPath,
			Model model,HttpServletResponse response,HttpSession session) 
					throws UnsupportedEncodingException {
		logger.info("try Login:"+user.getId());
		if(!service.isValidUser(user.getId())) {
			logger.info("[error]로그인 시도 - 존재하지 않는 아이디:"+user.getId());
			model.addAttribute("error", "존재하지 않는 아이디입니다.");
			return "login";
		}
		if(!service.passwordCheck(user.getId(),user.getPassword())) {
			logger.info("[error]로그인 시도 - 패스워드 불일치:"+user.getId());
			model.addAttribute("error", "패스워드가 일치하지 않습니다.");
			return "login";
		}
		System.out.println("오알지패스:"+orgPath);
		if(orgPath.equals("")) {
			System.out.println("아무것도없다");
		}
		session.setAttribute("userid", user.getId());
		model.addAttribute("name", service.getUser(user.getId()).getName());
		
		Cookie cookie= null;
		if(remember!=null) {//remember에 체크했을 경우 쿠키에 아이디 저장
			cookie=new Cookie("id",java.net.URLEncoder.encode(user.getId(), "UTF-8"));
			cookie.setMaxAge(60*60*24*30);//유효기간 한달(초*분*시*일)
			response.addCookie(cookie);
		}else {//체크하지 않았을 경우 쿠키 무효화
			cookie=new Cookie("id",null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		logger.info("로그인 성공:"+user.getId());
		return "welcome";
	}
	
	@RequestMapping(value = "/SignUp", method = RequestMethod.POST)
	public String dologin(UserVO user,Model model) {
		if(!service.Validation(user)) {
			logger.info("[error]try SignUp but Not valid:"+user.getId());
			model.addAttribute("error", "\",|,<,>,{,}는 입력하실 수 없습니다.");
			return "login";
		}
		if(service.isValidUser(user.getId())) {
			logger.info("[error]try SignUp but Already existing ID:"+user.getId());
			model.addAttribute("error", "이미 존재하는 아이디입니다.");
			return "login";
		}
		if(!service.addUser(user)) {
			logger.info("[error]try SignUp but failed:"+user.getId());
			model.addAttribute("error", "회원가입에 실패했습니다.");
			return "login";
		}else {
			logger.info(user.getId()+"signUp Success");
			model.addAttribute("name",user.getName());
		}
		return "signUpOk";
	}
	
	
}
