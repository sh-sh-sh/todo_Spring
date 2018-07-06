package org.study.controller;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends ControllerUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale,HttpSession session,Model model) {
		logger.info("새 접속 - 접속 국가 : {}.", locale);
		
		readMsg(session, model);
		
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session,Model model,HttpServletRequest req) {
		readMsg(session, model);
		return "login";
	}
	
	@RequestMapping(value = "/login/error", method = RequestMethod.GET)
	public String loginError(HttpSession session,Model model,HttpServletRequest req) {
		readMsg(session, model);
		
		session.setAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
		
		return "redirect:/login";
	}
	
}
