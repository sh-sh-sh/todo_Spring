package org.study.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.study.model.UserVO;
import org.study.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	public void logout() {
	}
	
	@RequestMapping(value = "/logout/go", method = RequestMethod.GET)
	public String Afterlogout(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg", "로그아웃 되었습니다.");
		logger.info("로그아웃 성공");
		return "redirect:/";
	}
	
	
	
	
	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public String userEdit(RedirectAttributes rttr,Model model,Principal prin) {
		
		String id=prin.getName();
		if(!service.isValidUser(id)) {
			logger.error(" 프로필 수정 시도 - 존재하지 않는 아이디:"+id);
			rttr.addFlashAttribute("error", "존재하지 않는 아이디입니다.");
			return "redirect:/";
		}
		model.addAttribute("user",service.getUser(id));
		return "user/UserEdit";
	}
	
	
	
	
	@RequestMapping(value = "/user/edit/do", method = RequestMethod.POST)
	public String doUserEdit(UserVO user,RedirectAttributes rttr,Model model,Principal prin) {
		String id=prin.getName();
		user.setId(id);
		if(service.passwordCheck(id, user.getPassword())) {//비밀번호 일치 확인
			if(user.getNewPW().length()>0) {//새 패스워드를 입력했으면
				if(user.getNewPW().equals(user.getNewPWc())) {//새 비밀번호 확인과 일치한지 검증
					user.setPassword(user.getNewPW());
				}else {
					logger.error(" 프로필 수정 시도 - 새 패스워드와 패스워드 확인 불일치:"+id);
					rttr.addFlashAttribute("error", "새 패스워드와 패스워드 확인이 일치하지 않습니다.");
					return "redirect:/user/edit";
				}
			}
		}else {
			logger.error(" 프로필 수정 시도 - 비밀번호 불일치:"+id);
			rttr.addFlashAttribute("error", "패스워드가 일치하지 않습니다.");
			return "redirect:/user/edit";
		}
		
		service.pwHash(user);//비밀번호 해싱
		
		if(!service.Validation(user)) {//특수문자 유효성 검사
			logger.error(" 프로필 수정 시도 - 사용할 수 없는 문자 포함:"+id);
			rttr.addFlashAttribute("error", "\",|,<,>,{,}는 입력하실 수 없습니다.");
			return "redirect:/user/edit";
		}
		
		if(!service.updateUser(user)) {//업데이트
			logger.warn("프로필 수정 시도 - 정보를 수정하지 못함:"+id);
			rttr.addFlashAttribute("error", "정보를 수정하지 못했습니다. 문의 부탁드립니다.");
			return "redirect:/user/edit";
		}
		logger.info("프로필 수정 성공:"+id);
		rttr.addFlashAttribute("msg", "정보를 수정하였습니다.");
		return "redirect:/user/Profile";
	}
	
	
	
	
	@RequestMapping(value = "/user/del", method = RequestMethod.GET)
	public String userDel() {
		return "user/UserDel";
	}
	
	
	
	
	@RequestMapping(value = "/user/del/do", method = RequestMethod.POST)
	public String doUserDel(@RequestParam("password") String password, RedirectAttributes rttr,Principal prin) {
		String id=prin.getName();
		if(!service.passwordCheck(id, password)) {
			logger.error("탈퇴 - 비밀번호 불일치");
			rttr.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
			return "redirect:/user/del";
		}
		
		if(!service.deleteUser(id)) {
			logger.warn("탈퇴 - 탈퇴 실패");
			rttr.addFlashAttribute("error", "탈퇴에 실패했습니다 .문의 부탁드립니다.");
			return "redirect:/user/del";
		}
		logger.info("탈퇴 성공:"+id);
		rttr.addFlashAttribute("msg", "탈퇴되었습니다.");
		return "redirect:/";
	}
	

	@RequestMapping("/tryLogin")
	public void tryLogin() {
		logger.info("hello login");
	}
	
	
	@RequestMapping(value = "/user/welcome", method = RequestMethod.GET)
	public String welcome(RedirectAttributes rttr, Principal prin) {
		String id = prin.getName();
		rttr.addFlashAttribute("name",service.getUser(id).getName());
		return "user/welcome";
	}
	
	
	
	
	@RequestMapping(value = "/SignUp", method = RequestMethod.POST)
	public String signUp(@Valid UserVO user,BindingResult result,Model model,RedirectAttributes rttr) {
		
		if(result.hasErrors()) {
			List<ObjectError> list=result.getAllErrors();
			for(ObjectError error:list) {
				logger.error(" 가입 시도 - 이메일 형식 불일치:"+user.getId());
				rttr.addFlashAttribute("error", error.getDefaultMessage());
			}
			return "redirect:/login";
	      }
		
		if(!service.Validation(user)) {
			logger.error("가입 시도 - 유효하지 않는 문자:"+user.getId());
			rttr.addFlashAttribute("error", "\",|,<,>,{,}는 입력하실 수 없습니다.");
			return "redirect:/login";
		}
		if(service.isValidUser(user.getId())) {
			logger.error("가입 시도 - 이미 존재하는 아이디:"+user.getId());
			rttr.addFlashAttribute("error", "이미 존재하는 아이디입니다.");
			return "redirect:/login";
		}
		if(!service.addUser(user)) {
			logger.warn("가입 시도 - 실패:"+user.getId());
			rttr.addFlashAttribute("error", "회원가입에 실패했습니다.");
			return "redirect:/login";
		}else {
			logger.info(user.getId()+"signUp Success");
			model.addAttribute("name",user.getName());
		}
		return "signUpOk";
	}
	
	
	
	
	@RequestMapping(value = "/user/Profile", method = RequestMethod.GET)
	public String profile(Model model,Principal prin) {
//		logger.info("go Profile");
		String id=prin.getName();
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
