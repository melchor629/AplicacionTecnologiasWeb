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
        PeticionAmistad peticion = new PeticionAmistad();
        peticion.setUsuario(getEntityManager().find(Usuario.class, idDesde));
        peticion.setUsuario(getEntityManager().find(Usuario.class, idHacia));
        peticion.setMensaje(mensaje);
        
        // Se guarda en la base de datos
        getEntityManager().persist(peticion);
    }
    
    // Permite averiguar si hay una peticion de amistad mandada
    public void peticionMandada(int idDesde, int idHacia){
        // Contemplar la antisimetría
        PeticionAmistadPK clavePrimaria1 = new PeticionAmistadPK(idDesde, idHacia);
        PeticionAmistadPK clavePrimaria2 = new PeticionAmistadPK(idDesde, idHacia);
        PeticionAmistad peticion1 = getEntityManager().find(PeticionAmistad.class, clavePrimaria1);
        PeticionAmistad peticion2 = getEntityManager().find(PeticionAmistad.class, clavePrimaria2);
    }
    
}
