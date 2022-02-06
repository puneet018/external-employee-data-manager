package in.projectmang.models;


public class Role {
	private Integer id;
	private String role;
		
	public Role() {
		
	}
	
	public Role(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}


	public Role(String role,int id) {
		// TODO Auto-generated constructor stub
		this.role = role;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
	
}
