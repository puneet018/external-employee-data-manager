package in.projectmang.models;


public class User {
	private Integer id;
	private String fullName;
	private Role role;
	private String contactNum;
	private String emailId;
	private String password;
	private Boolean status;
	
	public User() {
		
	}
	
	public User(String email, String password) {
		// TODO Auto-generated constructor stub
		this.password = password;
		this.emailId = email;
	}
	
	public User(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
}
