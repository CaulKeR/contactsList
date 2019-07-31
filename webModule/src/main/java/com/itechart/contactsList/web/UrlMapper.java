package com.itechart.contactsList.web;

import com.itechart.contactsList.web.impl.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class UrlMapper {

    private HashMap<Pattern, Executable> executors = new HashMap<>();

    public UrlMapper() {
        executors.put(Pattern.compile("GET/contactsList/api/contacts"), new MainContactsInfo());
        executors.put(Pattern.compile("POST/contactsList/api/contact"), new  CreateContact());
        executors.put(Pattern.compile("GET/contactsList/api/contact/\\d+"), new ContactById());
        executors.put(Pattern.compile("DELETE/contactsList/api/contact/\\d+"), new DeleteContact());
        executors.put(Pattern.compile("PUT/contactsList/api/contact/\\d+"), new EditContact());
        executors.put(Pattern.compile("GET/contactsList/api/contact/\\d+/attachments"), new Attachments());
        executors.put(Pattern.compile("POST/contactsList/api/contact/\\d+/attachments"), new AddAttachment());
        executors.put(Pattern.compile("DELETE/contactsList/api/attachment/\\d+"), new DeleteAttachment());
        executors.put(Pattern.compile("GET/contactsList/api/attachment/\\d+"), new DownloadAttachment());
        executors.put(Pattern.compile("POST/contactsList/api/search"), new SearchContacts());
        executors.put(Pattern.compile("POST/contactsList/api/contact/\\d+/photo"), new UploadAvatar());
        executors.put(Pattern.compile("GET/contactsList/api/contact/\\d+/photo"), new GetAvatar());
        executors.put(Pattern.compile("GET/contactsList/api/contact/\\d+/mail"), new GetEmails());
        executors.put(Pattern.compile("POST/contactsList/api/mail"), new SendEmail());
        executors.put(Pattern.compile("GET/contactsList/api/mail/templates"), new GetTemplates());
        executors.put(Pattern.compile("GET/contactsList/api/contact/\\d+/attachment/\\d+"), new GetAttachmentInfo());
        executors.put(Pattern.compile("PUT/contactsList/api/contact/\\d+/attachment/\\d+"), new EditAttachment());
        executors.put(Pattern.compile("GET/contactsList/api/contact/\\d+/phones"), new GetPhones());
        executors.put(Pattern.compile("GET/contactsList/api/contact/\\d+/phone/\\d+"), new GetPhone());
        executors.put(Pattern.compile("PUT/contactsList/api/contact/\\d+/phone/\\d+"), new EditPhone());
        executors.put(Pattern.compile("POST/contactsList/api/contact/\\d+/phone"), new CreatePhone());
        executors.put(Pattern.compile("DELETE/contactsList/api/phone/\\d+"), new DeletePhone());
        executors.put(Pattern.compile("GET/contactsList/api/countOfContacts"), new GetCountOfContacts());
    }

    public Executable processRequestByUri(String uri) {
        Iterator iterator = executors.entrySet().iterator();
        System.out.println(uri);
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            if (((Pattern) pair.getKey()).matcher(uri).matches()){
                return (Executable) pair.getValue();
            }
        }
        System.err.println("Incorrect URL " + uri);
        return new ReferenceToIndex();
    }

}
