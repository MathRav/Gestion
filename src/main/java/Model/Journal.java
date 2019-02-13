package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int idEntreprise;
    private String code;
    private String intitule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(int idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    public Journal(){}
    public Journal(int id, int idEntreprise, String code, String intitule) {
        this.id = id;
        this.idEntreprise = idEntreprise;
        this.code = code;
        this.intitule = intitule;
    }
}
