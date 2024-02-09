
package com.enit.jee.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity

public class Project implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private String code;
	private String description ; 
	private Date startDate ; 
	@OneToMany(mappedBy = "project" , cascade= {CascadeType.PERSIST,CascadeType.REMOVE},fetch=FetchType.EAGER )
	private Collection<Task> tasks;

	public Project() {
		super();
		// TODO Auto-generated method stub

	}
	public Project(String code,String description,Date StartDate) {
		super();
		this.code=code;
		this.description =description ; 
		this.startDate = StartDate ;
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
	public Collection<Task> getTasks() {
		return this.tasks; 
	}
	public void setTasks(Collection<Task> tasks) {
		this.tasks=tasks ; 
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) 
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj ;
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
		
		return true;
	}
	@Override
	public String toString() {
		return "Project [ code = " + code + " , description = "
	+description +" , startdate = "+ startDate + " ]" ;
	}
	
}
