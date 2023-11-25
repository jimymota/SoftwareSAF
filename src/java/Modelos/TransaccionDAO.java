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

public class TransaccionDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public List Listar() {
        String sql = "select tr.IdTransaccion, co.IdContrato, tr.FechaVencimiento, cl.RazonSocial, tr.NumeFactura, co.TipoFacturacion, co.Tarifa, tr.Estado from cliente cl inner join contrato co on cl.IdCliente=co.IdCliente inner join transaccion tr on co.IdContrato=tr.IdContrato where cl.Estado='Activo'";
        List<Transaccion> lista = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setIdTransaccion(rs.getInt("IdTransaccion"));
                transaccion.setIdContrato(rs.getInt("IdContrato"));
                transaccion.setFechaVencimiento(rs.getDate("FechaVencimiento"));
                transaccion.setRazonSocial(rs.getString("RazonSocial"));
                transaccion.setNumeFactura(rs.getString("NumeFactura"));
                transaccion.setTipoFacturacion(rs.getString("TipoFacturacion"));
                transaccion.setTarifa(rs.getString("Tarifa"));
                transaccion.setEstado(rs.getString("Estado"));
                lista.add(transaccion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List ListaContrato() {
        String sql = "SELECT IdContrato FROM contrato where Estado='Pendiente'";
        List<Consulta> listaC = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Consulta contrato = new Consulta();
                contrato.setIdcontrato(rs.getInt("IdContrato"));
                listaC.add(contrato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaC;
    }

    public void Agregar(Transaccion transaccion) {
        String sentencia = "INSERT INTO transaccion (FechaRegistro, FechaVencimiento, NumeFactura, DiaAlerta, Observacion, Estado, IdContrato) VALUES (?,?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setDate(1, transaccion.getFechaRegistro());
            ps.setDate(2, transaccion.getFechaVencimiento());
            ps.setString(3, transaccion.getNumeFactura());
            ps.setInt(4, transaccion.getDiaAlerta());
            ps.setString(5, transaccion.getObservacion());
            ps.setString(6, transaccion.getEstado());
            ps.setInt(7, transaccion.getIdContrato());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Transaccion ConsultaPorCodigo(int idTransaccion) {
        Transaccion transaccion = new Transaccion();
        String sql = "select tr.IdTransaccion, cl.RazonSocial, co.IdContrato, tr.FechaVencimiento, tr.DiaAlerta, tr.NumeFactura, co.TipoFacturacion, co.Tarifa, tr.Observacion, tr.Estado from cliente cl inner join contrato co on cl.IdCliente=co.IdCliente inner join transaccion tr on co.IdContrato=tr.IdContrato where cl.Estado='Activo' and tr.IdTransaccion=" + idTransaccion;
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                transaccion.setIdTransaccion(rs.getInt(1));
                transaccion.setRazonSocial(rs.getString(2));
                transaccion.setIdContrato(rs.getInt(3));
                transaccion.setFechaVencimiento(rs.getDate(4));
                transaccion.setDiaAlerta(rs.getInt(5));
                transaccion.setNumeFactura(rs.getString(6));
                transaccion.setTipoFacturacion(rs.getString(7));
                transaccion.setTarifa(rs.getString(8));
                transaccion.setObservacion(rs.getString(9));
                transaccion.setEstado(rs.getString(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transaccion;
    }

    public void Actualizar(Transaccion transaccion) {
        String sentencia = "UPDATE transaccion set NumeFactura=?, FechaVencimiento=?, DiaAlerta=?, Observacion=?, Estado=?, IdContrato=? WHERE IdTransaccion=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            // ps.setString(1, transaccion.getFechaRegistro());
            ps.setString(1, transaccion.getNumeFactura());
            ps.setDate(2, transaccion.getFechaVencimiento());
            ps.setInt(3, transaccion.getDiaAlerta());
            ps.setString(4, transaccion.getObservacion());
            ps.setString(5, transaccion.getEstado());
            ps.setInt(6, transaccion.getIdContrato());
            ps.setInt(7, transaccion.getIdTransaccion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Eliminar(int id) {
        String sql = "DELETE FROM transaccion WHERE IdTransaccion=" + id;
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Reporte() {
        try {
            con = cn.Conexion();
            String path = "C:\\Users\\Limberg Ayasta\\Desktop\\Integrador1\\ProgramaFinal\\saf-main\\SoftwareSAF\\src\\java\\Reportes\\ReporteTransaccion.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, con);
            JasperExportManager.exportReportToHtmlFile(jprint, "C:\\Users\\Limberg Ayasta\\Desktop\\Integrador1\\ProgramaFinal\\saf-main\\SoftwareSAF\\web\\Report\\Transaccion.html");
        } catch (Exception ex) {
            Logger.getLogger(TransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List OrdLista() {
        String sql = "select tr.IdTransaccion, co.IdContrato, tr.FechaVencimiento, cl.RazonSocial, tr.NumeFactura, co.TipoFacturacion, co.Tarifa, tr.Estado from cliente cl inner join contrato co on cl.IdCliente=co.IdCliente inner join transaccion tr on co.IdContrato=tr.IdContrato where cl.Estado='Activo' order by cl.RazonSocial DESC";
        List<Transaccion> listaOrd = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setIdTransaccion(rs.getInt("IdTransaccion"));
                transaccion.setIdContrato(rs.getInt("IdContrato"));
                transaccion.setFechaVencimiento(rs.getDate("FechaVencimiento"));
                transaccion.setRazonSocial(rs.getString("RazonSocial"));
                transaccion.setNumeFactura(rs.getString("NumeFactura"));
                transaccion.setTipoFacturacion(rs.getString("TipoFacturacion"));
                transaccion.setTarifa(rs.getString("Tarifa"));
                transaccion.setEstado(rs.getString("Estado"));
                listaOrd.add(transaccion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaOrd;
    }

    public List BListar(String razonsocial) {
        String sql = "select tr.IdTransaccion, co.IdContrato, tr.FechaVencimiento, cl.RazonSocial, tr.NumeFactura, co.TipoFacturacion, co.Tarifa, tr.Estado from cliente cl inner join contrato co on cl.IdCliente=co.IdCliente inner join transaccion tr on co.IdContrato=tr.IdContrato where cl.Estado='Activo' and cl.RazonSocial like'%" + razonsocial + "%' order by cl.RazonSocial";
        List<Transaccion> listaB = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setIdTransaccion(rs.getInt("IdTransaccion"));
                transaccion.setIdContrato(rs.getInt("IdContrato"));
                transaccion.setFechaVencimiento(rs.getDate("FechaVencimiento"));
                transaccion.setRazonSocial(rs.getString("RazonSocial"));
                transaccion.setNumeFactura(rs.getString("NumeFactura"));
                transaccion.setTipoFacturacion(rs.getString("TipoFacturacion"));
                transaccion.setTarifa(rs.getString("Tarifa"));
                transaccion.setEstado(rs.getString("Estado"));
                listaB.add(transaccion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransaccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaB;
    }

}
