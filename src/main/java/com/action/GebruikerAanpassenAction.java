/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import static creatie.Controle.isNumeric;
import databank.TblPersoon;
import databank.TblSoort;
import databank.dao.PersoonDao;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zenodotus
 */
public class GebruikerAanpassenAction extends ActionSupport {
    
    private TblPersoon persoon;
    private String gebruikersnaam;
    private String redirectUrl;

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }
    
    
        

    public TblPersoon getPersoon() {
        return persoon;
    }

    public void setPersoon(TblPersoon persoon) {
        this.persoon = persoon;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
    
    
    
    public String execute() {
       
       
        
        return SUCCESS;
    }
    
    public void validate() {
       PersoonDao persoonDao = new PersoonDao();
       
      if(persoon != null) {
        if(persoon.getSoort() == null || persoon.getSoort().getSoort().equals("")) {
            TblSoort soort = new TblSoort();
            soort.setSoort("ontlener");
            persoon.setSoort(soort);
            }
        boolean correct = true;
        
        if(persoon.getEMail().isEmpty()) {
            correct = false;
            addActionError("Er dient een correct e-mailadres opgegeven te worden.<br>");
        }
        
        if((persoon.getTelefoon() != null && !persoon.getTelefoon().isEmpty()) && !isNumeric(persoon.getTelefoon())) {
            correct = false;
            addActionError("Het telefoonnummer dient uitsluitend uit cijfers te bestaan.<br>");
        }    
        if(correct == true) {
            
            if(!persoonDao.aanpassen(persoon)) {
                addActionError("Er is iets fout gegaan bij het verwerken van uw vraag");
                ValueStack stack = ActionContext.getContext().getValueStack();
            Map<String, TblPersoon> context = new HashMap<String, TblPersoon>();
            context.put("gebruiker", persoon);
            stack.push(context);
            }
        } else {
            ValueStack stack = ActionContext.getContext().getValueStack();
            Map<String, TblPersoon> context = new HashMap<String, TblPersoon>();
            context.put("gebruiker", persoon);
            stack.push(context);
             //redirectUrl = "gebruikers?gebruikersnaam=" + persoon.getGebruikersnaam() ;
             
        }
        }
    }
    
}
