/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import databank.TblInventarisatie;
import databank.TblPersoon;
import databank.TblProduct;
import databank.adapter.HibernateFactory;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author zenodotus
 */
public class InventarisDao {
    
    private Session session;
    
    public InventarisDao() {
        SessionFactory factory = HibernateFactory.getSessionFactory();
        session = factory.openSession();
            
    }
    
    public List<TblInventarisatie> getInventarisProduct(TblProduct product) {
        Query qryInventaris = session.createQuery("from TblInventarisatie where product = :product");
        qryInventaris.setParameter("product", product);
        return qryInventaris.list();
    }
    
    public List<TblInventarisatie> getInventaris() {
        Query qryInventaris = session.createQuery("from TblInventarisatie");
        
        return qryInventaris.list();
    }
    
    
    public int getInventarisProductAantal(TblProduct product) {
        Query qryInventaris = session.createQuery("from TblInventarisatie where product = :product");
        qryInventaris.setParameter("product", product);
        List<TblInventarisatie> inventaris = qryInventaris.list();
        int aantal = 0;
        for(int i = 0; i < inventaris.size(); i++) {
            aantal += inventaris.get(i).getAantal();
        }
        return aantal;
    }
    
    public int verwijderInvent(int id) {
            
            Transaction tx = session.beginTransaction();
            Query zoeken = session.createQuery("delete from TblInventarisatie where id = :id");
            zoeken.setParameter("id", id);
            int resultaat = zoeken.executeUpdate();
            tx.commit();
            return resultaat;
      
    }
    
    public void toevoegen(TblInventarisatie invent) {
        session.beginTransaction();
        session.save(invent);
        session.getTransaction().commit();
        session.close();
    }

}
