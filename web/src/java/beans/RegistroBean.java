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
    
    
    Usuario usuario;
    
    String error=null;
    
    String password=null;
    String passwordConfirmada=null;
    
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
        this.usuario= new Usuario();
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
       
     
        if(!password.equals(passwordConfirmada)|| password.equals("") || passwordConfirmada.equalsIgnoreCase("")){
            
            error="Contraseñas Incorrectas";
        }else{
            
            //las contraseñas estan correctas
             String hash = app.cosas.Hash.hash(usuario.getNombreUsuario()+":"+this.password);
                usuario.setContraseña(hash);
           
        }
        
        if(!check1 || !check2 ){
            
            if(error==null){
                
                error= "Tienes que aceptar las condiciones";
            }else{
                
            error= error +" - Tienes que aceptar las condiciones";
        
            }
        }    
        
        //Error
        if(error!=null){
            
            return "registro";
        }else{
            
            error= null;
            //si esta todo bien creo el usuario
            
            
            this.usuario.setId(Integer.SIZE);
            this.fachadaUsuario.create(this.usuario);
            
            return "index";
        }
       
    }
}
