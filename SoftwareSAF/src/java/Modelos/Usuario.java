package Modelos;

public class Usuario {
    
    int IdUsuario, IdRol;
 
    String Dni, Nombre, Password, Direccion, Telefono, Observacion, Estado, NRol;

    public Usuario() {
    }

    public Usuario(int IdUsuario, String Dni, String Nombre, String Password, String Direccion, String Telefono, String Observacion, String Estado, int IdRol, String NRol) {
        this.IdUsuario = IdUsuario;
        this.Dni = Dni;
        this.Nombre = Nombre;     
        this.Password = Password;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Observacion = Observacion;       
        this.Estado = Estado;
        this.IdRol = IdRol;
        this.NRol = NRol;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idusuario) {
        this.IdUsuario = idusuario;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        this.Dni = dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
   
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }    
    
    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }
        
    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
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

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int idrol) {
        this.IdRol = idrol;
    }

     public String getNRol() {
        return NRol;
    }

    public void setNRol(String nrol) {
        this.NRol = nrol;
    }    
    
    
}
