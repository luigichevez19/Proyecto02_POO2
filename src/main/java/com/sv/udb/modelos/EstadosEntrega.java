/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "estados_entrega", catalog = "vaso_leche", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosEntrega.findAll", query = "SELECT e FROM EstadosEntrega e")
    , @NamedQuery(name = "EstadosEntrega.findByCodiEsta", query = "SELECT e FROM EstadosEntrega e WHERE e.codiEsta = :codiEsta")
    , @NamedQuery(name = "EstadosEntrega.findByNombEsta", query = "SELECT e FROM EstadosEntrega e WHERE e.nombEsta = :nombEsta")})
public class EstadosEntrega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_esta")
    private Integer codiEsta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomb_esta")
    private String nombEsta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiEsta", fetch = FetchType.EAGER)
    private Collection<Entregas> entregasCollection;

    public EstadosEntrega() {
    }

    public EstadosEntrega(Integer codiEsta) {
        this.codiEsta = codiEsta;
    }

    public EstadosEntrega(Integer codiEsta, String nombEsta) {
        this.codiEsta = codiEsta;
        this.nombEsta = nombEsta;
    }

    public Integer getCodiEsta() {
        return codiEsta;
    }

    public void setCodiEsta(Integer codiEsta) {
        this.codiEsta = codiEsta;
    }

    public String getNombEsta() {
        return nombEsta;
    }

    public void setNombEsta(String nombEsta) {
        this.nombEsta = nombEsta;
    }

    @XmlTransient
    public Collection<Entregas> getEntregasCollection() {
        return entregasCollection;
    }

    public void setEntregasCollection(Collection<Entregas> entregasCollection) {
        this.entregasCollection = entregasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiEsta != null ? codiEsta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadosEntrega)) {
            return false;
        }
        EstadosEntrega other = (EstadosEntrega) object;
        if ((this.codiEsta == null && other.codiEsta != null) || (this.codiEsta != null && !this.codiEsta.equals(other.codiEsta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelos.EstadosEntrega[ codiEsta=" + codiEsta + " ]";
    }
    
}
