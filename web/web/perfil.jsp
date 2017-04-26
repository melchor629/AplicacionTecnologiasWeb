<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="app.entity.Usuario" %>
<%

    String cpath = request.getContextPath();
    Usuario u = (Usuario) session.getAttribute("usuario");
    boolean mostrarPerfil = true; // variable que determina si debe mostrarse el perfil del usuario

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
    <%@include file="snippets/head.jsp" %>
</head>

<body>
    <%@include file="snippets/nav-logged.jsp" %>

    <div class="container">

        <%
            if (request.getAttribute("otroUsuario") == null) {
        %>
        <a href="<%= cpath %>/editarPerfil.jsp"><i class="fa fa-pencil fa-2x">Editar perfil</i></a>
        <% } else {
            // Comprobar si el usuario actual tiene amistad con el usuario
            Boolean amigos = (Boolean) request.getAttribute("amigos");
        %>

        <% if (amigos) {%>
        <i class="fa fa-user-times fa-2x">Eliminar amistad</i>
        <% } else {
        mostrarPerfil = false;
        %>
        <i class="fa fa-user-plus fa-2x">Enviar solicitud de amistad</i>
        <% }
        } %>

        <div class="row">
            <div class="col-sm-4 col-md-3">
                <% if (u.getFoto() == null) { %>
                <img src="<%@include file="snippets/fotoPerfil.txt"%>" class="img-responsive profile-photo"
                     alt="Foto de perfil genérico">
                <% } else {%>
                <img src="<%=u.getFoto()%>" class="img-responsive profile-photo" alt="Foto de perfil de <%=u.getNombre()%>">
                <% }%>


                <h5 id="nombreApellidos">
                    <%=u.getNombre()%> <%=u.getApellidos()%>
                </h5>

                <% if(mostrarPerfil){ %>
                <p id="nombreUsuario">
                    <%=u.getNombreUsuario()%>
                </p>

                <p id="email">
                    <i class="fa fa-envelope-o"></i>
                    <a href="mailto:<%=u.getCorreo()%>"><%=u.getCorreo()%></a>
                </p>

                <% if (u.getTwitter() != null) {%>
                <p id="twitter">
                    <i class="fa fa-twitter"></i>
                    <a href="https://twitter.com/<%=u.getTwitter()%>" target="_blank">@<%=u.getTwitter()%></a>
                </p>
                <%} %>

                <% if (u.getInstagram() != null) {%>
                <p id="instagram">
                    <i class="fa fa-instagram"></i>
                    <a href="https://www.instagram.com/<%=u.getInstagram()%>" target="_blank"><%=u.getInstagram()%></a>
                </p>
                <%} %>

                <% if (u.getWeb() != null) {%>
                <p id="web">
                    <i class="fa fa-globe"></i>
                    <a href="<%=u.getWeb()%>" target="_blank"><%=u.getWeb()%></a>
                </p>
                <% }%>
                <% } %>
            </div>
            <% if(mostrarPerfil){ %>
            <div class="col-sm-8 col-md-9">
                <!-- TRABAJOS Y MENSAJES AQUI -->
            </div>
            <% } %>
        </div>

        <%@include file="snippets/footer.jsp" %>

    </div>

    <%@include file="snippets/body-end.jsp" %>
</body>
</html>
<%
    }
%>