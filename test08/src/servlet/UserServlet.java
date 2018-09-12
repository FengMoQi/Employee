package servlet;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;
import entity.ValidateCode;
import util.CodeUtil;
import util.CreateMD5;
import util.RandomNumber;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		if (type == null) {
			showLogin(request, response);
		} else if ("doLogin".equals(type)) {
			doLogin(request, response);
		} else if ("showRegiste".equals(type)) {
			showRegiste(request, response);
		} else if ("registe".equals(type)) {
			registe(request, response);
		} else if ("randomImage".equals(type)) {
			randomImage(request, response);
		}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void registe(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserDao ud = new UserDao();

		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("passwords");
			User user = new User();
			user.setUserName(userName);

			user.setPassword(CreateMD5.getMD5(password + "PASSWORD"));
			boolean flag = ud.add(user);
			if (flag) {
				// response.sendRedirect("success.jsp");
				response.sendRedirect("user");
			} else {
				// response.sendRedirect("fail.jsp");
				response.sendRedirect("user?type=showRegiste");
				// request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ud = null;  		主动垃圾回收机制
	}

	private void showRegiste(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/user/registe.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if ("username".equals(cookies[i].getName())) {
						name = cookies[i].getValue();
					}
				}
			}
			request.setAttribute("name", name);
			request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			String userName = request.getParameter("text1");
			String password = request.getParameter("text2");
			String yanzhengma = request.getParameter("text3");
			if (yanzhengma.equals(session.getAttribute("rand"))) {

				User user = new User();
				user.setUserName(userName);
				user.setPassword(CreateMD5.getMD5(password + "PASSWORD"));
				UserDao ud = new UserDao();
				boolean flag = ud.search(user);
				if (flag) {

					session.setAttribute("user", userName);
					Cookie cookie = new Cookie("username", userName);
					cookie.setMaxAge(60 * 60); // 设置时间（1小时）
					response.addCookie(cookie);
					// response.sendRedirect("success.jsp");
					response.sendRedirect("main");
					// request.getRequestDispatcher("WEB-INF/main/main.jsp").forward(request,
					// response);

				} else {
					// response.sendRedirect("fail.jsp");
					response.sendRedirect("user");
					// request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);
				}
			} else {
				// response.sendRedirect("fail.jsp");
				response.sendRedirect("user");
				// request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void randomImage(HttpServletRequest request, HttpServletResponse response) {
		try {

			RandomNumber rn = new RandomNumber();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);

			ValidateCode vc = rn.generateImage();

			ServletOutputStream outStream = response.getOutputStream();
			// 輸出圖像界面
			ImageIO.write(vc.getImage(), "JPEG", outStream);
			outStream.close();
			request.getSession().setAttribute("rand", vc.getRandom());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// // 调用工具类生成的验证码和验证码图片
		// Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
		//
		// // 将四位数字的验证码保存到Session中。
		// HttpSession session = request.getSession();
		// session.setAttribute("code", codeMap.get("code").toString());
		//
		// // 禁止图像缓存。
		// response.setHeader("Pragma", "no-cache");
		// response.setHeader("Cache-Control", "no-cache");
		// response .setDateHeader("Expires", -1);
		//
		// response.setContentType("image/jpeg");
		//
		// // 将图像输出到Servlet输出流中。
		// ServletOutputStream sos;
		// try {
		// sos = response.getOutputStream();
		// ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
		// sos.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

}
