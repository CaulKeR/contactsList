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
        executors.put(Pattern.compile("POST/contactsList/api/attachment/\\d+"), new DownloadAttachment());
    }

    public Executable processRequestByUri(String uri) {
        Iterator iterator = executors.entrySet().iterator();
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
