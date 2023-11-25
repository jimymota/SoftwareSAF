package Modelos;


import java.sql.Date;

public class CliCon {

    int idcontrato, idcliente, diafacturacion, diaalerta;
    String razonsocial,  tipofacturacion;
    Date fecharegistro, fechainicioc, fechafinc;
    double tarifa;

    public CliCon() {
    }

    public CliCon(String razonsocial, int idcontrato, Date fecharegistro, Date fechainicioc, Date fechafinc, int diafacturacion, int diaalerta, String tipofacturacion, double tarifa, int idcliente) {
        this.razonsocial = razonsocial;
        this.idcontrato = idcontrato;
        this.fecharegistro = fecharegistro;
        this.fechainicioc = fechainicioc;
        this.fechafinc = fechafinc;
        this.diafacturacion = diafacturacion;
        this.diaalerta = diaalerta;
        this.tipofacturacion = tipofacturacion;
        this.tarifa = tarifa;
        this.idcliente = idcliente;
    }
    
     public String getRazonSocial() {
        return razonsocial;
    }

    public void setRazonSocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }   
    
    public int getIdContrato() {
        return idcontrato;
    }

    public void setIdContrato(int idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Date getFechaRegistro() {
        return fecharegistro;
    }

    public void setFechaRegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Date getFechaInicioC() {
        return fechainicioc;
    }

    public void setFechaInicioC(Date fechainicioc) {
        this.fechainicioc = fechainicioc;
    }

    public Date getFechaFinC() {
        return fechafinc;
    }

    public void setFechaFinC(Date fechafinc) {
        this.fechafinc = fechafinc;
    }

    public int getDiaFacturacion() {
        return diafacturacion;
    }

    public void setDiaFacturacion(int diafacturacion) {
        this.diafacturacion = diafacturacion;
    }

    public int getDiaAlerta() {
        return diaalerta;
    }

    public void setDiaAlerta(int diaalerta) {
        this.diaalerta = diaalerta;
    }

    public String getTipoFacturacion() {
        return tipofacturacion;
    }

    public void setTipoFacturacion(String tipofacturacion) {
        this.tipofacturacion = tipofacturacion;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }


    public int getIdCliente() {
        return idcliente;
    }

    public void setIdCliente(int idcliente) {
        this.idcliente = idcliente;
    }

}
