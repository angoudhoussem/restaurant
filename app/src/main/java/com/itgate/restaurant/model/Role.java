package com.itgate.restaurant.model;

import java.util.List;

/**
 * Created by macbook on 29/04/2016.
 */
public class Role {


    private Long idRole;

    private String nomRole;

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public Role(Long idRole, String nomRole) {
        this.idRole = idRole;
        this.nomRole = nomRole;

    }

    public Role() {
    }


}
