package Modelos;

import Config.Conexion;
import Controladores.Email;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ContratoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public Consulta ConsultaPorCodigo(int codigoContrato) {
        Consulta consulta = new Consulta();
        con = cn.Conexion();
        String sql = "select co.IdContrato, co.FechaRegistro, co.FechaInicioC, co.FechaFinC, co.DiaFacturacion, co.DiaAlerta, co.TipoFacturacion, co.Tarifa, co.Estado, cl.RazonSocial, cl.IdCliente from contrato co inner join cliente cl on co.IdCliente=cl.IdCliente where co.IdContrato=" + codigoContrato;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                consulta.setIdcontrato(rs.getInt("IdContrato"));
                consulta.setCliente(rs.getString("RazonSocial"));
                consulta.setFecharegistro(rs.getDate("FechaRegistro"));
                consulta.setFechainicioc(rs.getDate("FechaInicioC"));
                consulta.setFechafinc(rs.getDate("FechaFinC"));
                consulta.setDiafacturacion(rs.getInt("DiaFacturacion"));
                consulta.setDiaalerta(rs.getInt("DiaAlerta"));
                consulta.setTipofacturacion(rs.getString("TipoFacturacion"));
                consulta.setTarifa(rs.getDouble("Tarifa"));
                consulta.setEstado(rs.getString("Estado"));
                consulta.setIdCliente(rs.getInt("IdCliente"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consulta;
    }

    public int BId(String ruc) {
        int Id = 0;
        con = cn.Conexion();
        String sql = "SELECT IdCliente FROM cliente where Ruc='" + ruc + "'";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Id = rs.getInt("IdCliente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Id;
    }

    public void Actualizar(Contrato contrato) {
        String sentencia = "UPDATE contrato set FechaRegistro=?, FechaInicioC=?, FechaFinC=?, DiaFacturacion=?, DiaAlerta=?, TipoFacturacion=?, Tarifa=?, Estado=?, IdCliente=? WHERE IdContrato=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setDate(1, contrato.getFechaRegistro());
            ps.setDate(2, contrato.getFechaInicioC());
            ps.setDate(3, contrato.getFechaFinC());
            ps.setInt(4, contrato.getDiaFacturacion());
            ps.setInt(5, contrato.getDiaAlerta());
            ps.setString(6, contrato.getTipoFacturacion());
            ps.setDouble(7, contrato.getTarifa());
            ps.setString(8, contrato.getEstado());
            
            /*switch (contrato.getEstado()) {
                case "Activo":
                    ps.setString(8, "1");
                    break;
                case "Inactivo":
                    ps.setString(8, "0");
                    break;
            }*/
            ps.setInt(9, contrato.getIdCliente());
            ps.setInt(10, contrato.getIdContrato());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Agregar(Contrato contrato) {
        String sentencia = "INSERT INTO contrato (FechaRegistro,FechaInicioC,FechaFinC,DiaFacturacion,DiaAlerta,TipoFacturacion,Tarifa,Estado,IdCliente) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setDate(1, contrato.getFechaRegistro());
            ps.setDate(2, contrato.getFechaInicioC());
            ps.setDate(3, contrato.getFechaFinC());
            ps.setInt(4, contrato.getDiaFacturacion());
            ps.setInt(5, contrato.getDiaAlerta());
            ps.setString(6, contrato.getTipoFacturacion());
            ps.setDouble(7, contrato.getTarifa());
            ps.setString(8, contrato.getEstado());
            /*switch (contrato.getEstado()) {
                case "Activo":
                    ps.setString(8, "1");
                    break;
                case "Inactivo":
                    ps.setString(8, "0");
                    break;
            }*/
            ps.setInt(9, contrato.getIdCliente());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List Listar() {
        String sql = "SELECT CO.IdContrato, C.RazonSocial, CO.FechaInicioC, CO.FechaFinC, CO.DiaFacturacion, CO.DiaAlerta, CO.TipoFacturacion, CO.Tarifa, CO.Estado FROM cliente C inner JOIN contrato CO ON C.IdCliente = CO.IdCliente";
        List<Consulta> lista = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setIdcontrato(rs.getInt("IdContrato"));
                consulta.setCliente(rs.getString("RazonSocial"));
                consulta.setFechainicioc(rs.getDate("FechaInicioC"));
                consulta.setFechafinc(rs.getDate("FechaFinC"));
                consulta.setDiafacturacion(rs.getInt("DiaFacturacion"));
                consulta.setDiaalerta(rs.getInt("DiaAlerta"));
                consulta.setTipofacturacion(rs.getString("TipoFacturacion"));
                consulta.setTarifa(rs.getDouble("Tarifa"));
                consulta.setEstado(rs.getString("Estado"));
                lista.add(consulta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List ListaCliente() {
        String sql = "SELECT Ruc FROM cliente where Estado='Activo'";
        List<Cliente> listaC = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setRuc(rs.getString("Ruc"));
                listaC.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaC;
    }

    public void SendEmail() throws ParseException, IOException, MessagingException {
        String consulta = "SELECT Cli.RazonSocial, con.IdContrato, con.FechaInicioC, con.FechaFinC, con.DiaFacturacion, con.DiaAlerta, con.TipoFacturacion, con.Tarifa FROM cliente Cli JOIN contrato Con ON Cli.IdCliente = Con.IdCliente WHERE cli.Estado=1";
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                CliCon clicon = new CliCon();
                String FechaFactura = LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonth() + "-" + rs.getInt("DiaFacturacion");
                Date fechafactura = formatter.parse(FechaFactura);
                Instant instant = fechafactura.toInstant();
                if (ChronoUnit.DAYS.between(instant, LocalDate.now()) == 0) {
                    Email m = new Email("D:/Integrador-1/ProgramaFinal/saf-main/SoftwareSAF/config");
                    String Cuerpo = "Se debe facturar al cliente " + rs.getString("RazonSocial") + " ya que su fecha de facturacion es: " + FechaFactura;
                    m.enviarEmail("Alerta - Facturacion Cliente", Cuerpo, "layasta@mio.pe");
                    //String correos[] = {"layasta@mio.pe", "soporte@mio.pe"};
                    //m.enviarEmail("Prueba", "Cuerpo", correos);
                    /*clicon.setIdContrato(rs.getInt("IdContrato"));
                    clicon.setIdCliente(rs.getInt("IdCliente"));
                    clicon.setFechaRegistro(rs.getDate("FechaRegistro"));
                    clicon.setFechaInicioC(rs.getDate("FechaInicioC"));
                    clicon.setFechaFinC(rs.getDate("FechaFinC"));
                    clicon.setDiaFacturacion(rs.getInt("DiaFacturacion"));
                    clicon.setDiaAlerta(rs.getInt("DiaAlerta"));
                    clicon.setTipoFacturacion(rs.getString("TipoFacturacion"));
                    clicon.setTarifa(rs.getDouble("Tarifa"));*/
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Eliminar(int id) {
        String sql = "DELETE FROM contrato WHERE IdContrato=" + id;
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List BListar(String razonsocial) {
        String sql = "select CO.IdContrato, C.RazonSocial, CO.FechaInicioC, CO.FechaFinC, CO.DiaFacturacion, CO.DiaAlerta, CO.TipoFacturacion, CO.Tarifa, CO.Estado FROM cliente C inner JOIN contrato CO ON C.IdCliente = CO.IdCliente where RazonSocial like'%" + razonsocial + "%' order by RazonSocial";
        List<Consulta> lista = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setIdcontrato(rs.getInt("IdContrato"));
                consulta.setCliente(rs.getString("RazonSocial"));
                consulta.setFechainicioc(rs.getDate("FechaInicioC"));
                consulta.setFechafinc(rs.getDate("FechaFinC"));
                consulta.setDiafacturacion(rs.getInt("DiaFacturacion"));
                consulta.setDiaalerta(rs.getInt("DiaAlerta"));
                consulta.setTipofacturacion(rs.getString("TipoFacturacion"));
                consulta.setTarifa(rs.getDouble("Tarifa"));
                consulta.setEstado(rs.getString("Estado"));
                lista.add(consulta);
            }
        } catch (Exception ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List OrdLista() {
        String sql = "select CO.IdContrato, C.RazonSocial, CO.FechaInicioC, CO.FechaFinC, CO.DiaFacturacion, CO.DiaAlerta, CO.TipoFacturacion, CO.Tarifa, CO.Estado FROM cliente C inner JOIN contrato CO ON C.IdCliente = CO.IdCliente order by RazonSocial DESC";
        List<Consulta> listaOrd = new ArrayList();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setIdcontrato(rs.getInt("IdContrato"));
                consulta.setCliente(rs.getString("RazonSocial"));
                consulta.setFechainicioc(rs.getDate("FechaInicioC"));
                consulta.setFechafinc(rs.getDate("FechaFinC"));
                consulta.setDiafacturacion(rs.getInt("DiaFacturacion"));
                consulta.setDiaalerta(rs.getInt("DiaAlerta"));
                consulta.setTipofacturacion(rs.getString("TipoFacturacion"));
                consulta.setTarifa(rs.getDouble("Tarifa"));
                consulta.setEstado(rs.getString("Estado"));
                listaOrd.add(consulta);
            }
        } catch (Exception ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaOrd;
    }

    public void Reporte() {
        try {
            con = cn.Conexion();
            String path = "C:\\Users\\Limberg Ayasta\\Desktop\\Integrador1\\ProgramaFinal\\saf-main\\SoftwareSAF\\src\\java\\Reportes\\ReporteContrato.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, con);
            JasperExportManager.exportReportToHtmlFile(jprint, "C:\\Users\\Limberg Ayasta\\Desktop\\Integrador1\\ProgramaFinal\\saf-main\\SoftwareSAF\\web\\Report\\contrato.html");
        } catch (Exception ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
