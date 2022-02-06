package in.projectmang.models;

import java.util.Date;

public class ExternalEmployeeHourlyCost {
	
	private Integer id;
	private User externalEmployee;
	private User user;
	private Double hourlyRate;
	private String description;
	private Date createdAt;
	
	public ExternalEmployeeHourlyCost() {
	}
	public ExternalEmployeeHourlyCost(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getExternalEmployee() {
		return externalEmployee;
	}
	public void setExternalEmployee(User externalEmployee) {
		this.externalEmployee = externalEmployee;
	}
	public Double getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(Double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
