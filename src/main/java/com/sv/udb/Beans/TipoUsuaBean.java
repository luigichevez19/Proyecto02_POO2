/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.Beans;

import com.sv.udb.controladores.DepartamentosFacadeLocal;
import com.sv.udb.controladores.TipoUsuariosFacade;
import com.sv.udb.controladores.TipoUsuariosFacadeLocal;
import com.sv.udb.modelos.Departamentos;
import com.sv.udb.modelos.TipoUsuarios;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Luis
 */
@Named(value = "tipoUsuaBean")
@ViewScoped
public class TipoUsuaBean 
implements Serializable {
 @EJB
  private TipoUsuariosFacadeLocal tipoFacade;
    private boolean guardando;
    
    private TipoUsuarios objeTipo;
    private List<TipoUsuarios> listUsua;
    /**
     * Creates a new instance of TiposBean
     */
    //Chevez
    public TipoUsuaBean() {
    }

    public boolean isGuardando() {
        return guardando;
    }

    public List<TipoUsuarios> getListUsua() {
        return listUsua;
    }

    public TipoUsuarios getObjeTipo() {
        return objeTipo;
    }

    public void setObjeTipo(TipoUsuarios objeTipo) {
        this.objeTipo = objeTipo;
    }

  
    
    
    //Se ejecuta despues de que la página carga
    @PostConstruct
    public void init()
    {
        this.objeTipo = new TipoUsuarios();
        this.listUsua = this.tipoFacade.findAll();
    }
    
    public void nuev()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.objeTipo = new TipoUsuarios();
        this.guardando = true;
        ctx.execute("$('#modaFormTipo').modal('show')");
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        Map<String, String> mapaPrms = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int codi = Integer.parseInt(mapaPrms.get("codiDepa"));
       this.objeTipo = tipoFacade.find(codi);
        this.guardando = false;
        ctx.execute("$('#modaFormTipo').modal('show')");
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.tipoFacade.create(this.objeTipo);
            this.listUsua.add(this.objeTipo);
            this.objeTipo = new TipoUsuarios();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    public void edit()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.tipoFacade.edit(this.objeTipo);
            this.setItem(this.objeTipo);
            this.objeTipo = new TipoUsuarios();
            this.guardando = true;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No se modificó')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.tipoFacade.remove(this.objeTipo);
            this.listUsua.remove(this.objeTipo);
            this.objeTipo = new TipoUsuarios();
            this.guardando = true;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'No se eliminó')");
        }
        finally
        {
            
        }
    }
    
    private void setItem(TipoUsuarios item)
    {
        int itemIndex = this.listUsua.indexOf(item);
            if (itemIndex != -1) {
            this.listUsua.set(itemIndex, item);
        }
    }
    
}
