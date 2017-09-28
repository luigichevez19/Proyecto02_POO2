/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controladores;

import com.sv.udb.modelos.EstadosEntrega;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Luis
 */
@Local
public interface EstadosEntregaFacadeLocal {

    void create(EstadosEntrega estadosEntrega);

    void edit(EstadosEntrega estadosEntrega);

    void remove(EstadosEntrega estadosEntrega);

    EstadosEntrega find(Object id);

    List<EstadosEntrega> findAll();

    List<EstadosEntrega> findRange(int[] range);

    int count();
    
}
