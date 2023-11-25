<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" >
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <script src="js/color-modes.js"></script>
        <title>Login - SAF</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">      
        <link href="css/sign-in.css" rel="stylesheet">      
    </head>
    <body class="text-center">
        <main class="form-signin w-100 m-auto">
            <form method="POST" action="Validar">
                <img class="mb-4" src="brand/logo.svg" alt="" width="72" height="57">
                <h1 class="h3 mb-3 fw-normal">Login</h1>
                <div class="form-floating">
                    <input type="input" class="form-control" id="floatingInput" name="txtusuario" placeholder="12345678" maxlength="8" onKeypress="if (event.keyCode < 45 || event.keyCode > 57)
                                event.returnValue = false;">
                    <label for="floatingInput">Dni</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" name="txtpassword" placeholder="Password">
                    <label for="floatingPassword">Password</label>
                </div>
                <button class="w-100 btn btn-lg btn-primary" type="submit" name="accion" value="Ingresar">Ingresar</button>
                <p class="mt-5 mb-3 text-body-secondary">&copy;
                    <script>document.write(new Date().getFullYear())</script> &mdash; Perú<i class="mdi mdi-heart text-danger"></i>
                </p>
            </form>
        </main>
    </body>
</html>
