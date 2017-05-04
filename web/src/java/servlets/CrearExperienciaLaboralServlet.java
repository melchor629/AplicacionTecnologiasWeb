/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import app.ejb.ExperienciaLaboralFacade;
import app.entity.ExperienciaLaboral;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CrearExperienciaLaboralServlet", urlPatterns = {"/CrearExperienciaLaboral"})
public class CrearExperienciaLaboralServlet extends HttpServlet {

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
     ExperienciaLaboralFacade fachada;
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
     
        DateFormat format= new SimpleDateFormat("dd/MM/yyyy");

         
        String id= (String)request.getParameter("id");
        String fechaComienzo = (String) request.getParameter("fechaComienzo");

        String puesto= (String) request.getParameter("puesto");
        String webEmpresa= (String) request.getParameter("webEmpresa");
         String empresa= (String) request.getParameter("empresa");
        String fechaFinalizacion = (String) request.getParameter("fechaFinalizacion");
        
        int error=0;

        
        if(!fechaComienzo.equals("") && !empresa.equals("") && !puesto.equals("")){
            
            ExperienciaLaboral e=null;
        try {
            e= new ExperienciaLaboral(Integer.parseInt(id), format.parse(fechaComienzo));
            
        } catch (ParseException ex) {
            Logger.getLogger(EditarEstudioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!fechaFinalizacion.equals("")){
            
        try {
            e.setFechaFinalizacion(format.parse(fechaFinalizacion));
        } catch (ParseException ex) {
            Logger.getLogger(EditarEstudioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }else{
            e.setFechaFinalizacion(null);
        }
        
        
        e.setEmpresa(empresa);
        
           
        
        
          if(!webEmpresa.equals("")){
             e.setWebEmpresa(webEmpresa);
        }else{
            e.setWebEmpresa(null);
        }
          
           
        e.setPuesto(puesto);
        
        
            
           fachada.create(e);//peta
            fachada.edit(e);
        }else{
            
                error=1;
            
            
            
        }
        
      
        
        
        if(error!=0){ //error 
            
             response.sendRedirect(request.getContextPath() + "/crearExperienciaLaboral.jsp?error="+error);
        }else{
            
            response.sendRedirect(request.getContextPath() + "/Perfil");

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
