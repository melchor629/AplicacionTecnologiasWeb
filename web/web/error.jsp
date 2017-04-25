<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String cpath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title>Error - LinkedOut</title>
    <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
    <%@include file="snippets/head.jsp"%>
  </head>

  <body>

    <%@include file="snippets/nav-logged.jsp"%>

    <div class="container">
        <%
        String error = "Error desconocido";
        
        if(request.getAttribute("error") != null){
            error = (String) request.getAttribute("error");
        }
        %>
        <h1>Ha habido un error...:</h1>
        <div class="bg-danger"><%= error %></div>
        
      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>
