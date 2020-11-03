/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import databank.TblPersoon;
import databank.TblProduct;
import databank.TblReservatie;
import databank.adapter.HibernateFactory;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author zenodotus
 */
public class ReservatieDao {
    
    private Session session;
    
    public ReservatieDao() {
        SessionFactory factory = HibernateFactory.getSessionFactory();
        session = factory.openSession();
            
    }
    
    public List<TblReservatie> getReservatiesProduct(TblProduct product) {
        Query qryReservaties = session.createQuery("from TblReservatie where product = :product order by reservatieDatum");
        qryReservaties.setParameter("product", product);
        return qryReservaties.list();
    }
    
    public List<TblReservatie> getReservatiesGebruiker(String gebruikersnaam) {
        Query qryGebruiker = session.createQuery("from TblPersoon where gebruikersnaam = :gebruikersnaam");
        qryGebruiker.setParameter("gebruikersnaam", gebruikersnaam);
        TblPersoon persoon = (TblPersoon) qryGebruiker.list().get(0);
        Query qryReservaties = session.createQuery("from TblReservatie where gebruiker = :gebruiker");
        qryReservaties.setParameter("gebruiker", persoon);
        return qryReservaties.list();
    }
    
    public List<TblReservatie> getReservatiesGebruikerPerProduct(String gebruikersnaam, TblProduct product) {
        Query qryGebruiker = session.createQuery("from TblPersoon where gebruikersnaam = :gebruikersnaam");
        qryGebruiker.setParameter("gebruikersnaam", gebruikersnaam);
        TblPersoon persoon = (TblPersoon) qryGebruiker.list().get(0);
        Query qryReservaties = session.createQuery("from TblReservatie where gebruiker = :gebruiker and product = :product");
        qryReservaties.setParameter("gebruiker", persoon);
        qryReservaties.setParameter("product", product);
        //qryReservaties.setMaxResults(1);
        return qryReservaties.list();
    }
    
    
    
    public List<TblReservatie> getAlleReservaties() {
        Query qryReservaties = session.createQuery("from TblReservatie");
        
        return qryReservaties.list();
    }
    
    
    
    public void toevoegenReservatie(TblReservatie reservatie) {
         session.beginTransaction();
         session.save(reservatie);
         session.getTransaction().commit();
         
    }
    
    public int verwijderReservatie(int id) {
            
            Transaction tx = session.beginTransaction();
            Query zoeken = session.createQuery("delete from TblReservatie where id = :id");
            zoeken.setParameter("id", id);
            int resultaat = zoeken.executeUpdate();
            tx.commit();
            return resultaat;
      
    }

    public List<TblReservatie> getReservatieById(int reservatie) {
        Query qryReservaties = session.createQuery("from TblReservatie where id = :id");
        qryReservaties.setParameter("id", reservatie);
        return qryReservaties.list();
    }
    
    public void verwijderReservatie(TblReservatie reservatie) {
        session.beginTransaction();
        session.delete(reservatie);
        session.getTransaction().commit();
        session.close();
    }
    
    public void updateBinnen(TblReservatie reservatie) {
            Transaction tx = session.beginTransaction();
            TblReservatie reservatieTabel = (TblReservatie) session.load(TblReservatie.class, reservatie.getId());
            reservatieTabel.setBinnen(reservatie.getBinnen());
            session.update(reservatieTabel);
            tx.commit();
    }

    public List<TblReservatie> getReservatiesOutDated(GregorianCalendar now) {
            Query reservaties = session.createQuery("from TblReservatie where binnen < :datum");
            reservaties.setParameter("datum", new Date(now.getTimeInMillis()));
            return (List<TblReservatie>) reservaties.list();
            
    }

    public List<TblReservatie> getReservatiesProductNotIn(TblProduct product) {
         Query qryReservaties = session.createQuery("from TblReservatie where product = :product and binnen = null order by reservatieDatum");
        qryReservaties.setParameter("product", product);
        return qryReservaties.list();
    }
}
