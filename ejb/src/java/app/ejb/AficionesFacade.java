package app.ejb;

import app.entity.Aficiones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author antonio
 */
@Stateless
public class AficionesFacade extends AbstractFacade<Aficiones> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AficionesFacade() {
        super(Aficiones.class);
    }
    
}
