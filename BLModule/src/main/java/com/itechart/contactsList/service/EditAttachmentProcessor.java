package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;
import com.itechart.contactsList.dto.AttachmentDTO;

public class EditAttachmentProcessor {

    public void run(AttachmentDTO attachment) {
        new AttachmentDAOImpl().update(attachment);
    }
}