package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import bll.CinemaBLL;
import bll.FilmBLL;
import bo.Cinema;

/**
 * Servlet implementation class CinemaServlet
 */
@WebServlet("/cinema")
public class CinemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CinemaBLL bllCinema;
	@Autowired
	private FilmBLL bllFilm;

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
		Cinema cinema = null;
		String cinemaid = request.getParameter("cinemaid");
		System.out.println("Cinema doGet, id: " + cinemaid);
		if (cinemaid != null)
			cinema = bllCinema.selectById(Integer.valueOf(cinemaid));

		if (cinema != null) {
			cinema.setFilms(bllFilm.selectByCinema(cinema));
			request.setAttribute("cinema", cinema);
		} else {
			System.out.println("Cinema Not Found: " + cinemaid);
		}
		// 3.Acceder a la jsp
		request.getRequestDispatcher("/WEB-INF/cinema.jsp").forward(request, response);
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
