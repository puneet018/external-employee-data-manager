package in.projectmang.models;

public class Cost {
	
	private Integer id;
	private User user;
	private Project project;
	private String costTitle;
	private String description;
	private Double cost;
	
	public Cost() {
	}
	public Cost(int id) {
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
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getCostTitle() {
		return costTitle;
	}
	public void setCostTitle(String costTitle) {
		this.costTitle = costTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	
	
}
