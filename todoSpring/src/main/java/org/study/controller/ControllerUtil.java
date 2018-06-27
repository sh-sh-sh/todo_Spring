package org.study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

public class ControllerUtil {
	
	String getid(HttpSession session) {
		return (String)session.getAttribute("userid");
	}
	
	void readMsg(HttpSession session,Model model) {
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
	}

}
