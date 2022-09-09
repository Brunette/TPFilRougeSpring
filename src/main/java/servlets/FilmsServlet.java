package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import bll.FilmBLL;
import bll.GenreBLL;
import bo.Film;
import bo.Genre;

/**
 * Servlet implementation class cinemasServlet
 */
@WebServlet("/films")
public class FilmsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FilmBLL bllFilm;
	@Autowired
	private GenreBLL bllGenre;

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
		List<Film> films = null;
		Map<String, String[]> paramMap = request.getParameterMap();
		Enumeration<String> params = request.getParameterNames();
		List<String> genreNames = new ArrayList<>();
		while (params.hasMoreElements()) {
			String val = params.nextElement();
			if (paramMap.get(val) != null && paramMap.get(val)[0].equals("on")) {
				genreNames.add(val);
			}
		}
		if (genreNames.size() > 0)
			films = bllFilm.selectByGenre(genreNames);
		else
			films = bllFilm.selectAll();

		if (films.size() > 0) {
			request.setAttribute("films", films);
		}
		List<Genre> genres = bllGenre.selectUsed();
		if (genres.size() > 0) {
			request.setAttribute("genres", genres);
		}
		// System.out.println("DoGetted'd " + films.size());
		// 3.Acceder a la jsp
		request.getRequestDispatcher("/WEB-INF/films.jsp").forward(request, response);
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
