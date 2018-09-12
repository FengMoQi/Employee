package servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class EmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			
				request.setCharacterEncoding("utf-8");
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
				} else if ("deleteBatch".equals(type)) {
					deleteBatch(request, response);
				} else if ("showModifyBatch1".equals(type)) {
					showModifyBatch1(request, response);
				} else if ("updateBatch1".equals(type)) {
					updateBatch1(request, response);
				} else if ("showModifyBatch2".equals(type)) {
					showModifyBatch2(request, response);
				} else if ("updateBatch2".equals(type)) {
					updateBatch2(request, response);
				} else if ("updateBatch3".equals(type)) {
					updateBatch3(request, response);
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
			DepartmentDao depDao = new DepartmentDao();
			List<Department> list = depDao.search();
			request.setAttribute("deps", list);
			int ye1 = 1;
			int ye2 = 1;
			EmployeeDao empDao = new EmployeeDao();
			Employee condition = new Employee();
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = -1;
			if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
				age = Integer.parseInt(request.getParameter("age"));
			}
			String depName = request.getParameter("depName");
			condition.setName(name);
			condition.setSex(sex);
			condition.setAge(age);
			Department dep = new Department();
			dep.setName(depName);
			condition.setDep(dep);
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int count = empDao.searchCount(condition);
			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
			List<Employee> emps = empDao.searchBYCondition(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
			request.setAttribute("p", p);
			request.setAttribute("ye1", ye1);
			request.setAttribute("ye2", ye2);
			request.setAttribute("c", condition);
			request.setAttribute("emps", emps);
			request.getRequestDispatcher("WEB-INF/employee/emps.jsp").forward(request, response);
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
	// EmployeeDao empDao = new EmployeeDao();
	// int count = empDao.searchCount();
	// Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE,
	// Constant.EMP_NUM_OF_PAGE);
	// List<Employee> emps = empDao.search(p.getBegin(), Constant.EMP_NUM_IN_PAGE);
	// request.setAttribute("p", p);
	//
	// request.setAttribute("emps", emps);
	// request.getRequestDispatcher("WEB-INF/employee/emps.jsp").forward(request,
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
		DepartmentDao depDao = new DepartmentDao();
		try {

			List<Department> list = depDao.search();
			request.setAttribute("deps", list);
			request.getRequestDispatcher("WEB-INF/employee/addEmployee.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {

			String path = "e:/shangchuan/";
			UUID uuid = UUID.randomUUID();
			String hz = "";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			String name = "";
			String sex = "";
			String age = "";
			String depID = "";
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);

				if ("name".equals(item.getFieldName())) {
					name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if ("sex".equals(item.getFieldName())) {
					sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if ("age".equals(item.getFieldName())) {
					age = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if ("depID".equals(item.getFieldName())) {
					depID = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if ("myFile".equals(item.getFieldName())) {
					hz = item.getName().substring(item.getName().lastIndexOf("."));
					File savedFile = new File(path, uuid.toString() + hz);
					item.write(savedFile);
				}
			}

			EmployeeDao empDao = new EmployeeDao();
			Employee emp = new Employee();

			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(Integer.parseInt(age));
			Department dep = new Department();
			dep.setId(Integer.parseInt(depID));
			emp.setDep(dep);
			emp.setPicture(uuid.toString() + hz);
			boolean flag = empDao.addd(emp);
			// show(request, response);

			response.sendRedirect("emps");
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showModify(HttpServletRequest request, HttpServletResponse response) {

		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> list = depDao.search();
			request.setAttribute("deps", list);
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDao empDao = new EmployeeDao();
			Employee emp = empDao.search(id);
			request.setAttribute("emp", emp);
			request.getRequestDispatcher("WEB-INF/employee/modifyEmployee.jsp").forward(request, response);
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

			Department dep = new Department();
			EmployeeDao empDao = new EmployeeDao();
			Employee emp = new Employee();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			int depID = Integer.parseInt(request.getParameter("depID"));
			emp.setId(id);
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			dep.setId(depID);
			emp.setDep(dep);
			boolean flag = empDao.update(emp);
			// show(request, response);

			response.sendRedirect("emps");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeDao empDao = new EmployeeDao();
		boolean flag = empDao.delete(id);
		try {
			response.sendRedirect("emps");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteBatch(HttpServletRequest request, HttpServletResponse response) {

		String ids = request.getParameter("ids");
		EmployeeDao empDao = new EmployeeDao();
		boolean flag = empDao.deleteBatch(ids);
		try {
			response.sendRedirect("emps");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showModifyBatch1(HttpServletRequest request, HttpServletResponse response) {

		try {
			String ids = request.getParameter("ids");
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> emp = empDao.searchBatch(ids);
			request.setAttribute("emp", emp.get(0));
			request.setAttribute("ids", ids);
			request.getRequestDispatcher("WEB-INF/employee/modifyBatch1Employee.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateBatch1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			EmployeeDao empDao = new EmployeeDao();
			Employee emp = new Employee();
			String ids = (String) request.getParameter("ids");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			boolean flag = empDao.updateBatch1(ids, emp);
			// show(request, response);

			response.sendRedirect("emps");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showModifyBatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("ids");
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> emps = empDao.searchBatch(ids);
			request.setAttribute("emps", emps);
			request.getRequestDispatcher("WEB-INF/employee/modifyBatch2Employee.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateBatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			EmployeeDao empDao = new EmployeeDao();

			String emps = request.getParameter("emps");
			String[] array = emps.split(";");
			List<Employee> list = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				String[] temp = array[i].split(",");
				Employee emp = new Employee();
				emp.setId(Integer.parseInt(temp[0]));
				emp.setName(temp[1]);
				emp.setSex(temp[2]);
				emp.setAge(Integer.parseInt(temp[3]));
				list.add(emp);
			}
			boolean flag = empDao.updateBatch2(list);
			// show(request, response);

			response.sendRedirect("emps");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateBatch3(HttpServletRequest request, HttpServletResponse response) {
		try {
			EmployeeDao empDao = new EmployeeDao();
			String emps = request.getParameter("emps");
			List<Employee> list = (List<Employee>) JSONArray.toCollection(JSONArray.fromObject(emps), Employee.class);
			System.out.println(list);
			boolean flag = empDao.updateBatch2(list);
			response.sendRedirect("emps");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
}
