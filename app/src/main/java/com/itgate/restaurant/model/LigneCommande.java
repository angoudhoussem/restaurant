package com.itgate.restaurant.model;

/**
 * Created by macbook on 04/05/2016.
 */
public class LigneCommande {

    private Long idLigneCommande;

    private double prixPlat;

    private double quantite;

    private Commande commande;

    private Plat plat;

    private boolean etat;

    public LigneCommande() {
    }

    public Long getIdLigneCommande() {
        return idLigneCommande;
    }

    public void setIdLigneCommande(Long idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    public double getPrixPlat() {
        return prixPlat;
    }

    public void setPrixPlat(double prixPlat) {
        this.prixPlat = prixPlat;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
}
