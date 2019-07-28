package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.dto.EmailDTO;
import com.itechart.contactsList.utility.Constants;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EverydayJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println();
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        List<ContactDTO> contacts = contactDAO.getContactsByBirthDate(Date.valueOf(LocalDate.now()));
        EmailDTO email = new EmailDTO();
        email.setEmails(new String[]{Constants.ADMIN_EMAIL});
        email.setSubject("Everyday notification");
        if (contacts.size() == 0) {
            email.setText("Today no one has a birthday");
        } else {
            String tempPart = "";
            for (int i = 0; i < contacts.size(); i++) {
                tempPart += contacts.get(i).getFirstName() + " " + contacts.get(i).getSurname() +
                        contacts.get(i).getPatronymic() + " - " + contacts.get(i).getEmail() + ", ";
            }
            email.setText("Today is the birthday of: " + tempPart);
        }
        new SendEmailProcessor().run(email);
    }
}