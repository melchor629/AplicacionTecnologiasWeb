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
 * @author antonio
 */
@WebServlet(name = "peticionAmistad", urlPatterns = {"/peticionAmistad"})
public class peticionAmistad extends HttpServlet {

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
        
        if(usuarioLogueado == null){
           error = true;
           cadenaError = "Sesion no iniciada";
        }
        else{
        try{
        Integer accion = Integer.parseInt(request.getParameter("accion"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        
        if(accion == 1){
            // Enviar solicitud de amistad a usuario
            // comprobar que el usuario al que la envias no es ya tu amigo ni tu mismo ni le has mandado la peticion ya
            if(id == usuarioLogueado.getId() || fachadaUsuario.sonAmigos(usuarioLogueado.getId(), id) || fachadaPeticionAmistad.peticionMandada(usuarioLogueado.getId(), id)){
                error = true;
                cadenaError = "Has intentado mandarte peticion a ti mismo, a un amigo tuyo o alguien que se la has mandado";
            }
            else{
                fachadaPeticionAmistad.mandarPeticionAmistad(usuarioLogueado.getId(), id, "El usuario "+usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos()+" te quiere stalkear");
            }
            
        } else if(accion == 2){
            // Eliminar amistad de usuario
            // Para eliminar a un usuario debes ser amigo suyo y no puedes ser tu mismo
            if(id != usuarioLogueado.getId() && fachadaUsuario.sonAmigos(usuarioLogueado.getId(), id)){
                fachadaUsuario.borrarAmistad(usuarioLogueado.getId(), id);
            }
            else{
                error = true;
                cadenaError = "Error al borrar amistad: No puedes borrarte a ti mismo ni a alguien que no es tu amigo";
            }
        }
        
        response.sendRedirect(cpath+"/Perfil?id="+id);
        }
        catch(Exception E){
            error = true;
        }
        }
        if (error) {
            request.setAttribute("error", cadenaError);
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
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
