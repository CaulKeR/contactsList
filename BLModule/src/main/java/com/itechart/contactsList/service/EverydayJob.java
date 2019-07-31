package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.dto.EmailDTO;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EverydayJob implements Job {

    private static final String ADMIN_EMAIL = "kirillkarpuk0@gmail.com";

    @Override
    public void execute(JobExecutionContext context) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println();
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        List<ContactDTO> contacts = contactDAO.getContactsByBirthDate(Date.valueOf(LocalDate.now()));
        EmailDTO email = new EmailDTO();
        email.setEmails(new String[]{ADMIN_EMAIL});
        email.setSubject("Everyday notification");
        if (contacts.size() == 0) {
            email.setText("Today no one has a birthday");
        } else {
            StringBuilder tempPart = new StringBuilder();
            for (ContactDTO contact : contacts) {
                tempPart.append(contact.getFirstName()).append(" ").append(contact.getSurname())
                        .append(contact.getPatronymic()).append(" - ").append(contact.getEmail())
                        .append(", ");
            }
            email.setText("Today is the birthday of: " + tempPart);
        }
        new EmailService().sendEmail(email);
    }
}