package com.hib.tut.entities;

import javax.persistence.*;

@Entity
@Table
public class Interns {

	
	//private int AutoId;
	
	@Id
	private int internId;
	
	@Column
	private String department;
	@Column
	private String role;

	@OneToOne
	@JoinColumn(name="SId")
	private Students students;

	public Interns(){}
	
	public Interns(int internId, String department, String role, Students students) {
		super();
		this.internId = internId;
		this.department = department;
		this.role = role;
		this.students = students;
	}

	public int getInternId() {
		return internId;
	}

	public void setInternId(int internId) {
		this.internId = internId;
	}

	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Students getStudents() {
		return students;
	}

	public void setStudents(Students students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Interns [internId=" + internId + ", department=" + department + ", role=" + role
				+ ", students=" + students + "]";
	}

}
