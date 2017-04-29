package servlets;

import app.ejb.MensajeFacade;
import app.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author antonio
 */
@WebServlet(name = "Mensaje", urlPatterns = {"/Mensaje"})
public class MensajeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB
    MensajeFacade fachadaMensajes;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         HttpSession session = request.getSession();

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

        if(usuarioLogueado == null){
            response.sendRedirect(request.getContextPath() + "/");
        }
        else{
        Integer idPara = Integer.parseInt(request.getParameter("idHacia"));
        String titulo = request.getParameter("titulo");
        String mensaje = request.getParameter("mensaje");
        
        if(titulo.isEmpty() || mensaje.isEmpty()){
            // Redireccion con error
            response.sendRedirect(request.getContextPath() + "/Perfil?id="+idPara+"&error=1");
        }
        else{
        // Insertar mensaje en la base de datos
        fachadaMensajes.crearMensaje(usuarioLogueado.getId(), idPara, titulo, mensaje);
        
        // Redireccionar con exito al perfil
        response.sendRedirect(request.getContextPath() + "/Perfil?id="+idPara+"&exito=1");
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
