package org.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.model.TodoVO;
import org.study.persistence.TodoDAO;

@Repository
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	public TodoDAO dao;
	

	@Override
	public boolean addTodo(TodoVO todo) {
		return dao.insertTodo(todo);
	}

	@Override
	public TodoVO getTodo(int idx) {
		return dao.getTodo(idx);
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
		System.out.println("rs:"+rs);
		int maxpage=rs/pager;
		if(rs%pager!=0) {
			maxpage++;
		}
		return maxpage;
	}

	@Override
	public String getCateName(int cat_id) {
		System.out.println("테스트1");
		System.out.println("카테고리테스트1:"+cat_id);
		String tmp=dao.getCateName(cat_id);
		System.out.println("테스트2:"+tmp);
		return tmp;
	}

	@Override
	public int doneRate(String id, String view) {
		return dao.doneRate(id, view);
	}

	@Override
	public boolean isCorrectUser(String id, int todoIdx) {
		TodoVO todo=new TodoVO();
		todo.setUser_id(id);
		todo.setIdx(todoIdx);
		return (dao.isCorrectUser(todo)==1)?true:false;
	}

}
