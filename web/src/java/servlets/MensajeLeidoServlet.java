package servlets;

import app.ejb.MensajeFacade;
import app.ejb.UsuarioFacade;
import app.entity.Usuario;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Rodrigo Represa Represa
 * @author Melchor Alejo Garau Madrigal
 */
@WebServlet(name = "MensajeLeidoServlet", urlPatterns = {"/MensajeLeidoServlet"})
public class MensajeLeidoServlet extends HttpServlet {

    
    @EJB
    private MensajeFacade m;
    @EJB
    private UsuarioFacade u ;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

        if(usuarioLogueado != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            int emisor = Integer.parseInt(request.getParameter("emisor"));
            m.marcarLeido(id);

            Usuario prueba = u.obtenerUsuarioPorId(usuarioLogueado.getId());
            session.setAttribute("usuario", prueba);
            if (request.getParameter("descartar") == null) {
                response.sendRedirect(request.getContextPath() + "/Perfil?id=" + emisor);
            } else {
                response.sendRedirect(request.getContextPath() + "/notificaciones.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
