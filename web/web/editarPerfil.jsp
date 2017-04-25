<%-- 
    Document   : editarPerfil.jsp
    Created on : 25-abr-2017, 16:56:36
    Author     : Lucia y Francis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="app.entity.Usuario"%>

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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Formulario cambiar datos perfil</title>
    <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
    <%@include file="snippets/head.jsp"%>
  </head>

  <body>

    <%@include file="snippets/nav-logged.jsp"%>

    <div class="container">

      <!-- Contenido -->
      
      <!-- imput text -->
        <h1>Datos:</h1>
        
        <form name="edit" action=”EditarPerfil" method=”post">
        <%
            if(u.getNombre()==null){
                %>
                 Nombre: <input type="text" name="nombre" value="" size="50" ><br/>
                <%
            }else{
                
                %>
                Nombre: <input type="text" name="nombre" value="<%= u.getNombre() %>" size="50" ><br/>
                <%
            }
            
         %>
         
             <%
            if(u.getApellidos()==null){
                %>
                Apellidos: <input type="text" name="apellidos" value="" size="100" ><br/>
                <%
            }else{
                
                %>
                 Apellidos: <input type="text" name="apellidos" value="<%= u.getApellidos() %>" size="100" ><br/>
                <%
            }
            
         %>
         
             <%
            if(u.getTwitter()==null){
                %>
                Twitter: <input type="text" name="twitter" value="" size="45" ><br/>
                <%
            }else{
                
                %>
                Twitter: <input type="text" name="twitter" value="<%= u.getTwitter() %>" size="45" ><br/>
                <%
            }
            
         %>
         
             <%
            if(u.getInstagram()==null){
                %>
                Instagram: <input type="text" name="instagram" value="" size="45" ><br/>
                <%
            }else{
                
                %>
                Instagram: <input type="text" name="instagram" value="<%= u.getInstagram() %>" size="45" ><br/>
                <%
            }
            
         %>
         
             <%
            if(u.getWeb()==null){
                %>
                 Web: <input type="text" name="web" value="" size="45" ><br/>
                <%
            }else{
                
                %>
                 Web: <input type="text" name="web" value="<%= u.getWeb() %>" size="45" ><br/>
                <%
            }
            
         %>
         
             <%
            if(u.getFoto()==null){
                %>
                 Foto: <input type="text" name="foto" value="" size="255" ><br/>
                <%
            }else{
                
                %>
                 Foto: <input type="text" name="foto" value="<%= u.getFoto() %>" size="255" ><br/>
                <%
            }
            
         %>
         
             <%
            if(u.getNombreUsuario()==null){
                %>
                Nombre Usuario: <input type="text" name="nombreUsuario" value="" size="45" ><br/>
                <%
            }else{
                
                %>
                 Nombre Usuario: <input type="text" name="nombreUsuario" value="<%= u.getNombreUsuario() %>" size="45" ><br/>
                <%
            }
            
         %>
         
             <%
            if(u.getContraseña()==null){
                %>
                Contraseña: <input type="text" name="contraseña" value="" size="200" ><br/>
                <%
            }else{
                
                %>
                Contraseña: <input type="text" name="contraseña" value="<%= u.getContraseña() %>" size="200" ><br/>
                <%
            }
            
         %>
         
             <%
            if(u.getCorreo()==null){
                %>
                Correo: <input type="text" name="correo" value="" size="255" ><br/>
                <%
            }else{
                
                %>
                 Correo: <input type="text" name="correo" value="<%= u.getCorreo() %>" size="255" ><br/>
                <%
            }
            
         %>
         <input type="submit" value="Submit">
        </form>
         
    
      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>

<% } %>
