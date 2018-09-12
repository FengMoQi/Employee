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
import entity.Project;
import entity.Score;

public class ScoreDao {

	public List<Score> searchBYCondition(Score condition, int begin, int numInPage) {
		List<Score> list = new ArrayList<>();
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

			if (condition.getEmp() != null && condition.getEmp().getName() != null
					&& !condition.getEmp().getName().equals("")) {
				where += " and e.name='" + condition.getEmp().getName() + "'";
			}
			if (condition.getDep() != null && condition.getDep().getName() != null
					&& !condition.getDep().getName().equals("")) {
				where += " and d.name='" + condition.getDep().getName() + "'";
			}
			if (condition.getPro() != null && condition.getPro().getName() != null
					&& !condition.getPro().getName().equals("")) {
				where += " and p.name='" + condition.getPro().getName() + "'";
			}
			if (condition.getScore() != -1) {
				where += " and score=" + condition.getScore();
			}
			if (condition.getGrade() != null && !condition.getGrade().equals("")) {
				where += " and grade='" + condition.getGrade() + "'";
			}
			String sql = "SELECT sc.id as id,e.name as name,e.id as e_id,d.name as d_name,p.name as p_name,p.id as p_id,score,grade FROM employee AS e LEFT JOIN department AS d ON e.d_id=d.id LEFT JOIN r_dep_pro AS r ON r.d_id=d.id LEFT JOIN project as p ON r.p_id=p.id LEFT JOIN score as sc ON e.id=sc.e_id and p.id=sc.p_id "
					+ where + "  limit " + begin + "," + numInPage;
			System.out.println(sql);
			rs = stat.executeQuery(sql);

			// 6.对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				Department dep = new Department();
				Project pro = new Project();
				Score sc = new Score();
				sc.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setId(rs.getInt("e_id"));
				dep.setName(rs.getString("d_name"));
				pro.setName(rs.getString("p_name"));
				pro.setId(rs.getInt("p_id"));
				sc.setScore(rs.getInt("score"));
				sc.setGrade(rs.getString("grade"));
				sc.setEmp(emp);
				sc.setDep(dep);
				sc.setPro(pro);
				list.add(sc);
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

	public int searchCount(Score condition) {
		// TODO Auto-generated method stub

		int count = -1;

		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 3.建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "Wxa@1996");
			// 5.建立statement sql语句执行器

			Statement stat = conn.createStatement();
			// 执行SQL语句并得到结果
			String where = " where 1=1 ";

			if (condition.getEmp() != null && condition.getEmp().getName() != null
					&& !condition.getEmp().getName().equals("")) {
				where += " and e.name='" + condition.getEmp().getName() + "'";
			}
			if (condition.getDep() != null && condition.getDep().getName() != null
					&& !condition.getDep().getName().equals("")) {
				where += " and d.name='" + condition.getDep().getName() + "'";
			}
			if (condition.getPro() != null && condition.getPro().getName() != null
					&& !condition.getPro().getName().equals("")) {
				where += " and p.name='" + condition.getPro().getName() + "'";
			}
			if (condition.getScore() != -1) {
				where += " and score=" + condition.getScore();
			}
			if (condition.getGrade() != null && !condition.getGrade().equals("")) {
				where += " and grade='" + condition.getGrade() + "'";
			}
			String sql = "select count(*) from employee AS e LEFT JOIN department AS d ON e.d_id=d.id LEFT JOIN r_dep_pro AS r ON r.d_id=d.id LEFT JOIN project as p ON r.p_id=p.id LEFT JOIN score as sc ON e.id=sc.e_id and p.id=sc.p_id  "
					+ where ;

			ResultSet rs = stat.executeQuery(sql);
			// 6.对结果集进行处理
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

	public boolean updateBatch(List<Score> sc) {
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

			for (int i = 0; i < sc.size(); i++) {
				if(sc.get(i).getEmp().getId()!=0&&sc.get(i).getPro().getId()!=0&&sc.get(i).getId()!=0) {
					String update = "update score set score=? where e_id=? and p_id=?";
					pstat = conn.prepareStatement(update);
					pstat.setInt(1, sc.get(i).getScore());
					pstat.setInt(2, sc.get(i).getEmp().getId());
					pstat.setInt(3, sc.get(i).getPro().getId());
					rs = pstat.executeUpdate();
				}else if(sc.get(i).getEmp().getId()!=0&&sc.get(i).getPro().getId()!=0&&sc.get(i).getId()==0) {
					String update = "insert into score (e_id,p_id,score) values(?,?,?)";
					pstat = conn.prepareStatement(update);
					pstat.setInt(1, sc.get(i).getEmp().getId());
					pstat.setInt(2, sc.get(i).getPro().getId());
					pstat.setInt(3, sc.get(i).getScore());
					rs = pstat.executeUpdate();
				}
				
				
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

}
