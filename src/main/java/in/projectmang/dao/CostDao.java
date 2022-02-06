package in.projectmang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import in.projectmang.configs.GetCon;
import in.projectmang.models.Cost;

public class CostDao {
	
	public static boolean addCost(Cost cost) {
		
		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "insert into costs(user_id,project_id,cost_title,description,cost) value(?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, cost.getUser().getId());
			ps.setInt(2, cost.getProject().getId());
			ps.setString(3, cost.getCostTitle());
			ps.setString(4, cost.getDescription());
			ps.setDouble(5, cost.getCost());
			
			if (ps.executeUpdate() == 1) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					cost.setId(rs.getInt(1));
					flag = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
