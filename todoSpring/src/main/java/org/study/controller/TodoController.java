package org.study.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.service.TodoService;

@Controller
@RequestMapping("/todo/*")
public class TodoController extends ControllerUtil {
	
	@Autowired
	private TodoService service;
	
	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
	
	String id;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String logout(HttpSession session,Model model,
			@RequestParam(value="view",required=false) String view,
			@RequestParam(value="page",required=false) int page) {
		System.out.println("서비스테스트2:"+service);
		System.out.println("뷰:"+view);
		System.out.println("페이지:"+page);
		if(view==null) {
			view="all";
		}
		
		id=getid(session);
		int maxpage=service.maxpage(id, view);
		System.out.println(maxpage);
		model.addAttribute("doneRate",service.doneRate(id, view));
		model.addAttribute("maxpage",maxpage);
		if(view.equals("all")&&maxpage==0) {
			session.setAttribute("msg", "등록한 할 일이 없습니다. Todo를 등록하세요!");
			return "redirect:/todo/add";
		}
		
		if(page<1||page>service.maxpage(id,view)) {
			session.setAttribute("error", "존재하지 않는 페이지에 접근해 1페이지로 이동되었습니다.");
			return "redirect:/todo/list?page=1&view=all";
		}
		
		switch(view) {
		case "all":
			model.addAttribute("title", "TODOLIST");
			break;
		case "today":
			model.addAttribute("title", "TODAY'S TODOLIST");
			if(maxpage==0) {
				model.addAttribute("msg", "오늘의 할 일이 없습니다!");
			}
			break;
		case "week":
			model.addAttribute("title", "WEEKLY TODOLIST");
			if(maxpage==0) {
				model.addAttribute("msg", "이번주에 해야 할 일이 없습니다!");
			}
			break;
		case "month":
			model.addAttribute("title", "MONTHLY TODOLIST");
			if(maxpage==0) {
				model.addAttribute("msg", "이번달에 해야 할 일이 없습니다!");
			}
			break;
		case "done":
			model.addAttribute("title", "DONE LIST");
			if(maxpage==0) {
				model.addAttribute("error", "완료한 할 일이 없습니다.");
			}
			break;
		case "undone":
			model.addAttribute("title", "UNDONE TODOLIST");
			if(maxpage==0) {
				model.addAttribute("msg", "완료하지 않은 할 일이 없습니다!");
			}
			break;
		}
		
		model.addAttribute("list",service.getTodoList(id, page, view));
		model.addAttribute("page",page);
		
		return "todo/TodoList";
	}
	
}
