package servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DepartmentDao;
import dao.ProjectDao;
import dao.r_dep_proDao;
import entity.Department;
import entity.Project;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class DepartmentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				String type = request.getParameter("type");
				if (type == null || "search".equals(type)) {
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
				} else if ("showProject".equals(type)) {
					showProject(request, response);
				} else if ("showProject3".equals(type)) {
					showProject3(request, response);
				} else if ("deleteProject".equals(type)) {
					deleteProject(request, response);
				} else if ("deleteProject3".equals(type)) {
					deleteProject3(request, response);
				} else if ("addProject".equals(type)) {
					addProject(request, response);
				} else if ("addProject3".equals(type)) {
					addProject3(request, response);
				}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addProject3(HttpServletRequest request, HttpServletResponse response) {
		try {
			r_dep_proDao rdpDao = new r_dep_proDao();
			int dId = Integer.parseInt(request.getParameter("dId"));
			String abc = request.getParameter("pId");
			String[] pId = abc.split(",");
			rdpDao.addd(dId, pId);
			List<Project> pros = rdpDao.search(dId);
			PrintWriter out;
			out = response.getWriter();
			String str = "";
			for (int i = 0; i < pros.size(); i++) {
				str += "<li data-id=" + pros.get(i).getId() + "><a>" + pros.get(i).getName() + "</a></li>";
			}
			out.print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addProject(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			r_dep_proDao rdpDao = new r_dep_proDao();
			int dId = Integer.parseInt(request.getParameter("dId"));
			int pId = Integer.parseInt(request.getParameter("pId"));
			rdpDao.addd(dId, pId);
			List<Project> pros = rdpDao.search(dId);
			PrintWriter out = response.getWriter();
			String str = "";
			for (int i = 0; i < pros.size(); i++) {
				str += "<li  data-id=" + pros.get(i).getId() + "><a>" + pros.get(i).getName() + "</a></li>";
			}
			out.print(str);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showProject(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		DepartmentDao depDao = new DepartmentDao();
		Department depName = depDao.search(id);
		r_dep_proDao rdpDao = new r_dep_proDao();
		try {
			List<Project> pros = rdpDao.search(id);
			request.setAttribute("pros", pros);
			List<Project> list = rdpDao.search(pros);
			request.setAttribute("list", list);
			request.setAttribute("dId", id);
			request.setAttribute("depName", depName);
			request.getRequestDispatcher("WEB-INF/department/showProject2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showProject3(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		DepartmentDao depDao = new DepartmentDao();
		Department depName = depDao.search(id);
		r_dep_proDao rdpDao = new r_dep_proDao();
		try {
			List<Project> pros = rdpDao.search(id);
			request.setAttribute("pros", pros);
			List<Project> list = rdpDao.search(pros);
			request.setAttribute("list", list);
			request.setAttribute("dId", id);
			request.setAttribute("depName", depName);
			request.getRequestDispatcher("WEB-INF/department/showProject3.jsp").forward(request, response);
		} catch (ServletException e) {
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
			DepartmentDao depDao = new DepartmentDao();
			List<Department> list = depDao.search();
			request.setAttribute("list", list);
			int ye1 = 1;
			int ye2 = 1;

			Department condition = new Department();
			String name = request.getParameter("name");
			int empCount = -1;
			if (request.getParameter("empCount") != null && !"".equals(request.getParameter("empCount"))) {
				empCount = Integer.parseInt(request.getParameter("empCount"));
			}
			condition.setName(name);
			condition.setEmpCount(empCount);
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int count = depDao.searchCount(condition);
			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
			List<Department> deps = depDao.searchBYCondition(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
			request.setAttribute("ye1", ye1);
			request.setAttribute("ye2", ye2);
			request.setAttribute("p", p);
			request.setAttribute("c", condition);
			request.setAttribute("deps", deps);
			request.getRequestDispatcher("WEB-INF/department/deps.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public void show(HttpServletRequest request, HttpServletResponse response) {
	//
	// try {
	// int ye = 1;
	// if (request.getParameter("ye") != null) {
	// ye = Integer.parseInt(request.getParameter("ye"));
	// }
	// DepartmentDao depDao = new DepartmentDao();
	// int count = depDao.searchCount();
	// Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE,
	// Constant.EMP_NUM_OF_PAGE);
	// List<Department> deps = depDao.search(p.getBegin(),
	// Constant.EMP_NUM_IN_PAGE);
	// request.setAttribute("p", p);
	//
	// request.setAttribute("deps", deps);
	// request.getRequestDispatcher("WEB-INF/department/deps.jsp").forward(request,
	// response);
	// } catch (ServletException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {

		try {

			request.getRequestDispatcher("WEB-INF/department/addDepartment.jsp").forward(request, response);
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
			DepartmentDao depDao = new DepartmentDao();
			Department dep = new Department();
			String name = request.getParameter("name");
			dep.setName(name);
			boolean flag = depDao.addd(dep);
			// show(request, response);

			response.sendRedirect("deps");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showModify(HttpServletRequest request, HttpServletResponse response) {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			DepartmentDao depDao = new DepartmentDao();
			Department dep = depDao.search(id);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher("WEB-INF/department/modifyDepartment.jsp").forward(request, response);
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
			DepartmentDao depDao = new DepartmentDao();
			Department dep = new Department();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			dep.setId(id);
			dep.setName(name);
			boolean flag = depDao.update(dep);
			// show(request, response);

			response.sendRedirect("deps");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		DepartmentDao depDao = new DepartmentDao();
		boolean flag = depDao.delete(id);
		try {
			response.sendRedirect("deps");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteProject(HttpServletRequest request, HttpServletResponse response) {
		try {
			int dId = Integer.parseInt(request.getParameter("dId"));
			int pId = Integer.parseInt(request.getParameter("pId"));
			r_dep_proDao rdpDao = new r_dep_proDao();
			rdpDao.delete(dId, pId);
			List<Project> pros = rdpDao.search(dId);
			List<Project> proj = rdpDao.search(pros);
			PrintWriter out = response.getWriter();
			String str = "";
			for (int i = 0; i < proj.size(); i++) {
				str += "<li data-id=" + proj.get(i).getId() + "><a>" + proj.get(i).getName() + "</a></li>";
			}
			out.print(str);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void deleteProject3(HttpServletRequest request, HttpServletResponse response) {
		try {
			int dId = Integer.parseInt(request.getParameter("dId"));
			String abc = request.getParameter("pId");
			String[] pId = abc.split(",");

			r_dep_proDao rdpDao = new r_dep_proDao();
			rdpDao.delete(dId, pId);
			List<Project> pros = rdpDao.search(dId);
			List<Project> proj = rdpDao.search(pros);
			PrintWriter out = response.getWriter();
			String str = "";
			for (int i = 0; i < proj.size(); i++) {
				str += "<li data-id=" + proj.get(i).getId() + "><a>" + proj.get(i).getName() + "</a></li>";
			}
			out.print(str);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
}
