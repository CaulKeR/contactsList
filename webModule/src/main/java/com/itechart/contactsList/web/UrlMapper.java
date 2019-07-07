package com.itechart.contactsList.web;

import com.itechart.contactsList.web.impl.*;

import java.util.HashMap;
import java.util.Map;

public class UrlMapper {

    private Map<String, Executable> executors = new HashMap<>();

    public UrlMapper() {
        executors.put("GET/contactsList/api/contacts", new MainContactsInfo());
        executors.put("POST/contactsList/api/contact", new  CreateContact());
        executors.put("GET/contactsList/api/contact/", new ContactById());
        executors.put("DELETE/contactsList/api/contact/", new DeleteContact());
        executors.put("PUT/contactsList/api/contact/", new EditContact());
    }

    public Executable processRequestByUri(String uri) {
        Executable processorObject = executors.get(uri.replaceAll("\\d", ""));
        if (processorObject != null) {
            return processorObject;
        } else {
            System.err.println("Incorrect URL " + uri);
        }
        return null;
    }

}
