package in.projectmang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import in.projectmang.configs.GetCon;
import in.projectmang.models.Role;
import in.projectmang.models.User;

public class UserDao {

	public boolean loginUser(User user) {
		boolean flag = false;
		PreparedStatement ps;
		Connection con = GetCon.getCon();
		try {
			String query = "select id,full_name,role_id,contact_number,status from users where email_id=? and password=?";

			ps = con.prepareStatement(query);
			ps.setString(1, user.getEmailId());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setFullName(rs.getString(2));
				user.setRole(new Role(rs.getInt(3)));
				user.setContactNum(rs.getString(4));
				user.setStatus(rs.getBoolean(5));

				flag = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return flag;
	}

	public void createUser(User user) {
		
		try {
			Connection conn = GetCon.getCon();

			String query = "insert into users(full_name,role_id,contact_number,email_id,password) value(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getFullName());
			ps.setInt(2, user.getRole().getId());
			ps.setString(3, user.getContactNum());
			ps.setString(4, user.getEmailId());
			ps.setString(5, user.getPassword());

			if (ps.executeUpdate() == 1) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public boolean roleAssign(User user) {
		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "update users set role_id=? where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(2, user.getId());
			ps.setInt(1, user.getRole().getId());
			
			if (ps.executeUpdate() == 1) {
				
					flag = true;
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	

	public static ArrayList<User> getAllEmployees() {
		// TODO Auto-generated method stub
		
		ArrayList<User> employees = new ArrayList<User>();
		
		try{
			Connection con = GetCon.getCon();

			String query = "select users.id,full_name,email_id,contact_number,password,role,role_id,status from users inner join roles on users.role_id=roles.id";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				User userInfo = new User();
				
				userInfo.setId(rs.getInt(1));
				userInfo.setFullName(rs.getString(2));
				userInfo.setEmailId(rs.getString(3));
				userInfo.setContactNum(rs.getString(4));
				userInfo.setPassword(rs.getString(5));
				userInfo.setRole(new Role(rs.getString(6),rs.getInt(7)));
				userInfo.setStatus(rs.getBoolean(8));
				
				employees.add(userInfo);
			}			

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return employees;
	}
	
	public static ArrayList<User> getExternalEmployees() {

		ArrayList<User> employees = new ArrayList<User>();
		
		try{
			Connection con = GetCon.getCon();

			String query = "select id,full_name,email_id,contact_number,status from users where role_id=5";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()){
				User userInfo = new User();
				
				userInfo.setId(rs.getInt(1));
				userInfo.setFullName(rs.getString(2));
				userInfo.setEmailId(rs.getString(3));
				userInfo.setContactNum(rs.getString(4));
				userInfo.setStatus(rs.getBoolean(5));
				
				employees.add(userInfo);
			}			

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return employees;
	}
	

	public static boolean deactivateUser(Integer userId) {

		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "update users set status=? where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(2, userId);
			ps.setBoolean(1, false);
			
			if (ps.executeUpdate() == 1) {
					flag = true;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	

}
