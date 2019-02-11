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
@Table(name = "mvtotal2")
public class MvSolde implements Serializable {

    @Id
    //private Long id;
    private Long idCompte;
    private Long numCompte;
    private double debit;
    private double credit;
    private double solde;

    public MvSolde() {
//		super();
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public Long getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(Long numCompte) {
        this.numCompte = numCompte;
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

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
