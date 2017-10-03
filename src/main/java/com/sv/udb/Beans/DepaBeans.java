/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.Beans;

import com.sv.udb.controladores.DepartamentosFacadeLocal;
import com.sv.udb.modelos.Departamentos;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Luis
 */
@Named(value = "depaBeans")
@ViewScoped
public class DepaBeans implements Serializable {
 @EJB
  private DepartamentosFacadeLocal departamentosFacade;
    private boolean guardando;
    
    private Departamentos objeDepartamento;
    private List<Departamentos> listDepartamento;
    /**
     * Creates a new instance of TiposBean
     */
    public DepaBeans() {
    }

    public boolean isGuardando() {
        return guardando;
    }

    public Departamentos getObjeDepartamento() {
        return objeDepartamento;
    }

    public void setObjeDepartamento(Departamentos objeDepartamento) {
        this.objeDepartamento = objeDepartamento;
    }

    public List<Departamentos> getListDepartamento() {
        return listDepartamento;
    }
    
    
    //Se ejecuta despues de que la página carga
    @PostConstruct
    public void init()
    {
        this.objeDepartamento = new Departamentos();
        this.listDepartamento = this.departamentosFacade.findAll();
    }
    
    public void nuev()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.objeDepartamento = new Departamentos();
        this.guardando = true;
        ctx.execute("$('#modaFormDepa').modal('show')");
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        Map<String, String> mapaPrms = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int codi = Integer.parseInt(mapaPrms.get("codiDepa"));
       this.objeDepartamento = departamentosFacade.find(codi);
        this.guardando = false;
        ctx.execute("$('#modaFormDepa').modal('show')");
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.departamentosFacade.create(this.objeDepartamento);
            this.listDepartamento.add(this.objeDepartamento);
            this.objeDepartamento = new Departamentos();
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
            this.departamentosFacade.edit(this.objeDepartamento);
            this.setItem(this.objeDepartamento);
            this.objeDepartamento = new Departamentos();
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
            this.departamentosFacade.remove(this.objeDepartamento);
            this.listDepartamento.remove(this.objeDepartamento);
            this.objeDepartamento = new Departamentos();
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
    
    private void setItem(Departamentos item)
    {
        int itemIndex = this.listDepartamento.indexOf(item);
            if (itemIndex != -1) {
            this.listDepartamento.set(itemIndex, item);
        }
    }
    
}
