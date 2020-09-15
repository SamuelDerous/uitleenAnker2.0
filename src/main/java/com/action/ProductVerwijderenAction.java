/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.Action;
import databank.dao.ProductDao;

/**
 *
 * @author zenodotus
 */
public class ProductVerwijderenAction implements Action {
    
    private int productId;
    
    
    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String execute() throws Exception {
        ProductDao productDao = new ProductDao();
        if(productDao.verwijderProduct(productId) != -1) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
    
    
}
