package com.administracion.volche.web.controller;


import com.administracion.volche.dao.CajaRepository;
import com.administracion.volche.dao.EdificeRepository;
import com.administracion.volche.domain.Caja;
import com.administracion.volche.domain.Edificio;
import com.administracion.volche.service.CajaService;
import com.administracion.volche.service.EdificeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/caja")
public class CajaController {

    @Autowired
    private CajaService cajaService;

    @Autowired
    private CajaRepository cajaRepository;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Caja>> listAllCajas() {
        List<Caja> cajas = cajaRepository.findAll();
        if(cajas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cajas, HttpStatus.OK);
    }

    @RequestMapping(value = "/mi_caja",method = RequestMethod.GET,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String VerCaja(@RequestParam("id") String cajaid) {
        return cajaService.loadCajaById( Integer.parseInt(cajaid) );
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String CreateCaja(@RequestBody String caja) {
        return cajaService.CreateCaja( caja );
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String EditCaja(@RequestParam("id") String cajaid, @RequestBody String caja) {
        return cajaService.UpdateCaja( cajaid,caja );
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String DeleteCaja(@RequestBody int cajaid) {
        return cajaService.DeleteCaja( cajaid );
    }

    @RequestMapping(value = "/ingreso_caja",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String IngresarEnCaja(@RequestParam("id") String cajaid,@RequestParam("monto")String monto) {
        return cajaService.IngresarDineroCaja( Integer.parseInt(cajaid),Integer.parseInt(monto) );
    }

    @RequestMapping(value = "/egreso_caja",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String SacarDeCaja(@RequestParam("id") String cajaid,@RequestParam("monto")String monto) {
        return cajaService.SacarDineroCaja( Integer.parseInt(cajaid),Integer.parseInt(monto) );
    }

    @RequestMapping(value = "/ingreso_fondo",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String IngresarEnFondo(@RequestParam("id") String cajaid,@RequestParam("monto")String monto) {
        return cajaService.IngresarDineroFondoReserva( Integer.parseInt(cajaid),Integer.parseInt(monto) );
    }

    @RequestMapping(value = "/egreso_fondo",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String SacarDeFondo(@RequestParam("id") String cajaid,@RequestParam("monto")String monto) {
        return cajaService.SacarDineroFondoReserva( Integer.parseInt(cajaid),Integer.parseInt(monto) );
    }



}
