<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Collection"%>
<%@page import="app.entity.Estudios"%>
<%@page import="app.entity.ExperienciaLaboral"%>
<%@page import="app.entity.Aficiones"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="app.entity.Usuario" %>
<%

    String cpath = request.getContextPath();
    Usuario u = (Usuario) session.getAttribute("usuario");
    boolean mostrarPerfil = true; // variable que determina si debe mostrarse el perfil del usuario
    Boolean amigos = true; // Si procede almacena si los usuarios son amigos
    if (u == null) {
        response.sendRedirect(cpath);
    } else {

        if (request.getAttribute("otroUsuario") != null) {
            amigos = (Boolean) request.getAttribute("amigos");
            u = (Usuario) request.getAttribute("otroUsuario");

            if (!amigos) {
                mostrarPerfil = false;
            }
        }
        request.setAttribute("pagina", "perfil");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Perfil de <%=u.getNombre()%> - LinkedOut</title>
        <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
        <%@include file="snippets/head.jsp" %>
    </head>

    <body>
        <%@include file="snippets/nav-logged.jsp" %>
        <div class="container">
            <% if (request.getParameter("error") != null && !request.getParameter("error").isEmpty()) { %>
            <div class="panel panel-danger">
                <div class="panel-heading">Error:</div>
                <div class="panel-body">
                    Has intentado mandar un mensaje sin título o cuerpo...
                </div>
            </div>
            <% } %>

            <% if (request.getParameter("exito") != null && !request.getParameter("exito").isEmpty()) {%>
            <div class="alert alert-success">
                Mensaje mandado con éxito a <%= u.getNombre()%>
            </div>
            <% } %>

            <% if (amigos) {
                    Boolean peticionEnviada = (Boolean) request.getAttribute("peticionEnviada");
                } %>
            <div class="row">
                <div class="col-sm-4 col-md-3">
                    <% if (u.getFoto() == null) { %>
                    <img src="<%@include file="snippets/fotoPerfil.txt"%>" class="img-responsive profile-photo"
                         alt="Foto de perfil genérico">
                    <% } else {%>
                    <img src="<%=u.getFoto()%>" class="img-responsive profile-photo" alt="Foto de perfil de <%=u.getNombre()%>">
                    <% }%>


                    <h5 id="nombreApellidos">
                        <%=u.getNombre()%> <%=u.getApellidos()%>
                    </h5>

                    <p id="nombreUsuario">
                        <i class="fa fa-at"></i> <%=u.getNombreUsuario()%>
                    </p>

                    <p id="email">
                        <i class="fa fa-envelope-o"></i>
                        <a href="mailto:<%=u.getCorreo()%>"><%=u.getCorreo()%></a>
                    </p>

                    <% if (u.getTwitter() != null) {%>
                    <p id="twitter">
                        <i class="fa fa-twitter"></i>
                        <a href="https://twitter.com/<%=u.getTwitter()%>" target="_blank">@<%=u.getTwitter()%></a>
                    </p>
                    <%} %>

                    <% if (u.getInstagram() != null) {%>
                    <p id="instagram">
                        <i class="fa fa-instagram"></i>
                        <a href="https://www.instagram.com/<%=u.getInstagram()%>" target="_blank"><%=u.getInstagram()%></a>
                    </p>
                    <%} %>

                    <% if (u.getWeb() != null) {%>
                    <p id="web">
                        <i class="fa fa-globe"></i>
                        <a href="<%=u.getWeb()%>" target="_blank"><%=u.getWeb()%></a>
                    </p>
                    <% }%>

                    <div class="profile-options">
                        <%
                            if (request.getAttribute("otroUsuario") == null) {
                        %>
                        <a href="<%= cpath%>/editarPerfil.jsp" class="btn btn-primary btn-raised">
                            <i class="fa fa-pencil"></i> Editar perfil
                        </a>
                        <% } else {
                            Boolean peticionEnviada = (Boolean) request.getAttribute("peticionEnviada");

                            // Comprobar si el usuario actual tiene amistad con el usuario
                            if (amigos) {%>
                        <a href="<%= cpath%>/PeticionAmistad?accion=2&id=<%= u.getId()%>" class="btn btn-primary btn-raised">
                            <i class="fa fa-user-times"></i> Eliminar amistad
                        </a>
                        <% } else {%>

                        <% if (!peticionEnviada) {%>
                        <a href="<%= cpath%>/PeticionAmistad?accion=1&id=<%= u.getId()%>" class="btn btn-primary btn-raised">
                            <i class="fa fa-user-plus"></i> Enviar solicitud de amistad
                        </a>
                        <% } else {

                            Boolean peticionMandadaPorMi = (Boolean) request.getAttribute("peticionMandadaPorMi");

                            if (peticionMandadaPorMi) { %>

                        <div class="alert alert-info">
                            Ya has mandado una peticion de amistad a este usuario, espera a que te acepte o rechace
                        </div>

                        <% } else {%>
                        <div class="alert alert-info">
                            Has recibido una peticion de amistad de este usuario, puedes aceptarla desde el <a href="<%= cpath%>/notificaciones.jsp">panel de notificaciones</a>
                        </div>
                        <%}
                                    }
                                }
                            } %>
                    </div>
                </div>
                <div class="col-sm-8 col-md-9">
                    <% if (request.getAttribute("otroUsuario") == null || mostrarPerfil) {
                            // Lista de aficiones
                            Collection<Aficiones> listaAficiones = u.getAficionesCollection();
                            // Lista de experiencia laboral
                            Collection<ExperienciaLaboral> listaExperienciaLaboral = u.getExperienciaLaboralCollection();
                            // Lista de estudios
                            Collection<Estudios> listaEstudios = u.getEstudiosCollection();
                            // Lista de contactos
                            Collection<Usuario> listaContactos1 = u.getUsuarioCollection();
                            // Lista de contactos para la otra parte
                            Collection<Usuario> listaContactos2 = u.getUsuarioCollection1();
                            // Lista con todos los contactos
                            Collection<Usuario> listaContactos = new ArrayList<Usuario>();
                            listaContactos.addAll(listaContactos1);
                            listaContactos.addAll(listaContactos2);
                    %>
                    <div class="page-header"><h3>Experiencia laboral</h3></div>
                    <ul class="list-group">
                        <% for (ExperienciaLaboral experiencia : listaExperienciaLaboral) { %>
                        <li class="list-group-item">
                            <ul class="list-group">
                                <li class="list-group-item"><h4><%= experiencia.getPuesto() %></h4></li>
                                <li class="list-group-item"><%= experiencia.getEmpresa()%></li>
                                <% if(experiencia.getWebEmpresa() != null){ %>
                                <li class="list-group-item"><a href="<%= experiencia.getWebEmpresa()%>"><%= experiencia.getWebEmpresa()%></a></li>
                                <% } %>
                                <%
                                    String fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(experiencia.getExperienciaLaboralPK().getFechaComienzo());
                                    String fechaFin;
                                    if(experiencia.getFechaFinalizacion() == null){
                                        fechaFin = "actualidad";
                                    }
                                    else{
                                     fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(experiencia.getFechaFinalizacion());
                                    }
                                    %>
                                <li class="list-group-item"><%= fechaInicio %> | <%= fechaFin %></li>
                            </ul>
                        </li>
                        <% } %>
                    </ul>
                    <div class="page-header"><h3>Formación</h3></div>
                    <ul class="list-group">
                        <% for (Estudios estudio : listaEstudios) { %>
                        <li class="list-group-item">
                            <ul class="list-group">
                                <li class="list-group-item"><h4><%= estudio.getDescripcion() %></h4></li>
                                <li class="list-group-item"><%= estudio.getUbicacion()%></li>
                                <%
                                    String fechaInicio = new SimpleDateFormat("yyyy-MM-dd").format(estudio.getEstudiosPK().getFechaComienzo());
                                    String fechaFin;
                                    if(estudio.getFechaFinalizacion() == null){
                                        fechaFin = "actualidad";
                                    }
                                    else{
                                     fechaFin = new SimpleDateFormat("yyyy-MM-dd").format(estudio.getFechaFinalizacion());
                                    }
                                    %>
                                <li class="list-group-item"><%= fechaInicio %> | <%= fechaFin %></li>
                            </ul>
                        </li>
                        <% } %>
                    </ul>
                    <div class="page-header"><h3>Aficiones</h3></div>
                    <ul class="list-group">
                        <% for (Aficiones experiencia : listaAficiones) { %>
                                <li class="list-group-item"><%= experiencia.getAficionesPK().getNombre() %></li>  
                        <% } %>
                    </ul>
                    
                    <div class="page-header"><h3>Contactos</h3></div>
                    <ul class="list-group">
                        <% for (Usuario contacto : listaContactos) { %>
                        <li class="list-group-item">
                            <% if (contacto.getFoto() == null) { %>
                    <img src="<%@include file="snippets/fotoPerfil.txt"%>" class="img-responsive profile-photo"
                         alt="Foto de perfil genérico"> <a href="<%= cpath %>/Perfil?id=<%= contacto.getId() %>"><%= contacto.getNombre() + " " + contacto.getApellidos() %></a>
                    <% } else {%>
                    <img src="<%=contacto.getFoto()%>" style="width: 10%; height: auto; display: inline-block;"class="img-responsive profile-photo" alt="Foto de perfil de <%=u.getNombre()%>"> <a href="<%= cpath %>/Perfil?id=<%= contacto.getId() %>"><%= contacto.getNombre() + " " + contacto.getApellidos() %></a>
                    <% }%></li>
                        <% } %>
                    </ul>
                    <% } %>
                    
                    
                </div>
            </div>
            <% if (request.getAttribute("otroUsuario") != null && amigos) {%>
            <div class="row">
                <h2>Mandar mensaje a <%= u.getNombreUsuario()%></h2>
                <form method="POST" action="Mensaje">
                    <input type="hidden" name="idHacia" value="<%= u.getId()%>">
                    <div class="form-group edit-profile-form-group">
                        <label for="comentario" class="col-sm-3 control-label">Titulo:</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-envelope-o"></i></div>
                            <input maxlength="100" type="text" class="form-control" name="titulo" placeholder="Titulo del mensaje">
                        </div>
                    </div>                            

                    <div class="form-group edit-profile-form-group">
                        <label for="comentario" class="col-sm-3 control-label">Mensaje:</label>
                        <div class="col-sm-9 input-group">
                            <div class="input-group-addon"><i class="fa fa-comment-o"></i></div>
                            <textarea maxlength="999" class="form-control" name="mensaje" placeholder="Mensaje..."></textarea>
                        </div>
                    </div>
                    <div class="text-right"><button type="submit" class="btn btn-primary btn-raised">Mandar mensaje</button></div>
                </form>
            </div>
            <% } %>
            <%@include file="snippets/footer.jsp" %>
        </div>
        <%@include file="snippets/body-end.jsp" %>
    </body>
</html>
<% }%>
