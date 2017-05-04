<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="app.entity.*" %>
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
        } else {
            request.setAttribute("pagina", "perfil");
        }
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
            <div class="panel panel-danger profile-send-info">
                <div class="panel-heading">Error:</div>
                <div class="panel-body">
                    Has intentado mandar un mensaje sin título o cuerpo...
                </div>
            </div>
            <% } %>

            <% if (request.getParameter("exito") != null && !request.getParameter("exito").isEmpty()) {%>
            <%
            // Posibles mensajes de exito
            String[] mensajes = {"Mensaje mandado con éxito a "+u.getNombre(),"Experiencia laboral borrada con éxito",
                "Formación borrada con éxito","Afición borrada con éxito","Experiencia laboral editada con éxito",
                "Formación editada con éxito","Afición editada con éxito","Experiencia laboral añadida con éxito",
                "Formación añadida con éxito","Afición añdida con éxito"};
            // Determinar tipo de exito
            int numeroExito = Integer.parseInt(request.getParameter("exito"));
            %>
            
            <div class="alert alert-success profile-send-info profile-send-info" style="top:50px">
                <%= mensajes[numeroExito] %>
            </div>
            <% } %>

            <% if (amigos) {
                Boolean peticionEnviada = (Boolean) request.getAttribute("peticionEnviada");
            } %>

            <div class="col-sm-4 col-md-3 profile-info-section">
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
                    <a href="mailto:<%=u.getCorreo()%>"><%=u.getCorreo()%>
                    </a>
                </p>

                <% if (u.getTwitter() != null) {%>
                <p id="twitter">
                    <i class="fa fa-twitter"></i>
                    <a href="https://twitter.com/<%=u.getTwitter()%>" target="_blank">@<%=u.getTwitter()%>
                    </a>
                </p>
                <%} %>

                <% if (u.getInstagram() != null) {%>
                <p id="instagram">
                    <i class="fa fa-instagram"></i>
                    <a href="https://www.instagram.com/<%=u.getInstagram()%>" target="_blank"><%=u.getInstagram()%>
                    </a>
                </p>
                <%} %>

                <% if (u.getWeb() != null) {%>
                <p id="web">
                    <i class="fa fa-globe"></i>
                    <a href="<%=u.getWeb()%>" target="_blank"><%=u.getWeb()%>
                    </a>
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
                        Has recibido una peticion de amistad de este usuario, puedes aceptarla desde el <a
                            href="<%= cpath%>/notificaciones.jsp">panel de notificaciones</a>
                    </div>
                    <%
                                    }
                                }
                            }
                        }
                    %>
                </div>
            </div>

            <div class="col-sm-8 col-md-9 row">
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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                %>

                <div class="col-lg-8">
                    <h3>Experiencia laboral</h3>
                    <a href="<%= cpath%>/crearExperienciaLaboral.jsp" class="btn btn-default btn-flat btn-sm profile-add"><i class="fa fa-plus"></i></a>
                    <ul class="list-group">
                        <% for (ExperienciaLaboral experiencia : listaExperienciaLaboral) {%>
                        <li class="list-group-item">
                            <h4><%= experiencia.getPuesto()%></h4>
                            <p>En <%= experiencia.getEmpresa()%></p>
                            <% if (experiencia.getWebEmpresa() != null) {%>
                            <p><a href="<%= experiencia.getWebEmpresa()%>"><%= experiencia.getWebEmpresa()%></a></p>
                            <% } %>
                            <%
                                String fechaInicio = dateFormat.format(experiencia.getExperienciaLaboralPK().getFechaComienzo());
                                String fechaFin;
                                Calendar tiempo;
                                long diferencia;
                                if (experiencia.getFechaFinalizacion() == null) {
                                    fechaFin = "actualidad";
                                    diferencia = System.currentTimeMillis() - experiencia.getExperienciaLaboralPK().getFechaComienzo().getTime();
                                } else {
                                    fechaFin = dateFormat.format(experiencia.getFechaFinalizacion());
                                    diferencia = experiencia.getFechaFinalizacion().getTime() - experiencia.getExperienciaLaboralPK().getFechaComienzo().getTime();
                                }
                                tiempo = new GregorianCalendar();
                                tiempo.setTime(new Date(diferencia));
                                int dias = tiempo.get(Calendar.DAY_OF_MONTH);
                                int meses = tiempo.get(Calendar.MONTH);
                                int años = tiempo.get(Calendar.YEAR) - 1970;
                            %>
                            <p class="text-muted"><small>
                                <%= fechaInicio%> - <%= fechaFin%>
                                (<% if(años != 0) {%><%=años%> año<%=años==1 ? "" : "s"%>, <%}%><% if(meses != 0) {%>
                                <%=meses%> mes<%=meses==1 ? "" : "es"%>, <% } %><%=dias%> dia<%=dias==1 ? "" : "s"%>)
                            </small></p>

                            <% if(request.getAttribute("otroUsuario") == null) { %>
                            <div class="edit-profile-jobs">
                                <a href="<%= cpath%>/editarTrabajo.jsp?fechaComienzo=<%= fechaInicio%>" class="btn btn-default btn-flat">
                                    <i class="fa fa-pencil"></i> EDITAR
                                </a>
                                <a href="<%= cpath%>/Borrar?accion=1&idUsuario=<%= u.getId() %>&fechaComienzo=<%= fechaInicio %>" class="btn btn-primary btn-flat">
                                    <i class="fa fa-trash-o"></i> BORRAR
                                </a>
                            </div>
                            <% } %>
                        </li>
                        <% }
                        if(listaExperienciaLaboral.size() == 0) { %>
                        <li class="list-group-item">
                            Sin experiencia laboral
                        </li>
                        <% } %>
                    </ul>

                    <h3>Formación</h3>
                    <a href="<%= cpath%>/crearEstudio.jsp" class="btn btn-default btn-flat btn-sm profile-add"><i class="fa fa-plus"></i></a>
                    <ul class="list-group">
                        <% for (Estudios estudio : listaEstudios) {%>
                        <li class="list-group-item">
                            <h4><%= estudio.getDescripcion()%></h4>
                            <p><%= estudio.getUbicacion()%></p>
                            <%
                                String fechaInicio = dateFormat.format(estudio.getEstudiosPK().getFechaComienzo());
                                String fechaFin;
                                Calendar tiempo;
                                long diferencia;
                                if (estudio.getFechaFinalizacion() == null) {
                                    fechaFin = "actualidad";
                                    diferencia = System.currentTimeMillis() - estudio.getEstudiosPK().getFechaComienzo().getTime();
                                } else {
                                    fechaFin = dateFormat.format(estudio.getFechaFinalizacion());
                                    diferencia = estudio.getFechaFinalizacion().getTime() - estudio.getEstudiosPK().getFechaComienzo().getTime();
                                }
                                tiempo = new GregorianCalendar();
                                tiempo.setTime(new Date(diferencia));
                                int dias = tiempo.get(Calendar.DAY_OF_MONTH);
                                int meses = tiempo.get(Calendar.MONTH);
                                int años = tiempo.get(Calendar.YEAR) - 1970;
                            %>
                            <p class="text-muted"><small>
                                <%= fechaInicio%> - <%= fechaFin%>
                                (<% if(años != 0) {%><%=años%> año<%=años==1 ? "" : "s"%>, <%}%><% if(meses != 0) {%>
                                <%=meses%> mes<%=meses==1 ? "" : "es"%>, <% } %><%=dias%> dia<%=dias==1 ? "" : "s"%>)
                            </small></p>

                            <% if(request.getAttribute("otroUsuario") == null) { %>
                            <div class="edit-profile-jobs">
                                <a href="<%= cpath%>/editarEstudio.jsp?fechaComienzo=<%= fechaInicio %>" class="btn btn-default btn-flat">
                                    <i class="fa fa-pencil"></i> EDITAR
                                </a>
                                <a href="<%= cpath%>/Borrar?accion=2&idUsuario=<%= u.getId() %>&fechaComienzo=<%= fechaInicio %>" class="btn btn-primary btn-flat">
                                    <i class="fa fa-trash-o"></i> BORRAR
                                </a>
                            </div>
                            <% } %>
                        </li>
                        <% }
                        if(listaEstudios.size() == 0) { %>
                        <li class="list-group-item">
                            Sin estudios
                        </li>
                        <% } %>
                    </ul>

                    <div class="clearfix">
                        <h3 class="pull-left">Aficiones</h3>
                        <a href="<%= cpath%>/crearAficion.jsp" class="btn btn-default btn-flat btn-sm profile-add"><i class="fa fa-plus"></i></a>
                    </div>
                    <ul class="list-group">
                        <% for (Aficiones experiencia : listaAficiones) {%>
                        <li class="list-group-item">
                            <%= experiencia.getAficionesPK().getNombre()%>

                            <% if(request.getAttribute("otroUsuario") == null) { %>
                            <div class="edit-profile-jobs pull-right">
                                <a href="<%= cpath%>/editarAficion.jsp?nombreAficion=<%= experiencia.getAficionesPK().getNombre() %>" class="btn btn-default btn-flat btn-sm">
                                    <i class="fa fa-pencil"></i>
                                </a>
                                <a href="<%= cpath%>/Borrar?accion=3&idUsuario=<%= u.getId() %>&nombreAficion=<%= experiencia.getAficionesPK().getNombre() %>" class="btn btn-primary btn-flat btn-sm">
                                    <i class="fa fa-trash-o"></i>
                                </a>
                            </div>
                            <% } %>
                        </li>
                        <% }
                        if(listaAficiones.size() == 0) { %>
                        <li class="list-group-item">
                            Sin aficiones
                        </li>
                        <% } %>
                    </ul>
                </div>

                <div class="col-lg-4 profile-contacts">
                    <h3>Contactos</h3>
                    <% for (Usuario contacto : listaContactos) { %>
                    <div class="row profile-contact">
                        <div class="col-lg-3 col-sm-2 col-xs-3 profile-contact-photo">
                            <a href="<%=cpath%>/Perfil?id=<%=contacto.getId()%>">
                                <div class="img-circle img-profile"
                                    <% if(contacto.getFoto() != null) { %>
                                     style="background-image: url('<%=contacto.getFoto()%>')"
                                    <% } else { %>
                                     style="background-image: url('<%@include file="snippets/fotoPerfil.txt"%>')"
                                    <% } %>
                                ></div>
                            </a>
                        </div>
                        <div class="col-lg-9 col-sm-10 col-xs-9 profile-contact-name">
                            <div>
                                <a href="<%= cpath%>/Perfil?id=<%= contacto.getId()%>"><%= contacto.getNombre() + " " + contacto.getApellidos()%></a>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>

                <% if(request.getAttribute("otroUsuario") == null) { %>
                <div class="col-xs-12">
                    <h3>Mensajes recibidos</h3>
                    <% for(Mensaje mensaje : u.getMensajeCollection1()) { %>
                    <div class="mensaje row">
                        <div class="col-xs-3 col-sm-1">
                            <div class="img-circle img-profile"
                                <% if(mensaje.getIdEmisor().getFoto() != null) { %>
                                 style="background-image: url('<%=mensaje.getIdEmisor().getFoto()%>')"
                                <% } else { %>
                                 style="background-image: url('<%@include file="snippets/fotoPerfil.txt"%>')"
                                <% } %>
                            ></div>
                        </div>
                        <div class="col-xs-9 col-sm-11">
                            <div>
                                <h4><%=mensaje.getTitulo()%></h4>
                                <small>de <a href="<%=cpath%>/Perfil?id=<%=mensaje.getIdEmisor().getId()%>">@<%=mensaje.getIdEmisor().getNombreUsuario()%></a></small>
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <p class="lead">
                                <%=mensaje.getTexto()%>
                            </p>
                        </div>
                    </div>
                    <% } %>
                </div>
                <% } %>

                <% } %>
            </div>

            <% if (request.getAttribute("otroUsuario") != null && amigos) {%>
            <div class="clearfix casual">
                <div class="col-sm-offset-4 col-md-offset-3">
                    <h3>Mandar mensaje a <%= u.getNombreUsuario()%></h3>
                    <form method="POST" action="<%=cpath%>/Mensaje">
                        <input type="hidden" name="idHacia" value="<%= u.getId()%>">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-envelope-o"></i></div>
                                <input maxlength="100" type="text" id="titulo" class="form-control" name="titulo"
                                       placeholder="Titulo del mensaje">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><i class="fa fa-comment-o"></i></div>
                                <textarea maxlength="999" class="form-control" id="mensaje" name="mensaje" placeholder="Mensaje..."></textarea>
                            </div>
                        </div>
                        <div class="text-right">
                            <button type="submit" class="btn btn-primary btn-raised">ENVIAR</button>
                        </div>
                    </form>
                </div>
            </div>
            <% } %>
            <%@include file="snippets/footer.jsp" %>
        </div>
        <%@include file="snippets/body-end.jsp" %>
    </body>
</html>
<% }%>
