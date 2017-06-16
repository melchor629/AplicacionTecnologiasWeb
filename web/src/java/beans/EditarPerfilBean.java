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
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    String error;
    String nombre;
    String apellidos;
    String correo;
    String nombreUsuario;
    boolean correcto=true;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
 
    
    
    public EditarPerfilBean() {
    }
    
    //obtengo el usuario de sesion
    @PostConstruct
    public void init () {
        
        this.error="";
        this.usuario = this.sesionBean.obtenerUsuario();
        contraseñaRepetida=null;
        contraseñaNueva=null;
        this.nombre= this.usuario.getNombre();
        this.apellidos= this.usuario.getApellidos();
        this.nombreUsuario=this.usuario.getNombreUsuario();
        this.correo=this.usuario.getCorreo();
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
        
         correcto=true;
        //si es distinto de null modifico los campos
        
            if((this.contraseñaNueva==null && this.contraseñaRepetida==null) || (this.contraseñaNueva.equals("") && this.contraseñaRepetida.equals(""))){
            
                //no hago nada se queda la contraseña que estaba
            }else if(!this.contraseñaNueva.equals(this.contraseñaRepetida)){

                error +=  "- Las contraseñas no coinciden";
                correcto=false;
            }
           
           
            
          
           if(this.nombreUsuario==null || this.nombreUsuario.equals("")){
               
               error +=  "- El nombre de usuario no puede estar vacío";
               correcto=false;
           }
           
           if(this.nombre==null || this.nombre.equals("")){
               error +=  "- El nombre no puede estar vacío";
               correcto=false;
           }
           
           if(this.apellidos==null || this.apellidos.equals("")){
              error +=  "- Los apellidos no pueden estar vacios"; 
               correcto=false;
           }
           
           if(this.correo==null || this.correo.equals("")){
               error +=  "- El correo no puede estar vacío";
               correcto=false;
           }
        
        
        if(!correcto){
            
            //si no esta correcto vuelvo con un error
            
            //si los campos estan vacios relleno con lo preestablecido
            
            if(this.nombreUsuario==null || this.nombreUsuario.equals("")){
               
              this.nombreUsuario=this.usuario.getNombreUsuario();
           }
           
           if(this.nombre==null || this.nombre.equals("")){
               this.nombre= this.usuario.getNombre();
           }
           
           if(this.apellidos==null || this.apellidos.equals("")){
               this.apellidos= this.usuario.getApellidos();
           }
           
           if(this.correo==null || this.correo.equals("")){
                this.correo=this.usuario.getCorreo();
           }
 
            
            return "editarPerfil";
        }else{
            
            //solo edito cuando todos los datos esten correctos
            
            //si las contraseñas no estan nulas y son iguales cambio la contraseña
            if((contraseñaNueva!=null && contraseñaRepetida!=null)  && (!this.contraseñaNueva.equals("") && !this.contraseñaRepetida.equals("")) && contraseñaNueva.equals(contraseñaRepetida)){
                
                 String hash = app.cosas.Hash.hash(usuario.getNombreUsuario()+":"+contraseñaNueva);
                usuario.setContraseña(hash);
            }
            
            this.usuario.setNombre(this.nombre);
            this.usuario.setNombreUsuario(this.nombreUsuario);
            this.usuario.setApellidos(this.apellidos);
            this.usuario.setCorreo(this.correo);
            
             this.fachadaUsuario.edit(this.usuario);
         
             return "perfil?faces-redirect=true";
        }
        
    }

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }
    
}
