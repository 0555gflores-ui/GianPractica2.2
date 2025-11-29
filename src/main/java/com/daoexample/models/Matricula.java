package com.daoexample.models;

public class Matricula {

    private String alumnoDni;
    private String asignaturaCodigo;
    private float calificacion;
    private String incidencias;

    public Matricula() {

    }

    public Matricula(String alumnoDni, String asignaturaCodigo, float calificacion, String incidencias) {
        this.alumnoDni = alumnoDni;
        this.asignaturaCodigo = asignaturaCodigo;
        this.calificacion = calificacion;
        this.incidencias = incidencias;
    }

    public String getAlumnoDni() {
        return alumnoDni;
    }

    public void setAlumnoDni(String alumnoDni) {
        this.alumnoDni = alumnoDni;
    }

    public String getAsignaturaCodigo() {
        return asignaturaCodigo;
    }

    public void setAsignaturaCodigo(String asignaturaCodigo) {
        this.asignaturaCodigo = asignaturaCodigo;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public String getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(String incidencias) {
        this.incidencias = incidencias;
    }
}
