/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Laura Rubio
 */
public class POJOHilo {

    private Integer id;
    private String nombre;
    /* La fecha es un atributo temporal */
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public POJOHilo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof POJOHilo)) {
            return false;
        }
        POJOHilo other = (POJOHilo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pojos.Hilo[ id=" + id + " ]";
    }
}
