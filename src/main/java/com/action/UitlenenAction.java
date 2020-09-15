/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

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
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 *
 * @author zenodotus
 */
public class UitlenenAction extends ActionSupport {

    private String gebruikersnaam;
    private Collection<Integer> productId;
    private Collection<String> aantal;
    private String productions;
    private String website;

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public Collection<Integer> getProductId() {
        return productId;
    }

    public void setProductId(Collection<Integer> productId) {
        this.productId = productId;
    }

    public Collection<String> getAantal() {
        return aantal;
    }

    public void setAantal(Collection<String> aantal) {
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
            String[] prod = productions.split(";");
            for (int i = 0; i < prod.length; i++) {
                String[] productCollectie = prod[i].split(":");
                String productNumber = productCollectie[0];
                String aantalNumber = productCollectie[1];
                if (!isInteger(productNumber)) {
                    correct = false;
                    addActionError("ProductId is geen correct getal");
                } else {
                    TblProduct product = productDao.getProductById(Integer.parseInt(productNumber));
                    TblUitleen uitlening = uitleenDao.getLaatsteUitlening(product);
                    if (product != null) {
                        Aantal aantallen = new Aantal();
                        int aantalUitlenen = aantallen.aantalUitgeleend(product);
                        int aantalReservaties = aantallen.aantalReservaties(product);
                        int maxUitleningen = aantallen.maxAantal(product);
                        if (aantalNumber != null && !isInteger(aantalNumber)) {
                            if (aantalNumber.equals("")) {
                                aantalNumber = "1";
                            } else {
                                correct = false;
                                addActionError("Aantal is geen correct getal");
                            }
                        }
                        if (aantalNumber != null && isInteger(aantalNumber)) {
                            if ((aantalUitlenen + Integer.parseInt(aantalNumber)) > maxUitleningen) {
                                isUitgeleend = true;
                                correct = false;
                            }
                            if (aantalReservaties + (Integer.parseInt(aantalNumber)) > maxUitleningen) {
                                isGereserveerd = true;
                                correct = false;
                            }
                        }
                        if (product.getVolledig() != 1) {
                            correct = false;
                            addActionError("Dit product is niet volledig en kan bijgevolg niet meer uitgeleend worden.");
                        } else if (product.getControle() != 1 || (uitlening != null && uitlening.getControle() != null && uitlening.getControle() == 0)) {
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
                        uitleen.setAantal(Integer.parseInt(aantalNumber));
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
    }
}
