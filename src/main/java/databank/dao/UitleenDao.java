/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import databank.TblPersoon;
import databank.TblProduct;
import databank.TblUitleen;
import databank.adapter.HibernateFactory;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author zenodotus
 */
public class UitleenDao {
    private Session session;
    
    public UitleenDao() {
        SessionFactory factory = HibernateFactory.getSessionFactory();
        session = factory.openSession();
            
    }
    
    public int getOverzicht(TblProduct product, int jaar) {
        Query qryUitleningen = session.createQuery("SELECT sum(aantal) FROM TblUitleen where spel = :product and year(uitleendatum) = :jaar");
        qryUitleningen.setParameter("product", product);
        qryUitleningen.setParameter("jaar", jaar);
        List<Long> test = qryUitleningen.list();
        if(test.isEmpty()) {
            return 0;
        } else {
            if(test.get(0) != null) {
                return test.get(0).intValue();
            } else {
                return 0;
            }
        }
    }
    
    public int getAcademicOverzicht(TblProduct product, int jaar) {
        Query qryUitleningen = session.createQuery("SELECT sum(aantal) FROM TblUitleen where spel = :product and ((year(uitleendatum) = :jaar and month(uitleendatum) >= 9) or (year(uitleendatum) = :jaarPlus and month(uitleendatum) < 9))");
        qryUitleningen.setParameter("product", product);
        qryUitleningen.setParameter("jaar", jaar);
        qryUitleningen.setParameter("jaarPlus", jaar + 1);
        List<Long> test = qryUitleningen.list();
        if(test.isEmpty()) {
            return 0;
        } else {
            if(test.get(0) != null) {
                return test.get(0).intValue();
            } else {
                return 0;
            }
        }
    }
    
    public int getOverzicht(TblProduct product, int jaar, int maand) {
        Query qryUitleningen = session.createQuery("SELECT sum(aantal) FROM TblUitleen where spel = :product and year(uitleendatum) = :jaar and month(uitleendatum) = :maand");
        qryUitleningen.setParameter("product", product);
        qryUitleningen.setParameter("jaar", jaar);
        qryUitleningen.setParameter("maand", maand);
        List<Long> test = qryUitleningen.list();
        if(test.isEmpty()) {
            return 0;
        } else {
            if(test.get(0) != null) {
                return test.get(0).intValue();
            } else {
                return 0;
            }
        }
    }
    
    public List<TblUitleen> getActieveUitleningen(TblProduct product) {
        Query qryUitleningen = session.createQuery("from TblUitleen where spel = :product and (teruggebracht = null)"); // or teruggebracht = '')");
        qryUitleningen.setParameter("product", product);
        return qryUitleningen.list();
    }
    
    public List<TblUitleen> getActieveUitleningen() {
        Query qryUitleningen = session.createQuery("from TblUitleen where (teruggebracht = null)");
        
        return qryUitleningen.list();
    }
    
    public List<TblUitleen> getActieveUitleningenGebruiker(String gebruikersnaam) {
        Query qryGebruiker = session.createQuery("from TblPersoon where gebruikersnaam = :gebruikersnaam");
        qryGebruiker.setParameter("gebruikersnaam", gebruikersnaam);
        TblPersoon persoon = (TblPersoon) qryGebruiker.list().get(0);
        Query zoeken = session.createQuery("from TblUitleen where (teruggebracht = null or teruggebracht = '') and naam = :ontlener");
        zoeken.setParameter("ontlener", persoon);
        return zoeken.list();
    }
    
    
    public void toevoegenUitleen(TblUitleen uitleen) {
        session.beginTransaction();
        session.save(uitleen);
        session.getTransaction().commit();
        
    }
    
    public List<TblUitleen> getTeControleren() {
        Query qryProduct = session.createQuery("from TblUitleen where controle = 0 order by naam");
        return qryProduct.list();
    }
    
    public TblUitleen getUitleningen(int id) {
        
                
                Query zoeken = session.createQuery("from TblUitleen where id = :id");
                
                zoeken.setParameter("id", id);
                
                List<TblUitleen> uitleningen = zoeken.list();
                
                return uitleningen.get(0);
                
    }
    
    public TblUitleen getLaatsteUitlening(TblProduct product) {
        
                
                Query zoeken = session.createQuery("from TblUitleen where spel = :product order by teruggebracht");
                
                zoeken.setParameter("product", product);
                
                List<TblUitleen> uitleningen = zoeken.list();
                if(!uitleningen.isEmpty()) {
                    return uitleningen.get(uitleningen.size() - 1);
                } else {
                    return null;
                }
                
    }
    
    public void aanpassen(TblUitleen uitleen) {
            Transaction tx = session.beginTransaction();
            TblUitleen uitleenTabel = (TblUitleen) session.load(TblUitleen.class, uitleen.getId());
            tx.commit();
            uitleenTabel.setUitleendatum(uitleen.getUitleendatum());
            uitleenTabel.setTerugbrengdatum(uitleen.getTerugbrengdatum());
            uitleenTabel.setAantal(uitleen.getAantal());
            uitleenTabel.setBoete(uitleen.getBoete());
            uitleenTabel.setHuurprijs(uitleen.getHuurprijs());
            uitleenTabel.setTeruggebracht(uitleen.getTeruggebracht());
            uitleenTabel.setOpmerking(uitleen.getOpmerking());
            Transaction updateProduct = session.beginTransaction();
            session.update(uitleenTabel);
            updateProduct.commit();
            
            
            
    }
}
