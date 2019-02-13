package Model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import org.hibernate.annotations.Immutable;
import java.sql.Date;
@Entity
@Immutable
@Table(name = "mvtotal")
public class mvtotal implements Serializable{
	@Id
	private Long id;
	private Long idExercice;
	private String codeJournal;
	private String intituleJournal;
	private Long idCompte;
	private String intitulePlanComptable;
	private String numeroTiers;
	private String intitulePlantiers;
	private Date dateMvt;
	private String reference;
	private String libelle;
	private Date echeance;
	private double debit;
	private double credit;

	public mvtotal() {
//		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

  public Long getIdExercice() {
		return idExercice;
	}

	public void setIdExercice(Long id) {
		this.idExercice = id;
	}

  public String getCodeJournal() {
		return codeJournal;
	}

	public void setCodeJournal(String id) {
		this.codeJournal = id;
	}

  public String getIntituleJournal(){
    return this.intituleJournal;
  }

  public void setIntituleJournal(String vao){
    intituleJournal=vao;
  }
  public Long getIdCompte(){
    return idCompte;
  }
  public void setIdCompte(Long vao){
  this.idCompte=vao;
  }

    public String getIntitulePlanComptable() {
        return intitulePlanComptable;
    }

    public void setIntitulePlanComptable(String intitulePlanComptable) {
        this.intitulePlanComptable = intitulePlanComptable;
    }

    public String getNumeroTiers() {
        return numeroTiers;
    }

    public void setNumeroTiers(String numeroTiers) {
        this.numeroTiers = numeroTiers;
    }

    public String getIntitulePlantiers() {
        return intitulePlantiers;
    }

    public void setIntitulePlantiers(String intitulePlantiers) {
        this.intitulePlantiers = intitulePlantiers;
    }

    public Date getDateMvt() {
        return dateMvt;
    }

    public void setDateMvt(Date dateMvt) {
        this.dateMvt = dateMvt;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

}
