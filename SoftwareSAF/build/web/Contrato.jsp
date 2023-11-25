<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <script src="js/color-modes.js"></script>
        <title>Sistema de Alertas para la Facturación y Cobranza - Contrato</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/Style.css" rel="stylesheet">
        <link href="css/headers.css" rel="stylesheet">
        <link href="css/dashboard.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <div class="row">
            <div class="col-md-3">
                <form action="Controlador?menu=Contratos" method="POST">
                    <div class="form-group">
                        <label>Id Contrato:</label>
                        <input type="text" class="form-control" name="txtIdContrato20" value="${contratoSelecionado.getIdcontrato()}" readonly>
                    </div>
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="txtIdCliente20" value="${contratoSelecionado.getIdCliente()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Cliente:</label>
                        <input type="text" class="form-control" name="txtCliente20" value="${contratoSelecionado.getCliente()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Fecha Registro:</label>
                        <input type="date" class="form-control" id="exampleInputPassword1" name="txtFechaRegistro20" value="${contratoSelecionado.getFecharegistro()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Fecha Inicio Contrato:</label>
                        <input type="date" class="form-control" name="txtFechaInicioC20" value="${contratoSelecionado.getFechainicioc()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Fecha Fin Contrato:</label>
                        <input type="date" class="form-control" name="txtFechaFinC20" value="${contratoSelecionado.getFechafinc()}" required>
                    </div>
                    <div class="form-group">
                        <label>Día Facturación:</label>
                        <input type="input" class="form-control" name="txtDiaFacturacion20" maxlength="2" onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;"value="${contratoSelecionado.getDiafacturacion()}" required>
                    </div>
                    <div class="form-group">
                        <label>Día Alerta:</label>
                        <input type="input"  class="form-control" name="txtDiaAlerta20" maxlength="2" onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" value="${contratoSelecionado.getDiaalerta()}" required>
                    </div>
                    <div class="form-group">
                        <label>Tipo Facturación:</label>
                        <input type="text" class="form-control" name="txtTipoFacturacion20"  value="${contratoSelecionado.getTipofacturacion()}" readonly>         
                    </div>
                    <div class="form-group">
                        <label>Tarifa:</label>
                        <input type="text" class="form-control" name="txtTarifa20" value="${contratoSelecionado.getTarifa()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Estado:</label>
                        <c:if test="${contratoSelecionado.getEstado() == 'Pendiente'}">
                            <select class="form-control form-control-sm" name="txtEstado20">
                                <option >Facturado</option>
                                <option selected>Pendiente</option>
                            </select>                        
                        </c:if>          
                        <c:if test="${contratoSelecionado.getEstado() == 'Facturado'}"> 
                            <select class="form-control form-control-sm" name="txtEstado20">
                                <option selected>Facturado</option>
                                <option>Pendiente</option>
                            </select>
                        </c:if>
                        <c:if test="${contratoSelecionado.getEstado()== null}"> 
                            <select class="form-control form-control-sm" name="txtEstado20">
                                <option >Facturado</option>
                                <option selected>Pendiente</option>
                            </select>
                        </c:if>                     
                    </div><br>                    
                    <button type="submit" class="btn btn-primary" name="accion" value="Actualizar">Guardar</button>
                </form>
            </div>
            <div class="col-md-8">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">LISTA DE CONTRATOS</h1>
                </div><BR>
                <div>
                    <form action="Controlador?menu=Contratos" method="POST">
                        <label for="gsearch">Cliente: </label>
                        <input type="search" id="gsearch" name="tsearch">
                        <input type="submit" class="btn btn-warning" name="accion" value="Buscar">
                    </form>
                </div><BR>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Inicio</th>
                            <th scope="col">Fin</th>
                            <th scope="col">Facturación</th>
                            <th scope="col">Alerta</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Tarifa</th>    
                            <th scope="col">Estado</th> 
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="consulta" items="${consultas}">
                            <tr>
                                <th scope="row">${consulta.getIdcontrato()}</th>
                                <td>${consulta.getCliente()}</td>                                 
                                <td>${consulta.getFechainicioc()}</td>
                                <td>${consulta.getFechafinc()}</td>
                                <td>${consulta.getDiafacturacion()}</td>
                                <td>${consulta.getDiaalerta()}</td>                            
                                <td>${consulta.getTipofacturacion()}</td>
                                <td>${consulta.getTarifa()}</td>
                                <td>${consulta.getEstado()}</td> 
                                <td>
                                    <a class="btn btn-success" href="Controlador?menu=Contratos&accion=Cargar&IdContrato=${consulta.getIdcontrato()}">Editar</a>           
                                   <!-- <a class="btn btn-danger" href="Controlador?menu=Contratos&accion=Eliminar&IdContrato=${consulta.getIdcontrato()}">Eliminar</a>-->
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Nuevo Contrato</button>
                    <a class="btn btn-primary" href="Controlador?menu=Contratos&accion=Listar">Actualizar</a>
                    <a class="btn btn-primary" href="Controlador?menu=Contratos&accion=Ordenar">Ordenar</a>
                    <a class="btn btn-primary" href="Controlador?menu=Contratos&accion=Reporte">Reporte</a>
                </div>
            </div>
        </div>
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel" name="accion" value="Agregar">Agregar nuevo cliente:</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="Controlador?menu=Contratos" method="POST">
                            <div class="form-group">
                                <label>Cliente: </label>
                                <select class="form-control form-control-sm" name="txtRuc">
                                    <c:forEach var="cliente" items="${clientes}">                                        
                                        <option>${cliente.getRuc()}</option>                                        
                                    </c:forEach>
                                </select>
                            </div>                            
                            <div class="form-group">
                                <label>Fecha de inicio: </label>
                                <input type="date" class="form-control" name="txtFechaInicioC" value="${contrato.getFechaInicioC()}" required>
                            </div>
                            <div class="form-group">
                                <label>Fecha de Fin: </label>
                                <input type="date" class="form-control" name="txtFechaFinC" value="${contrato.getFechaFinC()}" required>
                            </div>
                            <div class="form-group">
                                <label>Día Facturacion: </label>
                                <input type="input" class="form-control" name="txtDiaFacturacion" maxlength="2" onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" value="${contrato.getDiaFacturacion()}" required>
                            </div>
                            <div class="form-group">
                                <label>Día Alerta: </label>
                                <input type="input" class="form-control" name="txtDiaAlerta" maxlength="2" onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" value="${contrato.getDiaAlerta()}" required>
                            </div>
                            <div class="form-group">
                                <label>Tipo Facturación: </label>
                                <select class="form-control form-control-sm" name="txtTipoFacturacion">
                                    <option>Contado</option>
                                    <option>Credito</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Tarífa: </label>
                                <input type="text" class="form-control" name="txtTarifa" value="${contrato.getTarifa()}" required>
                            </div>
                            <div class="form-group">
                                <label>Estado: </label>
                                <select class="form-control form-control-sm" name="txtEstado" required>
                                    <!--<option>Facturado</option>-->
                                    <option>Pendiente</option>
                                </select>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" class="btn btn-primary" name="accion" value="Agregar">Guardar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>         
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>


