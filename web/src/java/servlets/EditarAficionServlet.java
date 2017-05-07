/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Francisco Reyes Sánchez
package servlets;

import app.ejb.AficionesFacade;
import app.ejb.UsuarioFacade;
import app.entity.Aficiones;
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
 * @author Lucia y Francis
 */
@WebServlet(name = "EditarAficion", urlPatterns = {"/EditarAficion"})
public class EditarAficionServlet extends HttpServlet {

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
    AficionesFacade fachadaAficiones;
    @EJB
    UsuarioFacade fachadaUsuario;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

        if (usuarioLogueado != null) {

            String id = (String) request.getParameter("id");
            String nombre = (String) request.getParameter("nombre");
            String nombreOriginal = (String) request.getParameter("nombreOriginal");

            
                Aficiones a = fachadaAficiones.obtenerAficionConIdyNombre(Integer.parseInt(id), nombreOriginal);
                fachadaAficiones.borrarAficion(Integer.parseInt(id), nombreOriginal);
                usuarioLogueado.getAficionesCollection().remove(a);
                Aficiones nuevaAficion = new Aficiones(Integer.parseInt(id), nombre);
                usuarioLogueado.getAficionesCollection().add(nuevaAficion);
                fachadaUsuario.edit(usuarioLogueado);
                app.entity.Usuario u= fachadaUsuario.obtenerUsuarioPorId(usuarioLogueado.getId());
                session.setAttribute("usuario", u);
                        
           

            response.sendRedirect(request.getContextPath() + "/Perfil");
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
