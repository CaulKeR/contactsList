package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;
import com.itechart.contactsList.dto.AttachmentDTO;

public class GetAttachmentInfoProcessor {

    public AttachmentDTO run(long id) {
        return new AttachmentDAOImpl().getAttachmentById(id);
    }
}
