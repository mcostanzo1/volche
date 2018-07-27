package com.administracion.volche.dao;

import com.administracion.volche.domain.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, String>{

    Presupuesto findByPresupuestoid(int presupuestoId);

    List<Presupuesto> findByEdificioid(int edificioId);

    List<Presupuesto> findPresupuestoByConsorcistaid(String consorcistaid);

    List<Presupuesto> findPresupuestoByEstadoIn(List<String> listado);

}
