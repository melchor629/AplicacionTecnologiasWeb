package app.ejb;

import app.entity.Aficiones;
import app.entity.Estudios;
import app.entity.EstudiosPK;
import app.entity.Usuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio Ángel Cruzado Castillo
 * //Francisco Reyes Sánchez
 * 
 */
@Stateless
public class EstudiosFacade extends AbstractFacade<Estudios> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudiosFacade() {
        super(Estudios.class);
    }

    public List<Estudios> obtenerEstudios(Usuario u) {

        Query q;

        q = this.em.createNamedQuery("Estudios.findByIdUsuario");
        q.setParameter("idUsuario", u.getId());

        return q.getResultList();
    }

    
      public Estudios obtenerEstudioConIdyFecha(String id, String fecha) throws ParseException{
       
       DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
       Query q;
       q = this.em.createQuery("SELECT e FROM Estudios e WHERE e.estudiosPK.idUsuario = :idUsuario AND e.estudiosPK.fechaComienzo = :fechaComienzo");
       q.setParameter("idUsuario", Integer.parseInt(id));
       q.setParameter("fechaComienzo", format.parse(fecha));
       
       Estudios e= (Estudios) q.getSingleResult();
       
       
       return e;
   }
      
     public void borrarEstudio(int idUsuario, Date fecha){
           EstudiosPK clave = new EstudiosPK(idUsuario, fecha);
           Estudios estudio = getEntityManager().find(Estudios.class, clave);
           getEntityManager().remove(estudio);
       } 


    public Estudios obtenerEstudio(int idUsuario, Date fecha) {
        EstudiosPK clave = new EstudiosPK(idUsuario, fecha);
        Estudios estudio = getEntityManager().find(Estudios.class, clave);
        return estudio;
    }

}
