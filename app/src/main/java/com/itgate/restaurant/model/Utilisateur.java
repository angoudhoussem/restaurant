package com.itgate.restaurant.model;


import android.os.Parcelable;

import java.io.Serializable;

public class Utilisateur implements Serializable {


    private Long idUtilisateur;
    private Long cin;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private String imageUtilisateur;
    private Role role;

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

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
