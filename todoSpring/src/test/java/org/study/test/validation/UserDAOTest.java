package org.study.test.validation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.study.model.TodoVO;
import org.study.model.UserVO;
import org.study.service.TodoService;
import org.study.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDAOTest {

	@Autowired 
	UserService service;
	
	@Autowired 
	TodoService Tservice;
	
	@Test
	public void testAddUser() throws Exception {
		UserVO user = new UserVO();
		user.setId("test3");
		user.setName("테스트3");
		user.setPassword("asdf");
		user.setEmail("asdf@test.com");
		assertTrue(service.addUser(user));
	}

	@Test
	public void testGetTodo() throws Exception {
		TodoVO todo=Tservice.getTodo(3);
		System.out.println(todo);
		assertNotNull(todo);
	}
}