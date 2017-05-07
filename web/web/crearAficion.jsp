<%@page import="app.entity.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<% //Melchor Alejo Garau Madrigal %>
<% //Francisco Reyes Sánchez%>
<%
  String cpath = request.getContextPath();
  
  Usuario u= (Usuario) session.getAttribute("usuario");

  if(u == null) {
      response.sendRedirect(cpath);
  } else {
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

        <%
  int error = 0;
    if (request.getParameter("error") != null) {
          error = Integer.parseInt(request.getParameter("error"));
          String msgError = "Error desconocido :/";
           if(error == 1) {
            msgError = "¡El nombre no puede estar vacio!";
                }
   %>
   
    <div class="alert alert-danger">
            <%=msgError%>
        </div>
        <%
            }
        %> 
      <!-- Contenido -->
       <form name="edit" action="<%=cpath%>/CrearAficion" method="POST">
           
           
           <textarea class="form-control" rows="3" name="nombre" placeholder="Aficion" ></textarea>
           
           <input type="hidden" value="<%=id %>" name="id">
           
      
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
<% } %>