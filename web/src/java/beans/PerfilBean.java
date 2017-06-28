/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import app.ejb.*;
import app.entity.Aficiones;
import app.entity.Estudios;
import app.entity.ExperienciaLaboral;
import app.entity.Usuario;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author antonio
 */
@Named(value = "perfilBean")
@ViewScoped
public class PerfilBean implements Serializable {

    private static final String[] MENSAJES_EXITO = {
        "Mensaje mandado con éxito",
        "Experiencia laboral borrada con éxito",
        "Formación borrada con éxito",
        "Afición borrada con éxito",
        "Experiencia laboral editada con éxito",
        "Formación editada con éxito",
        "Afición editada con éxito",
        "Experiencia laboral añadida con éxito",
        "Formación añadida con éxito",
        "Afición añadida con éxito",
        "Peticion de amistad mandada con exito",
        "Amistad borrada con éxito"
    };

    // Fachadas necesarias en etse bean
    @EJB
    private UsuarioFacade fachadaUsuarios;

    @EJB
    private PeticionAmistadFacade fachadaPeticionDeAmistad;

    @EJB
    private ExperienciaLaboralFacade fachadaExperienciaLaboral;

    @EJB
    private EstudiosFacade fachadaEstudios;

    @EJB
    private AficionesFacade fachadaAficiones;

    @EJB
    private MensajeFacade fachadaMensajes;

    @EJB
    private PeticionAmistadFacade fachadaPeticionAmistad;

    // Se inyecta el SessionBean, necesario para obtener datos relacionados con
    // la sesion que hay iniciada
    @Inject
    private SesionBean sesionBean;

    @Inject private GravatarBean gb;

    // Parametro donde se guarda el id del usuario a mostrar en perfil
    private int idParameter;

    // Parametro que indica el error producido
    private String errorParameter = "";

    // Parametro que indica el exito producido
    private String exitoParameter = "";

    // Otro parametro de exito "falso" usado para que ajax funcione
    private String exitoParameterFalso = "";

    // Usuario a mostrar en el perfil
    private Usuario usuario;

    // Donde se almacena la lista de contactos completa (UsuarioCollection y
    // UsuarioColletion1 del usuario para el que se va a mostrar el perfil
    private Collection<Usuario> contactos;

    // Indica si el perfil visitado es el del usuario que tiene la sesion iniciada
    private boolean miPerfil;

    // Indica si el usuario que ha iniciado sesion es amigo del que se esta mostrando
    private boolean amigos = false;

    // Indica si un usuario que no es amigo tuyo ha mandado peticion al que ha iniciado sesion
    private boolean peticionRecibida = false;

    // Indica si le has mandado la peticion a un usuario que es amigo tuyo
    private boolean peticionMandada = false;

    // Indica si deben mostrarse los mensajes de error o exito
    private boolean mostrarError = false;

    // Con exito
    private boolean mostrarExito = false;

    /**
     * Mandar mensaje a un usuario *
     */
    // En estas variables se recoge si procede la info necesaria para mandar
    // un mensaje a un usuario
    // Titulo del mensaje
    private String tituloMensaje;

    // Cuerpo del mensaje
    private String cuerpoMensaje;

    /**
     * Creates a new instance of PerfilBean
     */
    public PerfilBean() {
    }

    // Este metodo se ejecuta cuando ya se tiene el parámetro que se ha pasado por URL
    public void onParameterReceived() {

        // Gestionar parámetro de error
        if (!this.errorParameter.equals("")) {
            this.mostrarError = true;
        }

        if (!this.exitoParameter.equals("")) {
            this.mostrarExito = true;
        }

        // Fijar valor del parámetro si es vacío o no es correcto
        if (this.idParameter == 0) {
            this.idParameter = this.sesionBean.getUsuarioID();
        }

        // Ahora si se dispone del parametro
        if (this.idParameter == sesionBean.getUsuarioID()) {
            // Se muestra en el perfil el usuario que ha iniciado sesión
            this.usuario = sesionBean.obtenerUsuario();
            this.miPerfil = true;
            this.amigos = false;
        } else {
            // Se va a mostrar un usuario distinto al que ha iniciado sesion
            this.usuario = fachadaUsuarios.obtenerUsuarioPorId(this.idParameter);
            this.miPerfil = false;

            // Comprobar amistad
            this.amigos = this.fachadaUsuarios.sonAmigos(this.sesionBean.getUsuarioID(), this.usuario.getId());

            // Comprobar si la peticion ha sido recibida
            if (this.fachadaPeticionDeAmistad.peticionMandada(this.usuario.getId(), this.sesionBean.getUsuarioID())) {
                this.peticionRecibida = true;
            } else if (this.fachadaPeticionDeAmistad.peticionMandada(this.sesionBean.getUsuarioID(), this.usuario.getId())) {
                this.peticionMandada = true;
            }

            // Comprobar si la peticion ha sido mandada
        }

        // Se procede a rellenar debidamente la lista de contactos del usuario
        // Se crea una nueva copia para no modificar la lista de la entidad usuario
        // ya que esto hace que al editar la entidad en el borrado de items por ejemplo
        // acaben habiendo problemas
        this.contactos = new ArrayList<>(this.usuario.getUsuarioCollection());
        this.contactos.addAll(this.usuario.getUsuarioCollection1());
    }

    // Algunos metodos auxiliares mostrados en la vista
    // Dadas dos fechas devuelve como un String una representacion de cuanto tiempo ha transcurrido desde
    // una hasta otra
    public String tiempoTranscurrido(Date comienzo, Date fin) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaInicio = dateFormat.format(comienzo);
        String fechaFin;

        Calendar tiempo;
        long diferencia;
        if (fin == null) {
            fechaFin = "actualidad";
            diferencia = System.currentTimeMillis() - comienzo.getTime();
        } else {
            fechaFin = dateFormat.format(fin);
            diferencia = fin.getTime() - comienzo.getTime();
        }
        tiempo = new GregorianCalendar();
        tiempo.setTime(new Date(diferencia));
        int dias = tiempo.get(Calendar.DAY_OF_MONTH);
        int meses = tiempo.get(Calendar.MONTH);
        int años = tiempo.get(Calendar.YEAR) - 1970;

        // En esta cadena se almacenará la salida como un String
        String salida;

        salida = fechaInicio + " - " + fechaFin + " (";

        // Comprobacion de años
        if (años != 0) {
            salida += años + " año";

            if (años > 1) {
                salida += "s";
            }
            salida += ", ";
        }

        // Comprobacion de meses
        if (meses != 0) {
            salida += meses + " mes";

            if (meses > 1) {
                salida += "es";
            }
            salida += ", ";
        }

        if (dias != 0) {
            salida += dias + " día";

            if (dias > 1) {
                salida += "s";
            }
        }
        salida += ")";
        return salida;
    }

    // Devuelve la cadena de error que corresponde
    public String cadenaError() {
        return "Has intentado mandar un mensaje sin título o cuerpo...";
    }

    // Devuelve la cadena de exito que corresponde
    public String cadenaExito() {
        int numeroExito = 0;
        if (!exitoParameter.equals("")) {
            numeroExito = Integer.parseInt(exitoParameter);
        }
        if (!exitoParameterFalso.equals("")) {
            numeroExito = Integer.parseInt(exitoParameterFalso);
        }
        return MENSAJES_EXITO[numeroExito];
    }

    // Devuelve un String adecuado con la foto de perfil dependiendo de si el
    // usuario tiene foto o no
    public String fotoPerfil() {
        return this.usuario.getFoto() == null ? imagenPorDefecto(null) : this.usuario.getFoto();
    }

    public String fotoPerfilContacto(Usuario usuario) {
        return usuario.getFoto() == null ? imagenPorDefecto(usuario) : usuario.getFoto();
    }

    private String imagenPorDefecto(Usuario usuario) {
        if (usuario == null) {
            usuario = this.usuario;
        }

        return gb.imagenPorDefecto(usuario);
    }

    // Borra un item del perfil, que puede ser Experiencia Laboral, Formacion o Aficion
    public void borrarItem(Object item) {

        // Al ser bidireccional la asociacion, borrar tambien del usuario y editar este
        if (item instanceof ExperienciaLaboral) {
            this.fachadaExperienciaLaboral.remove((ExperienciaLaboral) item);
            this.usuario.getExperienciaLaboralCollection().remove((ExperienciaLaboral) item);
            this.exitoParameterFalso = "1";
        } else if (item instanceof Estudios) {
            this.fachadaEstudios.remove((Estudios) item);
            this.usuario.getEstudiosCollection().remove((Estudios) item);
            this.exitoParameterFalso = "2";
        } else if (item instanceof Aficiones) {
            this.fachadaAficiones.remove((Aficiones) item);
            this.usuario.getAficionesCollection().remove((Aficiones) item);
            this.exitoParameterFalso = "3";
        }

        this.fachadaUsuarios.edit(this.usuario);

        // Hacer que se muestre mensaje de exito
        this.mostrarExito = true;
    }

    // Mandar un mensaje a un usuario, se hara del mismo modo por AJAX tambien
    // idPara es la id del usuario al que se manda el mensaje
    public void mandarMensaje(int idPara) {
        this.fachadaMensajes.crearMensaje(this.sesionBean.getUsuarioID(), idPara, this.tituloMensaje, this.cuerpoMensaje);
// Una vez mandado el mensaje se fijan las variables necesarias para mostrar el mensaje de error
        this.mostrarExito = true;
        this.exitoParameterFalso = "0";

        // Vaciar los campos de texto para que no salgan rellenos de nuevo en la pagina
        setTituloMensaje(null);
        setCuerpoMensaje(null);
    }

    // Mandar peticion de amistad a un usuario, se hara mediante ajax tambien
    public void mandarPeticion() {
        Usuario usuarioLogueado = this.sesionBean.obtenerUsuario();
        this.fachadaPeticionAmistad.mandarPeticionAmistad(this.sesionBean.getUsuarioID(), this.usuario.getId(), "El usuario " + usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellidos() + " te quiere stalkear");
        // establecer correctamente mensaje de exito
        this.exitoParameterFalso = "10";
        this.mostrarExito = true;
        this.peticionMandada = true;
    }

    public void eliminarAmistad() {
        this.fachadaUsuarios.borrarAmistad(this.sesionBean.getUsuarioID(), this.usuario.getId());
        // Ya no son amigos los usuarios
        this.amigos = false;

        // Mostrar mensaje de exito adecuado
        this.mostrarExito = true;
        this.exitoParameterFalso = "11";
        // Actualizacion del usuario
        this.usuario = this.fachadaUsuarios.obtenerUsuarioPorId(this.usuario.getId());
    }

    //Getters y setters
    public void setIdParameter(int idParameter) {
        this.idParameter = idParameter;
    }

    public int getIdParameter() {
        return idParameter;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setMiPerfil(boolean miPerfil) {
        this.miPerfil = miPerfil;
    }

    public boolean getMiPerfil() {
        return miPerfil;
    }

    public boolean getAmigos() {
        return amigos;
    }

    public boolean getPeticionRecibida() {
        return peticionRecibida;
    }

    public boolean getPeticionMandada() {
        return peticionMandada;
    }

    public String getErrorParameter() {
        return errorParameter;
    }

    public void setErrorParameter(String errorParameter) {
        this.errorParameter = errorParameter;
    }

    public String getExitoParameter() {
        return exitoParameter;
    }

    public void setExitoParameter(String exitoParameter) {
        this.exitoParameter = exitoParameter;
    }

    public boolean getMostrarError() {
        return mostrarError;
    }

    public boolean getMostrarExito() {
        return mostrarExito;
    }

    public void setContactos(Collection<Usuario> contactos) {
        this.contactos = contactos;
    }

    public Collection<Usuario> getContactos() {
        return contactos;
    }

    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }

    public String getTituloMensaje() {
        return tituloMensaje;
    }

    public void setTituloMensaje(String tituloMensaje) {
        this.tituloMensaje = tituloMensaje;
    }

}
