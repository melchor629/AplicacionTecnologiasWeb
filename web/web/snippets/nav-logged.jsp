<!-- TODO -->
<%@ page contentType="text/html; charset=UTF-8" %>
<nav class="navbar navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Desplegar menú</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">LinkedOut</a>

            <ul class="nav navbar-nav">
                <li <%="perfil".equals(request.getAttribute("pagina")) ? "class=\"active\"" : ""%> >
                    <a href="<%=cpath%>/perfil.jsp"><i class="fa fa-user"></i> <span class="hidden-xs">Mi perfil</span></a>
                </li>
                <li <%="notificaciones".equals(request.getAttribute("pagina")) ? "class=\"active\"" : ""%>>
                    <a href="<%=cpath%>/notificaciones.jsp"><i class="fa fa-bell-o"></i> <span class="hidden-xs">Notificaciones</span></a>
                </li>
            </ul>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="<%=cpath%>/CerrarSesionServlet"><i class="glyphicon glyphicon-log-out"></i> Cerrar sesión</a>
                </li>
            </ul>

            <form class="navbar-form navbar-right" action="<%=cpath%>/BuscarServlet" method="POST">
                <div class="input-group">
                    <input type="text" placeholder="Buscar a..." class="form-control" name="buscar">
                    <span class="input-group-btn">
                        <button type="submit" id="buscar" class="btn btn-default"><i class="fa fa-search"></i></button>
                    </span>
                </div>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>