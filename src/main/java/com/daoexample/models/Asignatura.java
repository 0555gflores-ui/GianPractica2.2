package com.daoexample.models;

public class Asignatura {

    private String codigo;
    private String nombre;
    private int horasPorSemana;

    public Asignatura() {
    }

    public Asignatura(String codigo, String nombre, int horasPorSemana) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horasPorSemana = horasPorSemana;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHorasPorSemana() {
        return horasPorSemana;
    }

    public void setHorasPorSemana(int horasPorSemana) {
        this.horasPorSemana = horasPorSemana;
    }

}
