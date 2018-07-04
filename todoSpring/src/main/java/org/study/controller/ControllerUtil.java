package org.study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

//컨트롤러에서 공통적으로 사용할 기능
public class ControllerUtil {
	
	//세션에 저장된 아이디를 리턴해준다.
	String getid(HttpSession session) {
		return (String)session.getAttribute("userid");
	}
	
	//세션에 저장된 메세지를 모델에 쓰고 세션에서 지워준다.
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
