package com.administracion.volche.dao;

import com.administracion.volche.domain.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificeRepository extends JpaRepository<Edificio, String>{

    Edificio findByDireccion(String direccion);

    Edificio findByEdificioid(String edificio);

}
