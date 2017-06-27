/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.PeticionAmistadFacade;
import app.ejb.UsuarioFacade;
import app.entity.Mensaje;
import app.entity.PeticionAmistad;
import app.entity.Usuario;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    GravatarBean gb;
    
    @Inject
    SesionBean sesionBean;
    
    @EJB
    private UsuarioFacade uf;

    @EJB private PeticionAmistadFacade paf;
    
   
    
    public NotificacionesBean() {
    }
    
    //public List <Mensaje> obtenerListaMensajes (Usuario u) {
    
    @PostConstruct
    public void init (){
        this.u = sesionBean.obtenerUsuario();
            
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

        List <PeticionAmistad> pAux = new ArrayList<>();

        //quizás da error porque es null y no puedo hacerle add?
        pAux.addAll(u.getPeticionAmistadCollection1());

        listaPeticiones = pAux;

        if (listaPeticiones.size()>0) {
            this.hayPeticionesDeAmistad = true;
        } else{
            this.hayPeticionesDeAmistad = false;
        }
        
        
    }
    
    
    public String fotoPerfil (int id) {
        Usuario u = uf.obtenerUsuarioPorId(id);
        
        return u.getFoto() == null ? imagenPorDefecto(u) : u.getFoto();
    }
    
     private String imagenPorDefecto(Usuario usuario) {
        /*if (usuario == null) {
            usuario = this.uf
        }*/

        return gb.imagenPorDefecto(usuario);
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

    public void responder(Mensaje mensaje) throws IOException{
        for(Mensaje m : u.getMensajeCollection1()){
            if(m.getId().equals(mensaje.getId())){
                m.setLeido(true);
            }
        }
        uf.edit(u);
        this.redirect(mensaje.getIdEmisor().getId());
    }
    
    public void descartar(Mensaje mensaje) throws IOException{
        for(Mensaje m : u.getMensajeCollection1()){
            if(m.getId().equals(mensaje.getId())){
                m.setLeido(true);
            }
        }
        uf.edit(u);
        init();
    }
    
    public String aceptar(PeticionAmistad peticion){
        paf.eliminarPeticion(peticion);
        uf.aceptarPeticionAmistad(peticion);
        return "perfil?faces-redirect=true&id="+peticion.getUsuario().getId();
    }
    
    public void rechazar(PeticionAmistad peticion) throws IOException{
        u.getPeticionAmistadCollection1().remove(peticion);
        peticion.getUsuario().getPeticionAmistadCollection().remove(peticion);
        uf.edit(u);
        uf.edit(peticion.getUsuario());
        paf.remove(peticion);
        init();
    }
    
    public List<Mensaje> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
    
    public void redirect(int id) throws IOException {
    // ...

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("perfil.jsf?id="+id);
    }
    
    public void redirectN() throws IOException {
    // ...

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("notificaciones.jsf");
    }

    
    
}