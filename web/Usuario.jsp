<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <script src="js/color-modes.js"></script>
        <title>Sistema de Alertas para la Facturación y Cobranza - Usuarios</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/Style.css" rel="stylesheet">
        <link href="css/headers.css" rel="stylesheet">
        <link href="css/dashboard.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <div class="row">
            <div class="col-md-3">
                <form action="Controlador?menu=Usuarios" method="POST">
                    <div class="form-group">
                        <label>Id:</label>
                        <input type="text" class="form-control" id="exampleInputPassword1" name="txtid21" value="${usuarioxxx.getIdUsuario()}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Dni:</label>
                        <input type="input" class="form-control" name="txtdni21" maxlength="8" onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
                                    event.returnValue = false;" value="${usuarioxxx.getDni()}" required>                                                                                       
                    </div>
                    <div class="form-group">
                        <label>Nombre:</label>
                        <input type="text" class="form-control" name="txtnombre21" maxlength="150" value="${usuarioxxx.getNombre()}" required>
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" class="form-control" name="txtpassword21" maxlength="30" value="${usuarioxxx.getPassword()}" required>
                    </div>
                    <div class="form-group">
                        <label>Dirección:</label>
                        <input type="text" class="form-control" name="txtdireccion21" value="${usuarioxxx.getDireccion()}">
                    </div>
                    <div class="form-group">
                        <label>Teléfono:</label>
                        <input type="text" class="form-control" name="txttelefono21" value="${usuarioxxx.getTelefono()}" required>
                    </div>
                    <div class="form-group">
                        <label>Observación:</label>
                        <input type="text" class="form-control" name="txtobservacion21" value="${usuarioxxx.getObservacion()}">
                    </div>                   
                    <div class="form-group">
                        <label>Estado:</label>
                        <c:if test="${usuarioxxx.getEstado() == 'Inactivo'}">
                            <select class="form-control form-control-sm" name="txtestado21">
                                <option >Activo</option>
                                <option selected>Inactivo</option>
                            </select>                        
                        </c:if>          
                        <c:if test="${usuarioxxx.getEstado() == 'Activo'}"> 
                            <select class="form-control form-control-sm" name="txtestado21">
                                <option selected>Activo</option>
                                <option>Inactivo</option>
                            </select>
                        </c:if>
                        <c:if test="${usuarioxxx.getEstado()== null}"> 
                            <select class="form-control form-control-sm" name="txtestado21">
                                <option selected>Activo</option>
                                <option>Inactivo</option>
                            </select>
                        </c:if>                     
                    </div> 
                    <div class="form-group">
                        <label>Rol:</label>
                        <c:if test="${usuarioxxx.getIdRol() == 1}">
                            <select class="form-control form-control-sm" name="txtrol21">
                                <option selected>Admin</option>
                                <option >Operador</option>
                            </select>                        
                        </c:if>      
                        <c:if test="${usuarioxxx.getIdRol() == 2}">
                            <select class="form-control form-control-sm" name="txtrol21">
                                <option >Admin</option>
                                <option selected>Operador</option>
                            </select>                        
                        </c:if>    
                        <c:if test="${usuarioxxx.getIdRol() == null}">
                            <select class="form-control form-control-sm" name="txtrol21">
                                <option >Admin</option>
                                <option selected>Operador</option>
                            </select>                        
                        </c:if>     
                    </div>
                    <br>          
                    <button type="submit" class="btn btn-primary" name="accion" value="Actualizar">Guardar</button>     
                </form>
            </div>
            <div class="col-md-8">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">LISTA DE USUARIOS</h1>                  
                </div><BR>                
                <div>
                    <form action="Controlador?menu=Usuarios" method="POST">
                        <label for="gsearch">Nombre:</label>
                        <input type="search" id="gsearch" name="tsearch">
                        <input type="submit" class="btn btn-warning" name="accion" value="Buscar">
                    </form>
                </div><BR>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Dni</th>
                            <th scope="col">Nombre</th>                                        
                            <!--<th scope="col">Contraseña</th>   -->                                    
                            <th scope="col">telefono</th>  
                            <th scope="col">Estado</th>
                            <th scope="col">Rol</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="usuario" items="${usuarios}">
                            <tr>
                                <th scope="row">${usuario.getIdUsuario()}</th>
                                <td>${usuario.getDni()}</td>
                                <td>${usuario.getNombre()}</td>
                                <!--<td>${usuario.getPassword()}</td>     -->                           
                                <td>${usuario.getTelefono()}</td>                                
                                <td>${usuario.getEstado()}</td>
                                <td>${usuario.getNRol()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Usuarios&accion=Cargar&IdUsuario=${usuario.getIdUsuario()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Usuarios&accion=Eliminar&IdUsuario=${usuario.getIdUsuario()}">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Nuevo Usuario</button>
                    <a class="btn btn-primary" href="Controlador?menu=Usuarios&accion=Listar">Actualizar</a>
                    <a class="btn btn-primary" href="Controlador?menu=Usuarios&accion=Ordenar">Ordenar</a>
                    <a class="btn btn-primary" href="Controlador?menu=Usuarios&accion=Reporte">Reporte</a>
                </div>
            </div>
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Agregar Nuevo Usuario:</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="Controlador?menu=Usuarios" method="POST">                           
                                <div class="form-group">
                                    <label>Dni:</label>
                                    <input type="input" class="form-control" name="txtdni" maxlength="8" onKeypress="if (event.keyCode < 45 || event.keyCode > 57)event.returnValue = false;" value="${usuario.getDni()}" required>                                                                                  
                                </div>
                                <div class="form-group">
                                    <label>Nombre:</label>
                                    <input type="text" class="form-control" name="txtnombre" maxlength="150" value="${usuario.getNombre()}" required>
                                </div>
                                <div class="form-group">
                                    <label>Password:</label>
                                    <input type="password" class="form-control" name="txtpassword" maxlength="30" value="${usuario.getPassword()}" required>
                                </div>
                                <div class="form-group">
                                    <label>Dirección:</label>
                                    <input type="text" class="form-control" name="txtdireccion" value="${usuario.getDireccion()}">
                                </div>
                                <div class="form-group">
                                    <label>Teléfono:</label>
                                    <input type="text" class="form-control" name="txttelefono" value="${usuario.getTelefono()}" required>
                                </div>
                                <div class="form-group">
                                    <label>Observación:</label>
                                    <input type="text" class="form-control" name="txtobservacion" value="${usuario.getObservacion()}">
                                </div>
                                <div class="form-group">
                                    <label>Estado:</label>
                                    <select class="form-control form-control-sm" name="txtestado">
                                        <option>Activo</option>
                                        <option>Inactivo</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Rol</label>
                                    <select class="form-control form-control-sm" name="txtrol">
                                        <option>Admin</option>
                                        <option>Operador</option>
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


