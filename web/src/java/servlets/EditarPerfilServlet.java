package servlets;

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
 * @author Melchor Alejo Garau Madrigal
 * //Francisco Reyes Sánchez
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

        int error = 0; //1 si hay algun error, 0 si no hay errores
        HttpSession session = request.getSession();
        Usuario usuarioActualizado = (Usuario) session.getAttribute("usuario");

        if (usuarioActualizado != null) {

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
            if (!nombre.equals("")) {
                usuarioActualizado.setNombre(nombre);
            } else {
                //no puede estar vacio
                error = 2;
            }
            if (!apellidos.equals("")) {
                usuarioActualizado.setApellidos(apellidos);
            } else {
                error = 3;
            }

            usuarioActualizado.setTwitter(twitter.isEmpty() ? null : twitter);

            usuarioActualizado.setInstagram(instagram.isEmpty() ? null : instagram);

            usuarioActualizado.setWeb(web.isEmpty() ? null : web);

            usuarioActualizado.setFoto(foto.isEmpty() ? null : foto);

            if (!correo.equals("")) {
                usuarioActualizado.setCorreo(correo);
            } else {
                error = 4;
            }

            if (!nombreUsuario.equals("")) {
                usuarioActualizado.setNombreUsuario(nombreUsuario);
            } else {
                error = 5;
            }
            if (!password1.equals("") && !password2.equals("") && password1.equals(password2)) {

                String hash = app.cosas.Hash.hash(password1);
                usuarioActualizado.setContraseña(hash);
            } else if (!password1.equals(password2)) {
                //los campos de contraseña estan vacios o las contraseñas son incorrectas.
                error = 1;

            }

            u.edit(usuarioActualizado);

            if (error != 0) { //error las contraseñas no coinciden
                session.setAttribute("usuario", usuarioActualizado);
                response.sendRedirect(request.getContextPath() + "/editarPerfil.jsp?error=" + error);
            } else {

                session.setAttribute("usuario", usuarioActualizado);
                response.sendRedirect(request.getContextPath() + "/Perfil");

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
