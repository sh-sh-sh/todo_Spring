package org.study.service;

import org.study.model.UserVO;

public interface UserService {
	public boolean addUser(UserVO user);
	//user를 DB에 insert하고 성공 여부를 반환함
	
	public UserVO getUser(String id);
	//DB에서 id에 해당하는 유저를 User 객체로 반환함
	
	public boolean deleteUser(String id);
	//db에서 id에 해당하는 user를 지우고 성공 여부를 반환함
	
	public boolean updateUser(UserVO user);
	//db에서 user를 업데이트하고 성공 여부를 반환함
	
	public boolean isValidUser(String id);
	//db에 id에 해당하는 유저가 있으면 true를 반환함
	
	public boolean passwordCheck(String id,String password);
	//id에 해당하는 유저의 패스워드가 password와 일치하면 true를 반환함
	
	public boolean Validation(UserVO user);
	//유저의 아이디와 비밀번호 특수문자 유효성 검사
	
	public void pwHash(UserVO user);
	//user의 password를 해싱해 hashedPW에 담음
	
	public int getTodoNum(String id);
	
	public int getDoneNum(String id);
	
}
