<%-- 
    Document   : perfil
    Created on : 03-abr-2017, 10:10:29
    Author     : Lucia y Francis
--%>

<%@page import="app.entity.Usuario"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>Dashboard Template for Bootstrap</title>

        <!-- Bootstrap core CSS -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/dashboard.css">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Project name</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">Dashboard</a></li>
                        <li><a href="#">Settings</a></li>
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Help</a></li>
                    </ul>
                    <form class="navbar-form navbar-right">
                        <input type="text" class="form-control" placeholder="Search...">
                    </form>
                </div>
            </div>
        </nav>

        <% 
        Usuario u = (Usuario)session.getAttribute("usuario");
        
        if(request.getAttribute("otroUsuario") != null){
            u = (Usuario) request.getAttribute("otroUsuario");
        }
        %>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Perfil de usuario</h1>

                    <div class="row placeholders">
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <img src="<%=u.getFoto()%>" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                            <h4><%=u.getNombre()%></h4>
                            <span class="text-muted"><%=u.getApellidos() %></span>
                        </div>
                    </div>

                    

                    <div class="itemPerfil">
                        Nombre:
                        <p><%=u.getNombre()%></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Apellidos
                        <p><%=u.getApellidos()%></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Twitter:
                        <p><a href="https://twitter.com/<%=u.getTwitter()%>" target="_blank"><%=u.getTwitter()%></a></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Instagram:
                        <p><%=u.getInstagram()%></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Página web:
                        <p><a href="<%=u.getWeb()%>" target="_blank"><%=u.getWeb()%></a></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Nombre de usuario:
                        <p><%=u.getNombreUsuario()%></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Correo electrónico:
                        <p><a href="mailto:<%=u.getCorreo()%>"><%=u.getCorreo()%></a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="assets/js/bootstrap.min.js"></script>

</body>
</html>
