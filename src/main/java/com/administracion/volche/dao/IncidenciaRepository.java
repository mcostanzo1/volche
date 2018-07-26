package com.administracion.volche.dao;

import com.administracion.volche.domain.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, String>{
    Incidencia findByIncidenciaid(int incidenciaId);

    List<Incidencia> findIncidenciaByEdificioid(int edificioId);
}
