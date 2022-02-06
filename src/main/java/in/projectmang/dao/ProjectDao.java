package in.projectmang.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import in.projectmang.configs.GetCon;
import in.projectmang.models.Project;

public class ProjectDao {

	public static ArrayList<Project> getManagerProjects(Integer projectMangId) {

		ArrayList<Project> projects = new ArrayList<Project>();

		try {
			Connection con = GetCon.getCon();

			String query = "select id,title,description,start_date,budget,deadline,status,end_date from projects where project_mang_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, projectMangId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Project projectInfo = new Project();

				projectInfo.setId(rs.getInt(1));
				projectInfo.setTitle(rs.getString(2));
				projectInfo.setDescription(rs.getString(3));
				projectInfo.setStartDate(rs.getString(4));
				projectInfo.setEndDate(rs.getString(8));
				projectInfo.setBudget(rs.getDouble(5));
				projectInfo.setDeadline(rs.getInt(6));
				projectInfo.setStatus(rs.getBoolean(7));

				projects.add(projectInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return projects;
	}

	public static ArrayList<Project> getAllProjects() {
		// TODO Auto-generated method stub

		ArrayList<Project> projects = new ArrayList<Project>();

		try {
			Connection con = GetCon.getCon();

			String query = "select id,title,description,start_date,budget,deadline,status,end_date from projects";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Project projectInfo = new Project();

				projectInfo.setId(rs.getInt(1));
				projectInfo.setTitle(rs.getString(2));
				projectInfo.setDescription(rs.getString(3));
				projectInfo.setStartDate(rs.getString(4));
				projectInfo.setEndDate(rs.getString(8));
				projectInfo.setBudget(rs.getDouble(5));
				projectInfo.setDeadline(rs.getInt(6));
				projectInfo.setStatus(rs.getBoolean(7));

				projects.add(projectInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return projects;
	}

	public boolean addProject(Project project) {
		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "insert into projects(project_mang_id,title,description,start_date,budget,deadline,end_date) value(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, project.getProjectManager().getId());
			ps.setString(2, project.getTitle());
			ps.setString(3, project.getDescription());
			ps.setDate(4, Date.valueOf(project.getStartDate()));
			ps.setDouble(5, project.getBudget());
			ps.setInt(6, project.getDeadline());
			ps.setDate(7, Date.valueOf(project.getEndDate()));

			if (ps.executeUpdate() == 1) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					project.setId(rs.getInt(1));
					flag = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean changeProjectStatus(Integer projectId) {
		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "update projects set status=? where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, 0);
			ps.setInt(2, projectId);

			if (ps.executeUpdate() == 1) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
