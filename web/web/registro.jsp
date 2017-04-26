<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Reg&iacute;strate - LinkedOut</title>
    <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
    <%@include file="snippets/head.jsp" %>
</head>

<body>

    <nav class="navbar navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Desplegar menú</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">LinkedOut</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <form class="navbar-form navbar-right" method="POST" action="<%=cpath%>/Main">
                    <div class="form-group">
                        <input type="text" placeholder="Usuario" class="form-control" name="usuario">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="Contraseña" class="form-control" name="password">
                    </div>
                    <button type="submit" class="btn">Entrar</button>
                </form>
            </div>
        </div>
    </nav>

    <div class="container">

        <form class="form-horizontal" role="form" action="<%=cpath%>/RegistroServlet" method="POST">
            <h2>Formulario de registro</h2>
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="nombre" class="col-sm-3 control-label">Nombre</label>
                        <div class="col-sm-9">
                            <input type="nombre" id="firstName" placeholder="Nombre" class="form-control" autofocus>
                            <span class="help-block">Nombre ej.: Evaristo Jos&eacute;</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="apellidos" class="col-sm-3 control-label">Apellidos</label>
                        <div class="col-sm-9">
                            <input type="apellidos" id="firstName" placeholder="Apellidos" class="form-control" autofocus>
                            <span class="help-block">Apellidos ej.: Escobar Kennedy</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="correo" class="col-sm-3 control-label">Correo</label>
                        <div class="col-sm-9">
                            <input type="correo" id="email" placeholder="Correo" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="nombreDeUsuario" class="col-sm-3 control-label">Nombre de Usuario</label>
                        <div class="col-sm-9">
                            <input type="usuario" id="firstName" placeholder="Nombre de Usuario" class="form-control">
                            <span class="help-block">Usuario ej.: TecnoWebWins666</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password1" class="col-sm-3 control-label">Password</label>
                        <div class="col-sm-9">
                            <input type="password1" id="password" placeholder="Password" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password2" class="col-sm-3 control-label">Vuelve a escribir la password</label>
                        <div class="col-sm-9">
                            <input type="password2" id="password" placeholder="Vuelve a escribir la password"
                                   class="form-control">
                        </div>
                    </div>
                </div>
            </div>

            <div class="text-center center-block row">
                <label class="checkbox-inline">
                    <input type="checkbox" id="almaCheckbox" value="OFF"> Entiendo que mis datos ya no me
                    pertenecen (>:D)
                </label>
                <br/>
                <label class="checkbox-inline">
                    <input type="checkbox" id="datosCheckbox" value="OFF"> Acepto vender mi alma al diablo
                </label>
            </div>


            <button type="submit" class="btn btn-primary btn-raised pull-right">REGISTRARME</button>
            <div class="clearfix"></div>
        </form>

        <%@include file="snippets/footer.jsp" %>
    </div>

    <%@include file="snippets/body-end.jsp" %>
</body>
</html>