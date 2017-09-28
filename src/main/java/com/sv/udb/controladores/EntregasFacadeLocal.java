/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controladores;

import com.sv.udb.modelos.Entregas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Luis
 */
@Local
public interface EntregasFacadeLocal {

    void create(Entregas entregas);

    void edit(Entregas entregas);

    void remove(Entregas entregas);

    Entregas find(Object id);

    List<Entregas> findAll();

    List<Entregas> findRange(int[] range);

    int count();
    
}
