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

public class ClienteDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public Cliente ConsultaPorCodigo(int idCliente) {
        Cliente cliente = new Cliente();
        String consulta = "SELECT * FROM cliente WHERE IdCliente = " + idCliente;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setRuc(rs.getString("Ruc"));
                cliente.setRazonSocial(rs.getString("RazonSocial"));
                cliente.setEstado(rs.getString("Estado"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public void Agregar(Cliente cliente) {
        String sentencia = "INSERT INTO cliente (Ruc,RazonSocial,Estado) VALUES (?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cliente.getRuc());
            ps.setString(2, cliente.getRazonSocial());
            ps.setString(3, cliente.getEstado());            
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Actualizar(Cliente cliente) {
        String sentencia = "UPDATE cliente set Ruc=?,RazonSocial=?,Estado=? WHERE IdCliente=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cliente.getRuc());
            ps.setString(2, cliente.getRazonSocial());
            ps.setString(3, cliente.getEstado());            
            ps.setInt(4, cliente.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    public List Listar() {
        String consulta = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setRuc(rs.getString("Ruc"));
                cliente.setRazonSocial(rs.getString("RazonSocial"));
                cliente.setEstado(rs.getString("Estado"));
                lista.add(cliente);
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void Eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE IdCliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Reporte() {
        try {
            con = cn.Conexion();
            String path = "C:\\Users\\Limberg Ayasta\\Desktop\\Integrador1\\ProgramaFinal\\saf-main\\SoftwareSAF\\src\\java\\Reportes\\ReporteClientes.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, con);
            JasperExportManager.exportReportToHtmlFile(jprint, "C:\\Users\\Limberg Ayasta\\Desktop\\Integrador1\\ProgramaFinal\\saf-main\\SoftwareSAF\\web\\Report\\clientes.html");
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean BRuc(String vRuc) {
        String consulta = "SELECT * FROM cliente where Ruc like '" + vRuc + "'";        
        boolean varb = false;      
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                varb = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return varb;
    }
    
    public List BListar(String razonsocial) {
        String consulta = "select * from cliente where RazonSocial like'%" + razonsocial + "%' order by RazonSocial DESC";
        List<Cliente> lista = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setRuc(rs.getString("Ruc"));
                cliente.setRazonSocial(rs.getString("RazonSocial"));
                cliente.setEstado(rs.getString("Estado"));
                lista.add(cliente);
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List OrdLista() {
        String consulta = "select * from cliente order by RazonSocial DESC";
        List<Cliente> listaOrd = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setRuc(rs.getString("Ruc"));
                cliente.setRazonSocial(rs.getString("RazonSocial"));
                cliente.setEstado(rs.getString("Estado"));
                listaOrd.add(cliente);
            }
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaOrd;
    }
    

}
