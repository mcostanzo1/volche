package com.administracion.volche.service;

import com.administracion.volche.dao.CajaRepository;
import com.administracion.volche.dao.EdificeRepository;
import com.administracion.volche.domain.Caja;
import com.administracion.volche.domain.Edificio;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CajaService {

    @Autowired
    private CajaRepository cajaRepository;

    @Transactional(readOnly=true)
    public String loadCajaById(final int cajaid)  {
        return CajaToJson(cajaRepository.findByCajaid(cajaid)).toString();
    }

    public String CreateCaja(String caja){
        Caja newCaja = jsonStringToCaja(caja);
        cajaRepository.save( newCaja );
        return  "Caja creada!";
    }

    public String UpdateCaja(String cajaid, String json){
        Caja laCaja =cajaRepository.findByCajaid( Integer.parseInt( cajaid ) );
        JSONObject serverJson = new JSONObject( json );
        int edificioid = Integer.parseInt(getOrNull( serverJson,"edificioid" ));
        int total = Integer.parseInt(getOrNull( serverJson,"total" ));
        String cbu =  getOrNull( serverJson,"cbu" ) ;
        String nro_cuenta =  getOrNull( serverJson,"nro_cuenta" ) ;
        String banco =  getOrNull( serverJson,"banco" ) ;
        String sucursal =  getOrNull( serverJson,"sucursal" ) ;
        String estado =  getOrNull( serverJson,"estado" ) ;
        laCaja.setEdificioid(edificioid);
        laCaja.setTotal(total);
        laCaja.setCbu(cbu);
        laCaja.setNro_cuenta(nro_cuenta);
        laCaja.setBanco(banco);
        laCaja.setSucursal(sucursal);
        laCaja.setEstado(estado);
        return  "La Caja se modificó con exito!";
    }

    public String DeleteCaja(int cajaid){
       Caja caja = cajaRepository.findByCajaid( cajaid );
        caja.setEstado( "CERRADA" );
        return "La Caja se cerró ";
    }

    public String IngresarDineroCaja(int cajaid,int ingreso){
        Caja caja = cajaRepository.findByCajaid(cajaid);
        caja.setTotal(caja.getTotal()+ingreso);
        cajaRepository.save(caja);
        return CajaToJson(caja).toString();
    }

    public String SacarDineroCaja(int cajaid,int egreso){
        Caja caja = cajaRepository.findByCajaid(cajaid);
        caja.setTotal(caja.getTotal()-egreso);
        cajaRepository.save(caja);
        return CajaToJson(caja).toString();
    }

    public String IngresarDineroFondoReserva(int cajaid,int ingreso){
        Caja caja = cajaRepository.findByCajaid(cajaid);
        caja.setFondo_reserva(caja.getFondo_reserva()+ingreso);
        cajaRepository.save(caja);
        return CajaToJson(caja).toString();
    }

    public String SacarDineroFondoReserva(int cajaid,int egreso){
        Caja caja = cajaRepository.findByCajaid(cajaid);
        caja.setFondo_reserva(caja.getFondo_reserva() - egreso);
        cajaRepository.save(caja);
        return CajaToJson(caja).toString();
    }

    private Caja jsonStringToCaja(String json){
        JSONObject serverJson = new JSONObject( json );
        int edificioid = Integer.parseInt(getOrNull( serverJson,"edificioid" ));
        int total = Integer.parseInt(getOrNull(serverJson,"total"));
        int fondo_reserva = Integer.parseInt(getOrNull(serverJson,"fondo_reserva"));
        String cbu = getOrNull(serverJson,"cbu");
        String nro_cuenta = getOrNull(serverJson,"nro_cuenta");
        String banco = getOrNull(serverJson,"banco");
        String sucursal = getOrNull(serverJson,"sucursal");
        String estado = getOrNull(serverJson,"estado");
        Caja newCaja = new Caja();
       newCaja.setEdificioid(edificioid);
       newCaja.setTotal(total);
       newCaja.setCbu(cbu);
       newCaja.setNro_cuenta(nro_cuenta);
       newCaja.setBanco(banco);
       newCaja.setSucursal(sucursal);
       newCaja.setEstado(estado);
       newCaja.setFondo_reserva(fondo_reserva);
        return newCaja;
    }

    private JSONObject CajaToJson(Caja caja){
        JSONObject serverJson = new JSONObject( caja );
        int edificioid = caja.getEdificioid();
        int total = caja.getTotal();
        String cbu = caja.getCbu();
        String nro_cuenta = caja.getNro_cuenta();
        String banco = caja.getBanco();
        String sucursal = caja.getSucursal();
        String estado = caja.getEstado();
        int fondo_reserva = caja.getFondo_reserva();
        serverJson.put("edificioid",edificioid);
        serverJson.put("total",total);
        serverJson.put("cbu",cbu);
        serverJson.put("nro_cuenta",nro_cuenta);
        serverJson.put("banco",banco);
        serverJson.put("sucursal",sucursal);
        serverJson.put("estado",estado);
        serverJson.put("fondo_reserva",fondo_reserva);
        return serverJson;
    }

    private static String getOrNull(JSONObject serverJson, String key){
        return serverJson.optString( key,null );
    }


}
