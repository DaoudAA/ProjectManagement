package com.enit.jee.entity;

import java.io.Serializable;
import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity


public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id

	private String code;

	private String description ; 

	private Date startDate ;

	private Date endDate ;
	@ManyToOne
	@JoinColumn(name = "PROJFOREIGNKEY")
	private Project project;

	public Task() {
		super();
		// TODO Auto-generated method stub

	}
	public Task(String code,String description,Date StartDate,Date endDate) {
		super();
		this.code=code;
		this.description =description ; 
		this.startDate = StartDate ;
		this.endDate = endDate ;
	}
	public String getCode() {
		return this.code; 
	}
	public void setCode(String code) {
		this.code=code ; 
	}
	
	public String getDescription() {
		return this.description; 
	}
	public void setDescription(String description) {
		this.description=description ; 
	}
	
	public Date getStartDate() {
		return this.startDate; 
	}
	public void setStartDate(Date startDate) {
		this.startDate=startDate ; 
	}
	
	public Date getEndDate() {
		return this.endDate; 
	}
	public void setEndDate(Date endDate) {
		this.endDate=endDate ; 
	}
	
	public Project getProject() {
		return this.project;
	}
	public void setProject(Project associatedProject) {
		this.project=associatedProject ;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) 
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj ;
		if (code ==null) {
			if (other.code !=null)
				return false;
		}else if (!code.equals(other.code))
			return false ;
		if (description == null ) {
			if (other.description != null) 
				return false ;
			}else if (!description.equals(other.description))
				return false ;
		if (startDate == null ) {
			if (other.startDate != null) {
				return false ;
			}else if (!startDate.equals(other.startDate))
					return false ;
		}
		if (endDate ==null) {
			if (endDate != null )
				return false;
		}else if (!endDate.equals(other.endDate))
			return false;
		
		
		return true;
	}
	@Override
	public String toString() {
		return "Task [ code = " + code + " , description = "
	+description +" , startdate = "+ startDate +" , endDate = " 
	+endDate	+" , associatedProject = " + project + " ]" ;
	}
}
