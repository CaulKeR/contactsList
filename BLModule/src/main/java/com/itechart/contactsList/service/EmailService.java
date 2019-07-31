package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.dto.EmailDTO;
import com.itechart.contactsList.dto.MessageTemplateDTO;
import org.stringtemplate.v4.ST;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailService {

    private static final String SERVICE_EMAIL = "contactslistservice@gmail.com";
    private static final String SERVICE_PASS = "GUQkedhX4QakhhW";

    public ContactDTO getByContactId(long id) {
        return new ContactDTO(new ContactDAOImpl().getEmailById(id));
    }

    public List<MessageTemplateDTO> getTemplates() {
        String hb = "Dear <name>,\n" +
                "I am writing to wish you a happy birthday. I hope that you enjoy the day.\n" +
                "Many happy returns!";
        List<MessageTemplateDTO> list = new ArrayList<>();
        MessageTemplateDTO template1 = new MessageTemplateDTO();
        template1.setTitle("Happy Birthday");
        template1.setText(hb);
        list.add(template1);
        String waiting = "Hello. <name>! We are waiting for you today. You remember it?";
        MessageTemplateDTO template2 = new MessageTemplateDTO();
        template2.setTitle("Waiting for you...");
        template2.setText(waiting);
        list.add(template2);
        return list;
    }

    public void sendEmail(EmailDTO emailDTO) {
        Transport transport = null;
        try (FileReader reader = new FileReader("email.properties")) {
            Properties emailProperties = new Properties();
            emailProperties.load(reader);
            for (int i = 0; i < emailDTO.getEmails().length; i++) {
                if (!emailDTO.getEmails()[i].equals("") && emailDTO.getEmails()[i] != null) {
                    Session mailSession = Session.getDefaultInstance(emailProperties, null);
                    MimeMessage emailMessage = new MimeMessage(mailSession);
                    emailMessage.setFrom(new InternetAddress(SERVICE_EMAIL));
                    emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDTO.getEmails()[i]));
                    emailMessage.setSubject(emailDTO.getSubject());
                    ST text = new ST(emailDTO.getText());
                    text.add("name", new ContactDAOImpl().getFirstNameByEmail(emailDTO.getEmails()[i]));
                    emailMessage.setContent(text.render(), "text/html");
                    transport = mailSession.getTransport("smtp");
                    if (transport != null) {
                        transport.connect("smtp.gmail.com", SERVICE_EMAIL, SERVICE_PASS);
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