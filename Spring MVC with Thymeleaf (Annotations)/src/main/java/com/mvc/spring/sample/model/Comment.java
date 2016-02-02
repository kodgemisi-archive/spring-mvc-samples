package com.mvc.spring.sample.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String value;
	
	private Calendar date;
	
	@JsonIgnore
	@ManyToOne
	private Movie target;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Movie getTarget() {
		return target;
	}

	public void setTarget(Movie target) {
		this.target = target;
	}


}
