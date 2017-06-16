package beans;

import app.ejb.MensajeFacade;
import app.ejb.PeticionAmistadFacade;
import app.ejb.UsuarioFacade;
import app.entity.Usuario;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Bean que almacena la sesión del usuario
 * @author Melchor Alejo Garau Madrigal
 */
@Named
@SessionScoped
public class SesionBean implements Serializable {
    /**
     * Si vale 0 significa que no se ha iniciado sesión, ya que
     * el ID 0 no existe en la base de datos nunca :)
     */
    private int usuarioID;
    private String usuario; //Valor desde formulario jsf
    private String password; //Valor desde formulario jsf
    private String error = null; //Valor para index.xhtml

    @EJB private UsuarioFacade uf;
    @EJB private PeticionAmistadFacade paf;
    @EJB private MensajeFacade mf;

    /**
     * @return {@code true} si esa persona ha iniciado sesión
     */
    public boolean haIniciadoSesion() {
        return usuarioID > 0;
    }

    /**
     * Devuelve el objeto {@link Usuario} correspondiente para
     * la sesión actual, o {@code null} si no ha iniciado sesión
     * @return el {@link Usuario} o {@code null}
     */
    public Usuario obtenerUsuario() {
        return uf.obtenerUsuarioPorId(usuarioID);
    }

    /**
     * Comprueba las credenciales del usuario y si son correctas
     * accedes a tu perfil, si no son correctas, redirije a /index.
     * Este método se llama por el formulario de WEB-INF/nav-unlogged.xhtml
     * para intentar iniciar sesión (ve a la linea 21).
     * @return cap a on vaig
     */
    public String iniciarSesion() {
        Usuario u = uf.obtenerUsuario(usuario, password);
        //Esto fuera, que no se puede guardar mucho tiempo :)
        this.usuario = null;
        this.password = null;
        if(u != null) {
            this.usuarioID = u.getId();
            this.error = null;
            return "perfil?faces-redirect=true";
        } else {
            this.error = "El usuario o la contraseña son incorrectas";
            return "index";
        }
    }

    /**
     * Inicia sesión con el usuario dado por el objeto
     * @param usuario Usuario al que iniciar sesión
     * @return URL para JSF
     */
    public String iniciarSesion(Usuario usuario) {
        this.usuarioID = usuario.getId();
        return "perfil?faces-redirect=true";
    }

    /**
     * Cierra sesión, si hay una sesión iniciada. Se llama desde el formulario
     * de WEB-INFO/nav-logged.xhtml para cerrar sesión (ve a la linea 41).
     * @return a /index si se ha cerrado sesión o a la página a la que está
     */
    public String cerrarSesion() {
        if(this.usuarioID > 0) {
            this.usuarioID = 0;
            return "index";
        } else {
            //Esto indica que redirija a la página a la que está
            return null;
        }
    }

    public int obtenerCantidadNotificaciones() {
        return paf.peticionesRecibidas(usuarioID) + mf.mensajesSinLeer(usuarioID);
    }


    /// GETTERS & SETTERS \\\
    //Si no se definen, desde JSF no podremos acceder a ellos
    //y eso seria malo :(
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getUsuarioID() {
        return usuarioID;
    }
    
    
}
