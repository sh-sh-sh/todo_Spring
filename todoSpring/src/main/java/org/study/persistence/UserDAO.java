package org.study.persistence;

import org.study.model.UserVO;

public interface UserDAO {
	public boolean insertUser(UserVO user);
	//user를 DB에 insert하고 성공 여부를 반환함
	
	public UserVO getUser(String id);
	//DB에서 id에 해당하는 유저를 User 객체로 반환함
	
	public boolean deleteUser(String id);
	//db에서 id에 해당하는 user를 지우고 성공 여부를 반환함
	
	public boolean updateUser(UserVO user);
	//db에서 user를 업데이트하고 성공 여부를 반환함
	
	public boolean isValidUser(String id);
	//db에 id에 해당하는 유저가 있으면 true를 반환함
	
	public String getPW(String id);
	//id에 해당하는 유저의 비밀번호 반환
}
