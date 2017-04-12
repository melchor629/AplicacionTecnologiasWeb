package app.ejb;

import app.entity.PeticionAmistad;
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
    
}
