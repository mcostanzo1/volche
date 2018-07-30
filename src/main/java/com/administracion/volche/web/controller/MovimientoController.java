package com.administracion.volche.web.controller;


import com.administracion.volche.dao.CajaRepository;
import com.administracion.volche.dao.MovimientoRepository;
import com.administracion.volche.domain.Caja;
import com.administracion.volche.domain.Movimiento;
import com.administracion.volche.service.CajaService;
import com.administracion.volche.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/movimiento")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private MovimientoRepository movimientoRepository;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Movimiento>> listAllMovimientos() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        if(movimientos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar",method = RequestMethod.GET,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String VerMovimiento(@RequestParam("id") String movimientoid) {
        return movimientoService.loadMoviminetoiById( Integer.parseInt(movimientoid) );
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String CreateMovimiento(@RequestBody String movimiento) throws ParseException {
        return movimientoService.CreateMovimiento( movimiento );
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String EditMovimiento(@RequestParam("id") String movimientoid, @RequestBody String movimiento) {
        return movimientoService.UpdateMovimiento( movimientoid,movimiento );
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String DeleteMovimiento(@RequestBody int movimientoid) {
        return movimientoService.DeleteMovimiento( movimientoid );
    }

    @RequestMapping(value = "/aprobar_trabajo",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String AprobarTrabajo(@RequestParam("id") String movimientoid) throws ParseException {
        return movimientoService.AprobarTrabajo( Integer.parseInt( movimientoid ) );
    }

    @RequestMapping(value = "/aprobar_pago",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String AprobarPago(@RequestParam("id") String movimientoid) throws ParseException {
        movimientoService.AprobarPago( Integer.parseInt( movimientoid ) );
        return "Pago aprobado";

    }



}
