package org.study.model;

public class UserVO {
	private String id;
	private String password;
	private String name;
	private String email;
	private String hashedPW;
	
//	private TodoService service=new TodoDaoImpl();
	
	
	public UserVO() {
		super();
		// TODO 자동 생성된 생성자 스텁
	}
	
	public UserVO(String id, String password, String name, String email) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
//		this.hashedPW=passAuth.hash(password.toCharArray());
	}
	
	
	
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", hashedPW="
				+ hashedPW + "]";
	}

	public String getHashedPW() {
		return hashedPW;
	}

	public void setHashedPW(String hashedPW) {
		this.hashedPW = hashedPW;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
//		this.hashedPW=passAuth.hash(password.toCharArray());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
//	public int getTodoNum() {
//		return service.todoCount(id);
//	}
//	
//	public int getDoneNum() {
//		return service.doneCount(id);
//	}




}
