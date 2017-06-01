/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.EstudiosFacade;
import app.ejb.ExperienciaLaboralFacade;
import app.ejb.UsuarioFacade;
import app.entity.Estudios;
import app.entity.ExperienciaLaboral;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@Named(value = "crearExperienciaBean")
//@Dependant CUIDAO!
@RequestScoped
public class CrearExperienciaBean implements Serializable{ //Serializable importante!

    /**
     * Creates a new instance of CrearExperienciaBean
     */
    private String msgError;
    private Date fechaComienzo;
    private Date fechaFinalizacion;
    private String empresa;
    private String puesto;
    private String webEmpresa;
    
      @EJB private ExperienciaLaboralFacade experienciaFacade;
      @EJB private UsuarioFacade usuarioFacade;
      @Inject private SesionBean sesionBean;

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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puestoAux) {
        this.puesto = puestoAux;
    }

    public String getWebEmpresa() {
        return webEmpresa;
    }

    public void setWebEmpresa(String webEmpresa) {
        this.webEmpresa = webEmpresa;
    }

    
    public SesionBean getSesionBean() {
        return sesionBean;
    }

    public void setSesionBean(SesionBean sesionBean) {
        this.sesionBean = sesionBean;
    }

   
    
    public String crear() {
        
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
    
    
    
        if (fComienzo.equals("") || empresa.equals("") || puesto.equals("")) {
            todoCorrecto = false;
        } 
        
        //Lo demás es opcional
       
     
     //si está bien, voy a perfil
        if( todoCorrecto ) {
            
            //¿Cómo averiguo el id del usuario? ¿Por dónde me lo pasan?
            //¿Por el session bean tal vez?
            
            ExperienciaLaboral e = new ExperienciaLaboral (sesionBean.obtenerUsuario().getId(),fechaComienzo);
            
             if (!fFinal.equals("")){
                 e.setFechaFinalizacion(fechaFinalizacion);
             } else {
                 e.setFechaFinalizacion(null);
             }

             if (empresa.equals("")){
                 e.setEmpresa(null);
            } else {
                 e.setEmpresa(empresa);
             }
             
             if (puesto.equals("")) {
                 e.setPuesto(null);
             } else {
                 e.setPuesto(puesto);
             }
             
             if (webEmpresa.equals("")) {
                 e.setWebEmpresa(null);
             } else {
                 e.setWebEmpresa(webEmpresa);
             }
             
            sesionBean.obtenerUsuario().getExperienciaLaboralCollection().add(e);
            usuarioFacade.edit(sesionBean.obtenerUsuario());
            experienciaFacade.create(e);
            
           // System.out.println("Exp laboral de:" + sesionBean.obtenerUsuario().getNombreUsuario() + sesionBean.obtenerUsuario().getExperienciaLaboralCollection().toString());
            
            return "perfil";
        } else { //y si no..
            //Esto indica que redirija a la página a la que está
            this.msgError = "Error: La fecha de comienzo, empresa y puesto no pueden estar vacíos";
            return null;
        }
    }
    
    

    
}
