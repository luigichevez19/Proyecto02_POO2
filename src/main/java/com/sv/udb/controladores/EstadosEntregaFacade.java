/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controladores;

import com.sv.udb.modelos.EstadosEntrega;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luis
 */
@Stateless
public class EstadosEntregaFacade extends AbstractFacade<EstadosEntrega> implements EstadosEntregaFacadeLocal {

    @PersistenceContext(unitName = "Pro2Pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadosEntregaFacade() {
        super(EstadosEntrega.class);
    }
    
}
