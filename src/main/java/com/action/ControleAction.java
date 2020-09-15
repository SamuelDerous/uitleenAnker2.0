/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import databank.TblProduct;
import databank.TblUitleen;
import databank.dao.ProductDao;
import databank.dao.UitleenDao;

/**
 *
 * @author zenodotus
 */
public class ControleAction extends ActionSupport {

    private int productId;
    private int uitleningId;
    private String volledig;

    public String controleProduct() {
        ProductDao dao = new ProductDao();
        TblProduct product = dao.getProductById(productId);
        System.out.println(volledig);
        if (volledig != null) {
            if (volledig.equals("on")) {
                volledig = "1";
            } else {
                volledig = "0";
            }
        } else {
            volledig = "0";
        }

        product.setControle(1);
        product.setVolledig(Integer.parseInt(volledig));
        dao.aanpassen(product);
        return SUCCESS;
    }

    public String controleUitlening() {
        UitleenDao dao = new UitleenDao();
        TblUitleen uitlening = dao.getUitleningen(uitleningId);
        if (volledig != null) {
            if (volledig.equals("on")) {
                volledig = "1";
            } else {
                volledig = "0";
            }
        } else {
            volledig = "0";
        }

        uitlening.setControle(1);
        uitlening.getSpel().setVolledig(Integer.parseInt(volledig));
        dao.aanpassen(uitlening);
        return SUCCESS;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUitleningId() {
        return uitleningId;
    }

    public void setUitleningId(int uitleningId) {
        this.uitleningId = uitleningId;
    }

    public String getVolledig() {
        return volledig;
    }

    public void setVolledig(String volledig) {
        this.volledig = volledig;
    }

}
