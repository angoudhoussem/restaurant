package com.itgate.restaurant.model;

import java.util.List;

/**
 * Created by macbook on 04/05/2016.
 */
public class Plat {


    private Long idPlat;

    private String libellePlat;

    private String designationPlat;

    private String imagePlat;

    private double prixPlat;

    private boolean disponible;

    private List<LigneCommande> ligneCommande;

    public Plat() {
    }

    public Long getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(Long idPlat) {
        this.idPlat = idPlat;
    }

    public String getLibellePlat() {
        return libellePlat;
    }

    public void setLibellePlat(String libellePlat) {
        this.libellePlat = libellePlat;
    }

    public String getDesignationPlat() {
        return designationPlat;
    }

    public void setDesignationPlat(String designationPlat) {
        this.designationPlat = designationPlat;
    }

    public String getImagePlat() {
        return imagePlat;
    }

    public void setImagePlat(String imagePlat) {
        this.imagePlat = imagePlat;
    }

    public double getPrixPlat() {
        return prixPlat;
    }

    public void setPrixPlat(double prixPlat) {
        this.prixPlat = prixPlat;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<LigneCommande> getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(List<LigneCommande> ligneCommande) {
        this.ligneCommande = ligneCommande;
    }
}
