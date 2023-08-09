package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int c;
            String[] params = new String[4];
            int i = 0;
            while ((c = fileInputStream.read()) != -1) {
                if (c != ' ') continue;
                params[i] = "";
                while ((c = fileInputStream.read()) != '\n') {
                    if (c != '\r') params[i] += (char) c;
                }
                i++;
            }
            if (params[0] != null)
                profile.setName(params[0]);
            if (params[1] != null)
                profile.setAge(Integer.valueOf(params[1]));
            if (params[2] != null)
                profile.setEmail(params[2]);
            if (params[3] != null)
                profile.setPhone(Long.valueOf(params[3]));
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return profile;
    }
}
