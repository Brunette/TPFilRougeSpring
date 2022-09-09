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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cinemas")
public class Cinema {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "cinema_name")
	private String name;
	@Column(name = "social")
	private String link;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true, orphanRemoval = true)
	@JoinColumn(name = "adresseid")
	private Address address;

	@OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER)
	private List<Salle> salles = new ArrayList<Salle>();

	@Transient
	private List<Seance> seances = new ArrayList<Seance>();

	@OneToOne(mappedBy = "cinema", fetch = FetchType.EAGER)
	private Superuser gerant;

	@Transient
	private List<Film> films = new ArrayList<Film>();

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Cinema() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Salle> getSalles() {
		return salles;
	}

	public void setSalles(List<Salle> salles) {
		this.salles = salles;
	}

	public Superuser getGerant() {
		return gerant;
	}

	public void setGerant(Superuser gerant) {
		this.gerant = gerant;
	}

	@Override
	public boolean equals(Object o) {
		return this.id == ((Cinema) o).getId();
	}

	public List<Seance> getSeances() {
		return seances;
	}

	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}

}
