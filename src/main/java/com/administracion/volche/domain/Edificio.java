package com.administracion.volche.domain;


import javax.persistence.*;



@Entity
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int edificioid;
    private String direccion;
    private int unidades_funcionales;
    private boolean sum;
    private boolean pileta;
    private boolean enabled;



    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getUnidades_funcionales() {
        return unidades_funcionales;
    }

    public void setUnidades_funcionales(int unidades_funcionales) {
        this.unidades_funcionales = unidades_funcionales;
    }

    public boolean isSum() {
        return sum;
    }

    public void setEdificioid(int edificioid) {
        this.edificioid = edificioid;
    }

    public int getEdificioid() {
        return edificioid;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

    }

    public void setSum(boolean sum) {
        this.sum = sum;
    }

    public boolean isPileta() {
        return pileta;
    }

    public void setPileta(boolean pileta) {
        this.pileta = pileta;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

