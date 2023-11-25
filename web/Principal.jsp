<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" >
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <script src="js/color-modes.js"></script>
        <title>Sistema de Alertas para la Facturación y Cobranza - SAF</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/Style.css" rel="stylesheet">
        <link href="css/headers.css" rel="stylesheet">
        <link href="css/dashboard.css" rel="stylesheet">
    </head>
    <body>
        <main>
            <header class="p-3 mb-3 border-bottom">
                <div class="container">               
                    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                        <a href="#" class="d-flex align-items-center mb-2 mb-lg-0 link-body-emphasis text-decoration-none">
                            <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
                        </a>
                        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                            <li><a href="#" target="miContenedor" class="nav-link px-2 link-secondary">Inicio</a></li>
                            <li><a href="Controlador?menu=Rols&accion=Listar" target="miContenedor" class="nav-link px-2 link-body-emphasis">Roles</a></li> 
                            <li><a href="Controlador?menu=Usuarios&accion=Listar" target="miContenedor" class="nav-link px-2 link-body-emphasis">Usuarios</a></li>
                            <li><a href="Controlador?menu=Clientes&accion=Listar" target="miContenedor" class="nav-link px-2 link-body-emphasis">Clientes</a></li>                              
                            <li><a href="Controlador?menu=Contratos&accion=Listar" target="miContenedor" class="nav-link px-2 link-body-emphasis">Contratos</a></li>
                            <li><a href="Controlador?menu=Transacciones&accion=Listar" target="miContenedor" class="nav-link px-2 link-body-emphasis">Transacciones</a></li>
                        </ul>
                        <div class="dropdown text-end">
                            <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="brand/User.jpg" alt="mdo" width="32" height="32" class="rounded-circle"> ${usuario.getNombre()}
                            </a>
                            <ul class="dropdown-menu text-small">                            
                                <li><a class="dropdown-item" href="#">Settings</a></li>                            
                                <li><hr class="dropdown-divider"></li>
                                <li>
                                    <form  method="POST" action="Validar">
                                        <button type="submit" class="btn btn-link btn-logout" name="accion" value="Salir">Cerrar Sesión</button>
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </header>                            
            <div class="m-3" style="height:900px;">
                <iframe name="miContenedor" style="height:100%; width:100%;" frameBorder="1"></iframe>
            </div>
        </main>
        <script src="js/bootstrap.bundle.min.js"></script>    
        <!--<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/chart.js@4.2.1/dist/chart.umd.min.js" integrity="sha384-gdQErvCNWvHQZj6XZM0dNsAoY4v+j5P1XDpNkcM3HJG1Yx04ecqIHk7+4VBOCHOG" crossorigin="anonymous"></script>
            <script src="js/dashboard.js"></script>-->
    </body>
</html>