package in.projectmang.models;

public class Project {

	private Integer id;
	private User projectManager;
	private String title;
	private String description;
	private String startDate;
	private String endDate;
	private Double budget;
	private Integer deadline;
	private Boolean status;
	
	
	public Project() {
	
	}	
	
	public Project(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	public Project(String title) {
		this.title = title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public User getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(User projectManager) {
		this.projectManager = projectManager;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Integer getDeadline() {
		return deadline;
	}

	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

	
	
	
	
	
}
