package org.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.study.model.UserVO;
import org.study.persistence.UserDAO;
import org.study.sec.PasswordAuthentication;

public class UserServiceImpl implements UserService {
	
	@Autowired
	public PasswordAuthentication passAuth;
	
	@Autowired
	public UserDAO dao;
	
	static int rs;
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
		return passAuth.authenticate(password.toCharArray(), dao.getPW(id));
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
		user.setHashedPW(passAuth.hash(user.getPassword().toCharArray()));
	}

}
