<%@page import="app.entity.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String cpath = request.getContextPath();
  Usuario u = (Usuario)session.getAttribute("usuario");
  if (u == null) {
        response.sendRedirect(cpath);
    } else {
      request.setAttribute("pagina", "notificaciones");


  Collection<Mensaje> mensajes = u.getMensajeCollection1();
  Collection<Mensaje> mensajesNo = new ArrayList<Mensaje>();

  for(Mensaje m : mensajes){
      if(!m.getLeido()){
          mensajesNo.add(m);
      }
  }
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

        <div class="col-sm-6">
      <h2 class="page-header">Peticiones de amistad <span class="badge">


              <!--Se supone que esto debe retornar el número de peticiones que me han enviado-->
              <%= u.getPeticionAmistadCollection1().size() %>



      </span></h2>

            <% for (PeticionAmistad p : u.getPeticionAmistadCollection1()) { %>
            <div class="peticion-amigo row">
                <div class="col-xs-3 col-sm-2 col-lg-2">
                    <div class="img-circle img-profile"
                            <% if(p.getUsuario().getFoto() != null) { %>
                         style="background-image: url('<%=p.getUsuario().getFoto()%>')"
                            <% } else { %>
                         style="background-image: url('<%@include file="snippets/fotoPerfil.txt"%>')"
                            <% } %>
                    ></div>
                </div>
                <div class="col-xs-9 col-sm-10 col-lg-10">
                    <h3>@<%= p.getUsuario().getNombreUsuario()%></h3>
                </div>

                <div class="col-xs-12 forms">
                    <div class="clearfix">
                        <form id="rechazar-<%=p.getUsuario().getId()%>" class="pull-right form-horizontal" role="form" action="<%= cpath%>/AmistadHandlerServlet" method="POST">
                            <input type="hidden" name="tipo" value="rechazar">
                            <input type="hidden" name="iduser" value="<%=p.getUsuario().getId()%>">
                            <button type="submit" form="rechazar-<%=p.getUsuario().getId()%>" class="btn btn-default btn-raised">
                                <i class="fa fa-user-times"></i> Rechazar
                            </button>
                        </form>

                        <form id="aceptar-<%=p.getUsuario().getId()%>" class="pull-right form-horizontal" role="form" action="<%= cpath%>/AmistadHandlerServlet" method="POST">
                            <input type="hidden" name="tipo" value="aceptar">
                            <input type="hidden" name="iduser" value="<%=p.getUsuario().getId()%>">
                            <button type="submit" form="aceptar-<%=p.getUsuario().getId()%>" class="btn btn-primary btn-raised">
                                <i class="fa fa-check"></i> Aceptar
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <% } %>

        </div>

      
      <h2 class="page-header">Mensajes <span class="badge"><%= mensajesNo.size()%></span></h2>



      <% for (Mensaje mn : mensajesNo){ %>
            <p> <%= mn.getIdEmisor().getNombreUsuario() %>
                <%= mn.getTitulo()%>
                <%= mn.getTexto()%>
                <div class="text-right">
                    <a type="submit" class="btn btn-primary btn-raised" href="<%=cpath%>/MensajeLeidoServlet?id=<%= mn.getId()%>&emisor=<%=mn.getIdEmisor().getId()%>">RESPONDER</a>
                </div>
            </p>

      <% } %>

      
      
      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>
<% } %>