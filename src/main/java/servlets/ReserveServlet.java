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

import bll.ReservationBLL;
import bll.SeanceBLL;
import bo.Reservation;
import bo.Seance;
import bo.User;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/reserve")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	ReservationBLL bllReservation;
	@Autowired
	SeanceBLL bllSeance;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String seanceStr = request.getParameter("sc");
		String quantity = request.getParameter("quantity");
		int seanceId = -1;
		if (seanceStr != null) {
			seanceId = Integer.valueOf(seanceStr);
			System.out.println("Seance ID " + seanceId + " #" + quantity);
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("signedin");
		if (user != null && quantity != null) {
			Reservation reservation = new Reservation();
			Seance seance = bllSeance.selectById(seanceId);
			reservation.setSeance(seance);
			reservation.setUtilisateur(user);
			reservation.setNbPlaces(Integer.valueOf(quantity));
			bllReservation.insert(reservation);
			// TODO Reserve based off of UserID + SeanceId + quantity
			response.sendRedirect("profile");
		} else {
			response.sendRedirect("acceuil");
		}
	}

}
