<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="app.ejb.ExperienciaLaboralFacade"%>
<%@page import="app.entity.ExperienciaLaboral"%>
<%@page import="app.entity.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String cpath = request.getContextPath();
  
 String fechaComienzo= request.getParameter("fechaComienzo");
  
  Usuario u= (Usuario) session.getAttribute("usuario");
  String id= u.getId().toString();
  
    ExperienciaLaboralFacade fachada =(ExperienciaLaboralFacade) session.getAttribute("listaTrabajos");
  
  ExperienciaLaboral e= fachada.obtenerTrabajoConIdyFecha(id, fechaComienzo);
  
  DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
%>


        
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Editando Experiencia Laboral - LinkedOut</title>
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
      <form class="form-horizontal" name="edit" action="<%=cpath%>/EditarTrabajo" method="POST">
          
       <div class="form-group">
         <label for="fechaComienzo">Fecha de comienzo</label>
         <input type="Date" class="form-control" id="fechaComienzo" name="fechaComienzo" placeholder="Fecha de comienzo" value="<%= format.format(e.getExperienciaLaboralPK().getFechaComienzo()) %>">
       </div>
       
       <% 
           if(e.getFechaFinalizacion()!=null){
           %>
       <div class="form-group">
         <label for="fechaFinalizacion">Fecha de finalización</label>
         <input type="Date" class="form-control" id="fechaFinalizacion" name="fechaFinalizacion" placeholder="Fecha de finalización" value="<%= format.format(e.getFechaFinalizacion()) %>">
       </div>
       <% 
 }else{
           %>
           <div class="form-group">
         <label for="fechaFinalizacion">Fecha de finalización</label>
         <input type="Date" class="form-control" id="fechaFinalizacion" name="fechaFinalizacion" placeholder="Fecha de finalización" value="">
       </div>
           <%
               }
%>

<% 
           if(e.getEmpresa()!=null){
           %>
       <div class="form-group">
         <label for="empresa">Empresa</label>
         <input type="text" class="form-control" id="empresa" name="empresa" placeholder="Empresa" value="<%= e.getEmpresa()%>">
       </div>
       <% 
 }else{
           %>
          <div class="form-group">
         <label for="empresa">Empresa</label>
         <input type="text" class="form-control" id="empresa" name="empresa" placeholder="Empresa" value="">
       </div>
           <%
               }
%>

<% 
           if(e.getPuesto()!=null){
           %>
       <div class="form-group">
         <label for="puesto">Puesto</label>
         <input type="text" class="form-control" id="puesto" name="puesto" placeholder="puesto" value="<%= e.getPuesto()%>">
       </div>
       <% 
 }else{
           %>
           <div class="form-group">
         <label for="puesto">Puesto</label>
         <input type="text" class="form-control" id="puesto" name="puesto" placeholder="Puesto" value="">
       </div>
           <%
               }
%>

<% 
           if(e.getWebEmpresa()!=null){
           %>
       <div class="form-group">
         <label for="webEmpresa">Web Empresa</label>
         <input type="text" class="form-control" id="webEmpresa" name="webEmpresa" placeholder="Web Empresa" value="<%= e.getWebEmpresa()%>">
       </div>
       <% 
 }else{
           %>
           <div class="form-group">
         <label for="webEmpresa">Web Empresa</label>
         <input type="text" class="form-control" id="webEmpresa" name="webEmpresa" placeholder="Web Empresa" value="">
       </div>
           <%
               }
%>

       <input type="hidden" value="<%=id %>" name="id">
           
       <input type="hidden" value="<%= fechaComienzo %>" name="fechaComienzoPK">   
       
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
