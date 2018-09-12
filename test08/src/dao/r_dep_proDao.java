package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.Department;
import entity.Project;

public class r_dep_proDao {
	public List<Project> search(int id) {
		List<Project> list = new ArrayList<>();
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.����statement sql���ִ����
			Statement stat = conn.createStatement();
			// ִ��SQL��䲢�õ����
			ResultSet rs = stat.executeQuery(
					"select p.id as p_id,p.name as p_name from department as d left join r_dep_pro as r on d.id = r.d_id left join project as p on r.p_id = p.id where d.id="
							+ id);
			// 6.�Խ�������д���
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
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
	
	public boolean delete(int dId, String[] pId) {
		boolean flag = false;
		int rs=0;
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.����statement sql���ִ����

			// ִ��SQL��䲢�õ����

			String sql = "delete from r_dep_pro where d_id=? and p_id=?";
			PreparedStatement stat = conn.prepareStatement(sql);
			for(int i=0;i<pId.length;i++) {
			stat.setInt(1, dId);
			stat.setInt(2,Integer.parseInt(pId[i]));
			rs = stat.executeUpdate();
			}
			// 6.�Խ�������д���
			
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
	
	

	public boolean delete(int dId, int pId) {
		boolean flag = false;
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.����statement sql���ִ����

			// ִ��SQL��䲢�õ����

			String sql = "delete from r_dep_pro where d_id=? and p_id=?";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, dId);
			stat.setInt(2, pId);
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

	public List<Project> search(List<Project> pros) {
		List<Project> list = new ArrayList();
		String ids = "";
		for (int i = 0; i < pros.size(); i++) {
			int id = pros.get(i).getId();
			ids += id + ",";
		}
		ids = ids.substring(0, ids.length() - 1);
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");

			// 3.��������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.����statement sql���ִ����

			Statement stat = conn.createStatement();
			// ִ��SQL��䲢�õ����
			ResultSet rs = stat.executeQuery("select * from project where id not in (" + ids + ")");
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

	public boolean addd(int dId, int pId) {
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
			String sql = "insert into r_dep_pro(d_id,p_id) values(?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, dId);
			pstat.setInt(2, pId);
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
	public boolean addd(int dId, String[] pId) {
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
			String sql = "insert into r_dep_pro(d_id,p_id) values(?,?)";
			pstat = conn.prepareStatement(sql);
			for(int i=0;i<pId.length;i++) {
			pstat.setInt(1, dId);
			pstat.setInt(2, Integer.parseInt(pId[i]));
			rs = pstat.executeUpdate();
			}
			
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

}
