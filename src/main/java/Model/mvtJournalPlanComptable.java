package Model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "MvtJournalPlanComptable")
public class mvtJournalPlanComptable implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	private int codeJournal;
	private String intituleJournal;
	private String numerocompte;
	private String intitulePlanComptable;
	private String numerotiers;
        private String intitulePlantiers;
        private java.sql.Date date_Mvt;
        private String reference;
        private String libelle;
        private java.sql.Date echeance;
        private Double debit;
        private Double credit;

    public mvtJournalPlanComptable(Long id, int codeJournal, String intituleJournal, String numerocompte, String intitulePlanComptable, String numerotiers, String intitulePlantiers, Date date_Mvt, String reference, String libelle, Date echeance, Double debit, Double credit) {
        this.id = id;
        this.codeJournal = codeJournal;
        this.intituleJournal = intituleJournal;
        this.numerocompte = numerocompte;
        this.intitulePlanComptable = intitulePlanComptable;
        this.numerotiers = numerotiers;
        this.intitulePlantiers = intitulePlantiers;
        this.date_Mvt = date_Mvt;
        this.reference = reference;
        this.libelle = libelle;
        this.echeance = echeance;
        this.debit = debit;
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodeJournal() {
        return codeJournal;
    }

    public void setCodeJournal(int codeJournal) {
        this.codeJournal = codeJournal;
    }

    public String getIntituleJournal() {
        return intituleJournal;
    }

    public void setIntituleJournal(String intituleJournal) {
        this.intituleJournal = intituleJournal;
    }

    public String getNumerocompte() {
        return numerocompte;
    }

    public void setNumerocompte(String numerocompte) {
        this.numerocompte = numerocompte;
    }

    public String getIntitulePlanComptable() {
        return intitulePlanComptable;
    }

    public void setIntitulePlanComptable(String intitulePlanComptable) {
        this.intitulePlanComptable = intitulePlanComptable;
    }

    public String getNumerotiers() {
        return numerotiers;
    }

    public void setNumerotiers(String numerotiers) {
        this.numerotiers = numerotiers;
    }

    public String getIntitulePlantiers() {
        return intitulePlantiers;
    }

    public void setIntitulePlantiers(String intitulePlantiers) {
        this.intitulePlantiers = intitulePlantiers;
    }

    public Date getDate_Mvt() {
        return date_Mvt;
    }

    public void setDate_Mvt(Date date_Mvt) {
        this.date_Mvt = date_Mvt;
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

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }


	public mvtJournalPlanComptable() {
//		super();
	}


}
