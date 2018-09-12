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
import entity.Employee;
import util.Constant;

public class EmployeeDao {
	List<Employee> list;

	public List<Employee> searchBYCondition(Employee condition, int begin, int numInPage) {
		List<Employee> list=new ArrayList<>();
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
				where += " and e.name='" + condition.getName() + "'";
			}
			if (condition.getSex()!=null&&!condition.getSex().equals("")) {
				where += " and sex='" + condition.getSex() + "'";
			}
			if (condition.getAge() != -1) {
				where += " and age=" + condition.getAge();
			}
			if (condition.getDep()!=null&&condition.getDep().getName()!=null&&!condition.getDep().getName().equals("")) {
				where += " and d.name='" + condition.getDep().getName() + "'";
			}
			String sql = "select e.*,d.name as depName from employee as e left join department as d on e.d_id=d.id" + where + " limit " + begin + "," + numInPage;

			rs = stat.executeQuery(sql);

			// 6.对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				emp.setPicture(rs.getString("picture"));
				Department dep=new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("depName"));
				emp.setDep(dep);
				list.add(emp);
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

	public boolean update(Employee emp) {
		boolean flag = false;
		PreparedStatement pstat = null;
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "Wxa@1996");
			// 4.建立statement sql语句执行器
			String sql = "update employee set name=?,sex=?,age=?,d_id=? where id=?";
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
			pstat.setInt(4, emp.getDep().getId());
			pstat.setInt(5, emp.getId());
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

	public boolean addd(Employee emp) {
		boolean flag = false;
		List<Employee> emps = new ArrayList<Employee>();
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
			String sql = "insert into employee (name,sex,age,d_id,picture) values(?,?,?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
			pstat.setInt(4, emp.getDep().getId());
			pstat.setString(5, emp.getPicture());
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

	public Employee search(int id) {
		Employee emp = new Employee();
		Department dep=new Department();
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			Statement stat = conn.createStatement();
			// 执行SQL语句并得到结果
			ResultSet rs = stat.executeQuery("select * from employee where id=" + id);
			// 6.对结果集进行处理
			while (rs.next()) {

				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				dep.setId(rs.getInt("d_id"));
				emp.setPicture(rs.getString("picture"));
				emp.setDep(dep);
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
		return emp;

	}

	public List<Employee> search(int begin, int size) {
		list = new ArrayList<>();
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			Statement stat = conn.createStatement();
			// 执行SQL语句并得到结果
			ResultSet rs = stat.executeQuery("select * from employee limit " + begin + "," + size);
			// 6.对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);

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
			ResultSet rs = stat.executeQuery("select count(*) from employee ");
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

	public List<Employee> search() {
		list = new ArrayList<>();
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			Statement stat = conn.createStatement();
			// 执行SQL语句并得到结果
			ResultSet rs = stat.executeQuery("select * from employee ");
			// 6.对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);

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

	public List<Employee> searchBatch(String ids) {
		list = new ArrayList<>();
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			Statement stat = conn.createStatement();
			// 执行SQL语句并得到结果
			ResultSet rs = stat.executeQuery("select * from employee where id in (" + ids + ")");
			// 6.对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);

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
			String sql = "delete from employee where id=?";
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

	public boolean deleteBatch(String ids) {
		boolean flag = false;
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			// 执行SQL语句并得到结果
			String sql = "delete from employee where id in (" + ids + ")";
			Statement stat = conn.createStatement();
			// 6.对结果集进行处理
			int rs = stat.executeUpdate(sql);
			if (rs > 0) {
				flag = true;
			}
			// 关闭
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

	public boolean updateBatch1(String ids, Employee emp) {
		boolean flag = false;
		PreparedStatement pstat = null;
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "Wxa@1996");
			// 4.建立statement sql语句执行器
			String sql = "update employee set name=?,sex=?,age=? where id in (" + ids + ")";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, emp.getName());
			pstat.setString(2, emp.getSex());
			pstat.setInt(3, emp.getAge());
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

	public boolean updateBatch2(List<Employee> emps) {
		boolean flag = false;
		PreparedStatement pstat = null;
		int rs = 0;
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "Wxa@1996");
			// 4.建立statement sql语句执行器
			for (int i = 0; i < emps.size(); i++) {
				String sql = "update employee set name=?,sex=?,age=? where id=?";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, emps.get(i).getName());
				pstat.setString(2, emps.get(i).getSex());
				pstat.setInt(3, emps.get(i).getAge());
				pstat.setInt(4, emps.get(i).getId());
				rs = pstat.executeUpdate();
			}

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

	public int searchCount(Employee condition) {
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
				where += "and e.name='" + condition.getName() + "'";
			}
			if (condition.getSex()!=null&&!condition.getSex().equals("")) {
				where += "and sex='" + condition.getSex() + "'";
			}
			if (condition.getAge() != -1) {
				where += "and age=" + condition.getAge();
			}
			if (condition.getDep()!=null&&condition.getDep().getName()!=null&&!condition.getDep().getName().equals("")) {
				where += "and d.name='" + condition.getDep().getName() + "'";
			}
			String sql="select count(*) from employee as e left join department as d on e.d_id=d.id " + where;
			
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
