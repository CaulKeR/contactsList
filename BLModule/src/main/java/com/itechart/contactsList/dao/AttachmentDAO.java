package com.itechart.contactsList.dao;

import com.itechart.contactsList.dto.AttachmentDTO;

public interface AttachmentDAO {

    Long create(String fileName, Long userId);

    String getFileName(Long id);

    void delete(Long id);

    Long getUserId(Long id);

    AttachmentDTO getAttachmentById(Long id);

    void update(AttachmentDTO attachment);
}