package com.administracion.volche.dao;

import com.administracion.volche.domain.Caja;
import com.administracion.volche.domain.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaRepository extends JpaRepository<Caja, String>{

    Caja findByEdificioid(int edificioid);

    Caja findByCajaid(int cajaid);
}
