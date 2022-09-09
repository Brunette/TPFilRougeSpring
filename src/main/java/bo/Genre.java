package bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "genres")
@NamedQueries({ @NamedQuery(name = "trouverTousGenresUsed", query = "SELECT g FROM Genre g") })
//TODO sNamedQueries({ @NamedQuery(name = "trouverTousGenresUsed", query = "SELECT g FROM Genre  where USED") })

public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "genre_name")
	private String name;

	public Genre() {
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

}
