/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import databank.TblProduct;
import databank.dao.ProductDao;

/**
 *
 * @author zenodotus
 */
public class OverzichtAction extends ActionSupport {
    
    private int productId;
    private String sort;
    private int jaar;
    private String kwartaal;
    private int maand;
    
    private TblProduct product;
    
    public String execute() {
        ProductDao productDao = new ProductDao();
        product = productDao.getProductById(productId);
        return SUCCESS;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public String getKwartaal() {
        return kwartaal;
    }

    public void setKwartaal(String kwartaal) {
        this.kwartaal = kwartaal;
    }

    public int getMaand() {
        return maand;
    }

    public void setMaand(int maand) {
        this.maand = maand;
    }

    public TblProduct getProduct() {
        return product;
    }

    public void setProduct(TblProduct product) {
        this.product = product;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    
    
    
}
