/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import app.ejb.UsuarioFacade;
import app.entity.Usuario;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author user
 */
@WebServlet(name = "BuscarServlet", urlPatterns = {"/BuscarServlet"})
public class BuscarServlet extends HttpServlet {

    @EJB
    private UsuarioFacade u;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buscar = request.getParameter("buscar");
        
        List <Usuario> lista;
        
            lista = u.buscarUsuarios(buscar);
              
               // HttpSession session = request.getSession();
                request.setAttribute("resultados",lista);
                
                RequestDispatcher rd;
        
                rd = this.getServletContext().getRequestDispatcher("/busqueda.jsp");
                rd.forward(request, response);
                
        /*  if (!lista.isEmpty()){
            for (Usuario aux : lista) {
                System.out.println("HOLA SOY UN USER Y ESTOY EN EL SERVLET: " + aux.getNombreUsuario()); 
            
            }
        }*/
               // response.sendRedirect(request.getContextPath() + "/busqueda.jsp");
    
        
    }
    
 }