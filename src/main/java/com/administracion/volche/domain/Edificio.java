package com.administracion.volche.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Edificio {

    private static final long serialVersionUID = 1L;

    @Id
    private String edificioid;
    private String direccion;
    private int unidades_funcionales;
    private boolean sum;
    private boolean pileta;
    private boolean enabled;

    public String getEdificio_id() {
        return edificioid;
    }

    public void setEdificio_id(String edificio_id) {
        this.edificioid = edificio_id;
    }

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

