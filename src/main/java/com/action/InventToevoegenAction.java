/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import static creatie.Controle.isDate;
import static creatie.Controle.isNumeric;
import databank.TblInventarisatie;
import databank.TblProduct;
import databank.dao.InventarisDao;
import databank.dao.ProductDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zenodotus
 */
public class InventToevoegenAction extends ActionSupport {
    
    private int product;
    private String aankoopdatum;
    private String aantal;
    private String opmerkingen;

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getAankoopdatum() {
        return aankoopdatum;
    }

    public void setAankoopdatum(String aankoopdatum) {
        this.aankoopdatum = aankoopdatum;
    }

    public String getAantal() {
        return aantal;
    }

    public void setAantal(String aantal) {
        this.aantal = aantal;
    }

    public String getOpmerkingen() {
        return opmerkingen;
    }

    public void setOpmerkingen(String opmerkingen) {
        this.opmerkingen = opmerkingen;
    }
    
    @Override
    public String execute() {
        return SUCCESS;
    }
    
    @Override
    public void validate() {
        boolean correct = true;
        if(!isDate(aankoopdatum)) {
                if(aankoopdatum.equals("")) {
                    Date nu = new Date();
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    
                    aankoopdatum = formatter.format(nu);
                } else {
                    correct = false;
                    addActionError("De aankoopdatum dient een correcte datum te zijn.<br>");
                }
            }
            if(!isNumeric(aantal))  {
                if(aantal.equals("")) {
                    aantal = "1";
                } else {
                    correct = false;
                    addActionError("Het aantal moet een correct getal zijn.<br>");
                }
            }
            if(correct == true) {
                try {
                ProductDao productDao = new ProductDao();
                TblProduct pr = productDao.getProductById(product);
                TblInventarisatie invent = new TblInventarisatie();
                invent.setProduct(pr);
                invent.setAantal(Integer.parseInt(aantal));
                invent.setOpmerking(opmerkingen);
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dAankoopdatum = (Date) formatter.parse(aankoopdatum);
                invent.setDatum(dAankoopdatum);
                InventarisDao inventDao = new InventarisDao();
                inventDao.toevoegen(invent);
                } catch(Exception ex) {
                    addActionError("Er is een fout opgetreden bij het toevoegen van het product.<br>");
                    ex.printStackTrace();
                }
            }
    }
}
