package org.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.study.model.UserVO;
import org.study.persistence.TodoDAO;
import org.study.persistence.UserDAO;

public class UserServiceImpl implements UserService {
	
	@Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	public UserDAO dao;
	
	@Autowired
	public TodoDAO todoDao;
	
	final static String ex="\"|<>{}";

	@Override
	public boolean addUser(UserVO user) {
		pwHash(user);
		return dao.insertUser(user);
	}

	@Override
	public UserVO getUser(String id) {
		return dao.getUser(id);
	}

	@Override
	public boolean deleteUser(String id) {
		return dao.deleteUser(id);
	}

	@Override
	public boolean updateUser(UserVO user) {
		return dao.updateUser(user);
	}

	@Override
	public boolean isValidUser(String id) {
		return dao.isValidUser(id);
	}

	@Override
	public boolean passwordCheck(String id, String password) {
		return bcryptPasswordEncoder.matches(password, dao.getPW(id));
	}

	@Override
	public boolean Validation(UserVO user) {
		for(int i=0;i<ex.length();i++) {
			if(user.getId().contains(Character.toString(ex.charAt(i)))
					||user.getName().contains(Character.toString(ex.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void pwHash(UserVO user) {
		user.setHashedPW(this.bcryptPasswordEncoder.encode(user.getPassword()));
	}

	@Override
	public int getTodoNum(String id) {
		return todoDao.TodoCount(id);
	}

	@Override
	public int getDoneNum(String id) {
		return todoDao.doneCount(id);
	}

}
