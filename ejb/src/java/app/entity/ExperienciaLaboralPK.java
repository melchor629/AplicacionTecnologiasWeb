/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ExperienciaLaboralPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idUsuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Comienzo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaComienzo;

    public ExperienciaLaboralPK() {
    }

    public ExperienciaLaboralPK(int idUsuario, Date fechaComienzo) {
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
        if (!(object instanceof ExperienciaLaboralPK)) {
            return false;
        }
        ExperienciaLaboralPK other = (ExperienciaLaboralPK) object;
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
        return "app.entity.ExperienciaLaboralPK[ idUsuario=" + idUsuario + ", fechaComienzo=" + fechaComienzo + " ]";
    }
    
}
