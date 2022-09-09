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
import bll.ModificationBLL;
import bll.SeanceBLL;
import bo.Cinema;
import bo.Modification;
import bo.Seance;
import bo.Superuser;

/**
 * Servlet implementation class GerantServlet
 */
@WebServlet(urlPatterns = { "/gerant", "/modifymod" })
public class GerantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private SeanceBLL bllSeance;
	@Autowired
	private CinemaBLL bllCinema;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Superuser suser = (Superuser) request.getSession().getAttribute("superuser");
		int cinemaid = 0;
		if (suser != null && suser.getCinema().getId() != 0) {
			cinemaid = suser.getCinema().getId();

		} else {
			if (request.getParameter("cinemaid") == null) {
				response.sendRedirect("acceuil");
				return;
			}
			cinemaid = Integer.valueOf(request.getParameter("cinemaid"));

		}
		List<Modification> modifications = bllModification.selectByCinemaID(cinemaid);
		request.setAttribute("modifications", modifications);
		Cinema cinema = bllCinema.selectById(cinemaid);
		cinema.setFilms(bllFilm.selectByCinema(cinema));
		request.setAttribute("cinema", cinema);
		request.getRequestDispatcher("/WEB-INF/gerant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String modStr = request.getParameter("modificationid");
		System.out.println("doPost Mod " + modStr);
		if (modStr != null) {
			Modification modification = bllModification.selectById(Integer.valueOf(modStr));
			if (modification.getType() == Modification.Type.Create) {
				Seance seance = new Seance();
				seance.setFilm(modification.getFilm());
				seance.setHeureSeance(modification.getHeureSeance());
				seance.setSalle(modification.getSalle());
				bllSeance.insert(seance);
			} else if (modification.getType() == Modification.Type.Update) {
				Seance seance = bllSeance.selectById(modification.getPrevSeance().getId());
				seance.setFilm(modification.getFilm());
				seance.setHeureSeance(modification.getHeureSeance());
				seance.setSalle(modification.getSalle());
				bllSeance.update(seance);
			} else if (modification.getType() == Modification.Type.Delete) {
				bllSeance.deleteById(modification.getPrevSeance().getId());
			}
			bllModification.deleteById(Integer.valueOf(modStr));
		} else {
			String deleteStr = request.getParameter("deleteid");
			if (deleteStr != null) {
				bllModification.deleteById(Integer.valueOf(deleteStr));
			}
		}
		response.sendRedirect("gerant");
	}

}
