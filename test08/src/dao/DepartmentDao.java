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

public class DepartmentDao extends BaseDao{
	List<Department> list;

	public List<Department> searchBYCondition(Department condition, int begin, int numInPage) {
		List<Department> list=new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"Wxa@1996");

			stat = conn.createStatement();
			// 执行SQL语句并得到结果
			String where = " where 1=1 ";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += "and name='" + condition.getName() + "'";
			}
			if (condition.getEmpCount() != -1) {
				where += "and ifnull(emp_count,0)=" + condition.getEmpCount();
			}
			String sql = "select * from department " + where + " limit " + begin + "," + numInPage;

			rs = stat.executeQuery(sql);

			// 6.对结果集进行处理
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));
				list.add(dep);
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

	public boolean update(Department dep) {
		boolean flag = false;
		PreparedStatement pstat = null;
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "Wxa@1996");
			// 4.建立statement sql语句执行器
			String sql = "update department set name=? where id=?";
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dep.getName());
			pstat.setInt(2, dep.getId());
			int rs = pstat.executeUpdate();
			// 6.对结果集进行处理
			if (rs > 0) {
				flag = true;
			}

			// 关闭
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

	public boolean addd(Department dep) {
		boolean flag = false;
		List<Department> deps = new ArrayList<Department>();
		Connection conn = null;
		PreparedStatement pstat = null;
		int rs = 0;
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"Wxa@1996");
			// 4.建立statement sql语句执行器
			String sql = "insert into department(name) values(?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dep.getName());
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

	public Department search(int id) {
		Department dep = new Department();
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			Statement stat = conn.createStatement();
			// 执行SQL语句并得到结果
			ResultSet rs = stat.executeQuery("select * from department where id=" + id);
			// 6.对结果集进行处理
			while (rs.next()) {

				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));
			}
			// 关闭
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
		return dep;

	}

	public List<Department> search(int begin, int size) {
		list = new ArrayList<>();
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			Statement stat = conn.createStatement();
			// 执行SQL语句并得到结果
			ResultSet rs = stat.executeQuery("select * from department limit " + begin + "," + size);
			// 6.对结果集进行处理
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));
				list.add(dep);

			}
			// 关闭
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
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			Statement stat = conn.createStatement();
			// 执行SQL语句并得到结果
			ResultSet rs = stat.executeQuery("select count(*) from department ");
			// 6.对结果集进行处理
			while (rs.next()) {
				count = rs.getInt(1);

			}
			// 关闭
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

	public List<Department> search() {
		list = new ArrayList<>();
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			Statement stat = conn.createStatement();
			// 执行SQL语句并得到结果
			ResultSet rs = stat.executeQuery("select * from department ");
			// 6.对结果集进行处理
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));
				list.add(dep);

			}
			// 关闭
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
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			// 执行SQL语句并得到结果
			String sql = "delete from department where id=?";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, id);
			// 6.对结果集进行处理
			int rs = stat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
			// 关闭s
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



	public int searchCount(Department condition) {
		// TODO Auto-generated method stub
		
		int count = -1;
		
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			Statement stat = conn.createStatement();
			// 执行SQL语句并得到结果
			String where = " where 1=1 ";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += "and name='" + condition.getName() + "'";
			}
			if (condition.getEmpCount() != -1) {
				where += "and ifnull(emp_count,0)=" + condition.getEmpCount();
			}
			String sql="select count(*) from department " + where;
			
			ResultSet rs = stat.executeQuery(sql);
			// 6.对结果集进行处理
			if(rs.next()) {
				count=rs.getInt(1);}
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
