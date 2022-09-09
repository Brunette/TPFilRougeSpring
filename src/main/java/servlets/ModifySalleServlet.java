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

import bll.FilmBLL;
import bll.ModificationBLL;
import bll.SalleBLL;
import bll.SeanceBLL;
import bll.UserBLL;
import bo.Film;
import bo.Modification;
import bo.Salle;
import bo.Seance;
import bo.Superuser;
import bo.User;

/**
 * Servlet implementation class ModifySalleServlet
 */
@WebServlet("/modifysalle")
public class ModifySalleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserBLL bllUser;
	@Autowired
	private SeanceBLL bllSeance;
	@Autowired
	private SalleBLL bllSalle;
	@Autowired
	private FilmBLL bllFilm;
	@Autowired
	private ModificationBLL bllModification;

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
		// TODO Auto-generated method stub
		Salle salle = null;
		Integer salleid = Integer.valueOf(request.getParameter("salleid"));
		User gerant = bllUser.selectBySalleID(salleid);

		HttpSession session = request.getSession();
		Superuser superuser = (Superuser) session.getAttribute("superuser");
		if (superuser != null && (superuser.isAdmin() || superuser.getId() == gerant.getId())) {
			if (salleid != null) {
				List<Film> films = bllFilm.selectAll();
				salle = bllSalle.selectById(salleid.intValue());
				List<Seance> seances;
				request.setAttribute("salle", salle);
				seances = bllSeance.selectBySalle(salle);
				request.setAttribute("seances", seances);
				request.setAttribute("films", films);
				request.getRequestDispatcher("/WEB-INF/modsalle.jsp").forward(request, response);
			} else
				response.sendRedirect("acceuil");
		} else
			response.sendRedirect("acceuil");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String salleid = request.getParameter("salleid");
		String deleteid = request.getParameter("deleteid");
		if (deleteid != null) {
			if (salleid != null) {
				bllSeance.deleteById(Integer.valueOf(deleteid));
				response.sendRedirect("modifysalle?salleid=" + salleid);
				return;
			}
		}

		String seanceid = request.getParameter("seanceid");
		String filmid = request.getParameter("filmid");
		String dateStr = request.getParameter("date");
		String timeStr = request.getParameter("time");
		LocalDateTime datetime = LocalDateTime.of(LocalDate.parse(dateStr), LocalTime.parse(timeStr));
		Seance seance = null;

		HttpSession session = request.getSession();
		Superuser superuser = (Superuser) session.getAttribute("superuser");

		if (filmid != null && salleid != null)

		{
			if (seanceid == null) {
				System.out.println("Seance id == " + null + ", " + salleid + ", " + filmid);
				seance = new Seance();
				Film film = bllFilm.selectById(Integer.valueOf(filmid));
				// seance.setSalleId(Integer.valueOf(salleid));
				seance.setFilm(film);
			} else {
				System.out.println("Seance id == " + seanceid);
				seance = bllSeance.selectById(Integer.valueOf(seanceid));
				if (seance.getFilm().getId() != Integer.valueOf(filmid)) {
					Film film = bllFilm.selectById(Integer.valueOf(filmid));
					seance.setFilm(film);
				}
			}
		}
		seance.setHeureSeance(datetime);
		if (superuser != null && superuser.isAdmin()) {
			Modification modification = new Modification();
			modification.setEditor(superuser);
			modification.setNewSeance(seance);
			bllModification.insert(modification);
		} else {
			if (seanceid != null)
				bllSeance.update(seance);
			else {
				bllSeance.insert(seance);
			}
		}
		response.sendRedirect("modifysalle?salleid=" + salleid);
	}

}
