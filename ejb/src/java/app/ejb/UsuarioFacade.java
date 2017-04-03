/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public Usuario ObtenerUsuario (String usuario, String pass) {
        Query q;
        q = this.em.createQuery("select * from usuario where NombreUsuario = :usuario and Contraseña = :password ");
        String hashPasado = app.cosas.Hash.hash(usuario+":"+pass);
        q.setParameter("usuario", usuario);
        q.setParameter("password",hashPasado);
        List<Usuario> lista = (List)q.getResultList();
        
        if (lista.size()>0){
            return lista.get(0);
        } else{
            return null;
        }   
               
    }
    
}
