/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

import databank.TblBeschrijving;
import databank.TblSoort;
import databank.adapter.HibernateFactory;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author zenodotus
 */
public class BeschrijvingDao {
    private Session session;
    
    public BeschrijvingDao() {
        SessionFactory factory = HibernateFactory.getSessionFactory();
        session = factory.openSession();
            
    }
    
    public List<TblBeschrijving> getBeschrijvingen() {
        Query soort = session.createQuery("from TblBeschrijving");
        List<TblBeschrijving> soorten = soort.list();
       session.close();
       return soorten;
    }
}
