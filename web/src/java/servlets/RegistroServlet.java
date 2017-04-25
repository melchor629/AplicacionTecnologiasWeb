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
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rodrii
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String nombre = request.getParameter("nombre");
     String apellidos = request.getParameter("apellidos");
     String twitter = request.getParameter("twitter");
     String instagram = request.getParameter("instagram");
     String web = request.getParameter("web");
     String nombreUsuario = request.getParameter("usuario");
     String contraseña1 = request.getParameter("password1");
     String contraseña2 = request.getParameter("password2");
     String foto = request.getParameter("foto");
     String correo = request.getParameter("correo");
     
     
     
     Usuario user = new Usuario();
     
     if(contraseña1.equals(contraseña2)){
        String contraseñasDiferentes = "Los valores de contraseña no coinciden"; 
        request.setAttribute("contraseñaDiferentes", contraseñasDiferentes );
        RequestDispatcher rd;
        rd = (RequestDispatcher) this.getServletContext().getRequestDispatcher("/registro.jsp");
        rd.forward(request, response);
        return;
     }
     
     //try{
        user.setNombre(nombre);
        user.setApellidos(apellidos);
        user.setTwitter(twitter);
        user.setInstagram(instagram);
        user.setCorreo(correo);
        user.setWeb(web);
        user.setFoto(foto);
        user.setContraseña(contraseña1);
        user.setNombre(nombreUsuario);
        
        
       // catch()
     //}
     
     u.insertarUsuario(user);
     
     HttpSession session = request.getSession();
     session.setAttribute("usuario", user);
     response.sendRedirect(request.getContextPath() + "/perfil.jsp");
     
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
