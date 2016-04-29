package com.itgate.restaurant.model;


import android.os.Parcelable;

import java.io.Serializable;

public class Utilisateur implements Serializable {


    private Long idUtilisateur;
    private String login;
    private String password;
    private String imageUtilisateur;
    private  Role role;

    public Utilisateur() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUtilisateur() {
        return imageUtilisateur;
    }

    public void setImageUtilisateur(String imageUtilisateur) {
        this.imageUtilisateur = imageUtilisateur;
    }
}
