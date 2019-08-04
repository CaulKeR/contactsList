package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;
import com.itechart.contactsList.dto.AttachmentDTO;
import com.itechart.contactsList.utility.ServerDirectories;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AttachmentService {

    private static final Logger log = Logger.getLogger(AttachmentService.class);

    public List<AttachmentDTO> getAll(Long userId) {
        ArrayList<AttachmentDTO> list = new ArrayList<>();
        File[] listOfFiles = new File(ServerDirectories.ATTACHMENTS_DIRECTORY + userId).listFiles();
        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
                    Long id = Long.parseLong(listOfFile.getName());
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
            log.info("Empty folder for id=" + userId);
        }
        return list;
    }

    public void upload(List<FileItem> formItems, Long userId) {
        String uploadPath = ServerDirectories.ATTACHMENTS_DIRECTORY + userId;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            if (!uploadDir.mkdir()) {
                log.info("Directory is not created for id=" + userId);
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
                        log.info("File added: " + filePath);
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

    public void delete(Long id) {
        new AttachmentDAOImpl().delete(id);
    }

    public AttachmentDTO getById(Long id) {
        return new AttachmentDAOImpl().getAttachmentById(id);
    }

    public void download(Long fileId, PrintWriter out) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        try (FileInputStream reader = new FileInputStream(ServerDirectories.ATTACHMENTS_DIRECTORY + attachmentDAO.getUserId(fileId) +
                File.separator + fileId)) {
            int temp;
            while ((temp = reader.read()) != -1) {
                out.write(temp);
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    public String getFileName(Long fileId) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        return attachmentDAO.getFileName(fileId);
    }

    private String modifyFileName(String fileName, Long userId) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        String newName = String.valueOf(attachmentDAO.create(fileName, userId));
        System.out.println(newName);
        return newName;
    }
}