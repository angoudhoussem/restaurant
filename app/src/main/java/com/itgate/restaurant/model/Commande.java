package com.itgate.restaurant.model;

import java.util.Date;
import java.util.List;

/**
 * Created by macbook on 04/05/2016.
 */
public class Commande {


    private Long idCommande;

    private String dateCommande;

    private boolean etatCommande;

    private Table tableCommande;

    private List<LigneCommande> ligneCommande;

    private Utilisateur utilisateur;

    private Long idFirstPlat;


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Long getIdFirstPlat() {
        return idFirstPlat;
    }

    public void setIdFirstPlat(Long idFirstPlat) {
        this.idFirstPlat = idFirstPlat;
    }


    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public boolean isEtatCommande() {
        return etatCommande;
    }

    public void setEtatCommande(boolean etatCommande) {
        this.etatCommande = etatCommande;
    }


    public List<LigneCommande> getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(List<LigneCommande> ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    public Table getTableCommande() {
        return tableCommande;
    }

    public void setTableCommande(Table tableCommande) {
        this.tableCommande = tableCommande;
    }

    public Long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Long idCommande) {
        this.idCommande = idCommande;
    }
}
