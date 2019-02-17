package Model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "plancomptable")
public class planComptable implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idEntreprise;

	private String code;
	private String intitule;


	public planComptable() {
//		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

  public Long getIdEntreprise() {
		return idEntreprise;
	}

	public void setIdEntreprise(Long id) {
		this.idEntreprise = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String name) {
		this.code = name;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String passportNumber) {
		this.intitule = passportNumber;
	}



	@Override
	public String toString() {
		return String.format("planComptable [id=%s, identreprise=%s, code=%s, intitule=%s]", id, idEntreprise, code,intitule);
	}

}
