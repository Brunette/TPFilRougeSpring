package bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "trouverTousUsers", query = "SELECT u FROM User u"),
		@NamedQuery(name = "trouverUserById", query = "SELECT u FROM User u JOIN fetch u.superuser WHERE u.id = :id"),
		@NamedQuery(name = "trouverUserByUsername", query = "SELECT u FROM User u JOIN u.superuser WHERE u.username = :username"),
		@NamedQuery(name = "trouverUserBySalle", query = "SELECT u FROM User u LEFT JOIN u.superuser as superuser LEFT JOIN superuser.cinema as cinema LEFT JOIN cinema.salles as salle WHERE salle.id = :salleid"),
		@NamedQuery(name = "supprimerUserById", query = "DELETE from User WHERE id = :id") })

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "username")
	private String username;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "subbed_news")
	private boolean subNews;
	@Column(name = "cpo")
	private String codePostal;
	@Column(name = "email")
	private String email;
	@Column(name = "pswd")
	private String pswd;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Superuser superuser;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Reservation> reservations = new ArrayList<Reservation>();

	public User() {
		// TODO Auto-generated constructor stub
		id = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public boolean isSubNews() {
		return subNews;
	}

	public void setSubNews(boolean subNews) {
		this.subNews = subNews;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Superuser getSuperuser() {
		return superuser;
	}

	public void setSuperuser(Superuser superuser) {
		this.superuser = superuser;
	}

}
