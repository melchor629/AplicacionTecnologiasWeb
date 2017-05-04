<%-- 
    Document   : editarAficion
    Created on : 28-abr-2017, 17:50:11
    Author     : Lucia y Francis
--%>

<%@page import="app.entity.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String cpath = request.getContextPath();
  
  Usuario u= (Usuario) session.getAttribute("usuario");
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
       <form class="form-horizontal" name="edit" action="<%=cpath%>/CrearAficion" method="POST">
           
           
           <textarea class="form-control" rows="3" name="nombre" placeholder="Aficion" value=""></textarea>
           
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
