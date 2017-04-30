<%@page import="app.entity.Estudios" %>
<%@page import="app.entity.ExperienciaLaboral" %>
<%@page import="app.entity.Usuario" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>? - LinkedOut</title>
        <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
        <%@include file="snippets/head.jsp" %>
    </head>
    <body>

        <%@include file="snippets/nav-logged.jsp" %>

        <div class="container">

            <div class="row">
            <% List<Usuario> usuarios = (List<Usuario>) request.getAttribute("resultados");
                if (!usuarios.isEmpty()) {
                    for (Usuario aux : usuarios) {
                        List<ExperienciaLaboral> experiencia = new ArrayList<ExperienciaLaboral>(aux.getExperienciaLaboralCollection());
                        List<Estudios> estudios = new ArrayList<Estudios>(aux.getEstudiosCollection());
                        Collections.sort(experiencia, new Comparator<ExperienciaLaboral>() {
                            @Override
                            public int compare(ExperienciaLaboral o1, ExperienciaLaboral o2) {
                                return o2.getExperienciaLaboralPK().getFechaComienzo().compareTo(o1.getExperienciaLaboralPK().getFechaComienzo());
                            }
                        });
                        Collections.sort(estudios, new Comparator<Estudios>() {
                            @Override
                            public int compare(Estudios o1, Estudios o2) {
                                return o2.getEstudiosPK().getFechaComienzo().compareTo(o1.getEstudiosPK().getFechaComienzo());
                            }
                        });
            %>

                <div class="result-card-container col-sm-6 col-lg-4">
                    <div class="panel panel-default result-card">
                        <div class="panel-body">
                            <div class="col-xs-8 result-info-container">
                                <a href="<%=cpath%>/Perfil?id=<%= aux.getId() %>">
                                    <h4 class="result-name">
                                        <%= aux.getNombre()%>
                                        <!--<%= aux.getApellidos() %>-->
                                    </h4>
                                </a>
                                <p class="result-users">
                                    <a href="<%=cpath%>/Perfil?id=<%= aux.getId() %>">
                                        <small>@<%= aux.getNombreUsuario() %></small>
                                    </a>
                                </p>
                                <p class="result-info-jobs">
                                    <% //for (ExperienciaLaboral e : experiencia) { %>
                                    <% if(experiencia.size() > 0) { ExperienciaLaboral e = experiencia.get(0); %>
                                    <%=e.getFechaFinalizacion() == null ? "Trabaja como" : "Trabajó como"%> <%= e.getPuesto() %>
                                    en <%= e.getEmpresa() %>
                                    <br/>
                                    <%}%>

                                    <% //for (Estudios es : estudios) { %>
                                    <% if(estudios.size() > 0) { Estudios e = estudios.get(0); %>
                                    <%=e.getFechaFinalizacion() == null ? "Estudia" : "Estudió"%> <%= e.getDescripcion() %>
                                    en <%= e.getUbicacion() %>
                                    <%}%>
                                </p>
                            </div>

                            <div class="col-xs-4 result-photo-container">
                                <a href="<%=cpath%>/Perfil?id=<%= aux.getId() %> ">
                                    <img
                                            <% if(aux.getFoto() != null) { %> src="<%=aux.getFoto()%>" <% } else { %> src="<%@include file="snippets/fotoPerfil.txt"%>" <% } %>
                                            class="img-responsive result-photo" alt="Foto de perfil de <%=aux.getNombre() %>">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

            <%
                    }
                }
            %>
            </div>

            <%@include file="snippets/footer.jsp" %>
        </div>

        <%@include file="snippets/body-end.jsp" %>
    </body>
</html>
