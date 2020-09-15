/**
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Deze klasse wordt gebruikt voor het generen van een mail met de verlofdata
 * 
 */
package creatie;



import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author zenodotus
 */
public class MailSend {

    private String host;
    private String van;
    private String naar;
    private String onderwerp;
    private String bericht;

    public MailSend(String host, String van, String naar,
            String onderwerp, String bericht) {
        this.host = host;
        this.van = van;
        this.naar = naar;
        this.onderwerp = onderwerp;
        this.bericht = bericht;
    }

    public void verzend() {
        Properties mailProperties = System.getProperties();
        mailProperties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(mailProperties);
        session.getDebugOut();
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress adres = new InternetAddress(naar);
            
            message.setFrom(new InternetAddress(van));
            message.addRecipient(MimeMessage.RecipientType.TO, adres);
            message.setSubject(onderwerp);
            //message.setHeader("Content-Type", "text/html");
            BodyPart messageBodyPart = new MimeBodyPart();
            
            messageBodyPart.setText(bericht);
            messageBodyPart.setHeader("Content-Type", "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
         
         
           message.setContent(multipart);
           Transport.send(message);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Er is iets fout gegaan bij de verzending");
            ex.printStackTrace();
        }
    }

    
    
    
}
