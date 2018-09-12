package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.User;

public class UserDao {
	public boolean search(User user) {
		boolean flag = false;
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			Statement stat = conn.createStatement();
			String sql = "select * from user where username='" + user.getUserName() + "' and password='" + user.getPassword() + "'";
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;

	}
	
	
	public boolean add(User user) {
		int rs=0;
		boolean flag = false;
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "Wxa@1996");
			Statement stat = conn.createStatement();
			String sql = "insert into user (username,password) values('" + user.getUserName() + "','" + user.getPassword() + "')";
			rs = stat.executeUpdate(sql);
			if(rs>0) {
				flag=true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;

	}
	
	
}
