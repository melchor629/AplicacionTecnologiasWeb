<%@page import="app.ejb.AficionesFacade"%>
<%@page import="java.util.List"%>
<%@page import="app.entity.Aficiones"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="app.entity.Usuario" %>

<%
    String cpath = request.getContextPath();
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u == null) {
        response.sendRedirect(cpath);
    } else { %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Modificar perfil</title>
    <meta name="description" content="Modificar perfil de usuario">
    <%@include file="snippets/head.jsp" %>
</head>

<body>

    <%@include file="snippets/nav-logged.jsp" %>

    <div class="container">

        <!-- Contenido -->

        <!-- imput text -->

        <%
            if (request.getParameter("error") != null) {
                Integer error = Integer.parseInt((String) request.getParameter("error"));
                if (error == 1) {
        %>
        <div class="alert alert-danger">
            Las contraseñas no coinciden!
        </div>
        <%
                }

        %>
        
        <%

                if(error==2){
        %>
         <div class="alert alert-danger">
            El campo nombre no puede estar vacio!
        </div>
        <% 
            }
            %>
            
             <%

                if(error==3){
        %>
         <div class="alert alert-danger">
            El campo apellidos no puede estar vacio!
        </div>
        <% 
            }
            %>
            
             <%

                if(error==4){
        %>
         <div class="alert alert-danger">
            El campo correo no puede estar vacio!
        </div>
        <% 
            }
            %>
            
             <%

                if(error==5){
        %>
         <div class="alert alert-danger">
            El campo nombre usuario no puede estar vacio!
        </div>
        <% 
            }
            %>

        <%
            }
        %>


        <h1>Editando perfil de @<%=u.getNombreUsuario()%></h1>

        <form class="form-horizontal" name="edit" action="<%=cpath%>/EditarPerfil" method="POST">
            <div class="row">
                <div class="col-sm-6">
                    <%
                        if (u.getNombreUsuario() == null) {
                    %>
                    <div class="form-group">
                        <label for="nombreUsuario" class="col-sm-3 control-label">Nombre Usuario</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-user-o"></i></div>
                            <input type="text" id="nombreUsuario" class="form-control" name="nombreUsuario" value="" placeholder="Nombre Usuario">
                        </div>
                    </div>

                    <%
                    } else {

                    %>
                    <div class="form-group">
                        <label for="nombreUsuario" class="col-sm-3 control-label">Nombre Usuario</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-user-o"></i></div>
                            <input type="text" id="nombreUsuario" class="form-control" name="nombreUsuario" value="<%= u.getNombreUsuario() %>"
                                   placeholder="Nombre Usuario">
                        </div>
                    </div>

                    <%
                        }

                    %>

                    <%
                        if (u.getNombre() == null) {
                    %>

                    <div class="form-group">
                        <label for="nombre" class="col-sm-3 control-label">Nombre</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-address-card-o"></i></div>
                            <input type="text" id="nombre" class="form-control" name="nombre" value="" placeholder="Nombre">
                        </div>
                    </div>

                    <%
                    } else {

                    %>

                    <div class="form-group">
                        <label for="nombre" class="col-sm-3 control-label">Nombre</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-address-card-o"></i></div>
                            <input type="text" id="nombre" class="form-control" name="nombre" value="<%= u.getNombre() %>" placeholder="Nombre">
                        </div>
                    </div>

                    <%
                        }

                    %>

                    <%
                        if (u.getApellidos() == null) {
                    %>

                    <div class="form-group">
                        <label for="apellidos" class="col-sm-3 control-label">Apellidos</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-address-card-o"></i></div>
                            <input type="text" id="apellidos" class="form-control" name="apellidos" value="" placeholder="Apellidos">
                        </div>
                    </div>

                    <%
                    } else {

                    %>

                    <div class="form-group">
                        <label for="apellidos" class="col-sm-3 control-label">Apellidos</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-address-card-o"></i></div>
                            <input type="text" id="apellidos" class="form-control" name="apellidos" value="<%= u.getApellidos() %>"
                                   placeholder="Apellidos">
                        </div>
                    </div>

                    <%
                        }

                    %>

                    <%
                        if (u.getFoto() == null) {
                    %>

                    <div class="form-group">
                        <label for="foto" class="col-sm-3 control-label">Foto</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-picture-o"></i></div>
                            <input type="text" id="foto" class="form-control" name="foto" value="" placeholder="Foto">
                        </div>
                    </div>


                    <%
                    } else {

                    %>
                    <div class="form-group">
                        <label for="foto" class="col-sm-3 control-label">Foto</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-picture-o"></i></div>
                            <input type="text" id="foto" class="form-control" name="foto" value="<%= u.getFoto() %>" placeholder="Foto">
                        </div>
                    </div>

                    <%
                        }

                    %>

                    <!--Al editar el perfil, la contraseña aparece sin ofuscar (gran palabra) y cualquiera la puede ver. Además que debería aparecer dos veces la contraseña
                    (esta ultima en blanco), así si se edita la contraseña primera, habrá que comprobar que la segunda es igual.; si no se edita la primera,
                    no hay que comprobar la segunda ya que no la has cambiado. Como en todas las webs -->

                    <!-- mostrar contraseña siempre en blanco y comprobar que son iguales-->
                    <div class="form-group">
                        <label for="password1" class="col-sm-3 control-label">Contraseña</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-key"></i></div>
                            <input type="password" id="password1" class="form-control" name="password1" value="" placeholder="Contraseña">
                        </div>
                        <div class="col-sm-9 col-sm-offset-3">
                            <span class="help-block">Para cambiar la contraseña, solo tienes que escribir la nueva contraseña aquí</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password2" class="col-sm-3 control-label">Repetir contraseña</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-key"></i></div>
                            <input type="password" id="password2" class="form-control" name="password2" value="" placeholder="Repetir contraseña">
                        </div>
                        <div class="col-sm-9 col-sm-offset-3">
                            <span class="help-block">No olvides repetir la contraseña para asegurarnos de que la has escrito bien</span>
                        </div>
                    </div>

                    <%
                        if (u.getCorreo() == null) {
                    %>
                    <div class="form-group">
                        <label for="correo" class="col-sm-3 control-label">Correo</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-envelope-o"></i></div>
                            <input type="email" id="correo" class="form-control" name="correo" value="" placeholder="Correo">
                        </div>
                    </div>
                    <%
                    } else {

                    %>
                    <div class="form-group">
                        <label for="correo" class="col-sm-3 control-label">Correo</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-envelope-o"></i></div>
                            <input type="email" id="correo" class="form-control" name="correo" value="<%= u.getCorreo() %>"
                                   placeholder="Correo">
                        </div>
                    </div>
                    <%
                        }

                    %>

                    <%
                        if (u.getTwitter() == null) {
                    %>

                    <div class="form-group">
                        <label for="twitter" class="col-sm-3 control-label">Twitter</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-twitter"></i></div>
                            <input type="text" id="twitter" class="form-control" name="twitter" value="" placeholder="Twitter">
                        </div>
                    </div>

                    <%
                    } else {

                    %>

                    <div class="form-group">
                        <label for="twitter" class="col-sm-3 control-label">Twitter</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-twitter"></i></div>
                            <input type="text" id="twitter" class="form-control" name="twitter" value="<%= u.getTwitter() %>"
                                   placeholder="Twitter">
                        </div>
                    </div>

                    <%
                        }

                    %>

                    <%
                        if (u.getInstagram() == null) {
                    %>

                    <div class="form-group">
                        <label for="instagram" class="col-sm-3 control-label">Instagram</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-instagram"></i></div>
                            <input type="text" id="instagram" class="form-control" name="instagram" value="" placeholder="Instagram">
                        </div>
                    </div>

                    <%
                    } else {

                    %>
                    <div class="form-group">
                        <label for="instagram" class="col-sm-3 control-label">Instagram</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-instagram"></i></div>
                            <input type="text" id="instagram" class="form-control" name="instagram" value="<%= u.getInstagram() %>"
                                   placeholder="Instagram">
                        </div>
                    </div>

                    <%
                        }

                    %>

                    <%
                        if (u.getWeb() == null) {
                    %>
                    <div class="form-group">
                        <label for="web" class="col-sm-3 control-label">Web</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-globe"></i></div>
                            <input type="text" id="web" class="form-control" name="web" value="" placeholder="Web">
                        </div>
                    </div>

                    <%
                    } else {

                    %>
                    <div class="form-group">
                        <label for="web" class="col-sm-3 control-label">Web</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-globe"></i></div>
                            <input type="text" id="web" class="form-control" name="web" value="<%= u.getWeb() %>" placeholder="Web">
                        </div>
                    </div>

                    <%
                        }

                    %>
                    
                    <!-- Falta mostrar aficiones, experiencia laboral y estudios-->
                    
                    
                    <!--Aficiones -->
                     <%
                        
                        AficionesFacade fachadaAficiones= (AficionesFacade) session.getAttribute("listaAficiones");
                        List<Aficiones> aficiones = null;
                        aficiones = fachadaAficiones.obtenerAficiones(u);
                        
                        if (aficiones == null) {
                    %>
                    <div class="form-group">
                        <label for="aficiones" class="col-sm-3 control-label">Aficiones</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-globe"></i></div>
                            <input type="text" id="aficiones" class="form-control" name="aficiones" value="" placeholder="Aficiones">
                        </div>
                    </div>

                    <%
                    } else {

                    %>
                    <ul>
                    <%for(Aficiones a: aficiones){
                    %>
               

                            <li><%= a.getAficionesPK().getNombre() %> <input class="btn btn-default" type="submit" value="Editar">  </li>
                        
                            <%
                                }
                                %>
                    </ul>
                    <input class="btn btn-default" type="submit" value="añadir">
                    <%
                        }

                    %>
                    
                </div>

                <div class="col-sm-6">
                    <!-- Editar trabajos y eso -->
                </div>
            </div>

            <div class="form-group">
                <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-raised">GUARDAR</button>
                    <a href="javascript:history.back()" class="btn btn-default btn-raised">CANCELAR</a>
                </div>
            </div>
        </form>


        <%@include file="snippets/footer.jsp" %>
    </div>

    <%@include file="snippets/body-end.jsp" %>
</body>
</html>

<% } %>

