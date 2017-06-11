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
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Lucia y Francis
 */
@Named
@RequestScoped
public class RegistroBean {

    /**
     * Creates a new instance of RegistroBean
     */
    
    @EJB
    UsuarioFacade fachadaUsuario;
    
    
    String error="";
    
    String password=null;
    String passwordConfirmada=null;
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

    public boolean isCorrecto() {
        return correcto;
    }

    public void setCorrecto(boolean correcto) {
        this.correcto = correcto;
    }
    
    boolean check1=false;
    boolean check2=false;

    public boolean isCheck1() {
        return check1;
    }

    public void setCheck1(boolean check1) {
        this.check1 = check1;
    }

    public boolean isCheck2() {
        return check2;
    }

    public void setCheck2(boolean check2) {
        this.check2 = check2;
    }
    
    @PostConstruct
    public void init(){
        this.error="";
      password=null;
     passwordConfirmada=null;
        
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
  

    
    public RegistroBean() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmada() {
        return passwordConfirmada;
    }

    public void setPasswordConfirmada(String passwordConfirmada) {
        this.passwordConfirmada = passwordConfirmada;
    }
    
    public String doRegistrar(){
       
        correcto=true;
     
            if((this.password==null && this.passwordConfirmada==null) || (this.password.equals("") && this.passwordConfirmada.equals("")) || !this.password.equals(this.passwordConfirmada)){
            
                 error +=  "- Las contraseñas no coinciden o estan vacias";
                correcto=false;
            }
        
        if(!check1 || !check2 ){
            
          
                
            error= error +" - Tienes que aceptar las condiciones";
        
            correcto= false;
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
        
        //Error
        if(!correcto){
            
            return "registro";
        }else{
            
            Usuario usuario;
            //si esta todo bien creo el usuario
            
            usuario = new Usuario(Integer.SIZE);
            
            if((password!=null && passwordConfirmada!=null)  && (!this.password.equals("") && !this.passwordConfirmada.equals("")) && password.equals(passwordConfirmada)){
                
                 String hash = app.cosas.Hash.hash(this.nombreUsuario+":"+password);
                usuario.setContraseña(hash);
            }
            
            usuario.setNombre(this.nombre);
            usuario.setNombreUsuario(this.nombreUsuario);
            usuario.setApellidos(this.apellidos);
            usuario.setCorreo(this.correo);
            fachadaUsuario.create(usuario);
            
            return "index";
        }
       
    }
}
