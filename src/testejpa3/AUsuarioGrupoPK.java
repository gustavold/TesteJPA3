/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testejpa3;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author gustavoduarte
 */
@Embeddable
public class AUsuarioGrupoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "usuario_id")
    private int usuarioId;
    @Basic(optional = false)
    @Column(name = "grupo_id")
    private int grupoId;

    public AUsuarioGrupoPK() {
    }

    public AUsuarioGrupoPK(int usuarioId, int grupoId) {
        this.usuarioId = usuarioId;
        this.grupoId = grupoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) usuarioId;
        hash += (int) grupoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AUsuarioGrupoPK)) {
            return false;
        }
        AUsuarioGrupoPK other = (AUsuarioGrupoPK) object;
        if (this.usuarioId != other.usuarioId) {
            return false;
        }
        if (this.grupoId != other.grupoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testejpa3.AUsuarioGrupoPK[usuarioId=" + usuarioId + ", grupoId=" + grupoId + "]";
    }

}
