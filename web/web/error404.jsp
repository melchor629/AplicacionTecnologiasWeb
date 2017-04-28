<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
    if(requestUri == null) requestUri = "Desconocido";
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>No hemos podido encontrar lo que buscas - LinkedOut</title>
    <meta name="description" content="Muestra un error cuando la URL es errónea">
    <%@include file="snippets/head.jsp"%>
</head>
<body>

    <% if(session.getAttribute("usuario") != null) { %>
    <%@include file="snippets/nav-logged.jsp"%>
    <% } else { %>
    <%@include file="snippets/nav-unlogged.jsp"%>
    <style>body > .container { padding-top: 10px; }</style>
    <% } %>

    <div class="container">

        <div class="text-center">
            <h1>No hemos podido encontrar lo que buscas</h1>
            <div class="row">
                <div class="col-md-offset-4 col-md-4">
                    <img src="assets/img/sad logo.png" class="img-responsive">
                </div>
            </div>
            <div class="row">
                <div class="col-md-offset-3 col-md-6 text-center">
                    <p class="lead">
                        Comprueba que vienes de un enlace de la propia web o si vienes de fuera, lo mismo
                        este recurso ya no existe.
                    </p>
                    <p><small><%=requestUri%></small></p>
                </div>
            </div>
        </div>

        <%@include file="snippets/footer.jsp"%>
    </div>

    <%@include file="snippets/body-end.jsp"%>
</body>
</html>
