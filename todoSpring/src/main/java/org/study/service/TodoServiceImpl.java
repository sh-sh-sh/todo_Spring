package org.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.study.model.TodoVO;
import org.study.persistence.TodoDAO;

public class TodoServiceImpl implements TodoService {
	
	@Autowired
	public TodoDAO dao;
	
	final static String ex="\"|<>{}";
	
	@Override
	public boolean addTodo(TodoVO todo) {
		return dao.insertTodo(todo);
	}

	@Override
	public TodoVO getTodo(int idx) {
		TodoVO todo=dao.getTodo(idx);
		todo.setStart_date(todo.getStart_date().substring(0,16));
		todo.setTarget_date(todo.getTarget_date().substring(0, 16));
		todo.setCreate_date(todo.getCreate_date().substring(0, 16));
		return todo;
	}

	@Override
	public boolean deleteTodo(int idx) {
		return dao.deleteTodo(idx);
	}

	@Override
	public boolean updateTodo(TodoVO todo) {
		return dao.updateTodo(todo);
	}

	@Override
	public boolean setDone(int idx, boolean done) {
		TodoVO todo=new TodoVO();
		todo.setIdx(idx);
		todo.setDone(done);
		return dao.updateDone(todo);
	}

	@Override
	public List<TodoVO> getTodoList(String id, int page, String view) {
		TodoVO todo=new TodoVO();
		todo.setUser_id(id);
		todo.setPage((page-1)*pager);
		return dao.getTodoList(todo, view);
	}

	@Override
	public int maxpage(String id, String view) {
		int rs=dao.maxpage(id, view);
		int maxpage=rs/pager;
		if(rs%pager!=0) {
			maxpage++;
		}
		return maxpage;
	}

	
	@Override
	public boolean Validation(TodoVO todo) {
		for(int i=0;i<ex.length();i++) {
			if(todo.getTitle().contains(Character.toString(ex.charAt(i)))
					||todo.getContent().contains(Character.toString(ex.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	
//	@Override
//	public String getCateName(int cat_id) {
//		String tmp=dao.getCateName(cat_id);
//		return tmp;
//	}

	@Override
	public int doneRate(String id, String view) {
		return dao.doneRate(id, view);
	}

	@Override
	public boolean isCorrectUser(String id, int todoIdx) {
		TodoVO todo=new TodoVO();
		todo.setUser_id(id);
		todo.setIdx(todoIdx);
		return (dao.isCorrectUser(todo)==0)?true:false;
	}

}
