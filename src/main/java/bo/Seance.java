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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "seances")

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
