<%@page import="app.entity.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String cpath = request.getContextPath();
  Usuario u = (Usuario)session.getAttribute("usuario");
  if (u == null) {
        response.sendRedirect(cpath);
    } else {
      request.setAttribute("pagina", "notificaciones");
%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Panel de notificaciones - LinkedOut</title>
    <meta name="description" content="Panel de notificaciones"> <!-- TODO -->
    <%@include file="snippets/head.jsp"%>
  </head>

  <body>

    <%@include file="snippets/nav-logged.jsp"%>

    <div class="container">

        <%
            
            
            %>
            <%
                if(request.getParameter("info") != null && !request.getParameter("info").isEmpty()){
                    String mensaje = "S";
                    int numeroInfo = Integer.parseInt(request.getParameter("info"));
                    
                    switch(numeroInfo){
                        case 1:
                            mensaje = "Peticion de amistad aceptada con exito, ya sois amigos y puedes ver su perfil!";
                            break;
                            
                        case 2:
                            mensaje = "Peticion de amistad rechazada con exito...";
                            break;
                            
                        default:
                            break;
                    }
                %> 
                <div class="alert alert-success">
                    <%= mensaje %>
                </div>
        <%}
                %>
      <h1 class="page-header">Panel de notificaciones</h1>

      <h2 class="page-header">Peticiones de amistad <span class="badge">4</span></h2>
      
      <h2 class="page-header">Mensajes <span class="badge">4</span></h2>
      
      
      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>
<% } %>