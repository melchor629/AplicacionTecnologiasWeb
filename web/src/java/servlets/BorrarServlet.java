/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import app.ejb.AficionesFacade;
import app.ejb.EstudiosFacade;
import app.ejb.ExperienciaLaboralFacade;
import app.entity.Usuario;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Antonio Ángel Cruzado Castillo
 *
 * ¡¡ Importante !!
 *
 * Los borrados son delicados por el tema de las referencias. Un objeto no se
 * borra al no ser que su referencia sea null.
 *
 * De forma que cuando empleamos JPA y hacemos remove se nos garantiza que la
 * entidad se borra de la BD (normalmente la fila correspondiente).
 *
 * Pero NO ser borra de la aplicación!! Si seguimos manteniendo la referencia a
 * la instancia, esta seguirá apareciendo!! Es por ello que desde este servlet
 * hay que borrarla para que deje de aparecer
 *
 */
@WebServlet(name = "Borrar", urlPatterns = {"/Borrar"})
public class BorrarServlet extends HttpServlet {

    @EJB
    private ExperienciaLaboralFacade fachadaExperienciaLaboral;

    @EJB
    private EstudiosFacade fachadaEstudios;

    @EJB
    private AficionesFacade fachadaAficiones;

    /**
     *
     * Servlet que permite borrar cualquier cosa que se le pase
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cpath = request.getContextPath();
        HttpSession session = request.getSession();

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
        String numeroParametroPerfil = "0";

        if (usuarioLogueado != null) {
            // Usuario logueado dependiendo de la accion que quiere hacer
            // se necesitaran unos datos u otros
            // Acciones:
            // 1: Borrar Experiencia Laboral
            // 2: Borrar Estudio
            // 3: Borrar Aficion

            // Descripcion del posible error que haya podido ocurrir
            String cadenaError = "";
            // Si ha habido un error o no
            boolean hayError = false;

            if (request.getParameter("accion") != null && !request.getParameter("accion").isEmpty()) {
                // Obtener la accion demandada
                Integer accion = Integer.parseInt(request.getParameter("accion"));
                Integer idUsuario = usuarioLogueado.getId();

                switch (accion) {
                    // Si es 1 se quiere borrar la experiencia laboral
                    // La clave primaria esta formada por:
                    // idUsuario
                    // FechaComienzo
                    case 1:
                        // comprobar que se reciben los parametros y que la ideDeUsuario coincide con la del que ha iniciado sesion
                        if (request.getParameter("fechaComienzo") != null && !request.getParameter("fechaComienzo").isEmpty()) {
                            // Se reciben adecuadamente los datos, obtenerlos
                            String fechaURL = request.getParameter("fechaComienzo");
                            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date fecha = format.parse(fechaURL);

                                // Se procede a borrar la experiencia laboral
                                // Del objeto de sesion
                                usuarioLogueado.getExperienciaLaboralCollection().remove(fachadaExperienciaLaboral.obtenerExperienciaLaboral(idUsuario, fecha));
                                // De la BD
                                fachadaExperienciaLaboral.borrarExperienciaLaboral(idUsuario, fecha);
                                numeroParametroPerfil = "1";
                            } catch (ParseException ex) {
                                hayError = true;
                                cadenaError = "Formato de fecha pasada no valido [/Borrar]";
                            }

                        } // Faltan datos
                        else {
                            hayError = true;
                            cadenaError = "Faltan datos o mal formato o nombres incorrectos en el QueryString [/Borrar]";
                        }

                        break;
                    /*
                        Si la accion es 2 se quiere borrar el estudio
                        Datos de la clave primaria:
                        idUsuario
                        fechaComienzo
                     */
                    case 2:
                        // comprobar que se reciben los parametros y que la ideDeUsuario coincide con la del que ha iniciado sesion
                        if (request.getParameter("fechaComienzo") != null && !request.getParameter("fechaComienzo").isEmpty()) {
                            // Se reciben adecuadamente los datos, obtenerlos
                            String fechaURL = request.getParameter("fechaComienzo");
                            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date fecha = format.parse(fechaURL);

                                // Se procede a borrar la formacion
                                // Del objeto de sesion
                                usuarioLogueado.getEstudiosCollection().remove(fachadaEstudios.obtenerEstudio(idUsuario, fecha));
                                // De la BD
                                fachadaEstudios.borrarEstudio(idUsuario, fecha);
                                numeroParametroPerfil = "2";
                            } catch (ParseException ex) {
                                hayError = true;
                                cadenaError = "Formato de fecha pasada no valido [/Borrar]";
                            }

                        } // Faltan datos
                        else {
                            hayError = true;
                            cadenaError = "Faltan datos o mal formato o nombres incorrectos en el QueryString [/Borrar]";
                        }
                        break;

                    // La accion numero 3 corresponde a borrar una aficion, para ello se requiere:
                    // datos de clave primaria:
                    // idUsuario
                    // Nombre aficion
                    case 3:
                        // comprobar que se reciben los parametros y que la ideDeUsuario coincide con la del que ha iniciado sesion
                        if (request.getParameter("nombreAficion") != null && !request.getParameter("nombreAficion").isEmpty()) {
                            // Se reciben adecuadamente los datos, obtenerlos
                            String textoAficion = request.getParameter("nombreAficion");

                            // Se procede a borrar la aficion
                            // Del objeto de sesion
                            usuarioLogueado.getAficionesCollection().remove(fachadaAficiones.obtenerAficionConIdyNombre(idUsuario, textoAficion));
                            // de la BD
                            fachadaAficiones.borrarAficion(idUsuario, textoAficion);
                            numeroParametroPerfil = "3";
                        } // Faltan datos
                        else {
                            hayError = true;
                            cadenaError = "Faltan datos o mal formato o nombres incorrectos en el QueryString [/Borrar]";
                        }
                        break;

                    default:
                        hayError = true;
                        cadenaError = "Accion especificada no valida [/Borrar]";
                }

            } else {
                hayError = true;
                cadenaError = "No se ha proporcionado ninguna accion que hacer [/Borrar]";
            }

            if (hayError) {
                /*request.setAttribute("error", cadenaError);
                RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/error.jsp");
                rd.forward(request, response); */
                throw new RuntimeException(cadenaError);
            } else {
                response.sendRedirect(request.getContextPath() + "/perfil.jsp?exito=" + numeroParametroPerfil);
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
