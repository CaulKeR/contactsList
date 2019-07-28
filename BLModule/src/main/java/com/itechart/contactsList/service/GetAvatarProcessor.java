package com.itechart.contactsList.service;

import com.itechart.contactsList.utility.Constants;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class GetAvatarProcessor {

    public void run(long userId, PrintWriter out) {
        try (FileInputStream reader = new FileInputStream(Constants.photoDir + userId)) {
            int temp;
            while ((temp = reader.read()) != -1) {
                out.write(temp);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}