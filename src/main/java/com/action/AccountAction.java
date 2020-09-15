/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.Action;
import databank.TblProduct;
import databank.TblReservatie;
import databank.TblUitleen;
import databank.adapter.HibernateFactory;
import databank.dao.ProductDao;
import databank.dao.ReservatieDao;
import databank.dao.UitleenDao;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author zenodotus
 */
public class AccountAction implements Action {

    private int id;
    private TblProduct product;
    private List<TblReservatie> reservaties;
    private List<TblUitleen> uitleningen;
    
    SessionFactory factory = HibernateFactory.getSessionFactory();
                
                
    @Override
    public String execute() throws Exception {
        ProductDao productDao = new ProductDao();
        product = productDao.getProductById(id);
        ReservatieDao reservatieDao = new ReservatieDao();
        reservaties = reservatieDao.getReservatiesProduct(product);
        UitleenDao uitleenDao = new UitleenDao();
        uitleningen = uitleenDao.getActieveUitleningen(product);
        return SUCCESS;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TblProduct getProduct() {
        return product;
    }

    public void setProduct(TblProduct product) {
        this.product = product;
    }

    public List<TblReservatie> getReservaties() {
        return reservaties;
    }

    public void setReservaties(List<TblReservatie> reservaties) {
        this.reservaties = reservaties;
    }

    public List<TblUitleen> getUitleningen() {
        return uitleningen;
    }

    public void setUitleningen(List<TblUitleen> uitleningen) {
        this.uitleningen = uitleningen;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
    
    
    
    
}
