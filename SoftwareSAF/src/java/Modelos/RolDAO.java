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

public class RolDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public Rol ConsultaPorCodigo(int idRol) {
        Rol rol = new Rol();
        String consulta = "SELECT * FROM rol WHERE IdRol = " + idRol;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                rol.setIdRol(rs.getInt("IdRol"));
                rol.setNombre(rs.getString("Nombre"));
                rol.setDescripcion(rs.getString("Descripcion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rol;
    }

    public void Agregar(Rol rol) {
        String sentencia = "INSERT INTO rol (Nombre,Descripcion) VALUES (?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, rol.getNombre());
            ps.setString(2, rol.getDescripcion());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Actualizar(Rol rol) {
        String sentencia = "UPDATE rol set Nombre=?,Descripcion=? WHERE IdRol=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, rol.getNombre());
            ps.setString(2, rol.getDescripcion());
            ps.setInt(3, rol.getIdRol());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List Listar() {
        String consulta = "SELECT * FROM rol";
        List<Rol> lista = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("IdRol"));
                rol.setNombre(rs.getString("Nombre"));
                rol.setDescripcion(rs.getString("Descripcion"));
                lista.add(rol);
            }
        } catch (Exception ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void Eliminar(int id) {
        String sql = "DELETE FROM rol WHERE IdRol=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List BListar(String vrol) {
        String consulta = "select * from rol where Nombre like'%" + vrol + "%' order by Nombre DESC";
        List<Rol> lista = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("IdRol"));
                rol.setNombre(rs.getString("Nombre"));
                rol.setDescripcion(rs.getString("Descripcion"));
                lista.add(rol);
            }
        } catch (Exception ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List OrdLista() {
        String consulta = "select * from rol order by Nombre DESC";
        List<Rol> listaOrd = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("IdRol"));
                rol.setNombre(rs.getString("Nombre"));
                rol.setDescripcion(rs.getString("Descripcion"));
                listaOrd.add(rol);
            }
        } catch (Exception ex) {
            Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaOrd;
    }

    public void Reporte() {
        try {
            con = cn.Conexion();
            String path = "C:\\Users\\Limberg Ayasta\\Desktop\\Integrador1\\ProgramaFinal\\saf-main\\SoftwareSAF\\src\\java\\Reportes\\ReporteRol.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, con);
            JasperExportManager.exportReportToHtmlFile(jprint, "C:\\Users\\Limberg Ayasta\\Desktop\\Integrador1\\ProgramaFinal\\saf-main\\SoftwareSAF\\web\\Report\\Rol.html");
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
