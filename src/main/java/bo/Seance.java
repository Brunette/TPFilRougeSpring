package bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "seances")
@NamedQueries({ @NamedQuery(name = "trouverTousSeances", query = "SELECT s FROM Seance s"),
		@NamedQuery(name = "trouverSeanceById", query = "SELECT s FROM Seance s WHERE s.id = :id"),
		@NamedQuery(name = "trouverSeanceByCinema", query = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as sl LEFT JOIN sl.cinema as c LEFT JOIN sc.film as f where c.id = :cinemaid ORDER BY f.name,TIME(sc.heureSeance) ASC"),
		@NamedQuery(name = "trouverSeanceByCinemaDay", query = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as sl LEFT JOIN sl.cinema as c LEFT JOIN sc.film as f where c.id = :cinemaid AND DATE(sc.heureSeance) = :date ORDER BY f.name,TIME(sc.heureSeance) ASC"),
		@NamedQuery(name = "trouverSeanceByCinemaFilm", query = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as sl LEFT JOIN sl.cinema as c where c.id = :cinemaid AND sc.film.id = :filmid ORDER BY sc.film.name ASC"),
		@NamedQuery(name = "trouverSeanceByCinemaDayFilm", query = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as salle LEFT JOIN salle.cinema as cinema WHERE cinema.id = :cinemaid AND sc.film.id = :filmid AND DATE(sc.heureSeance) = :date ORDER BY sc.film.name, TIME(sc.heureSeance) ASC"),
		@NamedQuery(name = "trouverSeanceBySalle", query = "SELECT sc FROM Seance sc LEFT JOIN sc.salle as sl LEFT JOIN sc.film as f WHERE sl.id = :salleid ORDER BY sc.heureSeance ASC"),
		@NamedQuery(name = "supprimerSeanceById", query = "DELETE from Seance WHERE id = :id") })

public class Seance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "salleId")
	private Salle salle;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "filmId")
	private Film film;

	@Column(name = "heure_seance")
	private LocalDateTime heureSeance;

	@OneToMany(mappedBy = "seance", fetch = FetchType.LAZY)
	private List<Reservation> reservations = new ArrayList<Reservation>();

	public Seance() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getHeureSeance() {
		return heureSeance;
	}

	public void setHeureSeance(LocalDateTime heureSeance) {
		this.heureSeance = heureSeance;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	@Transient
	public String getDate() {
		return heureSeance.toLocalDate().toString();
	}

	@Transient
	public String getTime() {
		return heureSeance.toLocalTime().toString();
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

}
