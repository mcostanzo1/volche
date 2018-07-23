package com.administracion.volche.service;

import com.administracion.volche.dao.EdificeRepository;
import com.administracion.volche.domain.Edificio;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EdificeService {

    @Autowired
    private EdificeRepository edificeRepository;

    @Transactional(readOnly=true)
    public Edificio loadEdificioByDireccion(final String direccion)  {
        return edificeRepository.findByDireccion(direccion);
    }

    public String CreateEdifice(String edifice){
        Edificio newEdificio = jsonStringToEdifice(edifice);
        edificeRepository.save( newEdificio );
        return  "Edificio creado, la dirección es  " + newEdificio.getDireccion() + "!";
    }

    public String UpdateEdifice(int edificio, String json){
        Edificio elEdificio = edificeRepository.findByEdificioid( edificio );
        JSONObject serverJson = new JSONObject( json );
        String direccion = getOrNull( serverJson,"direccion" );
        int unidades_funcionales = Integer.parseInt( getOrNull( serverJson,"unidades_funcionales" ) );
        boolean sum = Boolean.parseBoolean( getOrNull( serverJson,"sum" ) );
        boolean pileta =  Boolean.parseBoolean(getOrNull( serverJson,"pileta" ));
        elEdificio.setDireccion( direccion );
        elEdificio.setPileta( pileta );
        elEdificio.setSum( sum );
        elEdificio.setUnidades_funcionales( unidades_funcionales );
        return  "El edificio se modificó con exito! "+ elEdificio;
    }

    public String DeleteEdifice(int edificio){
       Edificio edifice =  edificeRepository.findByEdificioid( edificio );
       edifice.setEnabled( false );
        return "Edificio deshabilitado "+ edifice;
    }

    private Edificio jsonStringToEdifice(String json){
        JSONObject serverJson = new JSONObject( json );
        String direccion = getOrNull( serverJson,"direccion" );
        int unidades_funcionales = Integer.parseInt( getOrNull( serverJson,"unidades_funcionales" ) );
        boolean sum = Boolean.parseBoolean( getOrNull( serverJson,"sum" ) );
        boolean pileta =  Boolean.parseBoolean(getOrNull( serverJson,"pileta" ));
        boolean enabled=  Boolean.parseBoolean(getOrNull( serverJson,"enabled" ));
        Edificio newEdificio = new Edificio();
        newEdificio.setDireccion( direccion );
        newEdificio.setUnidades_funcionales( unidades_funcionales );
        newEdificio.setPileta( pileta );
        newEdificio.setSum( sum );
        newEdificio.setEnabled( enabled );
        return newEdificio;
    }

    private static String getOrNull(JSONObject serverJson, String key){
        return serverJson.optString( key,null );
    }


}
