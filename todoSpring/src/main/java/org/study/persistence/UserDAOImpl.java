package org.study.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.study.model.UserVO;

public class UserDAOImpl implements UserDAO {
	
	private static final String name = "org.study.todoSpring.mappers.userMapper";
	String strRs;
	
	@Autowired
	SqlSession session;

	@Override
	public boolean insertUser(UserVO user) {
		return (session.insert(name+".insert", user)==1)?true:false;
	}

	@Override
	public UserVO getUser(String id) {
		return session.selectOne(name+".getUser", id);
	}

	@Override
	public boolean deleteUser(String id) {
		return (session.insert(name+".delete", id)==1)?true:false;
	}

	@Override
	public boolean updateUser(UserVO user) {
		return (session.insert(name+".update", user)==1)?true:false;
	}

	@Override
	public boolean isValidUser(String id) {
		return ((Integer)(session.selectOne(name+".userCount", id))==1)?true:false;
	}

	@Override
	public String getPW(String id) {
		return session.selectOne(name+".getPW", id);
	}

	

}
