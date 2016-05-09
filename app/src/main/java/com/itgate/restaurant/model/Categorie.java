package com.itgate.restaurant.model;

import java.util.Collection;

/**
 * Created by macbook on 04/05/2016.
 */
public class Categorie {

    private Long idCategorie;

    private String nomCategorie;
    private String designationCategorie;
    private String imageCategorie;
    private Collection<Plat> plats;

    public Categorie() {
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getDesignationCategorie() {
        return designationCategorie;
    }

    public void setDesignationCategorie(String designationCategorie) {
        this.designationCategorie = designationCategorie;
    }

    public String getImageCategorie() {
        return imageCategorie;
    }

    public void setImageCategorie(String imageCategorie) {
        this.imageCategorie = imageCategorie;
    }

    public Collection<Plat> getPlats() {
        return plats;
    }

    public void setPlats(Collection<Plat> plats) {
        this.plats = plats;
    }
}

