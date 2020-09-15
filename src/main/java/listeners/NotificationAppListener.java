/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import creatie.MailSend;
import databank.TblUitleen;
import databank.adapter.HibernateFactory;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Web application lifecycle listener.
 *
 * @author zenodotus
 */
public class NotificationAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        TimerTask notTimer = new NotTimerTask();
        Calendar nu = Calendar.getInstance();
        nu.set(Calendar.HOUR_OF_DAY, 9);
        nu.set(Calendar.MINUTE, 10);
        nu.set(Calendar.SECOND, 0);
        Timer timer = new Timer();
        timer.schedule(notTimer, nu.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
        System.out.println(nu.get(Calendar.HOUR_OF_DAY));
    }
    
    

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context destroyed");
    }
    
    class NotTimerTask extends TimerTask {

        @Override
        public void run() {
           GregorianCalendar nu = new GregorianCalendar();
            nu.add(GregorianCalendar.DAY_OF_MONTH, 4);
            SessionFactory factory = HibernateFactory.getSessionFactory();
            Session session = factory.openSession();
            Query qryUitleningen = session.createQuery("from TblUitleen where (teruggebracht = null) and terugbrengdatum < :datum");
            qryUitleningen.setDate("datum", new Date(nu.getTimeInMillis()));
            List<TblUitleen> uitleningen = qryUitleningen.list();
            MailSend mail;
            for(int i = 0; i < uitleningen.size(); i++) {
                mail = new MailSend("smtp.scarlet.be", "noreply@anker.be", uitleningen.get(i).getNaam().getEMail(), "Vervaldatum bijna bereikt", 
                "volgende werk moet binnen zijn op " + uitleningen.get(i).getTerugbrengdatum() + ": <b>" + uitleningen.get(i).getSpel().getNaam() + "</b><br>Met vriendelijke groeten<br>De Spelotheek Het anker");
                mail.verzend();
                System.out.println(uitleningen.get(i).getNaam().getGebruikersnaam());
            }
            System.out.println("Mails verzonden");
            System.out.println(uitleningen.size());
            
            GregorianCalendar now = new GregorianCalendar();
            //now.add(GregorianCalendar.DAY_OF_MONTH, 4);
            qryUitleningen = session.createQuery("from TblUitleen where (teruggebracht = null) and terugbrengdatum < :datum");
            qryUitleningen.setDate("datum", new Date(now.getTimeInMillis()));
            uitleningen = qryUitleningen.list();
            for(int i = 0; i < uitleningen.size(); i++) {
                mail = new MailSend("smtp.scarlet.be", "noreply@anker.be", uitleningen.get(i).getNaam().getEMail(), "Vervaldatum bereikt", 
                "volgende werk moest binnen zijn op " + uitleningen.get(i).getTerugbrengdatum() + ": <b>" + uitleningen.get(i).getSpel().getNaam() + "</b><br>Met vriendelijke groeten<br>De Spelotheek Het anker");
                mail.verzend();
                //System.out.println(uitleningen.get(i).getNaam().getGebruikersnaam());
                Transaction tx = session.beginTransaction();
            TblUitleen uitleenTabel = (TblUitleen) session.load(TblUitleen.class, uitleningen.get(i).getId());
            tx.commit();
            Transaction updateProduct = session.beginTransaction();
            uitleenTabel.setMails(uitleenTabel.getMails() + 1); 
            session.update(uitleenTabel);
            updateProduct.commit();
            
            }
            System.out.println("Mails verzonden");
            System.out.println(uitleningen.size());
            
        }
        
    }
}
