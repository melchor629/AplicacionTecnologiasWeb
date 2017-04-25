package app.ejb;

import app.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author antonio
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario obtenerUsuario (String usuario, String pass) {
        Query q;
        q = this.em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :usuario AND u.contraseña = :password ");
        String hashPasado = app.cosas.Hash.hash(usuario+":"+pass);
        q.setParameter("usuario", usuario);
        q.setParameter("password",hashPasado);
        List<Usuario> lista = (List)q.getResultList();
        System.out.println(pass + " " + hashPasado);
        
        if (lista.size()>0){
            return lista.get(0);
        } else{
            return null;
        }             
    }
    
     public void insertarUsuario (Usuario usuario) {
       EntityManager gestorEntidades = getEntityManager();
       gestorEntidades.persist(usuario);
    }
    
     
    public Usuario obtenerUsuarioPorId(int idUsuario){
        EntityManager gestorEntidades = getEntityManager();
        
       Usuario usuario = gestorEntidades.find(Usuario.class, idUsuario);
        
       /* Query q;
        q = gestorEntidades.createQuery("SELECT u FROM Usuario u WHERE u.id = :idUsuario");
        q.setParameter("idUsuario", idUsuario);
        Usuario usuario = (Usuario) q.getSingleResult(); */
       
        return usuario;
    }
    
}
