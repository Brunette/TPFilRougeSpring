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
@Table(name = "salles")
@NamedQueries({ @NamedQuery(name = "trouverTousSalles", query = "SELECT s FROM Salle s"),
		@NamedQuery(name = "trouverSalleById", query = "SELECT s FROM Salle s WHERE s.id = :id"),
		@NamedQuery(name = "trouverSallesByCinema", query = "SELECT s FROM Salle s WHERE s.cinema.id = :cinemaid"),
		@NamedQuery(name = "supprimerSalleById", query = "DELETE from Salle WHERE id = :id") })
public class Salle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cinemaId")
	private Cinema cinema;

	@Column(name = "salleNum")
	private int salleNum;
	@Column(name = "numSeats")
	private int numSeats;

	public Salle() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalleNum() {
		return salleNum;
	}

	public void setSalleNum(int salleNum) {
		this.salleNum = salleNum;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

}
