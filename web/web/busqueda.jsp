<%-- 
    Document   : busqueda
    Created on : 27-abr-2017, 13:24:43
    Author     : Rodrii
--%>

<%@page import="app.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String cpath = request.getContextPath();
  List<Usuario> usuarios = (List) request.getAttribute("resultados");
  
%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>? - LinkedOut</title>
    <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
    <%@include file="snippets/head.jsp"%>
  </head>

  <body>

    <%@include file="snippets/nav-logged.jsp"%>

    <div class="container">

      <!-- Contenido -->

      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
  <% for(Usuario u : usuarios){ %>
  <p><%=u.getNombre()%></p><br>
  <br/>
  <%}%>
</html>
