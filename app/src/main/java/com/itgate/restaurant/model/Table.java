package com.itgate.restaurant.model;

/**
 * Created by macbook on 08/05/2016.
 */
public class Table {

    private long idTable;
    private int numTable;
    private Departement departement;

    public Table() {
    }

    public long getIdTable() {
        return idTable;
    }

    public void setIdTable(long idTable) {
        this.idTable = idTable;
    }

    public int getNumTable() {
        return numTable;
    }

    public void setNumTable(int numTable) {
        this.numTable = numTable;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
