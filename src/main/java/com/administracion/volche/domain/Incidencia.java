package com.administracion.volche.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int incidenciaid;
    private String tipo;
    private String titulo;
    private String descripcion;
    private String etapa;
    private boolean emergencia;
    private boolean finalizada;
    private int edificioid;
    private String username;
    private boolean aprobada;
    private int presupuestoid;


    public String getTipo() {
        return tipo;
    }

    public int getPresupuestoid() {
        return presupuestoid;
    }

    public void setPresupuestoid(int presupuestoid) {
        this.presupuestoid = presupuestoid;
    }

    public boolean isAprobada() {
        return aprobada;
    }

    public void setAprobada(boolean aprobada) {
        this.aprobada = aprobada;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public boolean isEmergencia() {
        return emergencia;
    }

    public void setEmergencia(boolean emergencia) {
        this.emergencia = emergencia;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public int getEdificioid() {
        return edificioid;
    }

    public void setEdificioid(int edificioid) {
        this.edificioid = edificioid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIncidenciaid() {
        return incidenciaid;
    }

    public void setIncidenciaid(int incidenciaid) {
        this.incidenciaid = incidenciaid;
    }
}

