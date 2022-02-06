package in.projectmang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import in.projectmang.configs.GetCon;
import in.projectmang.models.EmployeeActivity;

public class EmployeeActivityDao {

	public static boolean submitHours(EmployeeActivity employeeActivity) {

		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "insert into employee_activities (hours,task_id) value (?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, employeeActivity.getHours());
			ps.setInt(2, employeeActivity.getTask().getId());

			if (ps.executeUpdate() == 1) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean checkActivity(Integer taskId) {
		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "select hours from employee_activities where task_id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, taskId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
