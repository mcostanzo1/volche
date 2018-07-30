package com.administracion.volche.service;

import com.administracion.volche.dao.CajaRepository;
import com.administracion.volche.dao.MovimientoRepository;
import com.administracion.volche.domain.Caja;
import com.administracion.volche.domain.Movimiento;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class MovimientoService {

    @Autowired
    private CajaRepository cajaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Transactional(readOnly=true)
    public String loadMoviminetoiById(final int movimientoid){
        return MovimientoToJson(movimientoRepository.findByMovimientoid(movimientoid)).toString();
    }

    public String CreateMovimiento(String movimiento) throws ParseException {
        Movimiento newMovimiento = jsonStringToMovimiento(movimiento);
        movimientoRepository.save( newMovimiento );
        return  "Movimiento creado!";
    }

    public String UpdateMovimiento(String movimientoid, String json){
        Movimiento elMovimiento = movimientoRepository.findByMovimientoid( Integer.parseInt( movimientoid ) );
        JSONObject serverJson = new JSONObject( json );
        int cajaid = Integer.parseInt(getOrNull( serverJson,"cajaid" ));
        int monto = Integer.parseInt(getOrNull(serverJson,"monto"));
        int presupuestoid = Integer.parseInt(getOrNull(serverJson,"presupuestoid"));
        int edificioid = Integer.parseInt(getOrNull(serverJson,"edificioid"));
        String usuarioid = getOrNull(serverJson,"usuarioid");
        String tipo = getOrNull(serverJson,"tipo");
        String estado = getOrNull(serverJson,"estado");
        String concepto = getOrNull(serverJson,"concepto");
        Movimiento newMovimiento = new Movimiento();
        newMovimiento.setCajaid(cajaid);
        newMovimiento.setMonto(monto);
        newMovimiento.setPresupuestoid(presupuestoid);
        newMovimiento.setEdificioid(edificioid);
        newMovimiento.setUsuarioid(usuarioid);
        newMovimiento.setTipo(tipo);
        newMovimiento.setEstado(estado);
        newMovimiento.setConcepto(concepto);
        movimientoRepository.save(newMovimiento);
        return  "El movimiento se modificó con exito!";
    }

    public String DeleteMovimiento(int movimientoid){
       Movimiento movimiento = movimientoRepository.findByMovimientoid( movimientoid );
        movimiento.setEstado( "FINALIZADO" );
        return "El movimiento se finalizó correctamente!";
    }



    private Movimiento jsonStringToMovimiento(String json) throws ParseException {
        JSONObject serverJson = new JSONObject( json );
        int cajaid = Integer.parseInt(getOrNull( serverJson,"cajaid" ));
        int monto = Integer.parseInt(getOrNull(serverJson,"monto"));
        int presupuestoid = Integer.parseInt(getOrNull(serverJson,"presupuestoid"));
        int edificioid = Integer.parseInt(getOrNull(serverJson,"edificioid"));
        String usuarioid = getOrNull(serverJson,"usuarioid");
        String tipo = getOrNull(serverJson,"tipo");
        String estado = getOrNull(serverJson,"estado");
        String concepto = getOrNull(serverJson,"concepto");
        Date fecha_pago = StringToDateConverter(getOrNull(serverJson,"fecha_pago"));
        Date fecha_vto = StringToDateConverter(getOrNull(serverJson,"fecha_vto"));
        Date fecha_carga = StringToDateConverter(getOrNull(serverJson,"fecha_carga"));
        Date fecha_mes_liquidado = StringToDateConverter(getOrNull(serverJson,"fecha_mes_liquidado"));

        Movimiento newMovimiento = new Movimiento();
        newMovimiento.setCajaid(cajaid);
        newMovimiento.setMonto(monto);
        newMovimiento.setPresupuestoid(presupuestoid);
        newMovimiento.setEdificioid(edificioid);
        newMovimiento.setUsuarioid(usuarioid);
        newMovimiento.setTipo(tipo);
        newMovimiento.setEstado(estado);
        newMovimiento.setConcepto(concepto);
        newMovimiento.setFecha_pago(fecha_pago);
        newMovimiento.setFecha_vto(fecha_vto);
        newMovimiento.setFecha_carga(fecha_carga);
        newMovimiento.setFecha_mes_liquidado(fecha_mes_liquidado);

        return newMovimiento;
    }

    private JSONObject MovimientoToJson(Movimiento movimiento){
        JSONObject serverJson = new JSONObject( movimiento );
        int cajaid=movimiento.getCajaid();
        int monto = movimiento.getMonto();
        int presupuestoid = movimiento.getPresupuestoid();
        int edificioid = movimiento.getEdificioid();
        String usuarioid = movimiento.getUsuarioid();
        String tipo = movimiento.getTipo();
        String estado = movimiento.getEstado();
        String concepto = movimiento.getConcepto();
        String fecha_pago = DateToStringConverter(movimiento.getFecha_pago());
        String fecha_vto = DateToStringConverter(movimiento.getFecha_vto());
        String fecha_carga = DateToStringConverter(movimiento.getFecha_carga());
        String fecha_mes_liquidado = DateToStringConverter(movimiento.getFecha_mes_liquidado());
        serverJson.put("cajaid",cajaid);
        serverJson.put("monto",monto);
        serverJson.put("presupuestoid",presupuestoid);
        serverJson.put("presupuestoid",presupuestoid);
        serverJson.put("edificioid",edificioid);
        serverJson.put("usuarioid",usuarioid);
        serverJson.put("tipo",tipo);
        serverJson.put("estado",estado);
        serverJson.put("concepto",concepto);
        serverJson.put("fecha_pago",fecha_pago);
        serverJson.put("fecha_vto",fecha_vto);
        serverJson.put("fecha_carga",fecha_carga);
        serverJson.put("fecha_mes_liquidado",fecha_mes_liquidado);
        return serverJson;
    }

    private static String getOrNull(JSONObject serverJson, String key){
        return serverJson.optString( key,null );
    }

    private Date StringToDateConverter (String date) throws ParseException {
        DateFormat formatterr = new SimpleDateFormat("yyyy-MM-dd");
        return formatterr.parse(date);
    }

    private String DateToStringConverter(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

}
