package in.projectmang.models;

public class TaskStatus {
	
	private Integer id;
	private String status;
	
	public TaskStatus(){
		
	}
	public TaskStatus(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	public TaskStatus(int id, String status) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
