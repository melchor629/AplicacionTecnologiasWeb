<% //Melchor Alejo Garau Madrigal %>

<%@page import="app.entity.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String cpath = request.getContextPath();
  
  String nombreAficion = (String) request.getParameter("nombreAficion");
  Usuario u= (Usuario) session.getAttribute("usuario");

if(u == null){
    response.sendRedirect(request.getContextPath());
}else{
  String id= u.getId().toString();
%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Editando afición - LinkedOut</title>
    <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
    <%@include file="snippets/head.jsp"%>
  </head>

  <body>

    <%@include file="snippets/nav-logged.jsp"%>

    <div class="container">

      <!-- Contenido -->
       <form name="edit" action="<%=cpath%>/EditarAficion" method="POST" id="usrform">
           
           
           <textarea class="form-control" form="usrform" rows="3" name="nombre" placeholder="Aficion"><%= nombreAficion %></textarea>
           
           <input type="hidden" value="<%=id %>" name="id">
           
           <input type="hidden" value="<%=nombreAficion %>" name="nombreOriginal">
      
       <div class="form-group edit-hobby-form-group">
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
<% } %>