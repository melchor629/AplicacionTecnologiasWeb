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
      %>
      
          
            <li><%=nuser%></li>
            
            <%}
            }%>
            
      </ol>
            

      <%@include file="snippets/footer.jsp"%>
    </div>
  
    <%@include file="snippets/body-end.jsp"%>
  </body>
</html>
