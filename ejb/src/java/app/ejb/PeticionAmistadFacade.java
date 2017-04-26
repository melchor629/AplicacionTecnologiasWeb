package app.ejb;

import app.entity.PeticionAmistad;
import app.entity.PeticionAmistadPK;
import app.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author antonio
 */
@Stateless
public class PeticionAmistadFacade extends AbstractFacade<PeticionAmistad> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

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
    
    public void eliminarPeticion(int idDesde, int idHacia){
        PeticionAmistadPK clavePrimaria = new PeticionAmistadPK(idDesde, idHacia);
        PeticionAmistad peticion = getEntityManager().find(PeticionAmistad.class, clavePrimaria);
        getEntityManager().remove(peticion);
    }
    
}
