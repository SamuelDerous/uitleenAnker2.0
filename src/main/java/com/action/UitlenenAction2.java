/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import creatie.Aantal;
import static creatie.Controle.isInteger;
import databank.TblPersoon;
import databank.TblProduct;
import databank.TblUitleen;
import databank.dao.PersoonDao;
import databank.dao.ProductDao;
import databank.dao.UitleenDao;
import java.sql.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author zenodotus
 */
public class UitlenenAction2 extends ActionSupport {
    
    private String gebruikersnaam;
    private int productId;
    private String aantal;
    private String productions;
    private String website;

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getAantal() {
        return aantal;
    }

    public void setAantal(String aantal) {
        this.aantal = aantal;
    }

    

    public String getProductions() {
        return productions;
    }

    public void setProductions(String productions) {
        this.productions = productions;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    @Override
    public void validate() {
        boolean correct = true;
        boolean isUitgeleend = false;
        boolean isGereserveerd = false;
        PersoonDao persoonDao = new PersoonDao();
        ProductDao productDao = new ProductDao();
        UitleenDao uitleenDao = new UitleenDao();
        TblPersoon persoon = persoonDao.getGebruiker(gebruikersnaam);
        if (persoon == null) {
            correct = false;
            addActionError("Deze gebruiker bestaat niet in het systeem.");
        } else {
                //String[] productCollectie = prod[i].split(":");
                //String productNumber = productCollectie[0];
                //String aantalNumber = productCollectie[1];
                
                    TblProduct product = productDao.getProductById(productId);
                    if (product != null) {
                        TblUitleen controle = uitleenDao.getLaatsteUitlening(product);
                        
                        Aantal aantallen = new Aantal();
                        int aantalUitlenen = aantallen.aantalUitgeleend(product);
                        int aantalReservaties = aantallen.aantalReservaties(product);
                        int maxUitleningen = aantallen.maxAantal(product);
                        if (aantal != null && !isInteger(aantal)) {
                            if (aantal.equals("")) {
                                aantal = "1";
                            } else {
                                correct = false;
                                addActionError("Aantal is geen correct getal");
                            }
                        }
                        if (aantal != null && isInteger(aantal)) {
                            if ((aantalUitlenen + Integer.parseInt(aantal)) > maxUitleningen) {
                                isUitgeleend = true;
                                correct = false;
                            }
                            if (aantalReservaties + (Integer.parseInt(aantal)) > maxUitleningen) {
                                isGereserveerd = true;
                                correct = false;
                            }
                        }
                        if (product.getVolledig() != 1) {
                            correct = false;
                            addActionError("Dit product is niet volledig en kan bijgevolg niet meer uitgeleend worden.");
                        } else if (product.getControle() != 1 || (controle != null && controle.getControle() != null && controle.getControle() != 1)) {
                            correct = false;
                            addActionError("Dit product moet nog op hergebruik gecontroleerd worden.");
                        }

                    } else {
                        correct = false;
                        addActionError("Dit product bestaat niet in het systeem.");
                    }
                    if (correct) {
                        TblUitleen uitleen = new TblUitleen();

                        GregorianCalendar cal = new GregorianCalendar();
                        Date datum = new Date(cal.getTimeInMillis());
                        uitleen.setSpel(product);

                        uitleen.setNaam(persoon);
                        uitleen.setUitleendatum(datum);
                        uitleen.setAantal(Integer.parseInt(aantal));
                        GregorianCalendar calTermijn = new GregorianCalendar();
                        calTermijn.add(GregorianCalendar.DAY_OF_YEAR, 14);
                        Date termijn = new Date(calTermijn.getTimeInMillis());
                        uitleen.setTerugbrengdatum(termijn);

                        uitleenDao.toevoegenUitleen(uitleen);
                    } else {
                        if (isGereserveerd) {
                            addActionError("Het maximaal aantal is al gereserveerd.");
                        }
                        if (isUitgeleend) {
                            addActionError("Het maximaal aantal is al uitgeleend.");
                        }

                    }

                }
            }
        
    
}

    

