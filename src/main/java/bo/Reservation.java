package bo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")

@NamedQueries({ @NamedQuery(name = "trouverTousReservations", query = "SELECT r FROM Reservation r"),
		@NamedQuery(name = "trouverReservationByUser", query = "SELECT r FROM Reservation r WHERE r.user.id = :userid"),
		@NamedQuery(name = "trouverReservationById", query = "SELECT r FROM Reservation r WHERE r.id = :id"),
		@NamedQuery(name = "supprimerReservationById", query = "DELETE from Reservation WHERE id = :id") })
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "seanceId")
	private Seance seance;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@Column(name = "nb_places")
	private int nbPlaces;

	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public User getUtilisateur() {
		return user;
	}

	public void setUtilisateur(User user) {
		this.user = user;
	}

}
