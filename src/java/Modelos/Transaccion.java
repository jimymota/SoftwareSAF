package Modelos;

import java.sql.Date;

public class Transaccion {

    int IdTransaccion, IdContrato, DiaAlerta;
    String NumeFactura, Observacion, Estado, RazonSocial, TipoFacturacion, Tarifa;
    Date FechaVencimiento, FechaRegistro;

    public Transaccion() {
    }

    public Transaccion(int IdTransaccion, Date FechaRegistro, Date FechaVencimiento, String NumeFactura, String Observacion, String Estado, String RazonSocial, String TipoFacturacion, String Tarifa, int IdContrato, int DiaAlerta) {
        this.IdTransaccion = IdTransaccion;
        this.FechaRegistro = FechaRegistro;
        this.FechaVencimiento = FechaVencimiento;
        this.DiaAlerta = DiaAlerta;
        this.NumeFactura = NumeFactura;
        this.Observacion = Observacion;
        this.Estado = Estado;
        this.RazonSocial = RazonSocial;
        this.TipoFacturacion = TipoFacturacion;
        this.Tarifa = Tarifa;
        this.IdContrato = IdContrato;
    }

    public int getIdTransaccion() {
        return IdTransaccion;
    }

    public void setIdTransaccion(int idtransaccion) {
        this.IdTransaccion = idtransaccion;
    }

    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Date fecharegistro) {
        this.FechaRegistro = fecharegistro;
    }

    public Date getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(Date fechavencimiento) {
        this.FechaVencimiento = fechavencimiento;
    }

    public String getNumeFactura() {
        return NumeFactura;
    }

    public void setNumeFactura(String numefactura) {
        this.NumeFactura = numefactura;
    }
    
     public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonsocial) {
        this.RazonSocial = razonsocial;
    }
    
     public String getTipoFacturacion() {
        return TipoFacturacion;
    }

    public void setTipoFacturacion(String tipofacturacion) {
        this.TipoFacturacion = tipofacturacion;
    }
    
    public String getTarifa() {
        return Tarifa;
    }

    public void setTarifa(String tarifa) {
        this.Tarifa = tarifa;
    }    

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        this.Observacion = observacion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
    }

    public int getDiaAlerta() {
        return DiaAlerta;
    }

    public void setDiaAlerta(int diaalerta) {
        this.DiaAlerta = diaalerta;
    }

    public int getIdContrato() {
        return IdContrato;
    }

    public void setIdContrato(int idcontrato) {
        this.IdContrato = idcontrato;
    }

}
