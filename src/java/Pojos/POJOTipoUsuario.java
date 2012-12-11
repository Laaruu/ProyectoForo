/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

/**
 *
 * @author Laura Rubio
 */
public class POJOTipoUsuario {

    private Integer id;
    private String descripcion;

    public POJOTipoUsuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof POJOTipoUsuario)) {
            return false;
        }
        POJOTipoUsuario other = (POJOTipoUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pojos.TipoUsuario[ id=" + id + " ]";
    }
}
