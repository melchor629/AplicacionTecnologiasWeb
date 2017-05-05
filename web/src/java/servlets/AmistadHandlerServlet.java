/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import app.ejb.PeticionAmistadFacade;
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
 * @author user
 */
@WebServlet(name = "AmistadHandlerServlet", urlPatterns = {"/AmistadHandlerServlet"})
public class AmistadHandlerServlet extends HttpServlet {
    
    @EJB
    PeticionAmistadFacade fachadaPeticionAmistad;
    
    @EJB
    UsuarioFacade fachadaUsuario;

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
        String cpath = request.getContextPath();
        boolean error = false; // Controla si ha habido un error para hacer la correspondiente redireccion
        String cadenaError = "Ha habido un error inesperado... has modificado los parametros de la url a mano?";
        
        HttpSession session = request.getSession();
        
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        
        if (usuarioLogueado == null) {
            error = true;
            cadenaError = "Sesion no iniciada";
        } else {
            try {
                
                String accion = (String) request.getParameter("tipo");
                Integer iduser = Integer.parseInt(request.getParameter("iduser"));
                
                if (accion.equals("aceptar")) {
                    // Aceptar la solicitud de amistad de un usuario
                    // comprobar que el usuario al que la envias no es ya tu amigo 
                   
                    if (fachadaUsuario.sonAmigos(usuarioLogueado.getId(), iduser)) {
                        error = true;
                        cadenaError = "Has intentado aceptar la petición de un usuario que ya es tu amigo.";
                        
                    } else {
                        fachadaUsuario.aceptarPeticionAmistad(iduser, usuarioLogueado.getId());
                        fachadaPeticionAmistad.eliminarPeticion(iduser, usuarioLogueado.getId());
                        session.setAttribute("usuario", fachadaUsuario.obtenerUsuarioPorId(usuarioLogueado.getId()));
                        System.out.println(usuarioLogueado.getNombreUsuario() + " ha aceptado a " + fachadaUsuario.obtenerUsuarioPorId(iduser).getNombreUsuario());
                    }
                    
                } else if (accion.equals("rechazar")) {
                    fachadaPeticionAmistad.eliminarPeticion(iduser, usuarioLogueado.getId());
                    session.setAttribute("usuario", fachadaUsuario.obtenerUsuarioPorId(usuarioLogueado.getId()));
                    System.out.println(usuarioLogueado.getNombreUsuario() + " ha rechazado a " + fachadaUsuario.obtenerUsuarioPorId(iduser).getNombreUsuario());
                }
                
                response.sendRedirect(cpath + "/notificaciones.jsp");
            } catch (Exception E) {
                error = true;
            }
        }
        if (error) {
            
            if (cadenaError.equals("Sesion no iniciada")) {
                // redireccion a pagina principal
                response.sendRedirect(cpath);
            } else {
                // Se lanza excepcion indicando el error
                throw new RuntimeException(cadenaError);
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