package in.projectmang.models;

import java.sql.Timestamp;

public class Task {
	
	private Integer id;
	private User employee;
	private Project project;
	private TaskStatus taskStatus;
	private String taskTitle;
	private String instruction;
	private String taskDate;
	private Integer totalHours;
	private Integer totalHoursCompleted;
	private Timestamp createdAt;
	private String updatedAt;
	
	public Task() {
	}
	public Task(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getEmployee() {
		return employee;
	}
	public void setEmployee(User employee) {
		this.employee = employee;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public TaskStatus getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}
	public Integer getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}
	public Integer getTotalHoursCompleted() {
		return totalHoursCompleted;
	}
	public void setTotalHoursCompleted(Integer totalHoursCompleted) {
		this.totalHoursCompleted = totalHoursCompleted;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
}
