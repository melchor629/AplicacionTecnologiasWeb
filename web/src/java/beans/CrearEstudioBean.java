/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.EstudiosFacade;
import app.ejb.UsuarioFacade;
import app.entity.Estudios;
import app.entity.Usuario;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author user
 */ 
@Named(value = "crearEstudioBean")
@RequestScoped
public class CrearEstudioBean implements Serializable {

    /**
     * Creates a new instance of CrearEstudioBean
     */
    
    private String msgError;
    private Date fechaComienzo;
    private Date fechaFinalizacion;
    private String descripcion;
    private String ubicacion;
    
      @EJB private EstudiosFacade estudiosFacade;
      @EJB private UsuarioFacade usuarioFacade;
      @Inject private SesionBean sesionBean;
      @Inject private PerfilBean perfilBean;

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public String crear() {
    //aquí compruebo que todo está bien, que las variables de crearestudio.xhtml
    //han sido creadas correctamente
    //mirar el servlet de francis para ver qué es obligatorio y qué no
    
    boolean todoCorrecto = true; //Y yo que me alegro :)
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String fComienzo ="";
    if (fechaComienzo != null) {
    fComienzo = sdf.format(fechaComienzo);
    } else {
    fComienzo = "";
    }
    
    String fFinal ="";
    if (fechaFinalizacion != null) {
    fFinal = sdf.format(fechaFinalizacion);
    } else {
    fFinal = "";
    }
    
    
    
        if (fComienzo.equals("")) {
            todoCorrecto = false;
        } 
        
        //Lo demás es opcional
       
     
     //si está bien, voy a perfil
        if( todoCorrecto ) {
            
            //¿Cómo averiguo el id del usuario? ¿Por dónde me lo pasan?
            //¿Por el session bean tal vez?
            Estudios e = new Estudios (sesionBean.obtenerUsuario().getId(),fechaComienzo);
           
             if (!fFinal.equals("")){
                 e.setFechaFinalizacion(fechaFinalizacion);
             } else {
                 e.setFechaFinalizacion(null);
             }

             if (descripcion.equals("")){
                 e.setDescripcion(null);
            } else {
                 e.setDescripcion(descripcion);
             }
             
             if (ubicacion.equals(null)) {
                 e.setUbicacion(null);
             } else {
                 e.setUbicacion(ubicacion);
             }
             
             Usuario funcionaplis = sesionBean.obtenerUsuario();
            
             funcionaplis.getEstudiosCollection().add(e);
             
             estudiosFacade.create(e);
            usuarioFacade.edit(funcionaplis);
           
            
            return "perfil?faces-redirect=true";
        } else { //y si no..
            //Esto indica que redirija a la página a la que está
            this.msgError = "Error: La fecha de comienzo no puede estar vacía";
            return null;
        }
    }
    
    
}
