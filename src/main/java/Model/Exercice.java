package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.GenerationType;

@Entity
public class Exercice {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long identreprise;
    private int annee;
    private int isclotured;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdentreprise() {
        return identreprise;
    }

    public void setIdentreprise(Long identreprise) {
        this.identreprise = identreprise;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getIsclotured() {
        return isclotured;
    }

    public void setIsclotured(int isclotured) {
        this.isclotured = isclotured;
    }
}
