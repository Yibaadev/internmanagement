package co.kozao.internmanagement.model;

import java.time.LocalDate;

public class Intern {
	
	private int id;
	private String name;
	private String surname;
	private String email;
	private LocalDate startDate;
	private LocalDate endDate;
	private int group;
	private Supervisor supervisor;
	
	
	public Intern(int id, String name, String surname, String email, LocalDate startDate, LocalDate endDate, int group, Supervisor supervisor ) {
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate ;
		this.group = group ;
		this.supervisor = supervisor;
		 
	}
	
	public Intern() {

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public int getGroup() {
		return group;
	}


	public void setGroup(int group) {
		this.group = group;
	}


	public Supervisor getSupervisor() {
		return supervisor;
	}


	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
	
	
}
