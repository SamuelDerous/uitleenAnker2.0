/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databank.dao;

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
public class SoortDao {
    private Session session;
    
    public SoortDao() {
        SessionFactory factory = HibernateFactory.getSessionFactory();
        session = factory.openSession();
            
    }
    
    public List<TblSoort> getSoorten() {
        Query soort = session.createQuery("from TblSoort");
        List<TblSoort> soorten = soort.list();
       session.close();
       return soorten;
    }
}
