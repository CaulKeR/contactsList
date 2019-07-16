package com.itechart.contactsList.dao;

public interface AttachmentDAO {
        long create(String fileName, long userId);
        String getFileName(long id);
        void delete(long id);
        long getUserId(long id);
}