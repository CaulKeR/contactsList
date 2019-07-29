package com.itechart.contactsList.dao;

import com.itechart.contactsList.dto.AttachmentDTO;

public interface AttachmentDAO {
    long create(String fileName, long userId);
    String getFileName(long id);
    void delete(long id);
    long getUserId(long id);
    AttachmentDTO getAttachmentById(long id);
    void update(AttachmentDTO attachment);
}