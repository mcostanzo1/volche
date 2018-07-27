package com.administracion.volche.service;

import com.administracion.volche.dao.PresupuestoRepository;
import com.administracion.volche.dao.UserRepository;
import com.administracion.volche.domain.Incidencia;
import com.administracion.volche.domain.Presupuesto;
import com.administracion.volche.domain.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;


@Service
public class PresupuestoService {

    @Autowired
    private PresupuestoRepository presupuestoRepository;


    @Transactional(readOnly=true)
    public Presupuesto loadPresupuestoById(int presupuestoId)  {
        return presupuestoRepository.findByPresupuestoid(presupuestoId);
    }

    public String CreatePresupuesto(String presupuesto){
        Presupuesto newpresupuesto = jsonStringToPresupuesto(presupuesto);
        presupuestoRepository.save( newpresupuesto );
        return  "Presupuesto cargado!, el identificador de presupuesto es:  " + newpresupuesto.getPresupuestoid();
    }

    public String UpdatePresupuesto(String presupuestoId, String json){
        Presupuesto presupuesto = loadPresupuestoById( Integer.parseInt( presupuestoId ) );
        JSONObject serverJson = new JSONObject( json );
        String consorcistaid = getOrNull( serverJson,"consorcistaid" );
        String proveedorid = getOrNull( serverJson,"proveedorid" );
        int precio = Integer.parseInt(getOrNull( serverJson,"precio" ));
        String fecha_desde = getOrNull( serverJson,"fecha_desde" );
        String fecha_hasta = getOrNull( serverJson,"fecha_hasta" );
        String metodo_pago = getOrNull( serverJson,"metodo_pago" );
        boolean cuotas_sin_interes = Boolean.parseBoolean( getOrNull( serverJson,"cuotas_sin_interes" ) );
        int cant_cuotas = Integer.parseInt(getOrNull( serverJson,"cant_cuotas" ));
        int interes = Integer.parseInt(getOrNull( serverJson,"interes" ));
        String link = getOrNull( serverJson,"link" );
        String comentarios = getOrNull( serverJson,"comentarios" );
        String estado = getOrNull( serverJson,"estado" );
        int edificioid = Integer.parseInt(getOrNull( serverJson,"edificioid" ));
        presupuesto.setConsorcistaid( consorcistaid );
        presupuesto.setProveedorid( proveedorid );
        presupuesto.setPrecio( precio );
        presupuesto.setFecha_desde( fecha_desde );
        presupuesto.setFecha_hasta( fecha_hasta );
        presupuesto.setMetodo_pago( metodo_pago );
        presupuesto.setCuotas_sin_interes( cuotas_sin_interes );
        presupuesto.setCant_cuotas( cant_cuotas );
        presupuesto.setInteres( interes );
        presupuesto.setLink( link );
        presupuesto.setComentarios( comentarios );
        presupuesto.setEstado( estado );
        presupuesto.setEdificioid( edificioid );
        presupuestoRepository.save( presupuesto );
        return  "El presupuesto se modificó con exito! ";
    }

    public String FinalizarPresupuesto(int presupuestoId){
        Presupuesto presupuesto = loadPresupuestoById( presupuestoId );
        presupuesto.setEstado( "finalizado" );
        presupuestoRepository.save( presupuesto );
        return "Presupuesto finalizado ";
    }

    public JSONArray GetPresupuestoByEdificio(int edificioID) throws Exception {
        List<Presupuesto> listado = presupuestoRepository.findByEdificioid(edificioID);
        JSONArray presupuestos = new JSONArray();
        for (int i =0; i < listado.size(); i++) {
            presupuestos.put( presupuestoToJson( listado.get( i ) ) );
        }
        return presupuestos;
    }

    public JSONArray GetPresupuestosByUser(Principal principal) {
        List<Presupuesto> listado = presupuestoRepository.findPresupuestoByConsorcistaid( principal.getName() );
        System.out.println(principal.getName());
        JSONArray jsonArray = new JSONArray();
        JSONObject presupuesto;
        for(int i = 0; i<listado.size();i++){
            presupuesto = presupuestoToJson( listado.get( i ) );
            jsonArray.put( presupuesto );
        }
        return jsonArray;
    }

    public String GetPresupuestoByTipo(String stringJsonObject){
        JSONObject jsonObject = new JSONObject( stringJsonObject );
        Object tipos = jsonObject.opt( "estado" );
        String in = tipos.toString().replace( "[","" ).replace( "]","" ).replace( "\"","" );
        List<String> listado = Arrays.asList( in.split( "," ) );
        List<Presupuesto> lista = presupuestoRepository.findPresupuestoByEstadoIn( listado );
        return ListadoFinder( lista );
    }

    private Presupuesto jsonStringToPresupuesto(String json){
        JSONObject serverJson = new JSONObject( json );
        String consorcistaid = getOrNull( serverJson,"consorcistaid" );
        String proveedorid = getOrNull( serverJson,"proveedorid" );
        int precio = Integer.parseInt(getOrNull( serverJson,"precio" ));
        String fecha_desde = getOrNull( serverJson,"fecha_desde" );
        String fecha_hasta = getOrNull( serverJson,"fecha_hasta" );
        String metodo_pago = getOrNull( serverJson,"metodo_pago" );
        boolean cuotas_sin_interes = Boolean.parseBoolean( getOrNull( serverJson,"cuotas_sin_interes" ) );
        int cant_cuotas = Integer.parseInt(getOrNull( serverJson,"cant_cuotas" ));
        int interes = Integer.parseInt(getOrNull( serverJson,"interes" ));
        String link = getOrNull( serverJson,"link" );
        String comentarios = getOrNull( serverJson,"comentarios" );
        String estado = getOrNull( serverJson,"estado" );
        int edificioid = Integer.parseInt(getOrNull( serverJson,"edificioid" ));
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setConsorcistaid( consorcistaid );
        presupuesto.setProveedorid( proveedorid );
        presupuesto.setPrecio( precio );
        presupuesto.setFecha_desde( fecha_desde );
        presupuesto.setFecha_hasta( fecha_hasta );
        presupuesto.setMetodo_pago( metodo_pago );
        presupuesto.setCuotas_sin_interes( cuotas_sin_interes );
        presupuesto.setCant_cuotas( cant_cuotas );
        presupuesto.setInteres( interes );
        presupuesto.setLink( link );
        presupuesto.setComentarios( comentarios );
        presupuesto.setEstado( estado );
        presupuesto.setEdificioid( edificioid );
        return presupuesto;
    }

    private JSONObject presupuestoToJson(Presupuesto presupuesto) {
        String consorcistaid = presupuesto.getConsorcistaid();
        String proveedorid = presupuesto.getProveedorid();
        int precio = (int) presupuesto.getPrecio();
        String fecha_desde = presupuesto.getFecha_desde();
        String fecha_hasta = presupuesto.getFecha_hasta();
        String metodo_pago = presupuesto.getMetodo_pago();
        boolean cuotas_sin_interes = presupuesto.isCuotas_sin_interes();
        int cant_cuotas = presupuesto.getCant_cuotas();
        int interes = (int) presupuesto.getInteres();
        String link = presupuesto.getLink();
        String comentarios = presupuesto.getComentarios();
        String estado = presupuesto.getEstado();
        int edificioid = presupuesto.getEdificioid();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "consorcistaid",consorcistaid );
        jsonObject.put( "proveedorid",proveedorid );
        jsonObject.put( "precio",precio );
        jsonObject.put( "fecha_desde",fecha_desde );
        jsonObject.put( "fecha_hasta",fecha_hasta );
        jsonObject.put( "metodo_pago",metodo_pago );
        jsonObject.put( "cuotas_sin_interes",cuotas_sin_interes );
        jsonObject.put( "cant_cuotas",cant_cuotas );
        jsonObject.put( "interes",interes );
        jsonObject.put( "link",link );
        jsonObject.put( "comentarios",comentarios );
        jsonObject.put( "estado",estado );
        jsonObject.put( "edificioid",edificioid );
        return jsonObject;
    }

    private String ListadoFinder(List<Presupuesto>lista) {
        if(lista.size()>0){
            JSONArray jsonArray = new JSONArray();
            JSONObject presupuesto;
            for(int i = 0; i<lista.size();i++){
                presupuesto = presupuestoToJson( lista.get( i ) );
                jsonArray.put( presupuesto );
            }
            return jsonArray.toString();}
        else
            return "{\"mensaje\": \"no se encontraron presupuestos\"}";
    }

    private static String getOrNull(JSONObject serverJson, String key){
        return serverJson.optString( key,null );
    }
}
