

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.EstudiosFacade;
import app.ejb.UsuarioFacade;
import app.entity.Estudios;
import app.entity.EstudiosPK;
import app.entity.Usuario;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@Named(value = "editarEstudioBean")
@RequestScoped
public class EditarEstudioBean implements Serializable {
    
    private String ubicacion;
    private String descripcion;
    private Date fechaComienzo;
    private Date fechaFinalizacion;
    private Estudios estudio;
    private String msgError;
    
    @EJB
    private EstudiosFacade estudiosFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    
    @Inject
    private SesionBean sesionBean;
    @Inject
    private PerfilBean perfilBean;
    private String fechaComienzoStr;
    
    @PostConstruct
    public void init () {
        if (estudio == null) { //y si compruebo si el hidden es null? :-/
           
           
           estudio = new Estudios();
           //fechaComienzo = null;
           //fechaComienzoStr="";
          
        } 
        
        
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public void setEstudio(Estudios estudio) {
        this.estudio = estudio;
    }

    public Estudios getEstudio() {
        return estudio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    public String goEdit (Estudios e) {
        estudio = e;        
        return "editarEstudio.jsf";
    }
    
    

    public String editar (){
        //Suponemos que me pasan todo bien
        
        //if (!fechaComienzoStr.equals("")) {
            SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy");
            try {
                estudio = estudiosFacade.obtenerEstudio(sesionBean.getUsuarioID(), sdf2.parse(fechaComienzoStr));
            } catch (ParseException ex) {
                Logger.getLogger(EditarEstudioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
       // }
        
        estudio = estudiosFacade.obtenerEstudio(sesionBean.getUsuarioID(), fechaComienzo);
        
        if (fechaComienzo.equals(estudio.getEstudiosPK().getFechaComienzo())) {
            Usuario nUser = sesionBean.obtenerUsuario();
            //actualizar colección? 
            estudiosFacade.edit(estudio);
            
            return "perfil";
        } else { //si la pk no es igual, creo de nuevo el objeto
            
            estudiosFacade.borrarEstudio(sesionBean.obtenerUsuario().getId(), estudio.getEstudiosPK().getFechaComienzo());
            
            Estudios nEstudio = new Estudios(sesionBean.obtenerUsuario().getId(), fechaComienzo);
            
            nEstudio.setDescripcion(estudio.getDescripcion());
            nEstudio.setFechaFinalizacion(estudio.getFechaFinalizacion());
            nEstudio.setUbicacion(estudio.getUbicacion());
            
             
            Usuario funcionaplis = sesionBean.obtenerUsuario();
            funcionaplis.getEstudiosCollection().add(nEstudio);
                     
            
            estudiosFacade.create(nEstudio);
            usuarioFacade.edit(funcionaplis);
            
            return "perfil";
            
        }   
    }
    
  /* @PostConstruct
    public void trucarBean () {
       Estudios it = sesionBean.obtenerUsuario().getEstudiosCollection().iterator().next(); 
       
       estudio = it;
       fechaComienzo = estudio.getEstudiosPK().getFechaComienzo()
       
        
    }
    */

    public String getFechaComienzoStr() {
        return fechaComienzoStr;
    }

    public void setFechaComienzoStr(String fechaComienzoStr) {
        this.fechaComienzoStr = fechaComienzoStr;
    }
    
    
    
     public String goEditEstudio(Estudios e) {
        estudio = e;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        fechaComienzo = estudio.getEstudiosPK().getFechaComienzo();
        fechaComienzoStr = sdf.format(estudio.getEstudiosPK().getFechaComienzo());
        return "editarEstudio.jsf";
    }
    
}
