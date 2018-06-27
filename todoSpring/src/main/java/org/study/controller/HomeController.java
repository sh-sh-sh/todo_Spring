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
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale,HttpSession session,Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		String msg=(String)session.getAttribute("msg");
		String error=(String)session.getAttribute("error");
		if(msg!=null) {
			model.addAttribute("msg",msg);
			session.removeAttribute("msg");
		}
		if(error!=null) {
			model.addAttribute("error",error);
			session.removeAttribute("error");
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model,HttpServletRequest req) {
		logger.info("go login");
		Cookie[] cookie= req.getCookies();
		String id=null;
		if(cookie!=null){
			for(int i=0;i<cookie.length;i++){
				if(cookie[i].getName().equals("id")){
					id=cookie[i].getValue();
					model.addAttribute("id", id);
				}
			}
		}
		return "login";
	}
	
}
