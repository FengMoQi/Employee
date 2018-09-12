package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DepartmentDao;
import dao.ProjectDao;
import entity.Department;
import entity.Project;
import util.Constant;
import util.Pagination;

public class ProjectServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			if (type == null||"search".equals(type)) {
				search(request, response);
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("showModify".equals(type)) {
				showModify(request, response);
			} else if ("update".equals(type)) {
				update(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			ProjectDao proDao = new ProjectDao();
			List<Project> list = proDao.search();
			request.setAttribute("list", list);
			int ye1=1;
			int ye2=1;
			Project condition = new Project();
			String name = request.getParameter("name");
			condition.setName(name);
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
 			int count = proDao.searchCount(condition);
			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
			List<Project> pros = proDao.searchByCondition(condition,p.getBegin(), Constant.EMP_NUM_IN_PAGE);
			request.setAttribute("ye1", ye1);
			request.setAttribute("ye2", ye2);
			request.setAttribute("p", p);
			request.setAttribute("c", condition);
			request.setAttribute("pros", pros);
			request.getRequestDispatcher("WEB-INF/project/pros.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	public void show(HttpServletRequest request, HttpServletResponse response) {
//
//		try {
//			int ye = 1;
//			if (request.getParameter("ye") != null) {
//				ye = Integer.parseInt(request.getParameter("ye"));
//			}
//			ProjectDao proDao = new ProjectDao();
// 			int count = proDao.searchCount();
//			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
//			List<Project> pros = proDao.search(p.getBegin(), Constant.EMP_NUM_IN_PAGE);
//			request.setAttribute("p", p);
//			
//			request.setAttribute("pros", pros);
//			request.getRequestDispatcher("WEB-INF/Project/pros.jsp").forward(request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {

		try {

			request.getRequestDispatcher("WEB-INF/project/addProject.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProjectDao proDao = new ProjectDao();
			Project pro = new Project();
			String name = request.getParameter("name");
			pro.setName(name);
			boolean flag = proDao.addd(pro);
			// show(request, response);

			response.sendRedirect("pros");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showModify(HttpServletRequest request, HttpServletResponse response) {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ProjectDao proDao = new ProjectDao();
			Project pro = proDao.search(id);
			request.setAttribute("pro", pro);
			request.getRequestDispatcher("WEB-INF/project/modifyProject.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProjectDao proDao = new ProjectDao();
			Project pro = new Project();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			pro.setId(id);
			pro.setName(name);
			boolean flag = proDao.update(pro);
			// show(request, response);
 
			response.sendRedirect("pros");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		ProjectDao proDao = new ProjectDao();
		boolean flag = proDao.delete(id);
		try {
			response.sendRedirect("pros");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
	
}
