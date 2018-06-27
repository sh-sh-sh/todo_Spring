package org.study.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.study.service.TodoService;

public class TodoVO {
	private int idx;
	private String user_id;
	private int category;
	private String title;
	private String content;
	private String start_date;
	private String target_date;
	private String create_date;
	private boolean done;
	
	private int page;
	
	@Autowired
	private TodoService service;
	

	public TodoVO(int idx, String user_id, int category, String title, String content, String start_date,
			String target_date, String create_date, boolean done) {
		super();
		this.idx = idx;
		this.user_id = user_id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.start_date = start_date;
		this.target_date = target_date;
		this.create_date = create_date;
		this.done = done;
	}
	public TodoVO() {
		super();
		// TODO 자동 생성된 생성자 스텁
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getCategory() {
		return category;
	}
	public String getCateName() {
		System.out.println("카테고리번호5:"+category);
		System.out.println("서비스테스트:"+service);
		return service.getCateName(category);
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getStart_date_listver() {
		//리스트에서 날짜까지만 출력하기 위해 잘라서 반환함
		return start_date.substring(2,10);
	}
	public String getTarget_date() {
		return target_date;
	}
	public String getTarget_date_listver() {
		//리스트에서 분까지만 출력하기 위해 target_date를 분까지 잘라 반환함
		return target_date.substring(2,16);
	}
	public void setTarget_date(String target_date) {
		this.target_date = target_date;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public boolean getPast() throws ParseException {
		//target_date가 현재 시간보다 이전이면 true를 반환함
		Date date= new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getTarget_date());
		
		return new Date().after(date);
	}
	
}