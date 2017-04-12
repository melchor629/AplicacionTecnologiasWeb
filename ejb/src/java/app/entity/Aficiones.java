package app.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author antonio
 */
@Entity
@Table(name = "Aficiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aficiones.findAll", query = "SELECT a FROM Aficiones a")
    , @NamedQuery(name = "Aficiones.findByIdUsuario", query = "SELECT a FROM Aficiones a WHERE a.aficionesPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "Aficiones.findByNombre", query = "SELECT a FROM Aficiones a WHERE a.aficionesPK.nombre = :nombre")})
public class Aficiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AficionesPK aficionesPK;
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Aficiones() {
    }

    public Aficiones(AficionesPK aficionesPK) {
        this.aficionesPK = aficionesPK;
    }

    public Aficiones(int idUsuario, String nombre) {
        this.aficionesPK = new AficionesPK(idUsuario, nombre);
    }

    public AficionesPK getAficionesPK() {
        return aficionesPK;
    }

    public void setAficionesPK(AficionesPK aficionesPK) {
        this.aficionesPK = aficionesPK;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aficionesPK != null ? aficionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aficiones)) {
            return false;
        }
        Aficiones other = (Aficiones) object;
        if ((this.aficionesPK == null && other.aficionesPK != null) || (this.aficionesPK != null && !this.aficionesPK.equals(other.aficionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.Aficiones[ aficionesPK=" + aficionesPK + " ]";
    }
    
}
