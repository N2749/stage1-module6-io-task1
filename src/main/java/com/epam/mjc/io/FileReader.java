package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        } catch (NumberFormatException e) {
            System.err.println("error during conversion of age or phone" + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("file not found" + e.getMessage());
        } catch (IOException e) {
            System.err.println("some kind of io exception i guess" + e.getMessage());
        }
        return profile;
    }

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        fileReader.getDataFromFile(new File("D:\\prog\\java\\mjs\\stage1-module6-io-task1\\src\\main\\resources\\Profile.txt"));
    }
}
