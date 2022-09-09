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
import bll.SeanceBLL;
import bo.Cinema;

/**
 * Servlet implementation class AcceuilServlet
 */
@WebServlet("/acceuil")
public class AcceuilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CinemaBLL bllCinema;
	@Autowired
	private FilmBLL bllFilm;
	@Autowired
	private SeanceBLL bllSeance;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Cinema> cinemas = null;
		String filter = request.getParameter("filter");
		if (filter != null)
			cinemas = bllCinema.selectByCritere(filter);
		else
			cinemas = bllCinema.selectAll();

		if (cinemas.size() > 0) {
			for (Cinema current : cinemas) {
				current.setFilms(bllFilm.selectByCinema(current));
			}
			request.setAttribute("cinemas", cinemas);
		}
		System.out.println("DoGetted'd " + cinemas.size());
		// 3.Acceder a la jsp
		request.getRequestDispatcher("/WEB-INF/acceuil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
