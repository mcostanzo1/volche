package com.administracion.volche.service;

import com.administracion.volche.dao.IncidenciaRepository;
import com.administracion.volche.domain.Incidencia;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Transactional(readOnly=true)
    public Optional<Incidencia> loadIncidenciaById(final String incidenciaId)  {
        return incidenciaRepository.findById(incidenciaId);
    }

    public String CreateIncidencia(String incidencia){
        Incidencia newIncidencia = jsonStringToIncidencia(incidencia);
        incidenciaRepository.save( newIncidencia );
        return  "Incidencia creada!";
    }

    public String UpdateIncidencia(int incidenciaid, String json){
        Incidencia laIncidencia = incidenciaRepository.findByIncidenciaid( incidenciaid );
        JSONObject serverJson = new JSONObject( json );
        String tipo = getOrNull( serverJson,"tipo" );
        String titulo = getOrNull( serverJson,"titulo" );
        String descripcion = getOrNull( serverJson,"descripcion" );
        String etapa = getOrNull( serverJson,"etapa" );
        boolean emergencia = Boolean.parseBoolean( getOrNull( serverJson,"emergencia" ) );
        boolean finalizada =  Boolean.parseBoolean(getOrNull( serverJson,"finalizada" ));
        int edificioid =  Integer.parseInt(getOrNull( serverJson,"edificioid" ));
        String username = getOrNull( serverJson,"username" );
        laIncidencia.setTipo( tipo );
        laIncidencia.setTitulo( titulo );
        laIncidencia.setDescripcion( descripcion );
        laIncidencia.setEtapa( etapa );
        laIncidencia.setEmergencia( emergencia );
        laIncidencia.setFinalizada( finalizada );
        laIncidencia.setEdificioid( edificioid );
        laIncidencia.setUsername( username );
        return  "La Incidencia se modificó con exito! "+ laIncidencia;
    }

    public String DeleteIncidencia(int incidenciaId){
       Incidencia incidencia =  incidenciaRepository.findByIncidenciaid( incidenciaId );
        incidencia.setFinalizada( true );
        return "La incidencia se finalizó con éxito"+ incidencia;
    }

    private Incidencia jsonStringToIncidencia(String json){
        JSONObject serverJson = new JSONObject( json );
        int incidenciaid = Integer.parseInt(getOrNull( serverJson,"incidenciaid" ));
        String tipo = getOrNull( serverJson,"tipo" );
        String titulo = getOrNull( serverJson,"titulo" );
        String descripcion = getOrNull( serverJson,"descripcion" );
        String etapa = getOrNull( serverJson,"etapa" );
        boolean emergencia = Boolean.parseBoolean( getOrNull( serverJson,"emergencia" ) );
        boolean finalizada =  Boolean.parseBoolean(getOrNull( serverJson,"finalizada" ));
        int edificioid =  Integer.parseInt(getOrNull( serverJson,"edificioid" ));
        String username = getOrNull( serverJson,"username" );
        Incidencia newIncidencia = new Incidencia();
        newIncidencia.setTipo( tipo );
        newIncidencia.setTitulo( titulo );
        newIncidencia.setDescripcion( descripcion );
        newIncidencia.setEtapa( etapa );
        newIncidencia.setEmergencia( emergencia );
        newIncidencia.setFinalizada( finalizada );
        newIncidencia.setEdificioid( edificioid );
        newIncidencia.setUsername( username );
        return newIncidencia;
    }

    private static String getOrNull(JSONObject serverJson, String key){
        return serverJson.optString( key,null );
    }


}
