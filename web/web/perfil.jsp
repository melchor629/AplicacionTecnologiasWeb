<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="app.entity.Usuario"%>
<%
    String cpath = request.getContextPath();
    Usuario u = (Usuario)session.getAttribute("usuario");

    if(request.getAttribute("otroUsuario") != null){
        u = (Usuario) request.getAttribute("otroUsuario");
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Perfil de <%=u.getNombre()%> - LinkedOut</title>
        <meta name="description" content="Descripcion de la pagina"> <!-- TODO -->
        <%@include file="snippets/head.jsp"%>
    </head>

    <body>

        <%@include file="snippets/nav-logged.jsp"%>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Perfil de usuario</h1>

                    <div class="row placeholders">
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <img src="<%=u.getFoto()%>" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                            <h4><%=u.getNombre()%></h4>
                            <span class="text-muted"><%=u.getApellidos() %></span>
                        </div>
                    </div>

                    

                    <div class="itemPerfil">
                        Nombre:
                        <p><%=u.getNombre()%></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Apellidos
                        <p><%=u.getApellidos()%></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Twitter:
                        <p><a href="https://twitter.com/<%=u.getTwitter()%>" target="_blank"><%=u.getTwitter()%></a></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Instagram:
                        <p><%=u.getInstagram()%></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Página web:
                        <p><a href="<%=u.getWeb()%>" target="_blank"><%=u.getWeb()%></a></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Nombre de usuario:
                        <p><%=u.getNombreUsuario()%></p>
                    </div>
                    
                    <div class="itemPerfil">
                        Correo electrónico:
                        <p><a href="mailto:<%=u.getCorreo()%>"><%=u.getCorreo()%></a></p>
                    </div>
                </div>
            </div>
            <%@include file="snippets/footer.jsp"%>
        </div>


    <%@include file="snippets/body-end.jsp"%>
</body>
</html>
