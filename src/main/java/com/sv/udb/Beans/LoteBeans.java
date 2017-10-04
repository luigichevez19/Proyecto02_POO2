/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.Beans;

import com.sv.udb.controladores.LotesFacadeLocal;
import com.sv.udb.modelos.Lotes;
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
@Named(value = "loteBeans")
@ViewScoped
public class LoteBeans implements Serializable {
 @EJB
  private LotesFacadeLocal lotesFacade;
    private boolean guardando;
    
    private Lotes objeLote;
    private List<Lotes> listLote;

    public Lotes getObjeLote() {
        return objeLote;
    }

    public void setObjeLote(Lotes objeLote) {
        this.objeLote = objeLote;
    }

    public List<Lotes> getListLote() {
        return listLote;
    }

    public boolean isGuardando() {
        return guardando;
    }

     /**
     * Creates a new instance of LoteBeans
     */
    public LoteBeans() {
    }
    //Se ejecuta despues de que la página carga
    @PostConstruct
    public void init()
    {
        this.objeLote = new Lotes();
        this.listLote = this.lotesFacade.findAll();
    }
    
    public void nuev()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.objeLote = new Lotes();
        this.guardando = true;
        ctx.execute("$('#modaFormLote').modal('show')");
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        Map<String, String> mapaPrms = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int codi = Integer.parseInt(mapaPrms.get("codiLote"));
       this.objeLote = lotesFacade.find(codi);
        this.guardando = false;
        ctx.execute("$('#modaFormLote').modal('show')");
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeLote.setFechRegi(new java.util.Date());
            System.out.println(this.objeLote.getFechVenc());
             //this.objeLote.setFechVenc(new java.util.Date());
            this.lotesFacade.create(this.objeLote);
            this.listLote.add(this.objeLote);
            this.objeLote = new Lotes();
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
            this.lotesFacade.edit(this.objeLote);
            this.setItem(this.objeLote);
            this.objeLote = new Lotes();
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
            this.lotesFacade.remove(this.objeLote);
            this.listLote.remove(this.objeLote);
            this.objeLote = new Lotes();
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
    
    private void setItem(Lotes item)
    {
        int itemIndex = this.listLote.indexOf(item);
            if (itemIndex != -1) {
            this.listLote.set(itemIndex, item);
        }
    }
    
}


