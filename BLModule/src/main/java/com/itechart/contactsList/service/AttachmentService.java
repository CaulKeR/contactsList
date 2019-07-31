package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;
import com.itechart.contactsList.dto.AttachmentDTO;
import com.itechart.contactsList.utility.Directories;
import org.apache.commons.fileupload.FileItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AttachmentService {

    public List<AttachmentDTO> getAll(long userId) {
        ArrayList<AttachmentDTO> list = new ArrayList<>();
        File[] listOfFiles = new File(Directories.ATTACHMENTS_DIRECTORY + userId).listFiles();
        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
                    long id = Long.parseLong(listOfFile.getName());
                    String fileName = attachmentDAO.getFileName(id);
                    if (fileName != null) {
                        AttachmentDTO attachment = new AttachmentDTO(id, userId, fileName, listOfFile.length(),
                                new Date(listOfFile.lastModified()));
                        list.add(attachment);
                    }
                } else if (listOfFile.isDirectory()) {
                    System.out.println("Directory " + listOfFile.getName());
                }
            }
        } else {
            System.out.println("Empty folder");
        }
        return list;
    }

    public void upload(List<FileItem> formItems, long userId) {
        String uploadPath = Directories.ATTACHMENTS_DIRECTORY + userId;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            if (!uploadDir.mkdir()) {
                System.err.println("Directory is not created!");
            }
        }
        try {

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + modifyFileName(fileName, userId);
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        System.out.println("File added: " + filePath);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit(AttachmentDTO attachment) {
        new AttachmentDAOImpl().update(attachment);
    }

    public void delete(long id) {
        new AttachmentDAOImpl().delete(id);
    }

    public AttachmentDTO getById(long id) {
        return new AttachmentDAOImpl().getAttachmentById(id);
    }

    public void download(long fileId, PrintWriter out) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        try (FileInputStream reader = new FileInputStream(Directories.ATTACHMENTS_DIRECTORY + attachmentDAO.getUserId(fileId) +
                File.separator + fileId)) {
            int temp;
            while ((temp = reader.read()) != -1) {
                out.write(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName(long fileId) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        return attachmentDAO.getFileName(fileId);
    }

    private String modifyFileName(String fileName, long userId) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        String newName = String.valueOf(attachmentDAO.create(fileName, userId));
        System.out.println(newName);
        return newName;
    }
}