package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.EmailDTO;
import com.itechart.contactsList.utility.Constants;
import org.stringtemplate.v4.ST;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailProcessor {

    public void run(EmailDTO emailDTO) {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        Transport transport = null;
        try {
            for (int i = 0; i < emailDTO.getEmails().length; i++) {
                if (!emailDTO.getEmails()[i].equals("") && emailDTO.getEmails()[i] != null) {
                    Session mailSession = Session.getDefaultInstance(emailProperties, null);
                    MimeMessage emailMessage = new MimeMessage(mailSession);
                    emailMessage.setFrom(new InternetAddress(Constants.SERVICE_EMAIL));
                    emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDTO.getEmails()[i]));
                    emailMessage.setSubject(emailDTO.getSubject());
                    ST text = new ST(emailDTO.getText());
                    text.add("name", new ContactDAOImpl().getFirstNameByEmail(emailDTO.getEmails()[i]));
                    emailMessage.setContent(text.render(), "text/html");
                    transport = mailSession.getTransport("smtp");
                    if (transport != null) {
                        transport.connect("smtp.gmail.com", Constants.SERVICE_EMAIL, Constants.SERVICE_PASS);
                        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
                        System.out.println("E-mail was sent successfully to " + emailDTO.getEmails()[i]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    System.err.println(e);
                    e.getCause();
                }
            }
        }
    }
}
