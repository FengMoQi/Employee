package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Project;

public class ProjectDao {
	List<Project> list;

	public List<Project> searchByCondition(Project condition, int begin, int numInPage) {
		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"Wxa@1996");
			stat = conn.createStatement();
			// ִ��SQL��䲢�õ����
			String where = " where 1=1 ";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += "and name='" + condition.getName() + "'";
			}
			String sql = "select * from project " + where + " limit " + begin + "," + numInPage;

			rs = stat.executeQuery(sql);

			// 6.�Խ�������д���
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public boolean update(Project pro) {
		boolean flag = false;
		PreparedStatement pstat = null;
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "Wxa@1996");
			// 4.����statement sql���ִ����
			String sql = "update project set name=? where id=?";
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, pro.getName());
			pstat.setInt(2, pro.getId());
			int rs = pstat.executeUpdate();
			// 6.�Խ�������д���
			if (rs > 0) {
				flag = true;
			}

			// �ر�
			pstat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean addd(Project pro) {
		boolean flag = false;
		List<Project> pros = new ArrayList<Project>();
		Connection conn = null;
		PreparedStatement pstat = null;
		int rs = 0;
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"Wxa@1996");
			// 4.����statement sql���ִ����
			String sql = "insert into project(name) values(?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pro.getName());
			rs = pstat.executeUpdate();
			pstat.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs > 0;
	}

	public Project search(int id) {
		Project pro = new Project();
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.����statement sql���ִ����

			Statement stat = conn.createStatement();
			// ִ��SQL��䲢�õ����
			ResultSet rs = stat.executeQuery("select * from project where id=" + id);
			// 6.�Խ�������д���
			while (rs.next()) {

				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
			}
			// �ر�
			rs.close();
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;

	}

	public List<Project> search(int begin, int size) {
		list = new ArrayList<>();
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.����statement sql���ִ����

			Statement stat = conn.createStatement();
			// ִ��SQL��䲢�õ����
			ResultSet rs = stat.executeQuery("select * from project limit " + begin + "," + size);
			// 6.�Խ�������д���
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);

			}
			// �ر�
			rs.close();
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public int searchCount() {
		int count = 0;
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.����statement sql���ִ����

			Statement stat = conn.createStatement();
			// ִ��SQL��䲢�õ����
			ResultSet rs = stat.executeQuery("select count(*) from project ");
			// 6.�Խ�������д���
			while (rs.next()) {
				count = rs.getInt(1);

			}
			// �ر�
			rs.close();
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	public List<Project> search() {
		list = new ArrayList<>();
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.����statement sql���ִ����

			Statement stat = conn.createStatement();
			// ִ��SQL��䲢�õ����
			ResultSet rs = stat.executeQuery("select * from project ");
			// 6.�Խ�������д���
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);

			}
			// �ر�
			rs.close();
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public boolean delete(int id) {
		boolean flag = false;
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.����statement sql���ִ����

			// ִ��SQL��䲢�õ����
			String sql = "delete from project where id=?";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, id);
			// 6.�Խ�������д���
			int rs = stat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
			// �ر�s
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}
	public boolean delete(int id,List<Project> pros) {
		boolean flag = false;
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.����statement sql���ִ����

			// ִ��SQL��䲢�õ����
			String sql = "delete from project where id=?";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, id);
			// 6.�Խ�������д���
			int rs = stat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
			// �ر�s
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	public int searchCount(Project condition) {
		// TODO Auto-generated method stub

		int count = -1;

		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "Wxa@1996");
			// 5.����statement sql���ִ����

			Statement stat = conn.createStatement();
			// ִ��SQL��䲢�õ����
			String where = " where 1=1 ";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += "and name='" + condition.getName() + "'";
			}
			String sql = "select count(*) from project " + where;

			ResultSet rs = stat.executeQuery(sql);
			// 6.�Խ�������д���
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}
}
