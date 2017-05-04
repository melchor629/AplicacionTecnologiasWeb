<%-- 
    Document   : editarAficion
    Created on : 28-abr-2017, 17:50:11
    Author     : Lucia y Francis
--%>

<%@page import="app.entity.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String cpath = request.getContextPath();
  
  String nombreAficion = (String) request.getParameter("nombreAficion");
  Usuario u= (Usuario) session.getAttribute("usuario");
  String id= u.getId().toString();
  
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
       <form class="form-horizontal" name="edit" action="<%=cpath%>/EditarAficion" method="POST">
           
           
           <textarea class="form-control" rows="3" name="nombre" placeholder="Aficion" value="<%= nombreAficion %>"></textarea>
           
           <input type="hidden" value="<%=id %>" name="id">
           
           <input type="hidden" value="<%=nombreAficion %>" name="nombreOriginal">
      
       <div class="form-group edit-profile-form-group">
                <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-raised">GUARDAR</button>
                    <a href="javascript:history.back()" class="btn btn-default btn-raised">CANCELAR</a>
                </div>
            </div>
        </form>
      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>
