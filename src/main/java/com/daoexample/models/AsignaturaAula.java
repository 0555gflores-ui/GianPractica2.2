package com.daoexample.models;

public class AsignaturaAula {

    private String asignaturaCodigo;
    private String aulaCodigo;
    private int mes;
    private int dia;
    private int hora;

    public AsignaturaAula() {

    }

    public AsignaturaAula(String asignaturaCodigo, String aulaCodigo, int mes, int dia, int hora) {
        this.asignaturaCodigo = asignaturaCodigo;
        this.aulaCodigo = aulaCodigo;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
    }

    public String getAsignaturaCodigo() {
        return asignaturaCodigo;
    }

    public void setAsignaturaCodigo(String asignaturaCodigo) {
        this.asignaturaCodigo = asignaturaCodigo;
    }

    public String getAulaCodigo() {
        return aulaCodigo;
    }

    public void setAulaCodigo(String aulaCodigo) {
        this.aulaCodigo = aulaCodigo;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
}
