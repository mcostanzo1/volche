package com.administracion.volche.service;

import com.administracion.volche.dao.IncidenciaRepository;
import com.administracion.volche.domain.Incidencia;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    public String CreateIncidencia(String incidencia) throws ParseException {
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

    public JSONArray GetIncidenciasByUser(Principal principal){
      List<Incidencia> listado = incidenciaRepository.findIncidenciaByUsername( principal.getName() );
        JSONArray jsonArray = new JSONArray();
        JSONObject incidencia;
        for(int i = 0; i<listado.size();i++){
            incidencia = incidenciaToJson( listado.get( i ) );
            jsonArray.put( incidencia );
        }
        return jsonArray;
    }

    public String GetIncidenciasByUserString ( String username){
        List<Incidencia> listado = incidenciaRepository.findIncidenciaByUsername( username );
        return ListadoFinder( listado );
    }

    public String GetIncidenciaByTipo(String stringJsonObject){
        JSONObject jsonObject = new JSONObject( stringJsonObject );
        Object tipos = jsonObject.opt( "tipos" );
        String in = tipos.toString().replace( "[","" ).replace( "]","" ).replace( "\"","" );
        List<String> listado = Arrays.asList( in.split( "," ) );
        List<Incidencia> lista = incidenciaRepository.findIncidenciaByTipoIn( listado );
        return ListadoFinder( lista );
    }

    public String GetEmergencias() {
        List<Incidencia> listado =incidenciaRepository.findIncidenciaByEmergencia( true );
        return ListadoFinder( listado );
    }


    @Transactional(readOnly=true)
    public Optional<Incidencia> loadIncidenciaById(final String incidenciaId)  {
        return incidenciaRepository.findById(incidenciaId);
    }

    private Incidencia jsonStringToIncidencia(String json) throws ParseException {
        JSONObject serverJson = new JSONObject( json );
        String tipo = getOrNull( serverJson,"tipo" );
        String titulo = getOrNull( serverJson,"titulo" );
        String descripcion = getOrNull( serverJson,"descripcion" );
        String etapa = getOrNull( serverJson,"etapa" );
        boolean emergencia = Boolean.parseBoolean( getOrNull( serverJson,"emergencia" ) );
        boolean finalizada =  Boolean.parseBoolean(getOrNull( serverJson,"finalizada" ));
        int edificioid =  Integer.parseInt(getOrNull( serverJson,"edificioid" ));
        String username = getOrNull( serverJson,"username" );
        String fecha = getOrNull(serverJson,"fecha");
        Incidencia newIncidencia = new Incidencia();
        newIncidencia.setTipo( tipo );
        newIncidencia.setTitulo( titulo );
        newIncidencia.setDescripcion( descripcion );
        newIncidencia.setEtapa( etapa );
        newIncidencia.setEmergencia( emergencia );
        newIncidencia.setFinalizada( finalizada );
        newIncidencia.setEdificioid( edificioid );
        newIncidencia.setUsername( username );
        newIncidencia.setFecha(StringToDateConverter(fecha));
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
        Date fecha = incidencia.getFecha();
        serverJson.put( "incidenciaid", incidenciaid );
        serverJson.put( "tipo", tipo );
        serverJson.put( "titulo", titulo );
        serverJson.put( "descripcion", descripcion );
        serverJson.put( "etapa", etapa );
        serverJson.put( "emergencia", emergencia );
        serverJson.put( "finalizada", finalizada );
        serverJson.put( "edificioid", edificioid );
        serverJson.put("fecha", DateToStringConverter(fecha));
        return serverJson;
    }

    private String ListadoFinder( List<Incidencia> listado){
        if(listado.size()>0){
            JSONArray jsonArray = new JSONArray();
            JSONObject incidencia;
            for(int i = 0; i<listado.size();i++){
                incidencia = incidenciaToJson( listado.get( i ) );
                jsonArray.put( incidencia );
            }
            return jsonArray.toString();}
        else
            return "{\"mensaje\": \"no se encontraron problemas\"}";
    }

    private Date StringToDateConverter (String date) throws ParseException {
        DateFormat formatterr = new SimpleDateFormat("yyyy-MM-dd");
        return formatterr.parse(date);
    }

    private String DateToStringConverter(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    private static String getOrNull(JSONObject serverJson, String key){
        return serverJson.optString( key,null );
    }

}
