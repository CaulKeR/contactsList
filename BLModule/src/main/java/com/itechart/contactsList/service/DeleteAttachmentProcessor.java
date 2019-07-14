package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;

public class DeleteAttachmentProcessor {

    public void run(long id) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        attachmentDAO.delete(id);
    }
}
