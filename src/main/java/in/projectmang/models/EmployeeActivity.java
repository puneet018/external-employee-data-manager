package in.projectmang.models;

import java.util.Date;

public class EmployeeActivity {
	
	private Integer id;
	private Task task;
	private Date datetime;
	private Integer hours;

	
	public EmployeeActivity() {
		
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Task getTask() {
		return task;
	}


	public void setTask(Task task) {
		this.task = task;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Integer getHours() {
		return hours;
	}


	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
	
	
}
