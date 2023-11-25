package Modelos;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class UsuarioDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();


    /*Login*/
    public Usuario Validar(String documento, String password) {
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM usuario WHERE Dni = ? AND Password = ?";
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, documento);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();
            do {
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setDni(rs.getString("Dni"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setPassword(rs.getString("Password"));
                usuario.setEstado(rs.getString("Estado"));
            } while (rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public Usuario BuscarCliente(int documento) {
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM usuario WHERE Dni = ?";
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, documento);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setDni(rs.getString("Dni"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setPassword(rs.getString("Password"));
                usuario.setDireccion(rs.getString("Direccion"));
                usuario.setIdRol(rs.getInt("IdRol"));
                System.err.println("" + usuario.getNombre());
            }
        } catch (Exception e) {
        }
        return usuario;
    }

    public List Listar() {
        String consulta = "SELECT Usu.IdUsuario, Usu.Dni, Usu.Nombre, Usu.Password, Usu.Telefono, Usu.Estado, Ro.Nombre as Rol, ro.IdRol as IdRol FROM Usuario Usu INNER JOIN Rol Ro ON Usu.IdRol = ro.IdRol";
        List<Usuario> lista = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setDni(rs.getString("Dni"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setPassword(rs.getString("Password"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setEstado(rs.getString("Estado"));
                usuario.setNRol(rs.getString("Rol"));
                usuario.setIdRol(rs.getInt("IdRol"));
                lista.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void Agregar(Usuario usuario) {
        String sentencia = "INSERT INTO usuario (Dni, Nombre, Password, Direccion, Telefono, Observacion, Estado, IdRol) VALUES (?,?,?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, usuario.getDni());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getObservacion());
            ps.setString(7, usuario.getEstado());
            ps.setInt(8, usuario.getIdRol());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario ConsultaPorCodigo(int idUsuario) {
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM usuario WHERE IdUsuario=" + idUsuario;
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setDni(rs.getString(2));
                usuario.setNombre(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setDireccion(rs.getString(5));
                usuario.setTelefono(rs.getString(6));
                usuario.setObservacion(rs.getString(7));
                usuario.setEstado(rs.getString(8));
                usuario.setIdRol(rs.getInt(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public void Actualizar(Usuario usuario) {
        String sentencia = "UPDATE usuario set Dni=?, Nombre=?, Password=?, Direccion=?, Telefono=?, Observacion=?, Estado=? , IdRol=? WHERE IdUsuario=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, usuario.getDni());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getObservacion());
            ps.setString(7, usuario.getEstado());
            ps.setInt(8, usuario.getIdRol());
            ps.setInt(9, usuario.getIdUsuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Eliminar(int id) {
        String sql = "DELETE FROM USUARIO WHERE IdUsuario=" + id;
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Reporte() {
        try {
            con = cn.Conexion();
            String path = "C:\\Users\\Limberg Ayasta\\Desktop\\Integrador1\\ProgramaFinal\\saf-main\\SoftwareSAF\\src\\java\\Reportes\\ReporteUsuarios.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, con);
            JasperExportManager.exportReportToHtmlFile(jprint, "C:\\Users\\Limberg Ayasta\\Desktop\\Integrador1\\ProgramaFinal\\saf-main\\SoftwareSAF\\web\\Report\\usuarios.html");
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean BDni(String vDni) {
        String consulta = "SELECT * FROM usuario where Dni like '" + vDni + "'";        
        boolean varb = false;      
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                varb = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return varb;
    }

    public List BListar(String vnombre) {
        String consulta = "SELECT Usu.IdUsuario, Usu.Dni, Usu.Nombre, Usu.Password, Usu.Telefono, Usu.Estado, Ro.Nombre as Rol, ro.IdRol as IdRol FROM Usuario Usu INNER JOIN Rol Ro ON Usu.IdRol = ro.IdRol where Usu.Nombre like'%" + vnombre + "%' order by Usu.Nombre DESC";
        List<Usuario> lista = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setDni(rs.getString("Dni"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setPassword(rs.getString("Password"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setEstado(rs.getString("Estado"));
                usuario.setNRol(rs.getString("Rol"));
                usuario.setIdRol(rs.getInt("IdRol"));
                lista.add(usuario);
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List OrdLista() {
        String consulta = "SELECT Usu.IdUsuario, Usu.Dni, Usu.Nombre, Usu.Password, Usu.Telefono, Usu.Estado, Ro.Nombre as Rol, ro.IdRol as IdRol FROM Usuario Usu INNER JOIN Rol Ro ON Usu.IdRol = ro.IdRol order by Usu.Nombre DESC";
        List<Usuario> listaOrd = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setDni(rs.getString("Dni"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setPassword(rs.getString("Password"));
                usuario.setTelefono(rs.getString("Telefono"));
                usuario.setEstado(rs.getString("Estado"));
                usuario.setNRol(rs.getString("Rol"));
                usuario.setIdRol(rs.getInt("IdRol"));
                listaOrd.add(usuario);
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaOrd;
    }

}
