<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="app.entity.Usuario"%>
<%
    String cpath = request.getContextPath();
    Usuario u = (Usuario) session.getAttribute("usuario");

    if (u == null) {
        response.sendRedirect(cpath);
    } else {

        if (request.getAttribute("otroUsuario") != null) {
            u = (Usuario) request.getAttribute("otroUsuario");
        }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Perfil de <%=u.getNombre()%> - LinkedOut</title>
        <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
        <%@include file="snippets/head.jsp"%>
    </head>

    <body>
        <%@include file="snippets/nav-logged.jsp"%>

        <div class="container">

            <% 
            if(request.getAttribute("otroUsuario") == null){
            %>
            <button>Editar perfil</button>
            <% } else{%>
            <% // Comprobar si el usuario actual tiene amistad con el usuario %>
            <button>Opciones de amistad</button>
            <% } %>
            
            <h1 class="page-header">Perfil de <%= u.getNombre()%></h1>

            <% if (u.getFoto() == null) { %>
            <img src="<%@include file="snippets/fotoPerfil.txt"%>" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
            <% } else {%>
            <img src="<%=u.getFoto()%>" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
            <% }%>

            <h2>Nombre</h2>
            <p><%=u.getNombre()%></p>

            <h2>Apellidos</h2>
            <p><%=u.getApellidos()%></p>

            <% if (u.getTwitter() != null) {%>
            <h2>Twitter</h2>
            <p><a href="https://twitter.com/<%=u.getTwitter()%>" target="_blank"><%=u.getTwitter()%></a></p>
                <%} %>

            <% if (u.getInstagram() != null) {%>
            <h2>Instagram</h2>
            <p><a href="https://www.instagram.com/<%=u.getInstagram()%>" target="_blank"><%=u.getInstagram()%></a></p>
                <%} %>

            <% if (u.getWeb() != null) {%>
            <h2>Página web</h2>
            <p><a href="<%=u.getWeb()%>" target="_blank"><%=u.getWeb()%></a></p>
                <% }%>

            <h2>Nombre de usuario</h2>
            <p><%=u.getNombreUsuario()%></p>

            <h2>Correo electrónico</h2>
            <p><a href="mailto:<%=u.getCorreo()%>"><%=u.getCorreo()%></a></p>

            <%@include file="snippets/footer.jsp"%>

        </div>

        <%@include file="snippets/body-end.jsp"%>
    </body>
</html>            
<%
    }
%>