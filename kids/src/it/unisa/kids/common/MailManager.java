/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.kids.common;

import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Marco Moretti
 */
public class MailManager implements Observer {

    private String smtpServer;
    private String from;
    private String password;

    public MailManager() {
        from = "kids.asilo@gmail.com";
        smtpServer = "smtp.gmail.com";
        password = "progettokids";
    }

    @Override
    public void update(Observable o, Object arg) {
        sendMail((Mail) arg);
    }

    public void sendMail(Mail mail) {
        try {
            Properties props = System.getProperties();

            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", password);
            props.put("mail.smtp.host", smtpServer);
            props.put("mail.smtp.port", 25);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.EnableSSL.enable", "true");

            Session session = Session.getInstance(props, null);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            for (String to : mail.getTo()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }


            message.setSubject(mail.getSubject());
            message.setContent(mail.getBody(), "text/html");

            Transport transport = session.getTransport("smtp");
            try {
                transport.connect(smtpServer, from, password);
                transport.sendMessage(message, message.getAllRecipients());
            } finally {
                transport.close();
            }
        } catch (MessagingException ex) {
            Logger.getLogger(MailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
