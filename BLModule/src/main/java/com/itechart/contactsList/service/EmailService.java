package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.dto.EmailDTO;
import com.itechart.contactsList.dto.MessageTemplateDTO;
import org.apache.log4j.Logger;
import org.stringtemplate.v4.ST;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailService {

    private static final Logger log = Logger.getLogger(EmailService.class);
    private static final String SERVICE_EMAIL = "contactslistservice@gmail.com";
    private static final String SERVICE_PASS = "GUQkedhX4QakhhW";

    public ContactDTO getByContactId(Long id) {
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
        try (InputStream in = EmailService.class.getResourceAsStream("/email.properties")) {
            Properties emailProperties = new Properties();
            emailProperties.load(in);
            for (int i = 0; i < emailDTO.getEmails().length; i++) {
                if (!emailDTO.getEmails()[i].equals("") && emailDTO.getIds()[i] != 0L) {
                    Session mailSession = Session.getDefaultInstance(emailProperties, null);
                    MimeMessage emailMessage = new MimeMessage(mailSession);
                    emailMessage.setFrom(new InternetAddress(SERVICE_EMAIL));
                    emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(new ContactDAOImpl()
                            .getEmailById(emailDTO.getIds()[i])));
                    emailMessage.setSubject(emailDTO.getSubject(), "UTF-8");
                    ST text = new ST(emailDTO.getText().trim());
                    text.add("name", new ContactDAOImpl().getFirstNameById(emailDTO.getIds()[i]));
                    emailMessage.setText(text.render(), "UTF-8");
                    transport = mailSession.getTransport("smtp");
                    if (transport != null) {
                        transport.connect("smtp.gmail.com", SERVICE_EMAIL, SERVICE_PASS);
                        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
                        log.info("E-mail was sent successfully to " + emailDTO.getEmails()[i]);
                    } else {
                        log.error("transport object is null");
                    }
                } else {
                    log.error("Email or id is null for id=" + emailDTO.getIds()[i]);
                }
            }
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    log.error(e);
                }
            }
        }
    }
}