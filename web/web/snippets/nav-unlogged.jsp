<% //Melchor Alejo Garau Madrigal %>

<%@ page contentType="text/html; charset=UTF-8" %>
<nav class="navbar navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Desplegar menú</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=cpath%>">LinkedOut</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" method="POST" action="<%=cpath%>/Main">
                <div class="form-group">
                    <input type="text" placeholder="Usuario" class="form-control" name="usuario">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Contraseña" class="form-control" name="password">
                </div>
                <button type="submit" class="btn btn-flat btn-dark">ENTRAR</button>
            </form>
        </div>
    </div>
</nav>