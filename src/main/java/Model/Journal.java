package Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Journal {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long idEntreprise;
    private String code;
    private String intitule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Long idEntreprise) {
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
    public Journal(Long id, Long idEntreprise, String code, String intitule) {
        this.id = id;
        this.idEntreprise = idEntreprise;
        this.code = code;
        this.intitule = intitule;
    }
}
