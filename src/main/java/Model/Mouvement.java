/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Rasamoelison
 */
@Entity
@Table(name="mvt")
public class Mouvement implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private Long id_Journal;
    private Date date_Mvt;
    private String reference;
    private Long id_compte;
    private Long id_tiers;
    private Long id_exercice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_Journal() {
        return id_Journal;
    }

    public void setId_Journal(Long id_Journal) {
        this.id_Journal = id_Journal;
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

    public Long getId_compte() {
        return id_compte;
    }

    public void setId_compte(Long id_compte) {
        this.id_compte = id_compte;
    }

    public Long getId_tiers() {
        return id_tiers;
    }

    public void setId_tiers(Long id_tiers) {
        this.id_tiers = id_tiers;
    }

    public Long getId_exercice() {
        return id_exercice;
    }

    public void setId_exercice(Long id_exercice) {
        this.id_exercice = id_exercice;
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
    private String libelle;
    private Date echeance;
    private double debit;
    private double credit;

    public Mouvement() {
    }

    public Mouvement(Long id, Long id_Journal, Date date_Mvt, String reference, Long id_compte, Long id_tiers, Long id_exercice, String libelle, Date echeance, double debit, double credit) {
        this.id = id;
        this.id_Journal = id_Journal;
        this.date_Mvt = date_Mvt;
        this.reference = reference;
        this.id_compte = id_compte;
        this.id_tiers = id_tiers;
        this.id_exercice = id_exercice;
        this.libelle = libelle;
        this.echeance = echeance;
        this.debit = debit;
        this.credit = credit;
    }
    
}
