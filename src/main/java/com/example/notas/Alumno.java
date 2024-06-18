package com.example.notas;

public class Alumno {
    private String nombre;
    private String apellidos;
    private double ad;
    private double sge;
    private double di;

    public Alumno(String nombre, String apellidos, double ad, double sge, double di) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ad = ad;
        this.sge = sge;
        this.di = di;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public double getAd() {
        return ad;
    }

    public double getSge() {
        return sge;
    }

    public double getDi() {
        return di;
    }

    public double calcularMedia() {
        return (ad + sge + di) / 3.0;
    }

    public boolean tieneAprobadosTodos() {

        return ad >= 5 && sge >= 5 && di >= 5;
    }
}
