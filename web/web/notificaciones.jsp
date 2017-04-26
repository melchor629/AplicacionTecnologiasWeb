<%@page import="app.entity.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String cpath = request.getContextPath();
  Usuario u = (Usuario)session.getAttribute("usuario");
%>



<% if (u == null) {
        response.sendRedirect(cpath);
    } else { %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Panel de notificaciones - LinkedOut</title>
    <meta name="description" content="Panel de notificaciones"> <!-- TODO -->
    <%@include file="snippets/head.jsp"%>
  </head>

  <body>

    <%@include file="snippets/nav-logged.jsp"%>

    <div class="container">

      <h1 class="page-header">Panel de notificaciones</h1>

      <h2 class="page-header">Peticiones de amistad</h1>
      
      <h2 class="page-header">Mensajes</h1>
      
      
      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>
<% } %>