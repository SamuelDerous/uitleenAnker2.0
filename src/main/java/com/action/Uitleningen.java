/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import databank.TblUitleen;
import databank.dao.UitleenDao;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zenodotus
 */
public class Uitleningen extends ActionSupport {
    
    private List<TblUitleen> uitleningen;
    
    public String execute() {
        UitleenDao dao = new UitleenDao();
        uitleningen = dao.getActieveUitleningen();
        return SUCCESS;
    }

    public List<TblUitleen> getUitleningen() {
        return uitleningen;
    }

    public void setUitleningen(List<TblUitleen> uitleningen) {
        this.uitleningen = uitleningen;
    }
    
    public boolean compareDates(Date newDate) {
        Date today = new Date();    
        return (today.equals(newDate) || today.after(newDate));
    }
    
    
}
