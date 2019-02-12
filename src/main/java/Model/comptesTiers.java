package Model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Table(name = "plantiers")
public class comptesTiers implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	private Long idEntreprise;

	private String numero;
	private String intitule;
	private String type;
	private String comptecollectif;


	public comptesTiers() {
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String name) {
		this.numero = name;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String passportNumber) {
		this.intitule = passportNumber;
	}

  public String getType() {
    return type;
  }

  public void setComptecollectif(String passportNumber) {
    this.comptecollectif = passportNumber;
  }

	@Override
	public String toString() {
		return String.format("compteTiers [id=%s, identreprise=%s, numero=%s, intitule=%s, type=%s, comptecollectif=%s]", id, idEntreprise, numero,intitule,type,comptecollectif);
	}

}
