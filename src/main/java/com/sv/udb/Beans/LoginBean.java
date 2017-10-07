/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.Beans;

import com.sv.udb.controladores.UsuariosFacadeLocal;
import com.sv.udb.modelos.Usuarios;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Oscar
 */
@Named(value = "loginBean")
@ViewScoped
public class LoginBean implements Serializable{

    /**
     * Creates a new instance of LoginBean
     */
    @EJB
    private UsuariosFacadeLocal usuaFacade;
    
    private Usuarios objeUsuario;
    public LoginBean() {       
    }

    public Usuarios getObjeUsuario() {
        return objeUsuario;
    }

    public void setObjeUsuario(Usuarios objeUsuario) {
        this.objeUsuario = objeUsuario;
    }

    @PostConstruct
    public void init()
    {
        this.objeUsuario = new Usuarios();
               
    }
    
    
    //Metodos Agregado
    public String login()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página        
        Usuarios obje;        
        try
        {
           obje = usuaFacade.login(this.objeUsuario);
           
            if(obje != null)
            {
                //SE CREA LA SESION
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", obje);
                /////////////
                ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Acceso concedido')");
                if(obje.getCodiTipo().getNombTipo().equals("Master"))
                {
                    return "menu?faces-redirect=true";
                }
                else
                {
                    return "menu?faces-redirect=true";
                }
               
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN
                    ,"Aviso", "Usuario y contraseña incorrecto"));
                
                return "";
            }
        }
        catch(Exception ex)
        {
                ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar ')");   
                return "";
        }             
    }
    
    //METODO PARA VERIFICAR SI TIENE PERMISO DE MASTER 
    public void verifiSesMast()
    {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();            
            Usuarios us =(Usuarios) contex.getExternalContext().getSessionMap().get("usuario");
            //Verificando si existe
            if(us != null)
            {
                //VERIFICANDO SI ES USUARIO MASTER
                if(!us.getCodiTipo().getNombTipo().equals("Master"))
                {
                    contex.getExternalContext().redirect("menu.xhtml");
                }               
                
            }
            else
            {
                contex.getExternalContext().redirect("login.xhtml");
            }
        } 
        catch (Exception e) 
        {
            
        }
    }
    
    //Verificar sesion en paginas que son compartidas por los dos tipos de usuarios
    public void verifiSes()
    {
        try {
            FacesContext contex = FacesContext.getCurrentInstance();            
            Usuarios us =(Usuarios) contex.getExternalContext().getSessionMap().get("usuario");
            if(us == null)
            {
                contex.getExternalContext().redirect("login.xhtml");
            }
        } 
        catch (Exception e) 
        {
            
        }
    }
     
    //verificar si es usuario master y mostrar las opciones en el MENU
    public boolean verifiMasterMenu()
    {
        boolean estad = false;
         try {
            FacesContext contex = FacesContext.getCurrentInstance();            
            Usuarios us =(Usuarios) contex.getExternalContext().getSessionMap().get("usuario");
            //Verificando si existe
            
                //VERIFICANDO SI ES USUARIO MASTER
                if(us.getCodiTipo().getNombTipo().equals("Master"))
                {
                    estad = true;
                    return estad;
                }
                else
                {
                    return estad;
                }                            
            
        } 
        catch (Exception e) 
        {
            
        }
         return estad;
   
    }
    
    //METODO DE CERRAR SESSION
    public String cerrarSesion()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }
}
