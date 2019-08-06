package com.itechart.contactsList.web;

import com.itechart.contactsList.web.impl.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UrlMapper {

    private static final Logger log = Logger.getLogger(UrlMapper.class);
    private HashMap<Pattern, Executable> executors = new HashMap<>();

    public UrlMapper() {
        executors.put(Pattern.compile("GET/api/contacts"), new MainContactsInfo());
        executors.put(Pattern.compile("POST/api/contact"), new CreateContact());
        executors.put(Pattern.compile("GET/api/contact/\\d+"), new ContactById());
        executors.put(Pattern.compile("DELETE/api/contact/\\d+"), new DeleteContact());
        executors.put(Pattern.compile("PUT/api/contact/\\d+"), new EditContact());
        executors.put(Pattern.compile("GET/api/contact/\\d+/attachments"), new Attachments());
        executors.put(Pattern.compile("POST/api/contact/\\d+/attachments"), new AddAttachment());
        executors.put(Pattern.compile("DELETE/api/attachment/\\d+"), new DeleteAttachment());
        executors.put(Pattern.compile("GET/api/attachment/\\d+"), new DownloadAttachment());
        executors.put(Pattern.compile("POST/api/search"), new SearchContacts());
        executors.put(Pattern.compile("POST/api/contact/\\d+/photo"), new UploadAvatar());
        executors.put(Pattern.compile("GET/api/contact/\\d+/photo"), new GetAvatar());
        executors.put(Pattern.compile("GET/api/contact/\\d+/mail"), new GetEmails());
        executors.put(Pattern.compile("POST/api/mail"), new SendEmail());
        executors.put(Pattern.compile("GET/api/mail/templates"), new GetTemplates());
        executors.put(Pattern.compile("GET/api/contact/\\d+/attachment/\\d+"), new GetAttachmentInfo());
        executors.put(Pattern.compile("PUT/api/contact/\\d+/attachment/\\d+"), new EditAttachment());
        executors.put(Pattern.compile("GET/api/contact/\\d+/phones"), new GetPhones());
        executors.put(Pattern.compile("GET/api/contact/\\d+/phone/\\d+"), new GetPhone());
        executors.put(Pattern.compile("PUT/api/contact/\\d+/phone/\\d+"), new EditPhone());
        executors.put(Pattern.compile("POST/api/contact/\\d+/phone"), new CreatePhone());
        executors.put(Pattern.compile("DELETE/api/phone/\\d+"), new DeletePhone());
        executors.put(Pattern.compile("GET/api/countOfContacts"), new GetCountOfContacts());
    }

    public Executable processRequestByUri(String uri) {
        for (Map.Entry<Pattern, Executable> patternExecutableEntry : executors.entrySet()) {
            if (((Pattern) ((Map.Entry) patternExecutableEntry).getKey()).matcher(uri).matches()) {
                return (Executable) ((Map.Entry) patternExecutableEntry).getValue();
            }
        }
        log.error("Incorrect URI " + uri);
        return new ReferenceToIndex();
    }

}