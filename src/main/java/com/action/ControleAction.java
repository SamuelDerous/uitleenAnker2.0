/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import creatie.MailSend;
import databank.TblProduct;
import databank.TblReservatie;
import databank.TblUitleen;
import databank.dao.ProductDao;
import databank.dao.ReservatieDao;
import databank.dao.UitleenDao;
import java.util.GregorianCalendar;
import java.util.List;

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
        if (uitlening.getSpel().getVolledig() == 1) {
            ReservatieDao reservatieDao = new ReservatieDao();
            GregorianCalendar cal = new GregorianCalendar();
            List<TblReservatie> reservaties = reservatieDao.getReservatiesProductNotIn(uitlening.getSpel());
            for (int i = 0; i < reservaties.size() && i <= uitlening.getAantal(); i++) {
                System.out.println(reservaties.get(i).getReservatieDatum());
                if (reservaties.get(i).getBinnen() == null) {
                    reservaties.get(i).setBinnen(cal.getTime());
                    reservatieDao.updateBinnen(reservaties.get(i));
                    cal.add(GregorianCalendar.DAY_OF_YEAR, 14);
                    MailSend mail = new MailSend("smtp.scarlet.be", "noreply@anker.be", reservaties.get(i).getGebruiker().getEMail(), "Uw gereserveerd spel is binnen",
                            "Het gereserveerde spel <b>" + reservaties.get(i).getProduct().getNaam() + "</b> is binnen. Het blijft twee weken beschikbaar. Tot " + cal.getTime() + "</b><br>Met vriendelijke groeten<br>De Spelotheek Het anker");
                    mail.verzend();
                    break;
                }
            }
        }
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
