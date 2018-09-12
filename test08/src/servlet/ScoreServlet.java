package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.ProjectDao;
import dao.ScoreDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class ScoreServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				String type = request.getParameter("type");
				if (type == null || "search".equals(type)) {
					search(request, response);
				}
				if ("updateBatch".equals(type)) {
					updateBatch(request, response);
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
			ScoreDao scDao = new ScoreDao();
			DepartmentDao depDao = new DepartmentDao();
			EmployeeDao empDao = new EmployeeDao();
			ProjectDao proDao = new ProjectDao();
			List<Department> deps = depDao.search();
			request.setAttribute("deps", deps);
			int ye1 = 1;
			int ye2 = 1;
			Score condition = new Score();
			String name = request.getParameter("name");
			String depName = request.getParameter("depName");
			String proName = request.getParameter("proName");
			int score = -1;
			if (request.getParameter("score") != null && !"".equals(request.getParameter("score"))) {
				score = Integer.parseInt(request.getParameter("score"));
			}
			String grade = request.getParameter("grade");
			Employee emp = new Employee();
			emp.setName(name);
			Department dep = new Department();
			dep.setName(depName);
			Project pro = new Project();
			pro.setName(proName);
			condition.setEmp(emp);
			condition.setDep(dep);
			condition.setPro(pro);
			condition.setScore(score);
			condition.setGrade(grade);
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int count = scDao.searchCount(condition);
			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
			List<Score> scores = scDao.searchBYCondition(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
			request.setAttribute("p", p);
			request.setAttribute("ye1", ye1);
			request.setAttribute("ye2", ye2);
			request.setAttribute("c", condition);
			request.setAttribute("scores", scores);
			request.getRequestDispatcher("WEB-INF/score/score.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateBatch(HttpServletRequest request, HttpServletResponse response) {
		try {
			ScoreDao scDao = new ScoreDao();
			String score = request.getParameter("score");
			List<Score> list = (List<Score>) JSONArray.toCollection(JSONArray.fromObject(score), Score.class);
			boolean flag = scDao.updateBatch(list);
			response.sendRedirect("score");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}
}