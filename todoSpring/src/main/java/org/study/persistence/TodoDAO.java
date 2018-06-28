package org.study.persistence;

import java.util.List;

import org.study.model.TodoVO;

public interface TodoDAO {
	
	public int TodoCount(String id);
	
	public int doneCount(String id);
	
	public boolean insertTodo(TodoVO todo);
	//할일을 추가하고 성공 여부를 반환한다
	
	public TodoVO getTodo(int idx);
	//idx에 해당하는 할일을 가져와 해당 할일을 Todo 객체로 반환한다
	
	public boolean deleteTodo(int idx);
	//idx에 해당하는 할일을 삭제하고 성공 여부를 반환한다
	
	public boolean updateTodo(TodoVO todo);
	//Todo 객체를 가져와 해당 할일을 업데이트(수정)하고 성공 여부를 반환함
	
	public boolean updateDone(TodoVO todo);
	//할일 완료 상태를 done값에 따라 바꾸고 성공 여부를 반환함
	
	public List<TodoVO> getTodoList(TodoVO todo,String view);
	//view에 해당하는 id의 할일을 page에 맞게 List에 담아 반환하는 메서드 
	
	public int maxpage(String id,String view);
	//veiw에 해당하는 id의 할일이 총 몇 페이지인지 반환해줌
	
//	public String getCateName(int cat_id);
//	//cat_id에 해당하는 카테고리 이름을 반환한다.
	
	public int doneRate(String id,String view);
	//view에 해당하는 id의 할일중 몇퍼센트가 완료되었는지 int타입으로 반환함
	
	public int isCorrectUser(TodoVO todo);
	//todoidx에 해당하는 todo의 user_id가 id와 동일한지 체크해 반환함
	//동일하면 1
}
