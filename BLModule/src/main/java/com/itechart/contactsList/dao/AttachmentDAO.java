package com.itechart.contactsList.dao;

public interface AttachmentDAO {
        long create(String fileName);
        String getFileName(long id);
        void delete(long id);
}