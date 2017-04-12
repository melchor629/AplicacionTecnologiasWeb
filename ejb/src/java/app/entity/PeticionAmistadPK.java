package app.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author antonio
 */
@Embeddable
public class PeticionAmistadPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idEmisor")
    private int idEmisor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idReceptor")
    private int idReceptor;

    public PeticionAmistadPK() {
    }

    public PeticionAmistadPK(int idEmisor, int idReceptor) {
        this.idEmisor = idEmisor;
        this.idReceptor = idReceptor;
    }

    public int getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(int idEmisor) {
        this.idEmisor = idEmisor;
    }

    public int getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(int idReceptor) {
        this.idReceptor = idReceptor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEmisor;
        hash += (int) idReceptor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeticionAmistadPK)) {
            return false;
        }
        PeticionAmistadPK other = (PeticionAmistadPK) object;
        if (this.idEmisor != other.idEmisor) {
            return false;
        }
        if (this.idReceptor != other.idReceptor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.PeticionAmistadPK[ idEmisor=" + idEmisor + ", idReceptor=" + idReceptor + " ]";
    }
    
}
