/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import databank.TblBeschrijving;
import databank.TblPersoon;
import databank.TblProduct;
import databank.TblSoort;
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
public class ProductDao {
    private Session session;
    
    public ProductDao() {
        SessionFactory factory = HibernateFactory.getSessionFactory();
        session = factory.openSession();
            
    }
    
    public List<TblProduct> getAlleProducten() {
        Query qryProduct = session.createQuery("from TblProduct order by naam");
        return qryProduct.list();
    }
    
    public List<TblProduct> getTeControleren() {
        Query qryProduct = session.createQuery("from TblProduct where controle = 0 order by naam");
        return qryProduct.list();
    }
    
   public TblProduct getProductById(int id) {
        try {    
        Query qryProduct = session.createQuery("from TblProduct where id = :id");
            qryProduct.setParameter("id", id);
            TblProduct product = (TblProduct) qryProduct.list().get(0);
            
            return product;
        } catch(Exception ex) {
            return null;
        }
            
    }
    
    public int verwijderProduct(int id) {
            
            Transaction tx = session.beginTransaction();
            Query zoeken = session.createQuery("delete from TblProduct where id = :id");
            zoeken.setParameter("id", id);
            int resultaat = zoeken.executeUpdate();
            tx.commit();
            return resultaat;
      
    }
    
    public void aanpassen(TblProduct product) {
            Transaction tx = session.beginTransaction();
            TblProduct productTabel = (TblProduct) session.load(TblProduct.class, product.getId());
            tx.commit();
            productTabel.setAankoopprijs(product.getAankoopprijs());
            productTabel.setBreukprijs(product.getBreukprijs());
            productTabel.setAantal(product.getAantal());
            productTabel.setOpmerking(product.getOpmerking());
            productTabel.setAankoopdatum(product.getAankoopdatum());
            productTabel.setBeschrijving(product.getBeschrijving());
            productTabel.setWebsite(product.getWebsite());
            productTabel.setPlaats(product.getPlaats());
            productTabel.setVolledig(product.getVolledig());
            productTabel.setControle(product.getControle());
            productTabel.setUitleentermijn(product.getUitleentermijn());
            Transaction updateProduct = session.beginTransaction();
            session.update(productTabel);
            updateProduct.commit();
            session.close();
            
            
    }
    
    public void toevoegen(TblProduct product) {
        Transaction tx = session.beginTransaction();
        
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }
}
