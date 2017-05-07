<%@page import="app.entity.Usuario"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="app.ejb.EstudiosFacade"%>
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
  
    EstudiosFacade fachada =(EstudiosFacade) session.getAttribute("listaEstudios");
  
  
  DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
  %>
  

                

<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Editando Estudios - LinkedOut</title>
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
            msgError = "¡La fecha de inicio no puede estar vacia!";
                }
   %>
   
    <div class="alert alert-danger">
            <%=msgError%>
        </div>
        <%
            }
        %>

      <!-- Contenido -->
      <form name="edit" action="<%=cpath%>/CrearEstudio" method="POST">
          
       <div class="form-group">
         <label for="fechaComienzo">Fecha de comienzo</label>
         <input type="Date" class="form-control" id="fechaComienzo" name="fechaComienzo" placeholder="Fecha de comienzo" value="">
       </div>
       
           <div class="form-group">
         <label for="fechaFinalizacion">Fecha de finalización</label>
         <input type="Date" class="form-control" id="fechaFinalizacion" name="fechaFinalizacion" placeholder="Fecha de finalización" value="">
       </div>
           

          <div class="form-group">
         <label for="descripcion">Descripción</label>
         <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Descripción" value="">
       </div>
      

           <div class="form-group">
         <label for="ubicacion">Ubicación</label>
         <input type="text" class="form-control" id="ubicacion" name="ubicacion" placeholder="Ubicación" value="">
       </div>
     
       <input type="hidden" value="<%=id %>" name="id">
            
       
       <div class="form-group edit-profile-form-group">
                <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-raised">GUARDAR</button>
                    <a href="<%=cpath%>/perfil.jsp" class="btn btn-default btn-raised">CANCELAR</a>
                </div>
            </div>
      </form>    
      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>
<% } %>