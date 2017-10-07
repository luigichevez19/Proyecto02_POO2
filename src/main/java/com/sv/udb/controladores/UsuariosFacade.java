/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controladores;

import com.sv.udb.modelos.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luis
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "Pro2Pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    @Override
    public Usuarios login( Usuarios usua)
    {
        Usuarios objeUsua = null;
        String consu ;
        try {
            consu ="FROM Usuarios u WHERE u.nombUsua = ?1 and u.contra = ?2";
            Query query = em.createQuery(consu);
            query.setParameter(1, usua.getNombUsua());
            query.setParameter(2, usua.getContra());
            
            List<Usuarios> list = query.getResultList();
            if(!list.isEmpty())
            {
                objeUsua = list.get(0);
            }
        } catch (Exception e) {
        }
        return objeUsua;
    }

    
}
