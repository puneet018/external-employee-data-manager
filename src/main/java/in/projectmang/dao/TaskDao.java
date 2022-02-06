package in.projectmang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import in.projectmang.configs.GetCon;
import in.projectmang.models.Project;
import in.projectmang.models.Task;
import in.projectmang.models.TaskStatus;
import in.projectmang.models.User;

public class TaskDao {

	public boolean addTaskAssign(Task task) {
		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "insert into tasks (project_id,employee_id,task_title,total_hours,instruction,task_date,updated_at) value (?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, task.getProject().getId());
			ps.setInt(2, task.getEmployee().getId());
			ps.setString(3, task.getTaskTitle());
			ps.setInt(4, task.getTotalHours());
			ps.setString(5, task.getInstruction());

			if (ps.executeUpdate() == 1) {

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					task.setId(rs.getInt(1));
					flag = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static ArrayList<Task> getAllTask(int projectManagerId) {
		ArrayList<Task> tasks = new ArrayList<Task>();

		try {
			Connection con = GetCon.getCon();

			String query = "select tasks.id,task_title,instruction,task_date,title,updated_at,full_name,contact_number,task_status_id,total_hours,total_hours_completed from tasks "
					+ "inner join projects on tasks.project_id=projects.id inner join users on tasks.employee_id=users.id";
			;
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Task taskInfo = new Task();
				User user = new User();

				taskInfo.setId(rs.getInt(1));
				taskInfo.setTaskTitle(rs.getString(2));
				taskInfo.setInstruction(rs.getString(3));
				taskInfo.setTaskDate((rs.getString(4)));
				taskInfo.setProject(new Project(rs.getString(5)));
				taskInfo.setUpdatedAt((rs.getDate(6).toString()));
				taskInfo.setTaskStatus(new TaskStatus(rs.getInt(9)));
				taskInfo.setTotalHours(rs.getInt(10));
				taskInfo.setTotalHoursCompleted(rs.getInt(11));
				user.setFullName(rs.getString(7));
				user.setContactNum(rs.getString(8));
				taskInfo.setEmployee(user);

				tasks.add(taskInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public static ArrayList<Task> getEmployeeTask(Integer employeeId) {
		ArrayList<Task> tasks = new ArrayList<Task>();

		try {
			Connection con = GetCon.getCon();

			String query = "select tasks.id,task_title,instruction,task_date,task_status_id,task_status.status,total_hours,total_hours_completed,updated_at,title from tasks "
					+ "inner join projects on tasks.project_id=projects.id inner join users on tasks.employee_id=users.id inner join task_status on tasks.task_status_id=task_status.id where tasks.employee_id=?";
			;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Task taskInfo = new Task();
				Project project = new Project();

				taskInfo.setId(rs.getInt(1));
				taskInfo.setTaskTitle(rs.getString(2));
				taskInfo.setInstruction(rs.getString(3));
				taskInfo.setTaskDate((rs.getString(4)));
				taskInfo.setTaskStatus(new TaskStatus(rs.getInt(5), rs.getString(6)));
				taskInfo.setTotalHours((rs.getInt(7)));
				taskInfo.setTotalHoursCompleted((rs.getInt(8)));
				taskInfo.setUpdatedAt((rs.getDate(9).toString()));
				project.setTitle(rs.getString(10));
				taskInfo.setProject(project);
				System.out.println(taskInfo.getTotalHoursCompleted());
				tasks.add(taskInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasks;
	}

	public static boolean submitHours(Task task) {

		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "update tasks set total_hours_completed=?,updated_at=CURRENT_TIMESTAMP where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, task.getTotalHoursCompleted());
			ps.setInt(2, task.getId());

			if (ps.executeUpdate() == 1) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean changeTaskStatus(Integer taskId, Integer taskStatusId) {
		boolean flag = false;
		try {
			Connection conn = GetCon.getCon();

			String query = "update tasks set task_status_id=? where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, taskStatusId);
			ps.setInt(2, taskId);

			if (ps.executeUpdate() == 1) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
