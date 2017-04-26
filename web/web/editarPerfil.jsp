<%@ page contentType="text/html; charset=UTF-8" %>
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
    <title>Modificar perfil</title>
    <meta name="description" content="Modificar perfil de usuario">
    <%@include file="snippets/head.jsp"%>
  </head>

  <body>

    <%@include file="snippets/nav-logged.jsp"%>

    <div class="container">

      <!-- Contenido -->
      
      <!-- imput text -->
        <h1>Datos:</h1>
        
        <form class="form-horizontal" name="edit" action="<%=cpath%>/EditarPerfil" method=”post">
        <%
            if(u.getNombre()==null){
                %>
                
                <div class="form-group">
                    <label for="nombre" class="col-sm-2 control-label">Nombre</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="nombre" value="">
                        </div>
                </div>
                
                <%
            }else{
                
                %>
                
                <div class="form-group">
                    <label for="nombre" class="col-sm-2 control-label">Nombre</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="nombre" value="<%= u.getNombre() %>">
                        </div>
                </div>
                        
                <%
            }
            
         %>
         
             <%
            if(u.getApellidos()==null){
                %>
                
                <div class="form-group">
                    <label for="apellidos" class="col-sm-2 control-label">Apellidos</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="apellidos" value="">
                        </div>
                </div>
  
                <%
            }else{
                
                %>
                
                <div class="form-group">
                    <label for="apellidos" class="col-sm-2 control-label">Apellidos</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="apellidos" value="<%= u.getApellidos() %>">
                        </div>
                </div>
                 
                <%
            }
            
         %>
         
             <%
            if(u.getTwitter()==null){
                %>
                
                <div class="form-group">
                    <label for="twitter" class="col-sm-2 control-label">Twitter</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="twitter" value="">
                        </div>
                </div>
                        
                <%
            }else{
                
                %>
                
                <div class="form-group">
                    <label for="twitter" class="col-sm-2 control-label">Twitter</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="twitter" value="<%= u.getTwitter() %>">
                        </div>
                </div>
                
                <%
            }
            
         %>
         
             <%
            if(u.getInstagram()==null){
                %>
                
                <div class="form-group">
                    <label for="instagram" class="col-sm-2 control-label">Instagram</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="instagram" value="">
                        </div>
                </div>
      
                <%
            }else{
                
                %>
                <div class="form-group">
                    <label for="instagram" class="col-sm-2 control-label">Instagram</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="instagram" value="<%= u.getInstagram() %>">
                        </div>
                </div>
                
                <%
            }
            
         %>
         
             <%
            if(u.getWeb()==null){
                %>
                 <div class="form-group">
                    <label for="web" class="col-sm-2 control-label">Web</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="web" value="">
                        </div>
                </div>
                 
                <%
            }else{
                
                %>
                 <div class="form-group">
                    <label for="web" class="col-sm-2 control-label">Web</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="web" value="<%= u.getWeb() %>">
                        </div>
                </div>
                 
                <%
            }
            
         %>
         
             <%
            if(u.getFoto()==null){
                %>
                
                   <div class="form-group">
                    <label for="foto" class="col-sm-2 control-label">Foto</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="foto" value="">
                        </div>
                </div>
                        
                 
                <%
            }else{
                
                %>
                <div class="form-group">
                    <label for="foto" class="col-sm-2 control-label">Foto</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="foto" value="<%= u.getFoto() %>">
                        </div>
                </div>
                
                <%
            }
            
         %>
         
             <%
            if(u.getNombreUsuario()==null){
                %>
                  <div class="form-group">
                    <label for="nombreUsuario" class="col-sm-2 control-label">Nombre Usuario</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="nombreUsuario" value="">
                        </div>
                </div>
                
                <%
            }else{
                
                %>
                 <div class="form-group">
                    <label for="nombreUsuario" class="col-sm-2 control-label">Nombre Usuario</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" name="nombreUsuario" value="<%= u.getNombreUsuario() %>">
                        </div>
                </div>
               
                <%
            }
            
         %>
         
         <!--Al editar el perfil, la contraseña aparece sin ofuscar (gran palabra) y cualquiera la puede ver. Además que debería aparecer dos veces la contraseña 
         (esta ultima en blanco), así si se edita la contraseña primera, habrá que comprobar que la segunda es igual.; si no se edita la primera,
         no hay que comprobar la segunda ya que no la has cambiado. Como en todas las webs -->
         
         <!-- mostrar contraseña siempre en blanco y comprobar que son iguales-->
             <%
            if(u.getContraseña()==null){
                %>
                <div class="form-group">
                    <label for="password1" class="col-sm-2 control-label">Contraseña</label>
                        <div class="col-sm-10">
                        <input type="password" class="form-control" name="password1" value="">
                        </div>
                </div>
                
                <%
            }else{
                
                %>
                <div class="form-group">
                    <label for="password1" class="col-sm-2 control-label">Contraseña</label>
                        <div class="col-sm-10">
                        <input type="password" class="form-control" name="password1" value="">
                        </div>
                </div>
                
                <%
            }
            
         %>
         
         <%
            if(u.getContraseña()==null){
                %>
                <div class="form-group">
                    <label for="password2" class="col-sm-2 control-label">Repetir contraseña</label>
                        <div class="col-sm-10">
                        <input type="password" class="form-control" name="password2" value="">
                        </div>
                </div>
                <%
            }else{
                
                %>
                <div class="form-group">
                    <label for="password2" class="col-sm-2 control-label">Repetir contraseña</label>
                        <div class="col-sm-10">
                        <input type="password" class="form-control" name="password2" value="">
                        </div>
                </div>
                <%
            }
            
         %>
         
             <%
            if(u.getCorreo()==null){
                %>
                <div class="form-group">
                    <label for="correo" class="col-sm-2 control-label">Correo</label>
                        <div class="col-sm-10">
                        <input type="email" class="form-control" name="correo" value="">
                        </div>
                </div>
                <%
            }else{
                
                %>
                <div class="form-group">
                    <label for="correo" class="col-sm-2 control-label">Correo</label>
                        <div class="col-sm-10">
                        <input type="email" class="form-control" name="correo" value="<%= u.getCorreo() %>">
                        </div>
                </div>
                <%
            }
            
         %>
         <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Guardar</button>
             </div>
         </div>
        </form>
         
    
      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>

<% } %>

