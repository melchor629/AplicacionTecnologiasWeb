/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import app.ejb.UsuarioFacade;
import app.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lucia y Francis
 */
@WebServlet(name = "EditarPerfilServlet", urlPatterns = {"/EditarPerfil"})
public class EditarPerfilServlet extends HttpServlet {

    
     @EJB
    private UsuarioFacade u;
     
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

        Usuario usuarioActualizado = (Usuario) session.getAttribute("usuario");
        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String twitter = request.getParameter("twitter");
        String instagram = request.getParameter("instagram");
        String web = request.getParameter("web");
        String foto = request.getParameter("foto");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String correo = request.getParameter("correo");
      
        //Comprobar que ambas password son iguales.
        usuarioActualizado.setNombre(nombre);
        usuarioActualizado.setApellidos(apellidos);
        usuarioActualizado.setTwitter(twitter);
        usuarioActualizado.setInstagram(instagram);
        usuarioActualizado.setWeb(web);
        usuarioActualizado.setFoto(foto);
        usuarioActualizado.setNombreUsuario(nombreUsuario);
        usuarioActualizado.setContraseña(password1);
        usuarioActualizado.setCorreo(correo);
        
       u.edit(usuarioActualizado);
        
       session.setAttribute("usuario", usuarioActualizado);
       response.sendRedirect(request.getContextPath() + "/Perfil");
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
