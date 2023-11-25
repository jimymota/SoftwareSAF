package Controladores;

import Modelos.Usuario;
import Modelos.UsuarioDAO;
import Modelos.Cliente;
import Modelos.ClienteDAO;
import Modelos.Consulta;
import Modelos.Contrato;
import Modelos.ContratoDAO;
import Modelos.Rol;
import Modelos.RolDAO;
import Modelos.Transaccion;
import Modelos.TransaccionDAO;
import java.io.IOException;
import java.text.DateFormat;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();

    Rol rol = new Rol();
    RolDAO rolDAO = new RolDAO();

    Consulta consulta = new Consulta();
    Contrato contrato = new Contrato();
    ContratoDAO ContratoDAO = new ContratoDAO();

    Transaccion transaccion = new Transaccion();
    TransaccionDAO transaccionDAO = new TransaccionDAO();

    int idUsuario, idCliente, VIdrol21;
    Date fecharegistro, fechaVencimiento, fechaInicioC, fechaFinC;
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }

        if (menu.equals("Usuarios")) {
            switch (accion) {
                case "Listar":
                    List lista = usuarioDAO.Listar();
                    request.setAttribute("usuarios", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtdni");
                    boolean BUsu = usuarioDAO.BDni(dni);
                    if (BUsu) {
                        // mensaje que encontro un dni igual
                    } else {
                        String nombre = request.getParameter("txtnombre");
                        String password = request.getParameter("txtpassword");
                        String direccion = request.getParameter("txtdireccion");
                        String telefono = request.getParameter("txttelefono");
                        String observacion = request.getParameter("txtobservacion");
                        String estado = request.getParameter("txtestado");
                        String ValRol2 = request.getParameter("txtrol");
                        switch (ValRol2) {
                            case "Admin":
                                VIdrol21 = 1;
                                break;
                            case "Operador":
                                VIdrol21 = 2;
                                break;
                            default:
                                VIdrol21 = 2;
                                break;
                        }
                        usuario.setDni(dni);
                        usuario.setNombre(nombre);
                        usuario.setPassword(password);
                        usuario.setDireccion(direccion);
                        usuario.setTelefono(telefono);
                        usuario.setObservacion(observacion);
                        usuario.setEstado(estado);
                        usuario.setIdRol(VIdrol21);
                        usuarioDAO.Agregar(usuario);
                    }
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    int VId21 = Integer.parseInt(request.getParameter("txtid21"));
                    String dni1 = request.getParameter("txtdni21");
                    String nombre1 = request.getParameter("txtnombre21");
                    String password1 = request.getParameter("txtpassword21");
                    String direccion1 = request.getParameter("txtdireccion21");
                    String telefono1 = request.getParameter("txttelefono21");
                    String observacion1 = request.getParameter("txtobservacion21");
                    String estado1 = request.getParameter("txtestado21");
                    String ValRol = request.getParameter("txtrol21");
                    switch (ValRol) {
                        case "Admin":
                            VIdrol21 = 1;
                            break;
                        case "Operador":
                            VIdrol21 = 2;
                            break;
                    }
                    usuario.setDni(dni1);
                    usuario.setNombre(nombre1);
                    usuario.setPassword(password1);
                    usuario.setDireccion(direccion1);
                    usuario.setTelefono(telefono1);
                    usuario.setObservacion(observacion1);
                    usuario.setEstado(estado1);
                    usuario.setIdRol(VIdrol21);
                    usuario.setIdUsuario(VId21);
                    usuarioDAO.Actualizar(usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Cargar":
                    int VIdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
                    usuario = usuarioDAO.ConsultaPorCodigo(VIdUsuario);
                    request.setAttribute("usuarioxxx", usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Buscar":
                    String TBuscar = request.getParameter("tsearch");
                    List listaUsu = usuarioDAO.BListar(TBuscar);
                    request.setAttribute("usuarios", listaUsu);
                    break;
                case "Ordenar":
                    List listaTotal = usuarioDAO.OrdLista();
                    request.setAttribute("usuarios", listaTotal);
                    break;
                case "Eliminar":
                    idUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
                    usuarioDAO.Eliminar(idUsuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Reporte":
                    usuarioDAO.Reporte();
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Usuario.jsp").forward(request, response);
        }

        if (menu.equals("Clientes")) {
            switch (accion) {
                case "Listar":
                    List lista = clienteDAO.Listar();
                    request.setAttribute("clientes", lista);
                    break;
                case "Agregar":
                    String ruc = request.getParameter("txtruc");
                    boolean RCli = clienteDAO.BRuc(ruc);
                    if (RCli) {
                        //mensaje por que si encontro ruc
                    } else {
                        String razonsocial = request.getParameter("txtrazonsocial");
                        String estado = request.getParameter("txtestado");
                        cliente.setRuc(ruc);
                        cliente.setRazonSocial(razonsocial);
                        cliente.setEstado(estado);
                        clienteDAO.Agregar(cliente);
                    }
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Cargar":
                    int VIdCliente = Integer.parseInt(request.getParameter("IdCliente"));
                    cliente = clienteDAO.ConsultaPorCodigo(VIdCliente);
                    request.setAttribute("clientexx", cliente);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    int VIdCliente2 = Integer.parseInt(request.getParameter("txtid20"));
                    String ruc1 = request.getParameter("txtruc20");
                    String razonsocial1 = request.getParameter("txtrazon20");
                    String estado1 = request.getParameter("txtestado20");
                    cliente.setRuc(ruc1);
                    cliente.setRazonSocial(razonsocial1);
                    cliente.setEstado(estado1);
                    cliente.setIdCliente(VIdCliente2);
                    clienteDAO.Actualizar(cliente);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    idCliente = Integer.parseInt(request.getParameter("IdCliente"));
                    clienteDAO.Eliminar(idCliente);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Reporte":
                    clienteDAO.Reporte();
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Buscar":
                    String TBuscar = request.getParameter("tsearch");
                    List listaCli = clienteDAO.BListar(TBuscar);
                    request.setAttribute("clientes", listaCli);
                    break;
                case "Ordenar":
                    List listaTotal = clienteDAO.OrdLista();
                    request.setAttribute("clientes", listaTotal);
                    break;
            }
            request.getRequestDispatcher("Cliente.jsp").forward(request, response);
        }

        if (menu.equals("Contratos")) {
            switch (accion) {
                case "Listar":
                    List lista = ContratoDAO.Listar();
                    request.setAttribute("consultas", lista);
                    List listaCl = ContratoDAO.ListaCliente();
                    request.setAttribute("clientes", listaCl);
                    break;
                case "Recordatorio": {
                    try {
                        ContratoDAO.SendEmail();
                    } catch (ParseException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MessagingException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                request.getRequestDispatcher("Controlador?menu=Contratos&accion=Listar").forward(request, response);
                break;
                case "Agregar":
                    try {
                    LocalDate dateTime = LocalDate.now();
                    fecharegistro = Date.valueOf(dateTime);
                    String Fecha2 = request.getParameter("txtFechaInicioC");
                    java.sql.Date fe2 = new java.sql.Date(formatter.parse(Fecha2).getTime());
                    fechaInicioC = fe2;
                    String Fecha3 = request.getParameter("txtFechaFinC");
                    java.sql.Date fe3 = new java.sql.Date(formatter.parse(Fecha3).getTime());
                    fechaFinC = fe3;
                    int diaFacturacion = Integer.parseInt(request.getParameter("txtDiaFacturacion"));
                    int diaAlerta = Integer.parseInt(request.getParameter("txtDiaAlerta"));
                    String tipoFacturacion = request.getParameter("txtTipoFacturacion");
                    double tarifa = Double.parseDouble(request.getParameter("txtTarifa"));
                    String estado = request.getParameter("txtEstado");
                    String Ruc = request.getParameter("txtRuc");
                    int IdCliente = ContratoDAO.BId(Ruc);
                    if (IdCliente > 0) {
                        contrato.setFechaRegistro(fecharegistro);
                        contrato.setFechaInicioC(fechaInicioC);
                        contrato.setFechaFinC(fechaFinC);
                        contrato.setDiaFacturacion(diaFacturacion);
                        contrato.setDiaAlerta(diaAlerta);
                        contrato.setTipoFacturacion(tipoFacturacion);
                        contrato.setTarifa(tarifa);
                        contrato.setEstado(estado);
                        contrato.setIdCliente(IdCliente);
                        ContratoDAO.Agregar(contrato);
                    } else {
                        ///mesaje que no se pudo obtener el id
                    }
                    request.getRequestDispatcher("Controlador?menu=Contratos&accion=Listar").forward(request, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
                case "Eliminar":
                    int VidCon = Integer.parseInt(request.getParameter("IdContrato"));
                    ContratoDAO.Eliminar(VidCon);
                    request.getRequestDispatcher("Controlador?menu=Contratos&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                 try {
                    int VIdContrato3 = Integer.parseInt(request.getParameter("txtIdContrato20"));
                    String Fecha4 = request.getParameter("txtFechaRegistro20");
                    java.sql.Date fe4 = new java.sql.Date(formatter.parse(Fecha4).getTime());
                    Date fecharegistro1 = fe4;
                    String Fecha5 = request.getParameter("txtFechaInicioC20");
                    java.sql.Date fe5 = new java.sql.Date(formatter.parse(Fecha5).getTime());
                    Date fechaInicioC1 = fe5;
                    String Fecha6 = request.getParameter("txtFechaFinC20");
                    java.sql.Date fe6 = new java.sql.Date(formatter.parse(Fecha6).getTime());
                    Date fechaFinC1 = fe6;
                    int diaFacturacion1 = Integer.parseInt(request.getParameter("txtDiaFacturacion20"));
                    int diaAlerta1 = Integer.parseInt(request.getParameter("txtDiaAlerta20"));
                    String tipoFacturacion1 = request.getParameter("txtTipoFacturacion20");
                    double tarifa1 = Double.parseDouble(request.getParameter("txtTarifa20"));
                    String estado1 = request.getParameter("txtEstado20");
                    int idCliente1 = Integer.parseInt(request.getParameter("txtIdCliente20"));
                    contrato.setFechaRegistro(fecharegistro1);
                    contrato.setFechaInicioC(fechaInicioC1);
                    contrato.setFechaFinC(fechaFinC1);
                    contrato.setDiaFacturacion(diaFacturacion1);
                    contrato.setDiaAlerta(diaAlerta1);
                    contrato.setTipoFacturacion(tipoFacturacion1);
                    contrato.setTarifa(tarifa1);
                    contrato.setEstado(estado1);
                    contrato.setIdCliente(idCliente1);
                    contrato.setIdContrato(VIdContrato3);
                    ContratoDAO.Actualizar(contrato);
                    request.getRequestDispatcher("Controlador?menu=Contratos&accion=Listar").forward(request, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
                case "Cargar":
                    int idContra = Integer.parseInt(request.getParameter("IdContrato"));
                    consulta = ContratoDAO.ConsultaPorCodigo(idContra);
                    request.setAttribute("contratoSelecionado", consulta);
                    request.getRequestDispatcher("Controlador?menu=Contratos&accion=Listar").forward(request, response);
                    break;
                case "Buscar":
                    String TBuscar = request.getParameter("tsearch");
                    List listaCli = ContratoDAO.BListar(TBuscar);
                    request.setAttribute("consultas", listaCli);
                    break;
                case "Ordenar":
                    List listaTotal = ContratoDAO.OrdLista();
                    request.setAttribute("consultas", listaTotal);
                    break;
                case "Reporte":
                    ContratoDAO.Reporte();
                    request.getRequestDispatcher("Controlador?menu=Contratos&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Contrato.jsp").forward(request, response);
        }

        if (menu.equals("Transacciones")) {
            switch (accion) {
                case "Listar":
                    List lista = transaccionDAO.Listar();
                    request.setAttribute("transacciones", lista);
                    List listaCo = transaccionDAO.ListaContrato();
                    request.setAttribute("contratos", listaCo); 
                    break;
                case "Agregar":
                    try {
                    LocalDate dateTime = LocalDate.now();
                    fecharegistro = Date.valueOf(dateTime);
                    int idcontrato = Integer.parseInt(request.getParameter("txtNumeroC"));
                    String Nfactura = request.getParameter("txtnumefactura");
                    String FechaV = request.getParameter("txtfechavencimiento");
                    java.sql.Date fvenci = new java.sql.Date(formatter.parse(FechaV).getTime());
                    Date fechaVencimiento = fvenci;
                    int diaA = Integer.parseInt(request.getParameter("txtdiaalerta"));
                    String observacion = request.getParameter("txtobservacion");
                    String estado = "Pendiente";
                    transaccion.setFechaRegistro(fecharegistro);
                    transaccion.setIdContrato(idcontrato);
                    transaccion.setNumeFactura(Nfactura);
                    transaccion.setFechaVencimiento(fechaVencimiento);
                    transaccion.setDiaAlerta(diaA);
                    transaccion.setObservacion(observacion);
                    transaccion.setEstado(estado);
                    transaccionDAO.Agregar(transaccion);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("Controlador?menu=Transacciones&accion=Listar").forward(request, response);
                break;
                case "Actualizar":
                    try {
                    int VId30 = Integer.parseInt(request.getParameter("txtid2"));
                    int idcontrato = Integer.parseInt(request.getParameter("txtcontrato2"));
                    String Nfactura = request.getParameter("txtnumefactura2");
                    String FechaV = request.getParameter("txtfechaVencimiento2");
                    java.sql.Date fvenci = new java.sql.Date(formatter.parse(FechaV).getTime());
                    Date fechaVencimiento = fvenci;
                    int diaA = Integer.parseInt(request.getParameter("txtdiaalerta2"));
                    String observacion = request.getParameter("txtobservacion2");
                    String estado = request.getParameter("txtestado2");
                    transaccion.setIdContrato(idcontrato);
                    transaccion.setNumeFactura(Nfactura);
                    transaccion.setFechaVencimiento(fechaVencimiento);
                    transaccion.setDiaAlerta(diaA);
                    transaccion.setObservacion(observacion);
                    transaccion.setEstado(estado);
                    transaccion.setIdTransaccion(VId30);
                    transaccionDAO.Actualizar(transaccion);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("Controlador?menu=Transacciones&accion=Listar").forward(request, response);
                break;
                case "Cargar":
                    int VIdTransaccion = Integer.parseInt(request.getParameter("IdTransaccion"));
                    transaccion = transaccionDAO.ConsultaPorCodigo(VIdTransaccion);
                    request.setAttribute("transaccionSeleccionado", transaccion);
                    request.getRequestDispatcher("Controlador?menu=Transacciones&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    int idTransaccion1 = Integer.parseInt(request.getParameter("IdTransaccion"));
                    transaccionDAO.Eliminar(idTransaccion1);
                    request.getRequestDispatcher("Controlador?menu=Transacciones&accion=Listar").forward(request, response);
                    break;
                case "Ordenar":
                    List listaOr = transaccionDAO.OrdLista();
                    request.setAttribute("transacciones", listaOr);
                    break;
                case "Reporte":
                    transaccionDAO.Reporte();
                    request.getRequestDispatcher("Controlador?menu=Transacciones&accion=Listar").forward(request, response);
                    break;
                case "Buscar":
                    String TBuscar = request.getParameter("tsearch");
                    List listaTra = transaccionDAO.BListar(TBuscar);
                    request.setAttribute("transacciones", listaTra);
                    break;
            }
            request.getRequestDispatcher("Transaccion.jsp").forward(request, response);
        }

        if (menu.equals("Rols")) {
            switch (accion) {
                case "Listar":
                    List lista = rolDAO.Listar();
                    request.setAttribute("rols", lista);
                    break;
                case "Agregar":
                    String nombre = request.getParameter("txtnombre");
                    String descripcion = request.getParameter("txtdescripcion");
                    rol.setNombre(nombre);
                    rol.setDescripcion(descripcion);
                    rolDAO.Agregar(rol);
                    request.getRequestDispatcher("Controlador?menu=Rols&accion=Listar").forward(request, response);
                    break;
                case "Cargar":
                    int VIdRol = Integer.parseInt(request.getParameter("IdRol"));
                    rol = rolDAO.ConsultaPorCodigo(VIdRol);
                    request.setAttribute("rolxx", rol);
                    request.getRequestDispatcher("Controlador?menu=Rols&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    int VIdRol2 = Integer.parseInt(request.getParameter("txtid2"));
                    String nombre1 = request.getParameter("txtnombre2");
                    String descripcion1 = request.getParameter("txtdescri2");
                    rol.setNombre(nombre1);
                    rol.setDescripcion(descripcion1);
                    rol.setIdRol(VIdRol2);
                    rolDAO.Actualizar(rol);
                    request.getRequestDispatcher("Controlador?menu=Rols&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    int idRol = Integer.parseInt(request.getParameter("IdRol"));
                    rolDAO.Eliminar(idRol);
                    request.getRequestDispatcher("Controlador?menu=Rols&accion=Listar").forward(request, response);
                    break;
                case "Buscar":
                    String TBuscar = request.getParameter("tsearch");
                    List listaRol = rolDAO.BListar(TBuscar);
                    request.setAttribute("rols", listaRol);
                    break;
                case "Ordenar":
                    List listaTotal = rolDAO.OrdLista();
                    request.setAttribute("rols", listaTotal);
                    break;
                case "Reporte":
                    rolDAO.Reporte();
                    request.getRequestDispatcher("Controlador?menu=Rols&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Rol.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
