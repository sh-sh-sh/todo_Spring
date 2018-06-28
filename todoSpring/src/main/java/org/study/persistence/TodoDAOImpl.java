package org.study.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.study.model.TodoVO;

public class TodoDAOImpl implements TodoDAO {
	
	private static final String name = "org.study.todoSpring.mappers.todoMapper";
	String strRs;
	
	@Autowired
	SqlSession session;

	@Override
	public int TodoCount(String id) {
		return session.selectOne(name+".todoCount",id);
	}

	@Override
	public int doneCount(String id) {
		return session.selectOne(name+".doneCount",id);
	}

	@Override
	public boolean insertTodo(TodoVO todo) {
		return (session.insert(name+".insert", todo)==1)?true:false;
	}

	@Override
	public TodoVO getTodo(int idx) {
		return session.selectOne(name+".getTodo", idx);
	}

	@Override
	public boolean deleteTodo(int idx) {
		return (session.delete(name+".delete", idx)==1)?true:false;
	}

	@Override
	public boolean updateTodo(TodoVO todo) {
		return (session.update(name+".update", todo)==1)?true:false;
	}

	@Override
	public boolean updateDone(TodoVO todo) {
		return (session.update(name+".updateDone", todo)==1)?true:false;
	}

	@Override
	public List<TodoVO> getTodoList(TodoVO todo,String view) {
		
		List<TodoVO> rs=null;
		
		switch(view) {
		case "today":
			rs=session.selectList(name + ".listToday", todo);
			break;
		case "week":
			rs=session.selectList(name + ".listWeek", todo);
			break;
		case "month":
			rs=session.selectList(name + ".listMonth", todo);
			break;
		case "done":
			rs=session.selectList(name + ".listDone", todo);
			break;
		case "undone":
			rs=session.selectList(name + ".listUndone", todo);
			break;
		case "all":
			rs=session.selectList(name + ".listAll", todo);
			break;
		}
		
		return rs;
	}

	
	
	@Override
	public int maxpage(String id,String view) {
		
		int rs=0;
		switch(view) {
		case "today":
			rs=session.selectOne(name+".maxpageToday",id);
			break;
		case "week":
			rs=session.selectOne(name+".maxpageWeek",id);
			break;
		case "month":
			rs=session.selectOne(name+".maxpageMonth",id);
			break;
		case "done":
			rs=session.selectOne(name+".maxpageDone",id);
			break;
		case "undone":
			rs=session.selectOne(name+".maxpageUndone",id);
			break;
		case "all":
			rs=session.selectOne(name+".maxpageAll",id);
			break;
		}
		
		return rs;
	}

	
	
//	@Override
//	public String getCateName(int cat_id) {
//		return session.selectOne(name+".getCateName",cat_id);
//	}

	
	
	@Override
	public int doneRate(String id,String view) {
		
		int rs=0;
		
		switch(view) {
		case "today":
			rs = session.selectOne(name+".doneRateToday",id);
			break;
		case "week":
			rs = session.selectOne(name+".doneRateWeek",id);
			break;
		case "month":
			rs = session.selectOne(name+".doneRateMonth",id);
			break;
		case "all":
			rs = session.selectOne(name+".doneRateAll",id);
			break;
		}
		return rs;
	}

	
	
	@Override
	public int isCorrectUser(TodoVO todo) {
		return session.selectOne(name+".isCorrectUser",todo);
	}

}
