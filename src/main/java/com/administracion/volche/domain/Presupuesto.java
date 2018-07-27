package com.administracion.volche.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int presupuestoid;
    private int edificioid;
    private String consorcistaid;
    private String proveedorid;
    private int precio;
    private String fecha_desde;
    private String fecha_hasta;
    private String metodo_pago;
    private boolean cuotas_sin_interes;
    private int cant_cuotas;
    private int interes;
    private String link;
    private String comentarios;
    private String estado;


    public String getConsorcistaid() {
        return consorcistaid;
    }

    public void setConsorcistaid(String consorcistaid) {
        this.consorcistaid = consorcistaid;
    }

    public int getEdificioid() {
        return edificioid;
    }

    public void setEdificioid(int edificioid) {
        this.edificioid = edificioid;
    }

    public String getProveedorid() {
        return proveedorid;
    }

    public void setProveedorid(String proveedorid) {
        this.proveedorid = proveedorid;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFecha_desde() {
        return fecha_desde;
    }

    public void setFecha_desde(String fecha_desde) {
        this.fecha_desde = fecha_desde;
    }

    public String getFecha_hasta() {
        return fecha_hasta;
    }

    public void setFecha_hasta(String fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public boolean isCuotas_sin_interes() {
        return cuotas_sin_interes;
    }

    public void setCuotas_sin_interes(boolean cuotas_sin_interes) {
        this.cuotas_sin_interes = cuotas_sin_interes;
    }

    public int getCant_cuotas() {
        return cant_cuotas;
    }

    public void setCant_cuotas(int cant_cuotas) {
        this.cant_cuotas = cant_cuotas;
    }

    public float getInteres() {
        return interes;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPresupuestoid() {
        return presupuestoid;
    }



}

