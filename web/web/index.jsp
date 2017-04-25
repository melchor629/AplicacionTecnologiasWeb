<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>LinkedOut</title>
    <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
    <%@include file="snippets/head.jsp"%>
  </head>

  <body>

    <nav class="navbar navbar-static-top navbar-fixed-topp">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
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

    <div class="jumbotron full-height">
      <div class="container">
        <div class="row">
          <div class="col-sm-4">
            <img src="<%=cpath%>assets/favicon/Logotipo%20melsho%20reducido.png" style="width:100%">
          </div>
          <div class="col-sm-8">
            <h1>Hoy puedes tomar una buena decisión</h1>
            <p class="lead">Tienes la oportunidad de unirte a una gran red social profesional</p>
            <p><a class="btn btn-primary btn-lg" href="#" role="button">Regístrate ahora</a></p>
          </div>
        </div>
      </div>
    </div>

    <div class="full-height" style="background-color: rgb(255, 210, 190)">
      <div class="container">
        Algo de texto con alguna cosa de fondo
      </div>
    </div>

    <div class="full-height" style="background-color: rgb(190, 210, 255)">
      <div class="container">
        Algo de más texto con alguna otra cosa de fondo
      </div>
    </div>

    <div class="container">

      <%@include file="snippets/footer.jsp"%>
    </div>

    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>
