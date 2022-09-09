package bo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "modifications")
@NamedQueries({
		@NamedQuery(name = "trouverModifByCinema", query = "SELECT m FROM Modification m LEFT JOIN m.salle as s LEFT JOIN s.cinema as c where c.id = :cinemaid"),
		@NamedQuery(name = "trouverModificationById", query = "SELECT m FROM Modification m WHERE m.id = :id"),
		@NamedQuery(name = "supprimerModificationById", query = "DELETE from Modification WHERE id = :id") })
public class Modification {

	public enum Type {
		Create, Delete, Update
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salleId")
	private Salle salle;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "filmId")
	private Film film;
	@Column(name = "heure_seance")
	private LocalDateTime heureSeance;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seanceId")
	private Seance prevSeance;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "editorId")
	private Superuser editor;

	public void setNewSeance(Seance newSeance) {
		this.salle = newSeance.getSalle();
		this.film = newSeance.getFilm();
		this.setHeureSeance(newSeance.getHeureSeance());
	}

	@Transient
	private Type type;

	public Modification() {

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

	public Superuser getEditor() {
		return editor;
	}

	public void setEditor(Superuser editor) {
		this.editor = editor;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Seance getPrevSeance() {
		return prevSeance;
	}

	public void setPrevSeance(Seance prevseance) {
		this.prevSeance = prevseance;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
