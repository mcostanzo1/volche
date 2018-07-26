package com.administracion.volche.service;

import com.administracion.volche.dao.IncidenciaRepository;
import com.administracion.volche.domain.Incidencia;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public String UpdateIncidencia(String incidenciaid, String json){
        Incidencia laIncidencia = incidenciaRepository.findByIncidenciaid( Integer.parseInt( incidenciaid ) );
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
        incidenciaRepository.save( laIncidencia );
        return  "{\"mensaje\": \"La incidencia se modificó con exito!\"}";
    }

    public String DeleteIncidencia(int incidenciaId){
       Incidencia incidencia =  incidenciaRepository.findByIncidenciaid( incidenciaId );
        incidencia.setFinalizada( true );
        return "{\"mensaje\": \"La incidencia finalizó con exito!\"}";
    }

    public JSONArray GetIncidenciaByEdificio(int edificioID) throws Exception {
        List<Incidencia> listado = incidenciaRepository.findIncidenciaByEdificioid(edificioID);
        JSONArray incidencias = new JSONArray();
        for (int i =0; i < listado.size(); i++) {
            incidencias.put( incidenciaToJson( listado.get( i ) ) );
        }
        return incidencias;
    }

    private Incidencia jsonStringToIncidencia(String json){
        JSONObject serverJson = new JSONObject( json );
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

    private JSONObject incidenciaToJson(Incidencia incidencia){
        JSONObject serverJson = new JSONObject();
        int incidenciaid = incidencia.getIncidenciaid();
        String tipo = incidencia.getTipo();
        String titulo = incidencia.getTitulo();
        String descripcion = incidencia.getDescripcion();
        String etapa = incidencia.getEtapa();
        boolean emergencia = incidencia.isEmergencia();
        boolean finalizada = incidencia.isFinalizada();
        int edificioid = incidencia.getEdificioid();
        serverJson.put( "incidenciaid", incidenciaid );
        serverJson.put( "tipo", tipo );
        serverJson.put( "titulo", titulo );
        serverJson.put( "descripcion", descripcion );
        serverJson.put( "etapa", etapa );
        serverJson.put( "emergencia", emergencia );
        serverJson.put( "finalizada", finalizada );
        serverJson.put( "edificioid", edificioid );
        return serverJson;
    }

    private static String getOrNull(JSONObject serverJson, String key){
        return serverJson.optString( key,null );
    }



}
