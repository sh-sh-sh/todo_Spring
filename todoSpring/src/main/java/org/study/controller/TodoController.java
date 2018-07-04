package org.study.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.study.model.TodoVO;
import org.study.service.TodoService;

@Controller
@RequestMapping("/todo/*")
public class TodoController extends ControllerUtil {
	
	@Autowired
	private TodoService service;
	
	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
	
	String id;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(HttpSession session,Model model,
			@RequestParam(value="view",required=false) String view,
			@RequestParam(value="page",required=false) int page) {
		readMsg(session, model);
		if(view==null) {
			view="all";
		}
		
		id=getid(session);
		int maxpage=service.maxpage(id, view);
		model.addAttribute("doneRate",service.doneRate(id, view));
		model.addAttribute("maxpage",maxpage);
		if(view.equals("all")&&maxpage==0) {
			logger.error("리스트 접근 - 등록한 할 일 없음:"+id);
			session.setAttribute("msg", "등록한 할 일이 없습니다. Todo를 등록하세요!");
			return "redirect:/todo/add";
		}
		
		if(page<1||page>service.maxpage(id,view)) {
			logger.error("리스트 접근 - : 존재하지 않는 페이지 접근"+id);
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
		logger.info("리스트 접근 성공 :"+id);
		model.addAttribute("list",service.getTodoList(id, page, view));
		model.addAttribute("page",page);
		
		return "todo/todoList";
	}
	
	
	
	
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(HttpSession session,Model model,
			@RequestParam(value="idx",required=true) int idx) {
		readMsg(session, model);
		id=getid(session);
		if(!service.isCorrectUser(id, idx)) {
			logger.error("Todo 접근  - 올바르지 않은 접근:"+id);
			session.setAttribute("error", "올바르지 않은 접근입니다.");
			return "redirect:/todo/list?page=1&view=all";
		}
		
		model.addAttribute("todo",service.getTodo(idx));
		
		return "todo/todoView";
	}
	
	
	
	
	@RequestMapping(value = "done", method = RequestMethod.GET)
	public String done(HttpSession session,Model model,
			@RequestParam(value="idx",required=true) int idx,
			@RequestParam(value="done",required=true) boolean done,
			HttpServletRequest req) {
		
		id=getid(session);
		
		if(!service.isCorrectUser(id, idx)) {
			logger.error("Todo 완료 - 올바르지 않은 접근:"+idx);
			session.setAttribute("error", "올바르지 않은 접근입니다.");
			return "redirect:/todo/list?page=1&view=all";
		}

		if(!service.setDone(idx, done)) {
			logger.warn("Todo 완료 - : 실패"+idx);
			session.setAttribute("error", "상태 변경에 실패했습니다. 문의 부탁드립니다.");
			return "redirect:/todo/list?page=1&view=all";
		}
		logger.info("Todo 완료 - 성공:"+idx);
		String orgPath=(String)req.getHeader("Referer");
		session.setAttribute("msg", "완료 상태가 변경되었습니다.");
		
		return "redirect:"+orgPath;
	}
	
	
	
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(HttpSession session,Model model,
			@RequestParam(value="idx",required=true) int idx) {
		
		readMsg(session,model);
		id=getid(session);
		
		if(!service.isCorrectUser(id, idx)) {
			logger.error("Todo 수정 - 올바르지 않은 접근:"+id);
			session.setAttribute("error", "올바르지 않은 접근입니다.");
			return "redirect:/todo/list?page=1&view=all";
		}
		logger.error("Todo 수정 - 성공:"+id);
		model.addAttribute("todo",service.getTodo(idx));
		
		return "todo/todoEdit";
	}
	
	
	
	
	@RequestMapping(value = "edit/do", method = RequestMethod.POST)
	public String doEdit(HttpSession session,Model model,@Valid TodoVO todo,BindingResult result) {
		
		if(result.hasErrors()) {
			List<ObjectError> list=result.getAllErrors();
			for(ObjectError error:list) {
				logger.error("Todo 수정 2  - 유효하지 않은 값:"+todo.getIdx());
				session.setAttribute("error", error.getDefaultMessage());
			}
			return "redirect:/todo/list?page=1&view=all";
	      }
		
		id=getid(session);
		
		if(!service.isCorrectUser(id, todo.getIdx())) {
			logger.error("Todo 수정 2 - 권한 없음:"+id+","+todo.getIdx());
			session.setAttribute("error", "해당 할일을 수정할 권한이 없습니다.");
			return "redirect:/todo/list?page=1&view=all";
		}
		
		if(!service.Validation(todo)) {
			logger.error("Todo 수정 2 - 사용할 수 없는 문자 포함:"+todo.getIdx());
			session.setAttribute("error", "\",|,<,>,{,}는 입력하실 수 없습니다.");
			return "redirect:/todo/edit?idx="+todo.getIdx();
		}
		
		todo.setStart_date(todo.getStart_date()+":00");
		todo.setTarget_date(todo.getTarget_date()+":00");
		
		if(!service.updateTodo(todo)) {
			logger.warn("Todo 수정 2 - 투두를 수정하지 못함:"+todo.getIdx());
			session.setAttribute("error", "할일을 수정하지 못했습니다. 문의 부탁드립니다.");
			return "redirect:/todo/edit?idx="+todo.getIdx();
		}
		
		logger.info("Todo 수정 성공:"+todo.getIdx());
		session.setAttribute("msg", "할일을 수정하였습니다.");
		
		return "redirect:/todo/view?idx="+todo.getIdx();
	}
	
	
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(HttpSession session,Model model) {
		readMsg(session,model);
		return "todo/todoAdd";
	}
	
	
	
	@RequestMapping(value = "add/do", method = RequestMethod.POST)
	public String doAdd(HttpSession session,Model model,TodoVO todo) {
		todo.setUser_id(getid(session));
		
		if(!service.Validation(todo)) {
			logger.error("Todo 추가 - 사용할 수 없는 문자 포함:"+todo.getUser_id());
			session.setAttribute("error", "\",|,<,>,{,}는 입력하실 수 없습니다.");
			return "redirect:/todo/add";
		}
		
		todo.setStart_date(todo.getStart_date()+":00");
		todo.setTarget_date(todo.getTarget_date()+":00");
		
		if(!service.addTodo(todo)) {
			logger.warn("Todo 추가 - 투두를 추가하지 못함:"+todo.getUser_id());
			session.setAttribute("error", "할일을 수정하지 못했습니다. 문의 부탁드립니다.");
			return "redirect:/todo/add";
		}
		
		logger.info("투두 추가 성공:"+todo.getUser_id());
		session.setAttribute("msg", "할일이 추가되었습니다.");
		
		return "redirect:/todo/list?page=1&view=all";
	}
	
	
	
	
	@RequestMapping(value = "del", method = RequestMethod.GET)
	public String del(HttpSession session,Model model,
			@RequestParam(value="idx",required=true) int idx) {
		id=getid(session);
		
		if(!service.isCorrectUser(id, idx)) {
			logger.error("Todo 삭제 - 권한 없음:"+id);
			session.setAttribute("error", "해당 할일을 삭제할 권한이 없습니다.");
			return "redirect:/todo/list?page=1&view=all";
		}
		
		if(!service.deleteTodo(idx)) {
			logger.warn("Todo 삭제 - 투두를 삭제하지 못함:"+idx);
			session.setAttribute("error", "할일을 삭제하지 못했습니다. 문의 부탁드립니다.");
			return "redirect:/todo/list?page=1&view=all";
		}
		
		logger.info("Todo 삭제 성공:"+idx);
		session.setAttribute("msg", "할일을 삭제하였습니다.");
		
		return "redirect:/todo/list?page=1&view=all";
	}
	
	
	
}
