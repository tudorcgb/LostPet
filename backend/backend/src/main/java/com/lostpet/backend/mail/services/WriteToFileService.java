package com.lostpet.backend.mail.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteToFileService {



    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-SSS");

    public void writeToFile(String content) {
        StringBuilder stringBuilder = null;
        OutputStream os = null;
        Date date;
        try {
            date = new Date();
            os = new FileOutputStream(new File("New_Message_" + formatter.format(date) + ".txt"));
            os.write(content.getBytes(),0,content.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
