package com.itechart.contactsList.service;

import com.itechart.contactsList.dto.MessageTemplateDTO;
import java.util.ArrayList;
import java.util.List;

public class GetTemplatesProcessor {

    public List<MessageTemplateDTO> run() {
        String hb = "Dear <name>,\n" +
                "I am writing to wish you a happy birthday. I hope that you enjoy the day.\n" +
                "Many happy returns!";
        List<MessageTemplateDTO> list = new ArrayList<>();
        MessageTemplateDTO template1 = new MessageTemplateDTO();
        template1.setTitle("Happy Birthday");
        template1.setText(hb);
        list.add(template1);
        String waiting = "Hello. <name>! We are waiting for you today. You remember it?";
        MessageTemplateDTO template2 = new MessageTemplateDTO();
        template2.setTitle("Waiting for you...");
        template2.setText(waiting);
        list.add(template2);
        return list;
    }
}