/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.UsuarioFacade;
import app.entity.Usuario;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Lucia y Francis
 */
@Named
@RequestScoped
public class EditarPerfilBean {

    /**
     * Creates a new instance of EditarPerfilBean
     */
    
    @EJB
    UsuarioFacade fachadaUsuario;
    
    @Inject SesionBean sesionBean;
    Usuario usuario;
    
    String contraseñaRepetida=null;
    String contraseñaNueva=null;
    String error=null;
    
    public EditarPerfilBean() {
    }
    
    //obtengo el usuario de sesion
    @PostConstruct
    public void init () {
        this.usuario = this.sesionBean.obtenerUsuario();
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public String getContraseñaRepetida(){
    
        return contraseñaRepetida;
    }
    
    public void setContraseñaRepetida(String contraseña){
        
        this.contraseñaRepetida=contraseña;
    }
    
    public String getError(){
        return error;
    }
    
    public void setError(String error){
        this.error=error;
    }
    
    public String getContraseñaNueva(){
        return contraseñaNueva;
    }
    
    public void setContraseñaNueva(String contraseña){
    
        this.contraseñaNueva=contraseña;
    }
    
    public String doGuardar(){
        
        boolean correcto=true;
        //si es distinto de null modifico los campos
        
            if(this.contraseñaNueva==null && this.contraseñaRepetida==null){
            
                //no hago nada se queda la contraseña que estaba
            }else if(!this.contraseñaNueva.equals(this.contraseñaRepetida)){
                
               
                correcto=false;
            }
           
            /*
            
            no hace falta comprobar esto, ya se hace 
           if(this.usuario.getNombreUsuario()==null){
               
               correcto=false;
           }
           
           if(this.usuario.getNombre()==null){
               
               correcto=false;
           }
           
           if(this.usuario.getApellidos()==null){
               
               correcto=false;
           }
           
           if(this.usuario.getCorreo()==null){
               
               correcto=false;
           }
        */
        
        
        if(!correcto){
            
            //si las contraseñas no son correctas vuelvo a editarPerfil con un error
             error="Las Contraseñas no coinciden";
            return "editarPerfil";
        }else{
            
            error=null;
            //solo edito cuando todos los datos esten correctos
            
            //si las contraseñas no estan nulas y son iguales cambio la contraseña
            if(contraseñaNueva!=null && contraseñaRepetida!=null && contraseñaNueva.equals(contraseñaRepetida)){
                
                 String hash = app.cosas.Hash.hash(usuario.getNombreUsuario()+":"+contraseñaNueva);
                usuario.setContraseña(hash);
            }
            
             this.fachadaUsuario.edit(this.usuario);
         
             return "perfil";
        }
        
    }
    
}
