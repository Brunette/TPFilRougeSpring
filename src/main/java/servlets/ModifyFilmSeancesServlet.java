package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import bll.CinemaBLL;
import bll.FilmBLL;
import bll.SalleBLL;
import bll.SeanceBLL;
import bo.Cinema;
import bo.Film;
import bo.Salle;
import bo.Seance;
import bo.Superuser;

/**
 * Servlet implementation class ModifyFilmSeancesServlet
 */
@WebServlet("/modifyfilm")
public class ModifyFilmSeancesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FilmBLL bllFilm;
	@Autowired
	private CinemaBLL bllCinema;
	@Autowired
	private SeanceBLL bllSeance;
	@Autowired
	private SalleBLL bllSalle;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Film film = null;
		Cinema cinema = null;
		Integer filmid = Integer.valueOf(request.getParameter("filmid"));
		Integer cinemaid = Integer.valueOf(request.getParameter("cinemaid"));
		HttpSession session = request.getSession();
		Superuser superuser = (Superuser) session.getAttribute("superuser");
		if (superuser != null && (superuser.isAdmin() || superuser.getCinema().getId() == cinemaid)) {

			if (cinemaid != null)
				cinema = bllCinema.selectById(cinemaid);
			if (cinema != null) {
				if (filmid != null)
					film = bllFilm.selectById(filmid);
				if (film != null) {
					List<Seance> seances;
					request.setAttribute("film", film);
					seances = bllSeance.selectByCinemaFilm(cinema, film);
					request.setAttribute("seances", seances);
					request.setAttribute("cinema", cinema);
					request.getRequestDispatcher("/WEB-INF/modfilm.jsp").forward(request, response);
				}
			} else
				response.sendRedirect("acceuil");
		}

		else
			response.sendRedirect("acceuil");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String salleid = request.getParameter("salleid");
		String deleteid = request.getParameter("deleteid");
		Integer cinemaid = Integer.valueOf(request.getParameter("cinemaid"));
		String filmid = request.getParameter("filmid");
		if (deleteid != null) {
			if (filmid != null && cinemaid != null) {
				bllSeance.deleteById(Integer.valueOf(deleteid));
				response.sendRedirect("modifyfilm?filmid=" + filmid + "&cinemaid=" + cinemaid);
				return;
			}
		}

		String seanceid = request.getParameter("seanceid");
		String dateStr = request.getParameter("date");
		String timeStr = request.getParameter("time");
		LocalDateTime datetime = LocalDateTime.of(LocalDate.parse(dateStr), LocalTime.parse(timeStr));
		Seance seance = null;
		if (filmid != null && salleid != null) {
			if (seanceid == null) {
				System.out.println("Seance id == " + null + ", " + salleid + ", " + filmid);
				seance = new Seance();
				Film film = bllFilm.selectById(Integer.valueOf(filmid));
				Salle salle = bllSalle.selectById(Integer.valueOf(salleid));
				seance.setSalle(salle);
				seance.setFilm(film);
			} else {
				System.out.println("Seance id == " + seanceid);
				seance = bllSeance.selectById(Integer.valueOf(seanceid));
				if (seance.getFilm().getId() != Integer.valueOf(filmid)) {
					Film film = bllFilm.selectById(Integer.valueOf(filmid));
					seance.setFilm(film);
				}
				Salle salle = bllSalle.selectById(Integer.valueOf(salleid));
				seance.setSalle(salle);
			}
		}
		seance.setHeureSeance(datetime);
		if (seanceid != null)
			bllSeance.update(seance);
		else {
			bllSeance.insert(seance);
		}
		response.sendRedirect("modifyfilm?filmid=" + filmid + "&cinemaid=" + cinemaid);
	}

}
