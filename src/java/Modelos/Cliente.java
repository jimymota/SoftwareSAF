
package Modelos;


public class Cliente {
   
    int idcliente;
    String ruc, razonsocial,estado;
    
    public Cliente(){
        
    }
    
    public Cliente(int idcliente, String ruc, String razonsocial, String estado) {
        this.idcliente = idcliente;
        this.ruc = ruc;
        this.razonsocial = razonsocial;
        this.estado = estado;
    }
    
     public int getIdCliente() {
        return idcliente;
    }

    public void setIdCliente(int idcliente) {
        this.idcliente = idcliente;
    }
    
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    public String getRazonSocial() {
        return razonsocial;
    }

    public void setRazonSocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }
    
     public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
}
