/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import static creatie.Controle.isDate;
import static creatie.Controle.isInteger;
import static creatie.Controle.isNumeric;
import databank.TblUitleen;
import databank.dao.UitleenDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author zenodotus
 */
public class UitleenAanpassenAction extends ActionSupport {
    
    private int uitleen;
    private String uitleendatum;
    private String terugbrengdatum;
    private String aantal;
    private String boete;
    private String huurprijs;
    private String opmerking;
    private String teruggebracht;

    public int getUitleen() {
        return uitleen;
    }

    public void setUitleen(int uitleen) {
        this.uitleen = uitleen;
    }

    

    public String getUitleendatum() {
        return uitleendatum;
    }

    public void setUitleendatum(String uitleenDatum) {
        this.uitleendatum = uitleenDatum;
    }

    public String getTerugbrengdatum() {
        return terugbrengdatum;
    }

    public void setTerugbrengdatum(String terugbrengdatum) {
        this.terugbrengdatum = terugbrengdatum;
    }

    public String getAantal() {
        return aantal;
    }

    public void setAantal(String aantal) {
        this.aantal = aantal;
    }

    public String getBoete() {
        return boete;
    }

    public void setBoete(String boete) {
        this.boete = boete;
    }

    public String getHuurprijs() {
        return huurprijs;
    }

    public void setHuurprijs(String huurprijs) {
        this.huurprijs = huurprijs;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public String getTeruggebracht() {
        return teruggebracht;
    }

    public void setTeruggebracht(String teruggebracht) {
        this.teruggebracht = teruggebracht;
    }
    
    @Override
    public String execute() {
        return SUCCESS;
        
    }
    
    @Override
    public void validate() {
        UitleenDao uitleenDao = new UitleenDao();
        TblUitleen uitlening = uitleenDao.getUitleningen(uitleen);
        boolean correct = true;
        if(!isDate(uitleendatum)) {
                if(uitleendatum == null || uitleendatum.equals("")) {
                    java.util.Date nu = new java.util.Date();
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    
                    uitleendatum = formatter.format(nu);
                } else {
                    correct = false;
                    addActionError("De aankoopdatum dient een correcte datum te zijn.<br>");
                }
            }
        if(!isDate(terugbrengdatum)) {
                if(terugbrengdatum.equals("")) {
                    try {
                    GregorianCalendar cal = new GregorianCalendar();
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date dAankoopdatum = (java.util.Date) formatter.parse(uitleendatum);
        
                    cal.setTime((dAankoopdatum));
                    cal.add(GregorianCalendar.DAY_OF_YEAR, (uitlening.getSpel().getUitleentermijn() * 7));
                                        
                    uitleendatum = formatter.format(cal.getTime());
                    } catch(Exception ex) {
                        addActionError("Er is een fout opgetreden bij het omzetten van de uitleendatum<br>");
                        ex.printStackTrace();
                    }
                    
                } else {
                    correct = false;
                    addActionError("De aankoopdatum dient een correcte datum te zijn.<br>");
                }
            }
        if(!isInteger(aantal)) {
            if(aantal.equals("")) {
                aantal = "1";
            } else {
                correct = false;
                addActionError("Het aantal is geen geldig getal.<br>");
            }
        }
        
        if(!isNumeric(boete)) {
            if(boete.equals("")) {
                boete = "0.0";
            } else {
                correct = false;
                addActionError("Boete is geen correcte valuta.<br>");
            }
        }
        
        if(!isNumeric(huurprijs)) {
            if(huurprijs.equals("")) {
                huurprijs = "0.0";
            } else {
                correct = false;
                addActionError("Huurprijs is geen correcte valuta.<br>");
            }
        }
        if((teruggebracht != null) && (!isDate(teruggebracht))) {
            correct = false;
            addActionError("teruggebracht is geen correcte datum.<br>");
        
        }
        if(correct = true) {
            try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dUitleendatum = (java.util.Date) formatter.parse(uitleendatum);
            java.util.Date dTerugbrengdatum = (java.util.Date) formatter.parse(terugbrengdatum);
            
            uitlening.setUitleendatum(dUitleendatum);
            uitlening.setTerugbrengdatum(dTerugbrengdatum);
            uitlening.setAantal(Integer.parseInt(aantal));
            uitlening.setBoete(Double.parseDouble(boete));
            uitlening.setHuurprijs(Double.parseDouble(huurprijs));
            if(teruggebracht != null) {
                java.util.Date dTeruggebracht = (java.util.Date) formatter.parse(teruggebracht);
                uitlening.setTeruggebracht(dTeruggebracht);
            }
            uitlening.setOpmerking(opmerking);
            
                uitleenDao.aanpassen(uitlening);
            } catch(Exception ex) {
                addActionError("Er is een fout opgetreden bij het aanpassen van de uitlening.<br>");
                ex.printStackTrace();
            }
        }
    }
    
    
}
