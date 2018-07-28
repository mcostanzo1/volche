package com.administracion.volche.web.controller;


import com.administracion.volche.dao.IncidenciaRepository;
import com.administracion.volche.dao.PresupuestoRepository;
import com.administracion.volche.domain.Incidencia;
import com.administracion.volche.domain.Presupuesto;
import com.administracion.volche.service.IncidenciaService;
import com.administracion.volche.service.PresupuestoService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/presupuesto")
public class PresupuestoController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private PresupuestoService presupuestoService;

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private PresupuestoRepository presupuestoRepository;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Presupuesto>> listAllPresupuestos() {
        List<Presupuesto> presupuestos = presupuestoRepository.findAll();
        if(presupuestos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String CreatePresupuesto(@RequestBody String presupuesto) {
        return presupuestoService.CreatePresupuesto( presupuesto );
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String EditPresupuesto(@RequestParam("id") String presupuestoid, @RequestBody String presupuesto) {
        return presupuestoService.UpdatePresupuesto( presupuestoid,presupuesto );
    }

    @RequestMapping(value = "/edificio",method = RequestMethod.GET,  consumes = MediaType.APPLICATION_JSON_VALUE, produces  = MediaType.APPLICATION_JSON_VALUE)
    public String PresupuestoByEdifice(@RequestParam("id") String edificeId) throws Exception {
        JSONArray presupuestos = presupuestoService.GetPresupuestoByEdificio( Integer.parseInt( edificeId ) );
        if(presupuestos.length() == 0){
            return  "{\"mensaje\": \"no se encontraron presupuestos\"}";
        }else
        return presupuestoService.GetPresupuestoByEdificio( Integer.parseInt( edificeId ) ).toString();
    }

    @RequestMapping(value = "/mis_presupuetos",method = RequestMethod.GET,  consumes = MediaType.APPLICATION_JSON_VALUE, produces  = MediaType.APPLICATION_JSON_VALUE)
    public String MisPresupuestos(Principal principal) {
        JSONArray presupuestos = presupuestoService.GetPresupuestosByUser( principal  );
        if(presupuestos.length() == 0){
            return  "{\"mensaje\": \"no se encontraron presupuestos\"}";
        }else
            return presupuestos.toString();
    }

    @RequestMapping(value = "/estado",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE, produces  = MediaType.APPLICATION_JSON_VALUE)
    public String FindByState(@RequestBody String jsonObject){
        return presupuestoService.GetPresupuestoByTipo( jsonObject );
    }

    @RequestMapping(value = "/finalizar",method = RequestMethod.PUT,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String FinalizarPresupuesto(@RequestParam("id") int presupuestoid) {
        return presupuestoService.FinalizarPresupuesto( presupuestoid );
    }



}
