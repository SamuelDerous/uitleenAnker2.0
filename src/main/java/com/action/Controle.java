/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import databank.TblProduct;
import databank.dao.ProductDao;
import java.util.List;

/**
 *
 * @author zenodotus
 */
public class Controle extends ActionSupport {
    
    private List<TblProduct> producten;
    
    public String execute() {
        ProductDao productdao = new ProductDao();
        producten = productdao.getTeControleren();
        
        return SUCCESS;
    }

    public List<TblProduct> getProducten() {
        return producten;
    }

    public void setProducten(List<TblProduct> producten) {
        this.producten = producten;
    }
    
    
    
}
