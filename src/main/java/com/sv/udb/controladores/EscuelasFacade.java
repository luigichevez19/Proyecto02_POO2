/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controladores;

import com.sv.udb.modelos.Escuelas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luis
 */
@Stateless
public class EscuelasFacade extends AbstractFacade<Escuelas> implements EscuelasFacadeLocal {

    @PersistenceContext(unitName = "Pro2Pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EscuelasFacade() {
        super(Escuelas.class);
    }
    
}
