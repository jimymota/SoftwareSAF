package Modelos;

import java.sql.Date;

public class Consulta {

    int idcontrato, diafacturacion, diaalerta, idcliente;
    String cliente, tipofacturacion, estado;
    Date fechainicioc, fechafinc, fecharegistro;
    double tarifa;

    public Consulta() {

    }

    public Consulta(int idcontrato, String cliente, Date fechainicioc, Date fechafinc, int diafacturacion, int diaalerta, String tipofacturacion, double tarifa) {
        this.idcontrato = idcontrato;
        this.cliente = cliente;
        this.fechainicioc = fechainicioc;
        this.fechafinc = fechafinc;
        this.diafacturacion = diafacturacion;
        this.diaalerta = diaalerta;
        this.tipofacturacion = tipofacturacion;
        this.tarifa = tarifa;

    }

    public int getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(int idcontrato) {
        this.idcontrato = idcontrato;
    }

    public int getDiafacturacion() {
        return diafacturacion;
    }

    public void setDiafacturacion(int diafacturacion) {
        this.diafacturacion = diafacturacion;
    }

    public int getDiaalerta() {
        return diaalerta;
    }

    public void setDiaalerta(int diaalerta) {
        this.diaalerta = diaalerta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipofacturacion() {
        return tipofacturacion;
    }

    public void setTipofacturacion(String tipofacturacion) {
        this.tipofacturacion = tipofacturacion;
    }

    public Date getFechainicioc() {
        return fechainicioc;
    }

    public void setFechainicioc(Date fechainicioc) {
        this.fechainicioc = fechainicioc;
    }
    
     public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Date getFechafinc() {
        return fechafinc;
    }

    public void setFechafinc(Date fechafinc) {
        this.fechafinc = fechafinc;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }
    
     public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public int getIdCliente() {
        return idcliente;
    }

    public void setIdCliente(int idcliente) {
        this.idcliente = idcliente;
    }
    

}
