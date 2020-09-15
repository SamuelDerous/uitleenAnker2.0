/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.Action;
import databank.dao.ReservatieDao;

/**
 *
 * @author zenodotus
 */
public class ReservatieVerwijderenAction implements Action {
    
    private int reservatie;

    public int getReservatie() {
        return reservatie;
    }

    public void setReservatie(int reservatie) {
        this.reservatie = reservatie;
    }
    
    @Override
    public String execute() throws Exception {
        ReservatieDao reservatieDao = new ReservatieDao();
        if(reservatieDao.verwijderReservatie(reservatie) != -1) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
    
}
