/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import static creatie.Controle.isDate;
import static creatie.Controle.isInteger;
import static creatie.Controle.isNumeric;
import databank.TblBeschrijving;
import databank.TblProduct;
import databank.dao.ProductDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zenodotus
 */
public class ProductAanpassenAction extends ActionSupport {
    
    private int productId;
    private String naam;
    private String aankoopprijs;
    private String breukprijs;
    private String aankoopdatum;
    private String aantal;
    private String beschrijving;
    private String opmerking;
    private String website;
    private String uitleentermijn;
    private String plaats;
    private String volledig;
    private String controle;
    private String actie;

    public String getActie() {
        return actie;
    }

    public void setActie(String actie) {
        this.actie = actie;
    }
    
    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAankoopprijs() {
        return aankoopprijs;
    }

    public void setAankoopprijs(String aankoopprijs) {
        this.aankoopprijs = aankoopprijs;
    }

    public String getBreukprijs() {
        return breukprijs;
    }

    public void setBreukprijs(String breukprijs) {
        this.breukprijs = breukprijs;
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

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUitleentermijn() {
        return uitleentermijn;
    }

    public void setUitleentermijn(String uitleentermijn) {
        this.uitleentermijn = uitleentermijn;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getVolledig() {
        return volledig;
    }

    public void setVolledig(String volledig) {
        this.volledig = volledig;
    }
    
    
    
    public String execute() {
        return SUCCESS;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }
    
    
    
    public void validate() {
        boolean correct = true;
        double dbAankoopprijs = 0, dbBreukprijs = 0;
        int intAantal = 0;
        int intUitleentermijn = 0;
        if(volledig != null) {   
        if(volledig.equals("on")) {
            volledig = "1";
        } else {
            volledig = "0";
        }      
        } else {
                volledig = "0";
                }
   
        
        if(!isNumeric(aankoopprijs)) {
            if(aankoopprijs.equals("")) {
                aankoopprijs = "0";
            } else {
                correct = false;
                addActionError("De aankoopprijs dient een correct valuta te zijn.<br>");
            }
        }
        
            if(!isNumeric(breukprijs)) {
                if(breukprijs.equals("")) {
                    breukprijs = "0";
                } else {
                    correct = false;
                    addActionError("De breukprijs dient een correct valuta te zijn.<br>");
                }
            }
        if(!isInteger(aantal)) {
                if(aantal.equals("")) {
                    aantal = "1";
                } else {
                    correct = false;
                    addActionError("Het aantal dient een correct getal te zijn.<br>");
                }
            }
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
            if(!isInteger(uitleentermijn))  {
                if(uitleentermijn.equals("")) {
                    uitleentermijn = "2";
                } else {
                    correct = false;
                    addActionError("De uitleentermijn moet een correct getal zijn.<br>");
                }
            }
            if(naam == null || naam.length() <= 0) {
                correct = false;
                addActionError("U dient een naam van het product op te geven");
            }
            if(correct == true) {
            try {
            dbAankoopprijs = Double.parseDouble(aankoopprijs);
            dbBreukprijs = Double.parseDouble(breukprijs);
            intAantal = Integer.parseInt(aantal);
            intUitleentermijn = Integer.parseInt(aantal);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dAankoopdatum = (Date) formatter.parse(aankoopdatum);
            TblProduct product = new TblProduct();
            
            product.setNaam(naam);
            product.setAankoopprijs(dbAankoopprijs);
            product.setBreukprijs(dbBreukprijs);
            product.setAantal(intAantal);
            product.setWebsite(website);
            product.setUitleentermijn(Integer.parseInt(uitleentermijn));
            product.setOpmerking(opmerking);
            product.setAankoopdatum(dAankoopdatum);
            TblBeschrijving tblBeschrijving = new TblBeschrijving();
            tblBeschrijving.setSoort(beschrijving);
            product.setBeschrijving(tblBeschrijving);
            product.setPlaats(plaats);
            product.setVolledig(Integer.parseInt(volledig));
            
            ProductDao productDao = new ProductDao();
            if(actie.equals("aanpassen")) {
                product.setId(productId);
                product.setControle(1);
                productDao.aanpassen(product);
            } else if(actie.equals("toevoegen")) {
                product.setControle(1);
                productDao.toevoegen(product);
            } else {
                addActionError("Er is een fout geslopen in het verwerken van de aanvraag.<br>");
                
            product = productDao.getProductById(productId);
            ValueStack stack = ActionContext.getContext().getValueStack();
            Map<String, TblProduct> context = new HashMap<String, TblProduct>();
            context.put("product", product);
            stack.push(context);
            Map<String, String> action = new HashMap<String, String>();
            action.put("actie", actie);
            stack.push(action);
            }
            } catch(Exception ex) {
                addActionError("Er is een fout opgetreden bij het verwerken van de aanvraag.<br>");
                ex.printStackTrace();
                ProductDao productDao = new ProductDao();
            TblProduct product = productDao.getProductById(productId);
            ValueStack stack = ActionContext.getContext().getValueStack();
            Map<String, TblProduct> context = new HashMap<String, TblProduct>();
            context.put("product", product);
            stack.push(context);
            Map<String, String> action = new HashMap<String, String>();
            action.put("actie", actie);
            stack.push(action);
            }
            } else {
            ProductDao productDao = new ProductDao();
            TblProduct product = productDao.getProductById(productId);
            ValueStack stack = ActionContext.getContext().getValueStack();
            Map<String, TblProduct> context = new HashMap<String, TblProduct>();
            context.put("product", product);
            stack.push(context);
            Map<String, String> action = new HashMap<String, String>();
            action.put("actie", actie);
            stack.push(action);
            
            }
    }
    
}
