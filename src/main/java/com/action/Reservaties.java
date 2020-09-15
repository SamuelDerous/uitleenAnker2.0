/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import databank.TblReservatie;
import databank.dao.ReservatieDao;
import java.util.List;

/**
 *
 * @author zenodotus
 */
public class Reservaties extends ActionSupport {
    
    private List<TblReservatie> reservaties;
    
    public String execute() {
        ReservatieDao dao = new ReservatieDao();
        reservaties = dao.getAlleReservaties();
        return SUCCESS;
    }

    public List<TblReservatie> getReservaties() {
        return reservaties;
    }

    public void setReservaties(List<TblReservatie> reservaties) {
        this.reservaties = reservaties;
    }
    
    
    
}
