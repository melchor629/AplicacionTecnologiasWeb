package app.ejb;

import app.entity.PeticionAmistad;
import app.entity.PeticionAmistadPK;
import app.entity.Usuario;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Eduardo Guidet Jiménez
 * @author Antonio Ángel Cruzado Castillo
 */
@Stateless
public class PeticionAmistadFacade extends AbstractFacade<PeticionAmistad> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @EJB
    private UsuarioFacade ufacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public PeticionAmistadFacade() {
        super(PeticionAmistad.class);
    }
    
    // Permite a un usuario mandar una peticion de amistad a otro usuario
    public void mandarPeticionAmistad(int idDesde, int idHacia, String mensaje){
        PeticionAmistad peticion = new PeticionAmistad(idDesde, idHacia);
        peticion.setMensaje(mensaje);
        //Dado que no funciona el propio new (no guarda las ids de los usuarios en peticion)
        //Se las pasamos nosotros manualmente
        peticion.setUsuario(ufacade.obtenerUsuarioPorId(idDesde));
        peticion.setUsuario1(ufacade.obtenerUsuarioPorId(idHacia));
        //Buscamos los usuarios y actualizamos sus colecciones.
        Usuario u1 = ufacade.obtenerUsuarioPorId(idDesde);
        Usuario u2 = ufacade.obtenerUsuarioPorId(idHacia);
        
        u2.getPeticionAmistadCollection1().add(peticion);
        u1.getPeticionAmistadCollection().add(peticion);
        
        
        // Se guarda en la base de datos
        getEntityManager().persist(peticion);
    }
    
    // Permite averiguar si hay una peticion de amistad mandada
    public boolean peticionMandada(int idDesde, int idHacia){
        // Contemplar la antisimetría
        PeticionAmistadPK clavePrimaria = new PeticionAmistadPK(idDesde, idHacia);
        PeticionAmistad peticion = getEntityManager().find(PeticionAmistad.class, clavePrimaria);
        
        return peticion != null;
    }
    
    public void eliminarPeticion(PeticionAmistad peticion){
        Usuario u1 = peticion.getUsuario();
        Usuario u2 = peticion.getUsuario1();

        PeticionAmistadPK clavePrimaria = new PeticionAmistadPK(u1.getId(), u2.getId());
        
        PeticionAmistad peticionDos = getEntityManager().find(PeticionAmistad.class, clavePrimaria);
        getEntityManager().remove(peticionDos);
    }
    
    // Cantidad de peticiones que el usuario tiene pendiente de aceptar o rechazar
    public int peticionesRecibidas(int idUsuario){
        Query consulta = getEntityManager().createNamedQuery("PeticionAmistad.findByIdReceptor");
        consulta.setParameter("idReceptor", idUsuario);
        int cantidadPeticiones = consulta.getResultList().size();
        
        return cantidadPeticiones;  
    }
    }
