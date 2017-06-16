/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.entity.Mensaje;
import app.entity.PeticionAmistad;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    
    @Inject
    SesionBean sesionBean;
    
    
    public NotificacionesBean() {
    }
    
    //public List <Mensaje> obtenerListaMensajes (Usuario u) {
    
    @PostConstruct
    public void init (){
    
        if (listaMensajes == null) {
            
            List <Mensaje> mAux = new ArrayList<>();
            
            for (Mensaje m : sesionBean.obtenerUsuario().getMensajeCollection1()) {
                
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
            
            for (PeticionAmistad p: sesionBean.obtenerUsuario().getPeticionAmistadCollection1()) {
                
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

    public boolean isHayMensajesSinLeer() {
        return hayMensajesSinLeer;
    }

    public void setHayMensajesSinLeer(boolean hayMensajesSinLeer) {
        this.hayMensajesSinLeer = hayMensajesSinLeer;
    }

    public boolean isHayPeticionesDeAmistad() {
        return hayPeticionesDeAmistad;
    }

    public void setHayPeticionesDeAmistad(boolean hayPeticionesDeAmistad) {
        this.hayPeticionesDeAmistad = hayPeticionesDeAmistad;
    }

    
    
    public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
    
    
}


