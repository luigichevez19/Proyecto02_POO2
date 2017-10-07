/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.Beans;

import com.sv.udb.controladores.EstadosEntregaFacadeLocal;
import com.sv.udb.modelos.EstadosEntrega;
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
 * @author walte
 */
@Named(value = "estadosEntrega")
@ViewScoped
public class EstadosEntregaBeans implements Serializable{

    @EJB
    private EstadosEntregaFacadeLocal estadosEntregaFacade;    
    
    private boolean guardado;
    private EstadosEntrega objeEsta;
    private List<EstadosEntrega> listEsta;
    
    /**
     * Creates a new instance of EstadosEntrega
     */
    public EstadosEntregaBeans() {
    }

    public boolean isGuardado() {
        return guardado;
    }

    public EstadosEntrega getObjeEsta() {
        return objeEsta;
    }

    public void setObjeEsta(EstadosEntrega objeEsta) {
        this.objeEsta = objeEsta;
    }

    public List<EstadosEntrega> getListEsta() {
        return listEsta;
    }
    
    //Se ejecuta despues de que la página carga
    @PostConstruct
    public void init()
    {
        this.objeEsta = new EstadosEntrega();
        this.listEsta = this.estadosEntregaFacade.findAll();
    }
    
    public void nuev()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.objeEsta = new EstadosEntrega();
        this.guardado = true;
        ctx.execute("$('#modaFormTipo').modal('show')");
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        Map<String, String> mapaPrms = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int codi = Integer.parseInt(mapaPrms.get("id"));
        this.objeEsta = this.estadosEntregaFacade.find(codi);
        this.guardado = false;
        ctx.execute("$('#modaFormTipo').modal('show')");
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.estadosEntregaFacade.create(this.objeEsta);
            this.listEsta.add(this.objeEsta);
            this.objeEsta = new EstadosEntrega();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
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
            this.estadosEntregaFacade.edit(this.objeEsta);
            this.setItem(this.objeEsta);
            this.objeEsta = new EstadosEntrega();
            this.guardado = true;
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
            this.estadosEntregaFacade.remove(this.objeEsta);
            this.listEsta.remove(this.objeEsta);
            this.objeEsta = new EstadosEntrega();
            this.guardado = true;
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
    
    private void setItem(EstadosEntrega item)
    {
        int itemIndex = this.listEsta.indexOf(item);
            if (itemIndex != -1) {
            this.listEsta.set(itemIndex, item);
        }
    }
}
