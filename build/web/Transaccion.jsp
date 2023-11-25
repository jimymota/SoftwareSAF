<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <script src="js/color-modes.js"></script>
        <title>Sistema de Alertas para la Facturación y Cobranza - Transacciones</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/Style.css" rel="stylesheet">
        <link href="css/headers.css" rel="stylesheet">
        <link href="css/dashboard.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <div class="row">
            <div class="col-md-3">
                <form action="Controlador?menu=Transacciones" method="POST">
                    <div class="form-group">
                        <label>Cliente:</label>
                        <input type="text" class="form-control" name="txtid2" value="${transaccionSeleccionado.getIdTransaccion()}" readonly>
                        <input type="text" class="form-control" name="txtcliente2" value="${transaccionSeleccionado.getRazonSocial()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Contrato N°:</label>
                        <input type="text" class="form-control" name="txtcontrato2" value="${transaccionSeleccionado.getIdContrato()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Fecha Vencimiento:</label>
                        <input type="date" class="form-control" name="txtfechaVencimiento2" value="${transaccionSeleccionado.getFechaVencimiento()}" required>                                                                                       
                    </div>
                    <div class="form-group">
                        <label>Día Alerta:</label>
                        <input type="number" class="form-control" name="txtdiaalerta2" value="${transaccionSeleccionado.getDiaAlerta()}" required>                                                                                       
                    </div>
                    <div class="form-group">
                        <label>Número de Factura:</label>
                        <input type="text" class="form-control" name="txtnumefactura2" value="${transaccionSeleccionado.getNumeFactura()}" required>
                    </div>
                    <div class="form-group">
                        <label>Tipo Pago:</label>
                        <input type="text" class="form-control" name="txtTipoPago2" value="${transaccionSeleccionado.getTipoFacturacion()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Tarifa:</label>
                        <input type="text" class="form-control" name="txtTarifa2" value="${transaccionSeleccionado.getTarifa()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Observación:</label>
                        <input type="text" class="form-control" name="txtObservacion2" value="${transaccionSeleccionado.getObservacion()}">
                    </div>  
                    <div class="form-group">
                        <label>Estado:</label>
                        <c:if test="${transaccionSeleccionado.getEstado() == 'Cobrado'}">
                            <select class="form-control form-control-sm" name="txtestado2">
                                <option selected>Cobrado</option>
                                <option >Pendiente</option>
                            </select>                        
                        </c:if>          
                        <c:if test="${transaccionSeleccionado.getEstado() == 'Pendiente'}"> 
                            <select class="form-control form-control-sm" name="txtestado2">
                                <option >Cobrado</option>
                                <option selected>Pendiente</option>
                            </select>
                        </c:if>
                        <c:if test="${transaccionSeleccionado.getEstado()== null}"> 
                            <select class="form-control form-control-sm" name="txtestado2">
                                <option >Cobrado</option>
                                <option selected>Pendiente</option>
                            </select>
                        </c:if>                     
                    </div> 
                    <br>          
                    <button type="submit" class="btn btn-primary" name="accion" value="Actualizar">Guardar</button>     
                </form>
            </div>
            <div class="col-md-8">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">LISTA DE TRANSACCIONES</h1>                   
                </div><BR>
                <div>
                    <form action="Controlador?menu=Transacciones" method="POST">
                        <label for="gsearch">Transacción: </label>
                        <input type="search" id="gsearch" name="tsearch">
                        <input type="submit" class="btn btn-warning" name="accion" value="Buscar">
                        
                    </form>
                </div><BR>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Contrato</th>
                            <th scope="col">Vencimiento</th>
                            <th scope="col">Cliente</th>                                        
                            <th scope="col">Factura</th>     
                            <th scope="col">Tipo</th>                            
                            <th scope="col">Tarifa</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="transaccion" items="${transacciones}">
                            <tr>
                                <th scope="row">${transaccion.getIdTransaccion()}</th>
                                <td>${transaccion.getIdContrato()}</td>
                                <td>${transaccion.getFechaVencimiento()}</td>
                                <td>${transaccion.getRazonSocial()}</td>
                                <td>${transaccion.getNumeFactura()}</td>  
                                <td>${transaccion.getTipoFacturacion()}</td>  
                                <td>${transaccion.getTarifa()}</td>                                                            
                                <td>${transaccion.getEstado()}</td>
                                <td>
                                    <a class="btn btn-success" href="Controlador?menu=Transacciones&accion=Cargar&IdTransaccion=${transaccion.getIdTransaccion()}">Editar</a>
                                    <!--<a class="btn btn-danger" href="Controlador?menu=Transacciones&accion=Eliminar&IdTransaccion=${transaccion.getIdTransaccion()}">Eliminar</a>-->
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Nuevo Contrato</button>
                    <a class="btn btn-primary" href="Controlador?menu=Transacciones&accion=Listar">Mostrar todos</a>
                    <a class="btn btn-primary" href="Controlador?menu=Transacciones&accion=Ordenar">Ordenar todos</a>
                    <a class="btn btn-primary" href="Controlador?menu=Transacciones&accion=Reporte">Reporte</a>
                </div>
            </div>
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Agregar Nueva Transacción:</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="Controlador?menu=Transacciones" method="POST">                            
                                <div class="form-group">
                                    <label>Contrato N°:</label>
                                    <select class="form-control form-control-sm" name="txtNumeroC" required>
                                        <c:forEach var="contrato" items="${contratos}">                                        
                                            <option>${contrato.getIdcontrato()}</option>                                        
                                        </c:forEach>
                                    </select>
                                </div>                               
                                <div class="form-group">
                                    <label>Número de Factura:</label>
                                    <input type="text" class="form-control" name="txtnumefactura" required>
                                </div>                                
                                 <div class="form-group">
                                    <label>Fecha Vencimiento:</label>
                                    <input type="date" class="form-control" name="txtfechavencimiento" required>                                                                                       
                                </div>
                                <div class="form-group">
                                    <label>Día Alerta:</label>
                                    <input type="input" class="form-control" name="txtdiaalerta" maxlength="2" onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" required>                                                                                       
                                </div>                                
                                <div class="form-group">
                                    <label>Observación:</label>
                                    <input type="text" class="form-control" name="txtobservacion">
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


