/**
 *
 * @author Rodrigo Represa Represa
 */

package servlets;

import app.ejb.UsuarioFacade;
import app.entity.Usuario;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
     String nombreUsuario = request.getParameter("nombreUsuario");
     String contraseña1 = request.getParameter("password1");
     String contraseña2 = request.getParameter("password2");
     String correo = request.getParameter("correo");
     String almacheck = request.getParameter("almacheck");
     String datoscheck = request.getParameter("datoscheck");
     //String datoscheck = request.getParameter("datoschek");
     String error = "";
     
     if(nombre.isEmpty()){
         error = "Campo Nombre obligatorio";
         lanzarError(error, request, response);
     }else if(apellidos.isEmpty()){
         error = "Campo Apellidos obligatorio";
         lanzarError(error, request, response);
     }else if( nombreUsuario.isEmpty()){
         error = "Campo Nombre de Usuario obligatorio";
         lanzarError(error, request, response);
     }else if(contraseña1.isEmpty()){
         error = "Campo Contraseña obligatorio";
         lanzarError(error, request, response);
     }else if(contraseña2.isEmpty()){
         error = "Debe volver a escribir la contraseña";
         lanzarError(error, request, response);
     }else if(correo.isEmpty()){
         error = "Campo Correo obligatorio";
         lanzarError(error, request, response);
     }else if(almacheck == null || datoscheck == null ){
        error = "Debe aceptar los términos";
        lanzarError(error, request, response);
     }else{
         if(contraseña1.equals(contraseña2)){
            try{
                Usuario user = new Usuario(Integer.SIZE, nombre, apellidos, nombreUsuario, contraseña2, correo);
                u.create(user);
                HttpSession session = request.getSession();
                session.setAttribute("usuario", user);
                response.sendRedirect(request.getContextPath() + "/perfil.jsp");
            }catch(EJBException e){
                lanzarError("Usuario o correo en uso", request, response);
            }

        }else{
            String contraseñasDiferentes = "Los valores de contraseña no coinciden"; 
            lanzarError(contraseñasDiferentes, request, response);
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



private void lanzarError(String error,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("error", error );
        RequestDispatcher rd;
        rd = (RequestDispatcher) this.getServletContext().getRequestDispatcher("/registro.jsp");
        rd.forward(request, response);
        
}

}