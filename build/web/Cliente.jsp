<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" >
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <script src="js/color-modes.js"></script>
        <title>Sistema de Alertas para la Facturación y Cobranza - Clientes</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/Style.css" rel="stylesheet">
        <link href="css/headers.css" rel="stylesheet">
        <link href="css/dashboard.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <div class="row">
            <div class="col-md-3">
                <form action="Controlador?menu=Clientes" method="POST">
                    <div class="form-group">
                        <label>Id:</label>
                        <input type="text" class="form-control" id="exampleInputPassword1" name="txtid20" value="${clientexx.getIdCliente()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Ruc:</label>
                        <input type="input" class="form-control" id="exampleInputPassword1" name="txtruc20" maxlength="11" onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" value="${clientexx.getRuc()}" required>
                    </div>
                    <div class="form-group">
                        <label>Razón Social:</label>
                        <input type="text" class="form-control" name="txtrazon20" value="${clientexx.getRazonSocial()}" required>
                    </div>
                    <div class="form-group">
                        <label>Estado:</label>
                        <c:if test="${clientexx.getEstado() == 'Inactivo'}">
                            <select class="form-control form-control-sm" name="txtestado20">
                                <option >Activo</option>
                                <option selected>Inactivo</option>
                            </select>                        
                        </c:if>          
                        <c:if test="${clientexx.getEstado() == 'Activo'}"> 
                            <select class="form-control form-control-sm" name="txtestado20">
                                <option selected>Activo</option>
                                <option>Inactivo</option>
                            </select>
                        </c:if>
                        <c:if test="${clientexx.getEstado()== null}"> 
                            <select class="form-control form-control-sm" name="txtestado20">
                                <option selected>Activo</option>
                                <option>Inactivo</option>
                            </select>
                        </c:if>                     
                    </div><br>          
                    <button type="submit" class="btn btn-primary" name="accion" value="Actualizar">Guardar</button>
                </form>
            </div>
            <div class="col-md-8">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">LISTA DE CLIENTES</h1>
                </div><BR>
                <div>
                    <form action="Controlador?menu=Clientes" method="POST">
                        <label for="gsearch">Razón Social: </label>
                        <input type="search" id="gsearch" name="tsearch">
                        <input type="submit" class="btn btn-warning" name="accion" value="Buscar">
                    </form>
                </div><BR>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Ruc</th>
                            <th scope="col">Razon Social</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cliente" items="${clientes}">
                            <tr>
                                <th scope="row">${cliente.getIdCliente()}</th>
                                <td>${cliente.getRuc()}</td>
                                <td>${cliente.getRazonSocial()}</td>
                                <td>${cliente.getEstado()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Clientes&accion=Cargar&IdCliente=${cliente.getIdCliente()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Clientes&accion=Eliminar&IdCliente=${cliente.getIdCliente()}">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Nuevo Cliente</button>
                    <a class="btn btn-primary" href="Controlador?menu=Clientes&accion=Listar">Actualizar</a>
                    <a class="btn btn-primary" href="Controlador?menu=Clientes&accion=Ordenar">Ordenar</a>
                    <a class="btn btn-primary" href="Controlador?menu=Clientes&accion=Reporte">Reporte</a>
                </div>
            </div>
        </div>
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Agregar nuevo cliente:</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="Controlador?menu=Clientes" method="POST">
                            <div class="form-group">
                                <label>Ruc:</label>
                                <input type="input" class="form-control" id="exampleInputPassword1" name="txtruc" maxlength="8" onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" value="${cliente.getRuc()}" required>
                            </div>
                            <div class="form-group">
                                <label>Razón Social:</label>
                                <input type="text" class="form-control" name="txtrazonsocial" value="${cliente.getRazonSocial()}" required>
                            </div>
                            <div class="form-group">
                                <label>Estado: </label>
                                <select class="form-control form-control-sm" name="txtestado">                       
                                    <option >Activo</option>
                                    <option>Inactivo</option>
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