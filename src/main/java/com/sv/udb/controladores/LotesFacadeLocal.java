/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controladores;

import com.sv.udb.modelos.Lotes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Luis
 */
@Local
public interface LotesFacadeLocal {

    void create(Lotes lotes);

    void edit(Lotes lotes);

    void remove(Lotes lotes);

    Lotes find(Object id);

    List<Lotes> findAll();

    List<Lotes> findRange(int[] range);

    int count();
    
}
