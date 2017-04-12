package app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author antonio
 */
@Embeddable
public class EstudiosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IdUsuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Comienzo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaComienzo;

    public EstudiosPK() {
    }

    public EstudiosPK(int idUsuario, Date fechaComienzo) {
        this.idUsuario = idUsuario;
        this.fechaComienzo = fechaComienzo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (fechaComienzo != null ? fechaComienzo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudiosPK)) {
            return false;
        }
        EstudiosPK other = (EstudiosPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if ((this.fechaComienzo == null && other.fechaComienzo != null) || (this.fechaComienzo != null && !this.fechaComienzo.equals(other.fechaComienzo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.entity.EstudiosPK[ idUsuario=" + idUsuario + ", fechaComienzo=" + fechaComienzo + " ]";
    }
    
}
