/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import crypto.EncryptionIni;
import databank.TblPersoon;
import databank.TblSoort;
import databank.adapter.HibernateFactory;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author zenodotus
 */
public class PersoonDao {
    
    private Session session;
    
    public PersoonDao() {
        SessionFactory factory = HibernateFactory.getSessionFactory();
        session = factory.openSession();
            
    }
    
    public List<TblPersoon> login(String gebruikersnaam, String wachtwoord) {
        Query zoeken = session.createQuery("from TblPersoon where gebruikersnaam = :gebruikersnaam and wachtwoord = :wachtwoord");
        zoeken.setParameter("gebruikersnaam", gebruikersnaam);
        zoeken.setParameter("wachtwoord", wachtwoord);
        List<TblPersoon> personen = zoeken.list();
        return personen;

    }
    
    public TblPersoon getGebruiker(String gebruikersnaam) {
        try {
            Query zoeken = session.createQuery("from TblPersoon where gebruikersnaam = :gebruikersnaam");
            zoeken.setParameter("gebruikersnaam", gebruikersnaam);
            List<TblPersoon> personen = zoeken.list();
            return personen.get(0);
        } catch (Exception ex) {
            return null;
        }
            
    }
    
    public TblPersoon getGebruikerByMail(String mail) {
        try {
            Query zoeken = session.createQuery("from TblPersoon where eMail = :email");
            zoeken.setParameter("email", mail);
            List<TblPersoon> personen = zoeken.list();
            return personen.get(0);
        } catch (Exception ex) {
            return null;
        }
            
    }
    
    
    public boolean toevoegen(TblPersoon persoon) {
       try {
        session.beginTransaction();
        session.save(persoon);
        session.getTransaction().commit();
        return true;
       } catch(Exception ex) {
           ex.printStackTrace();
           return false;
       }
    }
    
    public boolean toevoegen(String gebruikersnaam, String voornaam, String naam, String wachtwoord, 
            String adres, String telefoon, String email, String soortnaam) {
        
        try {    
            TblPersoon persoon = new TblPersoon();
            persoon.setGebruikersnaam(gebruikersnaam);
            String encrWachtwoord = EncryptionIni.encrypt(wachtwoord);
            persoon.setWachtwoord(encrWachtwoord);
            persoon.setNaam(naam);
            persoon.setVoornaam(voornaam);
            persoon.setAdres(adres);
            persoon.setTelefoon(telefoon);
            TblSoort soort = new TblSoort();
            soort.setSoort(soortnaam);
            persoon.setSoort(soort);
            persoon.setEMail(email);
            session.beginTransaction();
            session.save(persoon);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
            
    }
    
    public boolean aanpassen(TblPersoon gebruiker) {
        try {
            
        
        Transaction tx = session.beginTransaction();
            TblPersoon persoon = (TblPersoon) session.load(TblPersoon.class, gebruiker.getGebruikersnaam());
            tx.commit();
            persoon.setNaam(gebruiker.getNaam());
            persoon.setVoornaam(gebruiker.getVoornaam());
            persoon.setAdres(gebruiker.getAdres());
            persoon.setTelefoon(gebruiker.getTelefoon());
            persoon.setEMail(gebruiker.getEMail());
            TblSoort tblSoort = new TblSoort();
            persoon.setSoort(gebruiker.getSoort());
            
            Transaction updatePersoon = session.beginTransaction();
            session.update(persoon);
            updatePersoon.commit();
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
            
            
    }
    
    public void aanpassenWachtwoord(String gebruikersnaam, String wachtwoord) {
            TblPersoon persoon = (TblPersoon) session.load(TblPersoon.class, gebruikersnaam);
            persoon.setWachtwoord(wachtwoord);
            Transaction updatePersoon = session.beginTransaction();
            session.update(persoon);
            updatePersoon.commit();
            
    }
    
    public List<TblPersoon> getAlleGebruikers() {
        Query zoeken = session.createQuery("from TblPersoon");
        List<TblPersoon> personen = zoeken.list();
        return personen;
    }
    
    public int verwijderGebruiker(String gebruikersnaam) {
            try {
            Transaction tx = session.beginTransaction();
            Query zoeken = session.createQuery("delete from TblPersoon where gebruikersnaam = :gebruikersnaam");
            zoeken.setParameter("gebruikersnaam", gebruikersnaam);
            int resultaat = zoeken.executeUpdate();
            tx.commit();
            return resultaat;
            } catch (Exception ex) {
                return -1;
            }
      
    }
}
