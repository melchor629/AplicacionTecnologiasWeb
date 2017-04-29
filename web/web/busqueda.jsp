<%@page import="app.entity.Estudios"%>
<%@page import="java.util.Collection"%>
<%@page import="app.entity.ExperienciaLaboral"%>
<%@page import="java.util.List"%>
<%@page import="app.entity.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>? - LinkedOut</title>
    <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
    <%@include file="snippets/head.jsp"%>
  </head>

  <body>

    <%@include file="snippets/nav-logged.jsp"%>

    <div class="container">
        
        <br></br>
        <br></br>
        <ol>
      <% List <Usuario> usuarios = (List<Usuario>)request.getAttribute("resultados");
                //System.out.println("HOLA LLEGO AL JSP AHORA");
        if (!usuarios.isEmpty()){
            for (Usuario aux : usuarios) {
                   // System.out.println("USERNAME ENCONTRADO: " + aux.getNombreUsuario());
                   String nuser = aux.getNombreUsuario();
                   Collection<ExperienciaLaboral> experiencia = aux.getExperienciaLaboralCollection();
                   Collection<Estudios> estudios = aux.getEstudiosCollection();
      %>
      
      
            <div class="panel panel-default">
            <div class="panel-body">
            <a href= "<%=cpath%>/Perfil?id=<%= aux.getId() %> "><img src="<%=aux.getFoto()%>" class="img-responsive profile-photo" alt="Foto de perfil de <%=aux.getNombre() %>" ></a>
            <li> <a href= "<%=cpath%>/Perfil?id=<%= aux.getId() %> "> <%= aux.getNombreUsuario() %> </a></li>
            <li><%= aux.getNombre()%></li>
            <li><%= aux.getApellidos() %></li>
            <li><% for( ExperienciaLaboral e : experiencia){ %>
                    Empresa: <%= e.getEmpresa() %>
                    Puesto: <%= e.getPuesto() %>
                <%}%>
            </li>
            <li><% for( Estudios  es : estudios){ %>
                    Donde estudió: <%= es.getUbicacion() %>
                    Estudios: <%= es.getDescripcion() %>
                <%}%>
            </li>
            <li><%= aux.getCorreo() %></li>
            </div>
            </div>
            
            
            
            <%}
            }%>
            
      </ol>
            

      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>
