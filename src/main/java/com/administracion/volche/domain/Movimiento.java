package com.administracion.volche.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


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
    private Date fecha_pago;
    private Date fecha_vto;
    private Date fecha_carga;
    private Date fecha_mes_liquidado;

    public int getMovimientoid() {
        return movimientoid;
    }

    public Date getFecha_mes_liquidado() {
        return fecha_mes_liquidado;
    }

    public void setFecha_mes_liquidado(Date fecha_mes_liquidado) {
        this.fecha_mes_liquidado = fecha_mes_liquidado;
    }

    public Date getFecha_vto() {
        return fecha_vto;
    }

    public void setFecha_vto(Date fecha_vto) {
        this.fecha_vto = fecha_vto;
    }

    public Date getFecha_carga() {
        return fecha_carga;
    }

    public void setFecha_carga(Date fecha_carga) {
        this.fecha_carga = fecha_carga;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
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

