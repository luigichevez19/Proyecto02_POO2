/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.Beans;

import com.sv.udb.controladores.EscuelasFacadeLocal;
import com.sv.udb.modelos.Escuelas;
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
@Named(value = "escuelaBeans")
@ViewScoped
public class EscuelaBeans implements Serializable {
 @EJB
  private EscuelasFacadeLocal EscuelasFacade;
    private boolean guardando;
    
    private Escuelas objeEscuela;
    private List<Escuelas> listEscuela;

    public List<Escuelas> getListEscuela() {
        return listEscuela;
    }

    public Escuelas getObjeEscuela() {
        return objeEscuela;
    }

    public void setObjeEscuela(Escuelas objeEscuela) {
        this.objeEscuela = objeEscuela;
    }
    /**
     * Creates a new instance of EscuelaBeans
     */
    public EscuelaBeans() {
    }

    public boolean isGuardando() {
        return guardando;
    }

 
    
    //Se ejecuta despues de que la página carga
    @PostConstruct
    public void init()
    {
        this.objeEscuela = new Escuelas();
        this.listEscuela = this.EscuelasFacade.findAll();
    }
    
    public void nuev()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        this.objeEscuela = new Escuelas();
        this.guardando = true;
        ctx.execute("$('#modaFormEscu').modal('show')");
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        Map<String, String> mapaPrms = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int codi = Integer.parseInt(mapaPrms.get("codiEscu"));
       this.objeEscuela = EscuelasFacade.find(codi);
        this.guardando = false;
        ctx.execute("$('#modaFormEscu').modal('show')");
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.EscuelasFacade.create(this.objeEscuela);
            this.listEscuela.add(this.objeEscuela);
            this.objeEscuela = new Escuelas();
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
            this.EscuelasFacade.edit(this.objeEscuela);
            this.setItem(this.objeEscuela);
            this.objeEscuela = new Escuelas();
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
            this.EscuelasFacade.remove(this.objeEscuela);
            this.listEscuela.remove(this.objeEscuela);
            this.objeEscuela = new Escuelas();
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
    
    private void setItem(Escuelas item)
    {
        int itemIndex = this.listEscuela.indexOf(item);
            if (itemIndex != -1) {
            this.listEscuela.set(itemIndex, item);
        }
    }
    
}
