package in.projectmang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import in.projectmang.configs.GetCon;
import in.projectmang.models.ExternalEmployeeHourlyCost;

public class ExternalEmployeeHourlyRateDao {
	
	public static boolean addCost(ExternalEmployeeHourlyCost exEmp) {
		
		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "insert into employee_rates(user_id,external_employee_id,hourly_rate,description) value(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, exEmp.getUser().getId());
			ps.setInt(2, exEmp.getExternalEmployee().getId());
			ps.setDouble(3, exEmp.getHourlyRate());
			ps.setString(4, exEmp.getDescription());
			
			
			if (ps.executeUpdate() == 1) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					exEmp.setId(rs.getInt(1));
					flag = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
