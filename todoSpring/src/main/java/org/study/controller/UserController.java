package org.study.controller;

import java.io.UnsupportedEncodingException;

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

@Controller
public class UserController extends ControllerUtil {
	
	@Autowired
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	String id;
	
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,Model model) {
		if(session.getAttribute("userid")==null) {
			logger.error(" 로그아웃 실패-로그인 되어있지 않음");
			model.addAttribute("error", "먼저 로그인을 하세요");
			return "login";
		}
		session.removeAttribute("userid");
		session.setAttribute("msg", "로그아웃 되었습니다.");
		logger.info("로그아웃 성공");
		return "redirect:/";
	}
	
	
	
	
	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public String userEdit(HttpSession session,Model model) {
		readMsg(session,model);
		id=getid(session);
		if(!service.isValidUser(id)) {
			logger.error(" 프로필 수정 시도 - 존재하지 않는 아이디:"+id);
			session.setAttribute("error", "존재하지 않는 아이디입니다.");
			return "redirect:/";
		}
		model.addAttribute("user",service.getUser(id));
		return "user/UserEdit";
	}
	
	
	
	
	@RequestMapping(value = "/user/edit/do", method = RequestMethod.POST)
	public String doUserEdit(UserVO user,HttpSession session,Model model) {
		id=getid(session);
		user.setId(id);
		if(service.passwordCheck(id, user.getPassword())) {//비밀번호 일치 확인
			if(user.getNewPW().length()>0) {//새 패스워드를 입력했으면
				if(user.getNewPW().equals(user.getNewPWc())) {//새 비밀번호 확인과 일치한지 검증
					user.setPassword(user.getNewPW());
				}else {
					logger.error(" 프로필 수정 시도 - 새 패스워드와 패스워드 확인 불일치:"+id);
					session.setAttribute("error", "새 패스워드와 패스워드 확인이 일치하지 않습니다.");
					return "redirect:/user/edit";
				}
			}
		}else {
			logger.error(" 프로필 수정 시도 - 비밀번호 불일치:"+id);
			session.setAttribute("error", "패스워드가 일치하지 않습니다.");
			return "redirect:/user/edit";
		}
		
		service.pwHash(user);//비밀번호 해싱
		
		if(!service.Validation(user)) {//특수문자 유효성 검사
			logger.error(" 프로필 수정 시도 - 사용할 수 없는 문자 포함:"+id);
			session.setAttribute("error", "\",|,<,>,{,}는 입력하실 수 없습니다.");
			return "redirect:/user/edit";
		}
		
		if(!service.updateUser(user)) {//업데이트
			logger.warn("프로필 수정 시도 - 정보를 수정하지 못함");
			session.setAttribute("error", "정보를 수정하지 못했습니다. 문의 부탁드립니다.");
			return "redirect:/user/edit";
		}
		logger.info("프로필 수정 성공:"+user.getId());
		session.setAttribute("msg", "정보를 수정하였습니다.");
		return "redirect:/user/Profile";
	}
	
	
	
	
	@RequestMapping(value = "/user/del", method = RequestMethod.GET)
	public String userDel(HttpSession session,Model model) {
		readMsg(session,model);
		return "user/UserDel";
	}
	
	
	
	
	@RequestMapping(value = "/user/del/do", method = RequestMethod.POST)
	public String doUserDel(@RequestParam("password") String password, HttpSession session) {
		id=getid(session);
		if(!service.passwordCheck(id, password)) {
			logger.error("탈퇴 - 비밀번호 불일치");
			session.setAttribute("error", "비밀번호가 일치하지 않습니다.");
			return "redirect:/user/del";
		}
		
		if(!service.deleteUser(id)) {
			logger.warn("탈퇴 - 탈퇴 실패");
			session.setAttribute("error", "탈퇴에 실패했습니다 .문의 부탁드립니다.");
			return "redirect:/user/del";
		}
		logger.info("탈퇴 성공:"+id);
		session.removeAttribute("userid");
		session.setAttribute("msg", "탈퇴되었습니다.");
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
			logger.error(" 로그인 시도 - 존재하지 않는 아이디:"+user.getId());
			model.addAttribute("error", "존재하지 않는 아이디입니다.");
			return "login";
		}
		if(!service.passwordCheck(user.getId(),user.getPassword())) {
			logger.error(" 로그인 시도 - 패스워드 불일치:"+user.getId());
			model.addAttribute("error", "패스워드가 일치하지 않습니다.");
			return "login";
		}
//		if(orgPath.equals("")) {
//		}
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
		return "user/welcome";
	}
	
	
	
	
	@RequestMapping(value = "/SignUp", method = RequestMethod.POST)
	public String dologin(UserVO user,Model model) {
		if(!service.Validation(user)) {
			logger.error(" try SignUp but Not valid:"+user.getId());
			model.addAttribute("error", "\",|,<,>,{,}는 입력하실 수 없습니다.");
			return "login";
		}
		if(service.isValidUser(user.getId())) {
			logger.error(" try SignUp but Already existing ID:"+user.getId());
			model.addAttribute("error", "이미 존재하는 아이디입니다.");
			return "login";
		}
		if(!service.addUser(user)) {
			logger.error(" try SignUp but failed:"+user.getId());
			model.addAttribute("error", "회원가입에 실패했습니다.");
			return "login";
		}else {
			logger.info(user.getId()+"signUp Success");
			model.addAttribute("name",user.getName());
		}
		return "signUpOk";
	}
	
	
	
	
	@RequestMapping(value = "/user/Profile", method = RequestMethod.GET)
	public String login(HttpSession session,Model model) {
		readMsg(session,model);
		logger.info("go Profile");
		id=getid(session);
		if(!service.isValidUser(id)) {//세션에 저장된 아이디가 존재하지 않을 경우
			logger.error(" 프로필 접근 실패:"+id);
			return"index";
		}
		model.addAttribute("user",service.getUser(id));
		model.addAttribute("TodoNum",service.getTodoNum(id));
		model.addAttribute("DoneNum",service.getDoneNum(id));
		return "user/Profile";
	}

}
