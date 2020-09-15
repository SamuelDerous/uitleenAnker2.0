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
public class Producten extends ActionSupport {
    
    private List<TblProduct> producten;
    
    public String execute() {
        ProductDao dao = new ProductDao();
        producten = dao.getAlleProducten();
        return SUCCESS;
    }
}
