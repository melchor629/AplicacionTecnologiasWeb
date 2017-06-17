/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.UsuarioFacade;
import app.entity.Mensaje;
import app.entity.PeticionAmistad;
import app.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author user
 */
@Named(value = "notificacionesBean")
@RequestScoped
public class NotificacionesBean {

    /**
     * Creates a new instance of NotificacionesBean
     */
    
    
    private List <Mensaje> listaMensajes;
    private List <PeticionAmistad> listaPeticiones;
    private boolean hayMensajesSinLeer;
    private boolean hayPeticionesDeAmistad;
    private Usuario u;
    
    @Inject
    SesionBean sesionBean;
    
    @EJB
    private UsuarioFacade uf;
    
   
    public NotificacionesBean() {
    }
    
    //public List <Mensaje> obtenerListaMensajes (Usuario u) {
    
    @PostConstruct
    public void init (){
        this.u = sesionBean.obtenerUsuario();
        if (listaMensajes == null) {
            
            List <Mensaje> mAux = new ArrayList<>();
            
            for (Mensaje m : u.getMensajeCollection1()) {
                
                if (!m.getLeido()) {
                    mAux.add(m);
                    //quizás da error porque es null y no puedo hacerle add?
                }
                
            }
            
            listaMensajes = mAux;
            
            if (listaMensajes.size()>0) {
                hayMensajesSinLeer = true;
            } else{
                hayMensajesSinLeer = false;
            }
            
        }
        
         if (listaPeticiones == null) {
            
            List <PeticionAmistad> pAux = new ArrayList<>();
            
            for (PeticionAmistad p: u.getPeticionAmistadCollection1()) {
                
                pAux.add(p);
                    //quizás da error porque es null y no puedo hacerle add?
                
                
            }
            
            listaPeticiones = pAux;
            
            if (listaPeticiones.size()>0) {
                this.hayPeticionesDeAmistad = true;
            } else{
                this.hayPeticionesDeAmistad = false;
            }
            
        }
        
        
    }

    public List<PeticionAmistad> getListaPeticiones() {
        return listaPeticiones;
    }

    public void setListaPeticiones(List<PeticionAmistad> listaPeticiones) {
        this.listaPeticiones = listaPeticiones;
    }


    public void setHayMensajesSinLeer(boolean hayMensajesSinLeer) {
        this.hayMensajesSinLeer = hayMensajesSinLeer;
    }

    
    public void setHayPeticionesDeAmistad(boolean hayPeticionesDeAmistad) {
        this.hayPeticionesDeAmistad = hayPeticionesDeAmistad;
    }

    public String responder(Mensaje mensaje){
        for(Mensaje m : u.getMensajeCollection1()){
            if(m.getId().equals(mensaje.getId())){
                m.setLeido(true);
            }
        }
        uf.edit(u);
        return "perfil.jsf?id="+mensaje.getIdEmisor().getId()+"";
    }
    
    public String descartar(Mensaje mensaje){
        for(Mensaje m : u.getMensajeCollection1()){
            if(m.getId().equals(mensaje.getId())){
                m.setLeido(true);
            }
        }
        uf.edit(u);
        return "notificaciones";
    }
    
    public String aceptar(PeticionAmistad peticion){
        uf.aceptarPeticionAmistad(peticion.getUsuario().getId(), peticion.getUsuario1().getId());
        u.getPeticionAmistadCollection1().remove(peticion);
        uf.edit(u);
        return "perfil";
    }
    
    public String rechazar(PeticionAmistad peticion){
        u.getPeticionAmistadCollection1().remove(peticion);
        uf.edit(u);
        return "notificaciones";
    }
    
    public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
    
    
}