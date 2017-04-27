package servlets;

import app.ejb.AficionesFacade;
import app.ejb.EstudiosFacade;
import app.ejb.ExperienciaLaboralFacade;
import app.ejb.UsuarioFacade;
import app.entity.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/Main"})
public class MainServlet extends HttpServlet {

    @EJB
    private UsuarioFacade u;
    @EJB
    private AficionesFacade fachadaAficiones;
    @EJB
    private EstudiosFacade fachadaEstudios;
    @EJB
    private ExperienciaLaboralFacade fachadaTrabajos;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        Usuario uLogged = null;
        try {
           uLogged = u.obtenerUsuario(usuario, password);
        } catch (Exception e) {
            response.getOutputStream().println("ERROR AL OBTENER  USUARIO");
            e.printStackTrace();
            return;
        }
        
        if (uLogged == null) {
            RequestDispatcher rd;
            rd = (RequestDispatcher) this.getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            return;
        } else {
        
                HttpSession session = request.getSession();
        
                    session.setAttribute("usuario", uLogged);
                    session.setAttribute("listaAficiones", fachadaAficiones);
                    session.setAttribute("listaEstudios", fachadaEstudios);
                    session.setAttribute("listaTrabajos", fachadaTrabajos);
                    response.sendRedirect(request.getContextPath() + "/perfil.jsp");
        }
        
    }
    
 }
