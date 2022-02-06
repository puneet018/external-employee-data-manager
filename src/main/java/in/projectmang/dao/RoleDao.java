package in.projectmang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import in.projectmang.configs.GetCon;
import in.projectmang.models.Role;

public class RoleDao {

	public static ArrayList<Role> getAllRoles() {
		ArrayList<Role> roles = new ArrayList<Role>();

		try {
			Connection con = GetCon.getCon();

			String query = "select id,role from roles";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Role roleInfo = new Role();

				roleInfo.setId(rs.getInt(1));
				roleInfo.setRole(rs.getString(2));

				roles.add(roleInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return roles;
	}

}
