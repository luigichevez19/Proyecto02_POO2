/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.Beans;

import com.sv.udb.controladores.EntregasFacadeLocal;
import com.sv.udb.modelos.Entregas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Named(value = "entregaBeans")
@ViewScoped
public class EntregaBeans implements Serializable{

    @EJB
    private EntregasFacadeLocal entregasFacade;

    private boolean guardado;
    private Entregas objeEntr;
    private List<Entregas> listEntr;
    
    /**
     * Creates a new instance of EntregaBeans
     */
    public EntregaBeans() {
    }

    public boolean isGuardado() {
        return guardado;
    }

    public Entregas getObjeEntr() {
        return objeEntr;
    }

    public void setObjeEntr(Entregas objeEntr) {
        this.objeEntr = objeEntr;
    }

    public List<Entregas> getListEntr() {
        return listEntr;
    }
    
    //Se ejecuta despues de que la página carga
    @PostConstruct
    public void init()
    {
        this.objeEntr = new Entregas();
        this.listEntr = this.entregasFacade.findAll();
    }
    
    public void nuev()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.objeEntr = new Entregas();
        this.guardado = true;
        ctx.execute("$('#modaFormTipo').modal('show')");
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        Map<String, String> mapaPrms = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int codi = Integer.parseInt(mapaPrms.get("id"));
        this.objeEntr = this.entregasFacade.find(codi);
        this.guardado = false;
        ctx.execute("$('#modaFormTipo').modal('show')");
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            this.objeEntr.setFecha(ahora);
            this.entregasFacade.create(this.objeEntr);
            this.listEntr.add(this.objeEntr);
            this.objeEntr = new Entregas();
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
            this.entregasFacade.edit(this.objeEntr);
            this.setItem(this.objeEntr);
            this.objeEntr = new Entregas();
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
            this.entregasFacade.remove(this.objeEntr);
            this.listEntr.remove(this.objeEntr);
            this.objeEntr = new Entregas();
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
    
    private void setItem(Entregas item)
    {
        int itemIndex = this.listEntr.indexOf(item);
            if (itemIndex != -1) {
            this.listEntr.set(itemIndex, item);
        }
    }
}
