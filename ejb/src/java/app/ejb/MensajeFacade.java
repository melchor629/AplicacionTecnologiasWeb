package app.ejb;

import app.entity.Mensaje;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author antonio
 * @author Rodrigo Represa Represa
 */
@Stateless
public class MensajeFacade extends AbstractFacade<Mensaje> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    UsuarioFacade fachadaUsuario;

    public MensajeFacade() {
        super(Mensaje.class);
    }
    
    // Devuelve una lista con los mensajes que ha recibido un usuario
    public Collection<Mensaje> mensajesUsuario(int idUsuario){
        Collection<Mensaje> mensajes;
        Query consulta = getEntityManager().createQuery("SELECT m FROM Mensaje m WHERE m.idReceptor = :id");
        consulta.setParameter("id", idUsuario);
        mensajes = consulta.getResultList();
        return mensajes;
    }
    
    // Cantidad de mensajes sin leer que tiene un usuario
    public int mensajesSinLeer(int idUsuario){
        int cantidad = 0;
        
        Query consulta = getEntityManager().createQuery("SELECT m FROM Mensaje m WHERE m.idReceptor = :id AND m.leido = false");
        consulta.setParameter("id", fachadaUsuario.obtenerUsuarioPorId(idUsuario));
        cantidad = consulta.getResultList().size();
        return cantidad;
    }
    
    public void crearMensaje(int idDesde, int idHacia, String titulo, String textoMensaje){
        Mensaje mensaje = new Mensaje(Integer.SIZE, textoMensaje, false, titulo);
        mensaje.setIdEmisor(fachadaUsuario.obtenerUsuarioPorId(idDesde));
        mensaje.setIdReceptor(fachadaUsuario.obtenerUsuarioPorId(idHacia));
        
        // Almacenar mensaje
        getEntityManager().persist(mensaje);
    }
    
    public void marcarLeido(int id){
        /*Query consulta = getEntityManager().createQuery("UPDATE mensaje SET leido = '1' WHERE id = :id");
        consulta.setParameter("id", id);
        cantidad = consulta.getResultList().size();*/
        Mensaje mensaje = getEntityManager().find(Mensaje.class, id);
        mensaje.setLeido(true);
        edit(mensaje);
    }

}
