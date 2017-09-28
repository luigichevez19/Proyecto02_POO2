/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelos;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "departamentos", catalog = "vaso_leche", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d")
    , @NamedQuery(name = "Departamentos.findByCodiDepa", query = "SELECT d FROM Departamentos d WHERE d.codiDepa = :codiDepa")
    , @NamedQuery(name = "Departamentos.findByNombDepa", query = "SELECT d FROM Departamentos d WHERE d.nombDepa = :nombDepa")})
public class Departamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_depa")
    private Integer codiDepa;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "nomb_depa")
    private String nombDepa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiDepa", fetch = FetchType.EAGER)
    private List<Escuelas> escuelasList;

    public Departamentos() {
    }

    public Departamentos(Integer codiDepa) {
        this.codiDepa = codiDepa;
    }

    public Departamentos(Integer codiDepa, String nombDepa) {
        this.codiDepa = codiDepa;
        this.nombDepa = nombDepa;
    }

    public Integer getCodiDepa() {
        return codiDepa;
    }

    public void setCodiDepa(Integer codiDepa) {
        this.codiDepa = codiDepa;
    }

    public String getNombDepa() {
        return nombDepa;
    }

    public void setNombDepa(String nombDepa) {
        this.nombDepa = nombDepa;
    }

    @XmlTransient
    public List<Escuelas> getEscuelasList() {
        return escuelasList;
    }

    public void setEscuelasList(List<Escuelas> escuelasList) {
        this.escuelasList = escuelasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiDepa != null ? codiDepa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.codiDepa == null && other.codiDepa != null) || (this.codiDepa != null && !this.codiDepa.equals(other.codiDepa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelos.Departamentos[ codiDepa=" + codiDepa + " ]";
    }
    
}
