package com.administracion.volche.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movimientoid;
    private int cajaid;
    private String usuarioid;
    private String tipo;
    private String estado;
    private String concepto;
    private int monto;
    private int presupuestoid;
    private int edificioid;

    public int getMovimientoid() {
        return movimientoid;
    }

    public int getEdificioid() {
        return edificioid;
    }

    public void setEdificioid(int edificioid) {
        this.edificioid = edificioid;
    }

    public void setMovimientoid(int movimientoid) {
        this.movimientoid = movimientoid;
    }

    public int getCajaid() {
        return cajaid;
    }

    public void setCajaid(int cajaid) {
        this.cajaid = cajaid;
    }

    public String getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(String usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getPresupuestoid() {
        return presupuestoid;
    }

    public void setPresupuestoid(int presupuestoid) {
        this.presupuestoid = presupuestoid;
    }
}

