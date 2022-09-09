package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import bll.UserBLL;
import bo.User;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet(urlPatterns = { "/signin", "/signout", "/signup" })
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserBLL bll;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("signedin");
		response.sendRedirect("acceuil");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SIGNING IN");
		String name = request.getParameter("username");
		String password = request.getParameter("pwd");
		String redirect = request.getParameter("redirect");
		String email = request.getParameter("email");
		String method = request.getServletPath();
		User user = null;
		String sesouviens = request.getParameter("sesouviens");
		if (method.equals("/signup")) {
			user = new User();
			user.setUsername(name);
			user.setPswd(password);
			user.setEmail(email);
			bll.insert(user);
		}
//		if (sesouviens != null && sesouviens.equals("on")) {
//			Cookie souviensCookie = new Cookie("sesouviens", "1");
//			response.addCookie(souviensCookie);
//		}
		user = bll.selectByUsername(name);
		HttpSession session = request.getSession();
		session.removeAttribute("superuser");
		session.removeAttribute("signedin");
		if (user != null && user.getPswd().equals(password)) {
			session.setAttribute("signedin", user);
			System.out.println("user.getSuperuser() != NULL?");
			if (user.getSuperuser() != null) {
				System.out.println("user.getSuperuser() NOT NULL");
				session.setAttribute("superuser", user.getSuperuser());
			}
		}
		if (redirect != null && !redirect.isEmpty()) {
			response.sendRedirect(redirect);
		} else

		{
			response.sendRedirect("acceuil");
		}
	}

}
