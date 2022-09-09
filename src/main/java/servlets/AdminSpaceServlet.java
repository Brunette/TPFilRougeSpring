package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import bll.CinemaBLL;
import bll.FilmBLL;
import bll.UserBLL;
import bo.Cinema;
import bo.User;

/**
 * Servlet implementation class AdminSpaceServlet
 */
@WebServlet(urlPatterns = { "/admin", "/modifyuser" })
public class AdminSpaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CinemaBLL bllCinema;
	@Autowired
	private UserBLL bllUser;
	@Autowired
	private FilmBLL bllFilm;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Cinema> cinemas = null;
		cinemas = bllCinema.selectAll();
		List<User> users = null;
		users = bllUser.selectAll();
		if (cinemas.size() > 0) {
			for (Cinema current : cinemas) {
				current.setFilms(bllFilm.selectByCinema(current));
			}
			request.setAttribute("cinemas", cinemas);
		}
		if (users.size() > 0)
			request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String deleteid = request.getParameter("userid");
		if (deleteid != null) {
			bllUser.deleteById(Integer.valueOf(deleteid));
		}
		response.sendRedirect("admin");
	}

}
