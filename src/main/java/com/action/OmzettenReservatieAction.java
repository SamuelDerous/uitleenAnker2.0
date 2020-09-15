/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import creatie.Aantal;
import databank.TblReservatie;
import databank.TblUitleen;
import databank.dao.ReservatieDao;
import databank.dao.UitleenDao;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author zenodotus
 */
public class OmzettenReservatieAction extends ActionSupport {
    
    private int reservatie;

    public int getReservatie() {
        return reservatie;
    }

    public void setReservatie(int reservatie) {
        this.reservatie = reservatie;
    }
    
    @Override
    public String execute() {
        return SUCCESS;
    }
    
    @Override
    public void validate() {
        ReservatieDao reservatieDao = new ReservatieDao();
        List<TblReservatie> reservaties = reservatieDao.getReservatieById(reservatie);
        Aantal aantallen = new Aantal();
        int aantalUitleningen = aantallen.aantalUitgeleend(reservaties.get(0).getProduct());
            if(!reservaties.isEmpty()) {
                int a = reservaties.get(0).getAantal();
                if((aantalUitleningen >= reservaties.get(0).getAantal())) {
                    addActionError("Het maximaal aantal uitleningen voor dit product is bereikt.<br>");
                
                } else {
                TblUitleen uitlening = new TblUitleen();
                uitlening.setNaam(reservaties.get(0).getGebruiker());
                uitlening.setSpel(reservaties.get(0).getProduct());
                uitlening.setAantal(reservaties.get(0).getAantal());
                GregorianCalendar cal = new GregorianCalendar();
                Date datum = new Date(cal.getTimeInMillis());
                uitlening.setUitleendatum(datum);
                GregorianCalendar calTermijn = new GregorianCalendar();
                int termijnUitleen;
                if(reservaties.get(0).getProduct().getUitleentermijn() == null || reservaties.get(0).getProduct().getUitleentermijn().equals("")) {
                    termijnUitleen = 28;
                } else {
                    termijnUitleen = reservaties.get(0).getProduct().getUitleentermijn() * 7;
                }
                calTermijn.add(GregorianCalendar.DAY_OF_YEAR, termijnUitleen);
                Date termijn = new Date(calTermijn.getTimeInMillis());
                uitlening.setTerugbrengdatum(termijn);
                UitleenDao uitleenDao = new UitleenDao();
                uitleenDao.toevoegenUitleen(uitlening);
                reservatieDao.verwijderReservatie(reservaties.get(0));
                
                }
            }
    }
    
}
