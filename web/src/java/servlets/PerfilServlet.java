package servlets;

import app.ejb.UsuarioFacade;
import app.entity.Usuario;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PerfilServlet", urlPatterns = {"/Perfil"})
public class PerfilServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    @EJB
    private UsuarioFacade fachadaUsuario;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

        if(usuarioLogueado == null){
            response.sendRedirect(request.getContextPath() + "/");
        }
        else{
        String idUsuario = request.getParameter("id");

        if (idUsuario == null || idUsuario.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/perfil.jsp");
        } else {
            int id = Integer.parseInt(idUsuario);
            Usuario usuario = fachadaUsuario.obtenerUsuarioPorId(id);
            request.setAttribute("otroUsuario", usuario);
            RequestDispatcher rd;

            if (usuario != null) {
                request.setAttribute("amigos", fachadaUsuario.sonAmigos(usuario.getId(), usuarioLogueado.getId()));
                rd = this.getServletContext().getRequestDispatcher("/perfil.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("error", "El usuario solicitado no existe");
                rd = this.getServletContext().getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
            }

        }
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
