/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import databank.TblInventarisatie;
import databank.dao.InventarisDao;
import java.util.List;

/**
 *
 * @author zenodotus
 */
public class Inventaris extends ActionSupport {
    
    private List<TblInventarisatie> inventaris;
    
    public String execute() {
        InventarisDao dao = new InventarisDao();
        inventaris = dao.getInventaris();
        return SUCCESS;
    }

    public List<TblInventarisatie> getInventaris() {
        return inventaris;
    }

    public void setInventaris(List<TblInventarisatie> inventaris) {
        this.inventaris = inventaris;
    }
    
    
    
}
