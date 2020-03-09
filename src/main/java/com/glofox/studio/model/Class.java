package com.glofox.studio.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Class {

	private Integer id;
	private String name;
	private int capacity;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	public Class(String name, int capacity, LocalDate startDate, LocalDate endDate) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Class() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
