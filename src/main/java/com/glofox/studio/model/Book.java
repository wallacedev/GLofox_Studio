package com.glofox.studio.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Book {
	private Integer id;
	private String memberName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private Integer classId;

	public Book(String memberName, LocalDate date, Integer classId) {
		super();
		this.memberName = memberName;
		this.date = date;
		this.setClassId(classId);
	}
	
	public Book() {
		
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}
