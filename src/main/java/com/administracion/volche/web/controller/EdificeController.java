package com.administracion.volche.web.controller;


import com.administracion.volche.dao.EdificeRepository;
import com.administracion.volche.domain.Edificio;
import com.administracion.volche.service.EdificeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/edificio")
public class EdificeController {

    @Autowired
    private EdificeService edificeService;

    @Autowired
    private EdificeRepository edificeRepository;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Edificio>> listAllEdificess() {
        List<Edificio> edifices = edificeRepository.findAll();
        if(edifices.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(edifices, HttpStatus.OK);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String CreateEdifice(@RequestBody String edificio_id) {
        return edificeService.CreateEdifice( edificio_id );
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String EditEdifice(@RequestBody int edificio_id,@RequestBody String edifice) {
        return edificeService.UpdateEdifice( edificio_id,edifice );
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String DeleteEdifice(@RequestBody int edificio_id) {
        return edificeService.DeleteEdifice( edificio_id );
    }



}
