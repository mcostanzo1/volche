package com.administracion.volche.web.controller;


import com.administracion.volche.dao.EdificeRepository;
import com.administracion.volche.dao.IncidenciaRepository;
import com.administracion.volche.domain.Edificio;
import com.administracion.volche.domain.Incidencia;
import com.administracion.volche.service.EdificeService;
import com.administracion.volche.service.IncidenciaService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/incidencia")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private IncidenciaRepository incidenciaRepository;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Incidencia>> listAllIncidencias() {
        List<Incidencia> incidencias = incidenciaRepository.findAll();
        if(incidencias.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(incidencias, HttpStatus.OK);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String CreateIncidencia(@RequestBody String incidencia) {
        return incidenciaService.CreateIncidencia( incidencia );
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String EditIncidencia(@RequestParam("id") String incidenciaid, @RequestBody String incidencia) {
        return incidenciaService.UpdateIncidencia( incidenciaid,incidencia );
    }

    @RequestMapping(value = "/edificio",method = RequestMethod.GET,  consumes = MediaType.APPLICATION_JSON_VALUE, produces  = MediaType.APPLICATION_JSON_VALUE)
    public String EditIncidencia(@RequestParam("id") String edificioid) throws Exception {
        JSONArray incidencias = incidenciaService.GetIncidenciaByEdificio( Integer.parseInt( edificioid ) );
        if(incidencias.length() == 0){
            return  "{\"mensaje\": \"no se encontraron problemas\"}";
        }else
        return incidenciaService.GetIncidenciaByEdificio( Integer.parseInt( edificioid ) ).toString();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String DeleteIncidencia(@RequestBody int incidenciaid) {
        return incidenciaService.DeleteIncidencia( incidenciaid );
    }



}
