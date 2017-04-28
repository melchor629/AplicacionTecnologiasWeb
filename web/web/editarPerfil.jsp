<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="app.entity.Usuario" %>

<%
    String cpath = request.getContextPath();
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u == null) {
        response.sendRedirect(cpath);
    } else {
        request.setAttribute("pagina", "perfil");
%>
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
        <h1 class="text-center">Editando perfil de @<%=u.getNombreUsuario()%></h1>

        <%
            int error = 0;
            if (request.getParameter("error") != null) {
                error = Integer.parseInt(request.getParameter("error"));
                String msgError = "Error desconocido :/";
                if(error == 1) {
                    msgError = "¡Las contraseñas no coinciden!";
                } else if(error==2) {
                    msgError = "¡El campo nombre no puede estar vacio!";
                } else if(error==3) {
                    msgError = "¡El campo apellidos no puede estar vacio!";
                } else if(error==4) {
                    msgError = "¡El campo correo no puede estar vacio!";
                } else if(error==5) {
                    msgError = "El campo nombre usuario no puede estar vacio!";
                }
        %>
        <div class="alert alert-danger">
            <%=msgError%>
        </div>
        <%
            }
        %>

        <form class="form-horizontal" name="edit" action="<%=cpath%>/EditarPerfil" method="POST">
            <div class="row">
                <div class="col-sm-6 col-xs-12">
                    <%
                        if (u.getNombreUsuario() == null) {
                    %>
                    <div class="form-group edit-profile-form-group <%= error == 5 ? "has-error" : "" %>">
                        <label for="nombreUsuario" class="col-sm-3 control-label">Nombre Usuario</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-user-o"></i></div>
                            <input type="text" id="nombreUsuario" class="form-control" name="nombreUsuario" value="" placeholder="Nombre Usuario">
                        </div>
                    </div>

                    <%
                    } else {

                    %>
                    <div class="form-group edit-profile-form-group <%= error == 5 ? "has-error" : "" %>">
                        <label for="nombreUsuario" class="col-sm-3 control-label">Nombre Usuario</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-user-o"></i></div>
                            <input type="text" id="nombreUsuario" class="form-control" name="nombreUsuario" value="<%= u.getNombreUsuario() %>"
                                   placeholder="Nombre Usuario">
                        </div>
                    </div>

                    <%
                        }

                        if (u.getNombre() == null) {
                    %>

                    <div class="form-group edit-profile-form-group <%= error == 2 ? "has-error" : "" %>">
                        <label for="nombre" class="col-sm-3 control-label">Nombre</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-address-card-o"></i></div>
                            <input type="text" id="nombre" class="form-control" name="nombre" value="" placeholder="Nombre">
                        </div>
                    </div>

                    <%
                    } else {

                    %>

                    <div class="form-group edit-profile-form-group <%= error == 2 ? "has-error" : "" %>">
                        <label for="nombre" class="col-sm-3 control-label">Nombre</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-address-card-o"></i></div>
                            <input type="text" id="nombre" class="form-control" name="nombre" value="<%= u.getNombre() %>" placeholder="Nombre">
                        </div>
                    </div>

                    <%
                        }

                        if (u.getApellidos() == null) {
                    %>

                    <div class="form-group edit-profile-form-group <%= error == 3 ? "has-error" : "" %>">
                        <label for="apellidos" class="col-sm-3 control-label">Apellidos</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-address-card-o"></i></div>
                            <input type="text" id="apellidos" class="form-control" name="apellidos" value="" placeholder="Apellidos">
                        </div>
                    </div>

                    <%
                    } else {

                    %>

                    <div class="form-group edit-profile-form-group <%= error == 3 ? "has-error" : "" %>">
                        <label for="apellidos" class="col-sm-3 control-label">Apellidos</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-address-card-o"></i></div>
                            <input type="text" id="apellidos" class="form-control" name="apellidos" value="<%= u.getApellidos() %>"
                                   placeholder="Apellidos">
                        </div>
                    </div>

                    <%
                        }

                        if (u.getCorreo() == null) {
                    %>
                    <div class="form-group edit-profile-form-group <%= error == 4 ? "has-error" : "" %>">
                        <label for="correo" class="col-sm-3 control-label">Correo</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-envelope-o"></i></div>
                            <input type="email" id="correo" class="form-control" name="correo" value="" placeholder="Correo">
                        </div>
                    </div>
                    <%
                    } else {

                    %>
                    <div class="form-group edit-profile-form-group <%= error == 4 ? "has-error" : "" %>">
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

                    <!--Al editar el perfil, la contraseña aparece sin ofuscar (gran palabra) y cualquiera la puede ver. Además que debería aparecer dos veces la contraseña
                    (esta ultima en blanco), así si se edita la contraseña primera, habrá que comprobar que la segunda es igual.; si no se edita la primera,
                    no hay que comprobar la segunda ya que no la has cambiado. Como en todas las webs -->

                    <!-- mostrar contraseña siempre en blanco y comprobar que son iguales-->
                    <div class="form-group edit-profile-form-group <%= error == 1 ? "has-error" : "" %>">
                        <label for="password1" class="col-sm-3 control-label">Contraseña</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-key"></i></div>
                            <input type="password" id="password1" class="form-control" name="password1" value="" placeholder="Contraseña">
                        </div>
                        <div class="col-sm-9 col-sm-offset-3 pad">
                            <span class="help-block">Para cambiar la contraseña, solo tienes que escribir la nueva contraseña aquí</span>
                        </div>
                    </div>

                    <div class="form-group edit-profile-form-group <%= error == 1 ? "has-error" : "" %>">
                        <label for="password2" class="col-sm-3 control-label">Repetir contraseña</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-key"></i></div>
                            <input type="password" id="password2" class="form-control" name="password2" value="" placeholder="Repetir contraseña">
                        </div>
                        <div class="col-sm-9 col-sm-offset-3 pad">
                            <span class="help-block">No olvides repetir la contraseña para asegurarnos de que la has escrito bien</span>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6 col-xs-12">
                    <div class="form-group edit-profile-form-group text-center">
                        <div class="col-sm-offset-3 col-sm-9">
                            <img src="<%=u.getFoto()%>" width="100%" id="foto-cambiame" class="profile-photo profile-photo-small">
                        </div>
                    </div>


                    <%
                        if (u.getFoto() == null) {
                    %>

                    <div class="form-group edit-profile-form-group">
                        <label for="foto" class="col-sm-3 control-label">Foto</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-picture-o"></i></div>
                            <input type="text" id="foto" class="form-control" name="foto" value="" placeholder="Foto">
                        </div>
                    </div>


                    <%
                    } else {

                    %>
                    <div class="form-group edit-profile-form-group">
                        <label for="foto" class="col-sm-3 control-label">Foto</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-picture-o"></i></div>
                            <input type="text" id="foto" class="form-control" name="foto" value="<%= u.getFoto() %>" placeholder="Foto">
                        </div>
                    </div>

                    <%
                        }

                        if (u.getTwitter() == null) {
                    %>

                    <div class="form-group edit-profile-form-group">
                        <label for="twitter" class="col-sm-3 control-label">Twitter</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-twitter"></i></div>
                            <input type="text" id="twitter" class="form-control" name="twitter" value="" placeholder="Twitter">
                        </div>
                    </div>

                    <%
                    } else {

                    %>

                    <div class="form-group edit-profile-form-group">
                        <label for="twitter" class="col-sm-3 control-label">Twitter</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-twitter"></i></div>
                            <input type="text" id="twitter" class="form-control" name="twitter" value="<%= u.getTwitter() %>"
                                   placeholder="Twitter">
                        </div>
                    </div>

                    <%
                        }

                        if (u.getInstagram() == null) {
                    %>

                    <div class="form-group edit-profile-form-group">
                        <label for="instagram" class="col-sm-3 control-label">Instagram</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-instagram"></i></div>
                            <input type="text" id="instagram" class="form-control" name="instagram" value="" placeholder="Instagram">
                        </div>
                    </div>

                    <%
                    } else {

                    %>
                    <div class="form-group edit-profile-form-group">
                        <label for="instagram" class="col-sm-3 control-label">Instagram</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-instagram"></i></div>
                            <input type="text" id="instagram" class="form-control" name="instagram" value="<%= u.getInstagram() %>"
                                   placeholder="Instagram">
                        </div>
                    </div>

                    <%
                        }

                        if (u.getWeb() == null) {
                    %>
                    <div class="form-group edit-profile-form-group">
                        <label for="web" class="col-sm-3 control-label">Web</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-globe"></i></div>
                            <input type="text" id="web" class="form-control" name="web" value="" placeholder="Web">
                        </div>
                    </div>

                    <%
                    } else {

                    %>
                    <div class="form-group edit-profile-form-group">
                        <label for="web" class="col-sm-3 control-label">Web</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-globe"></i></div>
                            <input type="text" id="web" class="form-control" name="web" value="<%= u.getWeb() %>" placeholder="Web">
                        </div>
                    </div>

                    <%
                        }

                    %>
                    
                </div>

            </div>

            <div class="form-group edit-profile-form-group">
                <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-raised">GUARDAR</button>
                    <a href="<%=cpath%>/perfil.jsp" class="btn btn-default btn-raised">CANCELAR</a>
                </div>
            </div>
        </form>


        <%@include file="snippets/footer.jsp" %>
    </div>

    <%@include file="snippets/body-end.jsp" %>
    <script>
        $(document).ready(function() {
            $('#foto').change(function() {
                var url = $(this).val();
                if(url) {
                    $('#foto-cambiame').attr('src', url);
                } else {
                    $('#foto-cambiame').attr('src', '<%@include file="snippets/fotoPerfil.txt"%>')
                }
            });
        });
    </script>
</body>
</html>

<% } %>

