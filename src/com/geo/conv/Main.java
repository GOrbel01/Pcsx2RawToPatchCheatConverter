package com.geo.conv;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileConverter fileConverter = new FileConverter();
        try {
            fileConverter.convertFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
