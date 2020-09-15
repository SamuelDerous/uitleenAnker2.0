/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import creatie.MailSend;
import databank.TblPersoon;
import databank.dao.PersoonDao;
import crypto.EncryptionIni;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 *
 * @author zenodotus
 */
public class SendPassword extends ActionSupport {
    
    private String email;
    private String gebruikersnaam;
    private TblPersoon gebruiker;
    
    @Override
    public String execute() throws GeneralSecurityException, IOException {
        if((email == null || email.isEmpty()) && (gebruikersnaam == null || gebruikersnaam.isEmpty())) {
            addActionError("Je dient ofwel een gebruikersnaam ofwel een e-mail of beide op te geven");
            return INPUT;
        }
        PersoonDao dao = new PersoonDao();
        if(email != null && !email.isEmpty()) {
            gebruiker = dao.getGebruikerByMail(email);
        }
        if(gebruiker == null && (gebruikersnaam != null && !gebruikersnaam.isEmpty())) {
            gebruiker = dao.getGebruiker(gebruikersnaam);
        }
        if(gebruiker == null) {
            addActionError("Het gebruikersnaam en de e-mail is niet gekend in ons systeem");
            return INPUT;
        }
        String onderwerp = "Je wachtwoord voor de spelotheek Het Anker";
        String bericht = "Uw inlog-gegevens zijn de volgende:<br><br><b>Gebruikersnaam: </b>" + gebruiker.getGebruikersnaam();
        bericht += "<br><b>Wachtwoord: </b>" + EncryptionIni.decrypt(gebruiker.getWachtwoord());
        MailSend mail = new MailSend("smtp.scarlet.be", "noreply@anker.be", gebruiker.getEMail(), onderwerp, bericht);
        mail.verzend();
        addActionMessage("Je wachtwoord is naar je opgegeven e-mail verzonden");
        return SUCCESS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public TblPersoon getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(TblPersoon gebruiker) {
        this.gebruiker = gebruiker;
    }
    
    
}
