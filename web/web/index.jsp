<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Descripcion de la pagina">
    <meta name="author" content="">
    <link rel="apple-touch-icon" sizes="57x57" href="<%=cpath%>/assets/favicon/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="<%=cpath%>/assets/favicon/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="<%=cpath%>/assets/favicon/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="<%=cpath%>/assets/favicon/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="<%=cpath%>/assets/favicon/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="<%=cpath%>/assets/favicon/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="<%=cpath%>/assets/favicon/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="<%=cpath%>/assets/favicon/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="<%=cpath%>/assets/favicon/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192"  href="<%=cpath%>/assets/favicon/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="<%=cpath%>/assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="<%=cpath%>/assets/favicon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="<%=cpath%>/assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="<%=cpath%>/assets/favicon/manifest.json">
    <meta name="msapplication-TileColor" content="#AB2F31">
    <meta name="msapplication-TileImage" content="<%=cpath%>/assets/favicon/ms-icon-144x144.png">
    <meta name="theme-color" content="#AB2F31">

    <title>LinkedOut</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Nuestro CSS -->
    <link rel="stylesheet" href="<%=cpath%>/assets/css/styles.css">
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
            <img src="assets/favicon/Logotipo%20melsho%20reducido.png" style="width:100%">
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

      <footer>
        <p>LinkedOut - 2017</p>
      </footer>
    </div>

    <script src="<%=cpath%>/assets/js/jquery-2.2.4.min.js"></script>
    <script src="<%=cpath%>/assets/js/bootstrap.min.js"></script>
  </body>
</html>
