/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "lotes", catalog = "vaso_leche", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lotes.findAll", query = "SELECT l FROM Lotes l")
    , @NamedQuery(name = "Lotes.findByCodiLote", query = "SELECT l FROM Lotes l WHERE l.codiLote = :codiLote")
    , @NamedQuery(name = "Lotes.findByCant", query = "SELECT l FROM Lotes l WHERE l.cant = :cant")
    , @NamedQuery(name = "Lotes.findByFechVenc", query = "SELECT l FROM Lotes l WHERE l.fechVenc = :fechVenc")
    , @NamedQuery(name = "Lotes.findByFechRegi", query = "SELECT l FROM Lotes l WHERE l.fechRegi = :fechRegi")
    , @NamedQuery(name = "Lotes.findByPrec", query = "SELECT l FROM Lotes l WHERE l.prec = :prec")})
public class Lotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codi_lote")
    private Integer codiLote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cant")
    private int cant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_venc")
    @Temporal(TemporalType.DATE)
    private Date fechVenc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fech_regi")
    @Temporal(TemporalType.DATE)
    private Date fechRegi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prec")
    private int prec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codiLote", fetch = FetchType.EAGER)
    private Collection<Entregas> entregasCollection;

    public Lotes() {
    }

    public Lotes(Integer codiLote) {
        this.codiLote = codiLote;
    }

    public Lotes(Integer codiLote, int cant, Date fechVenc, Date fechRegi, int prec) {
        this.codiLote = codiLote;
        this.cant = cant;
        this.fechVenc = fechVenc;
        this.fechRegi = fechRegi;
        this.prec = prec;
    }

    public Integer getCodiLote() {
        return codiLote;
    }

    public void setCodiLote(Integer codiLote) {
        this.codiLote = codiLote;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public Date getFechVenc() {
        return fechVenc;
    }

    public void setFechVenc(Date fechVenc) {
        this.fechVenc = fechVenc;
    }

    public Date getFechRegi() {
        return fechRegi;
    }

    public void setFechRegi(Date fechRegi) {
        this.fechRegi = fechRegi;
    }

    public int getPrec() {
        return prec;
    }

    public void setPrec(int prec) {
        this.prec = prec;
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
        hash += (codiLote != null ? codiLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lotes)) {
            return false;
        }
        Lotes other = (Lotes) object;
        if ((this.codiLote == null && other.codiLote != null) || (this.codiLote != null && !this.codiLote.equals(other.codiLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sv.udb.modelos.Lotes[ codiLote=" + codiLote + " ]";
    }
    
}
