package com.daoexample.models;

public class Aula {

    private String codigo;
    private int piso;
    private int pupitres;

    public Aula() {

    }

    public Aula(String codigo, int piso, int pupitres) {
        this.codigo = codigo;
        this.piso = piso;
        this.pupitres = pupitres;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getPupitres() {
        return pupitres;
    }

    public void setPupitres(int pupitres) {
        this.pupitres = pupitres;
    }
}
