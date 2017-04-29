<%/**
 *
 * @author Eduardo Guidet Jiménez
 */%>

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

    <%@include file="snippets/nav-unlogged.jsp"%>

    <div class="container">

        <% String error = (String) request.getAttribute("error"); %>
        
        <% if (error != null) {%>
        <div class="alert alert-danger">
            <strong>Error: <%= error%> </strong> 
        </div>
        <%}%>
      
       
        
        
        <form class="form-horizontal" role="form" action="<%=cpath%>/RegistroServlet" method="POST">
            <h2>Formulario de registro</h2>
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="nombre" class="col-sm-3 control-label">Nombre</label>
                        <div class="col-sm-9">
                            <input type="text" name="nombre" id="nombre" placeholder="Nombre" class="form-control" autofocus>
                            <span class="help-block">Nombre ej.: Evaristo Jos&eacute;</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="apellidos" class="col-sm-3 control-label">Apellidos</label>
                        <div class="col-sm-9">
                            <input type="text" name="apellidos" id="apellidos" placeholder="Apellidos" class="form-control">
                            <span class="help-block">Apellidos ej.: Escobar Kennedy</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="correo" class="col-sm-3 control-label">Correo</label>
                        <div class="col-sm-9">
                            <input type="text" name="correo" id="correo" placeholder="Correo" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="usuario" class="col-sm-3 control-label">Nombre de Usuario</label>
                        <div class="col-sm-9">
                            <input type="text" name="nombreUsuario" id="usuario" placeholder="Nombre de Usuario" class="form-control">
                            <span class="help-block">Usuario ej.: TecnoWebWins666</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password1" class="col-sm-3 control-label">Password</label>
                        <div class="col-sm-9">
                            <input type="password" name="password1" id="password1" placeholder="Password" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password2" class="col-sm-3 control-label">Vuelve a escribir la password</label>
                        <div class="col-sm-9">
                            <input type="password" name="password2" id="password2" placeholder="Vuelve a escribir la password"
                                   class="form-control">
                        </div>
                    </div>
                </div>
            </div>

            <div class="text-center center-block row">
                <label class="checkbox-inline">
                    <input type="checkbox" name="almacheck" id="almaCheckbox" value="ON"> Entiendo que mis datos ya no me
                    pertenecen (>:D)
                </label>
                <br/>
                <label class="checkbox-inline">
                    <input type="checkbox" name="datoscheck" id="datosCheckbox" value="ON"> Acepto vender mi alma al diablo
                </label>
            </div>


            <div class="pull-right registro-botones">
                <button type="submit" class="btn btn-primary btn-raised">REGISTRARME</button>
                <a href="javascript:history.back()" class="btn btn-default btn-raised">CANCELAR</a>
            </div>
            <div class="clearfix"></div>
        </form>
        <%@include file="snippets/footer.jsp" %>
    </div>

    <%@include file="snippets/body-end.jsp" %>
</body>
</html>