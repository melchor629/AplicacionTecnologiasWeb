/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.AficionesFacade;
import app.ejb.UsuarioFacade;
import app.entity.Aficiones;
import app.entity.Usuario;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Lucia y Francis
 */
@Named
@RequestScoped
public class CrearAficionBean {

    /**
     * Creates a new instance of CrearAficion
     */
    
    @Inject SesionBean sesionBean;
    
    @EJB
    UsuarioFacade fachadaUsuario;
    @EJB
    AficionesFacade fachadaAficiones;
    
    Usuario usuario;
    Aficiones aficion;
    String error=null;
    String nombre=null;
    
    public CrearAficionBean() {
    }
    
    @PostConstruct
    public void init () {
        this.usuario = this.sesionBean.obtenerUsuario();
        
        error= null; 
        this.aficion= null;
            
    }
    
    public Aficiones getAficion(){
        return this.aficion;
    }
    
    public void setAficion(Aficiones a){
        this.aficion=a;
    }
    
    public String getError(){
        return error;
    }
    
    public void setError(String e){
        this.error=e;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String n){
         this.nombre=n;
    }
    
      public String doGuardar(){
        
        //si hay un error
        
        if(nombre==null || nombre.equals("") || nombre.equals(" ")){
            error= " La descripción no puede estar vacia";
        }
        
        if(error!=null){
            
            return "crearAficion";
        }else{
            
            error=null;
            
            //como no puedo editar el trabajo creo borro el que tengo y creo uno nuevo
            aficion= new Aficiones(this.usuario.getId(), this.nombre);
            usuario.getAficionesCollection().add(this.aficion);
          
           fachadaUsuario.edit(this.usuario);
           
            return "perfil?faces-redirect=true";
        }
        
        
    }
}
