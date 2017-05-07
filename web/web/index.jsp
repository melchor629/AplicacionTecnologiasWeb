<% //Melchor Alejo Garau Madrigal %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String cpath = request.getContextPath();

if(session.getAttribute("usuario") != null){
    response.sendRedirect(cpath+"/perfil.jsp");
}
else{
%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>LinkedOut</title>
    <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
    <%@include file="snippets/head.jsp"%>
  </head>

  <body>

    <%@include file="snippets/nav-unlogged.jsp"%>

    <div class="jumbotron full-height">
      <div class="container">
        <div class="row">
          <div class="col-sm-4">
            <img src="<%=cpath%>/assets/favicon/Logotipo%20melsho%20reducido.png" style="width:100%">
          </div>
          <div class="col-sm-8">
            <h1>Hoy puedes tomar una buena decisión</h1>
            <p class="lead">Tienes la oportunidad de unirte a una gran red social profesional</p>
            <p><a class="btn btn-primary btn-lg btn-raised" href="<%=cpath%>/registro.jsp" role="button">Regístrate ahora</a></p>
          </div>
        </div>
      </div>
    </div>

    <div class="full-height" style="background-color: rgb(255, 210, 190)">
      <div class="container">
        Algo de texto con alguna cosa de fondo
      </div>
    </div>

    <div class="full-height" style="background-color: rgb(190, 210, 255)">
      <div class="container">
        Algo de más texto con alguna otra cosa de fondo
      </div>
    </div>

    <div class="container main-page">

      <%@include file="snippets/footer.jsp"%>
    </div>

    <%@include file="snippets/body-end.jsp"%>
    <% if(request.getAttribute("error") != null) { %>
    <script>
      $(function() {
          if($(window).width() < 768) $('.navbar-header').find('button').click();
          setTimeout(function() {
              $('.navbar-form').popover({
                  content: 'El usuario o la contraseña son incorrectas',
                  placement: 'bottom',
                  trigger: 'manual'
              }).popover('show');
          }, 0);
      });
    </script>
    <% } %>
  </body>
</html>
<% } %>
