<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
    String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
    if(servletName == null) servletName = "Desconocido";
    String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
    if(requestUri == null) requestUri = "Desconocido";
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Algo malo nos ha pasado - LinkedOut</title>
    <meta name="description" content="Muestra un error cuando ha pasado algo malo en el servidor">
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
            <h1>Algo en nuestra web ha fallado</h1>
            <div class="row">
                <div class="col-md-offset-4 col-md-4">
                    <img src="assets/img/dead logo.png" class="img-responsive">
                </div>
            </div>
            <div class="row">
                <div class="col-md-offset-3 col-md-6 text-center">
                    <p class="lead">
                        Inténtalo de nuevo o espera unos minutos a ver si vuelve a funcionar.
                        Si el problema persiste, contáctenos.
                    </p>
                    <p>
                        <a href="#collapseWithError" class="btn btn-raised btn-default" role="button" data-toggle="collapse">
                            Mostrar error
                        </a>
                    </p>
                </div>
            </div>
        </div>

        <div class="collapse" id="collapseWithError">
            <pre>
Error:
<%=throwable%><% if(throwable.getStackTrace() != null && throwable.getStackTrace().length > 0) { %>
---
Stack Trace:
<% StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    throwable.printStackTrace(pw);
    pw.close();
    sw.close(); %><%= sw.getBuffer().toString() %><% } %>
---
Servlet name: <%=servletName%>
---
Request URI: <%=requestUri%>
---
Context Path: <%=cpath%>
            </pre>
        </div>

        <%@include file="snippets/footer.jsp"%>
    </div>

    <%@include file="snippets/body-end.jsp"%>
</body>
</html>
