/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controladores;

import com.sv.udb.modelos.TipoUsuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Luis
 */
@Local
public interface TipoUsuariosFacadeLocal {

    void create(TipoUsuarios tipoUsuarios);

    void edit(TipoUsuarios tipoUsuarios);

    void remove(TipoUsuarios tipoUsuarios);

    TipoUsuarios find(Object id);

    List<TipoUsuarios> findAll();

    List<TipoUsuarios> findRange(int[] range);

    int count();
    
}
