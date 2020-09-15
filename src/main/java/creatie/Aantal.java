/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatie;

import databank.TblInventarisatie;
import databank.TblProduct;
import databank.TblReservatie;
import databank.TblUitleen;
import databank.dao.UitleenDao;
import databank.dao.InventarisDao;
import databank.dao.ProductDao;
import databank.dao.ReservatieDao;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author zenodotus
 */
public class Aantal {
    
        
    public int aantalUitgeleend(TblProduct product) {
        UitleenDao uitleenDao = new UitleenDao();
        InventarisDao inventarisDao = new InventarisDao();
        List<TblUitleen> uitleningen = uitleenDao.getActieveUitleningen(product);
        List<TblInventarisatie> inventaris = inventarisDao.getInventarisProduct(product);
        int aantalUitlenen = 0;
        
        for(int i = 0; i < uitleningen.size(); i++) {
            aantalUitlenen += uitleningen.get(i).getAantal();
        }
          
        return aantalUitlenen;
    }
    
    public int maxReservaties(TblProduct product, Session session) {
            ReservatieDao reservatieDao = new ReservatieDao();
            InventarisDao inventarisDao = new InventarisDao();
            List<TblReservatie> reservaties = reservatieDao.getReservatiesProduct(product);
            List<TblInventarisatie> inventaris = inventarisDao.getInventarisProduct(product);
            int aantalUitlenen = 0;
            int aantalReservaties = 0;
            int aantalInvent = 0;
            
            for(int i = 0; i < reservaties.size(); i++) {
                aantalReservaties += reservaties.get(i).getAantal();
            }
            for(int i = 0; i < inventaris.size(); i++) {
                aantalInvent += inventaris.get(i).getAantal();
            }
            
            return aantalReservaties + aantalInvent;
    }
    public int aantalReservaties(TblProduct product) {
            ReservatieDao reservatieDao = new ReservatieDao();
            List<TblReservatie> reservaties = reservatieDao.getReservatiesProduct(product);
            int aantalReservaties = 0;
            
            for(int i = 0; i < reservaties.size(); i++) {
                aantalReservaties += reservaties.get(i).getAantal();
            }
            
            return aantalReservaties;
    }
    
    public int maxAantal(TblProduct product) {
        ProductDao productDao = new ProductDao();
        InventarisDao inventarisDao = new InventarisDao();
        int aantalProduct = 0;
        int aantalInvent = 0;
        TblProduct producten = productDao.getProductById(product.getId());
        List<TblInventarisatie> invents = inventarisDao.getInventarisProduct(product);
        aantalProduct = producten.getAantal();
        for(int i = 0; i < invents.size(); i++) {
            aantalInvent += invents.get(i).getAantal();
        }
        
        return (aantalProduct + aantalInvent);
    }
    
}
