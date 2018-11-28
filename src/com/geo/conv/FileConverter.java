package com.geo.conv;

import com.geo.conv.functions.StringFunctions;
import com.geo.conv.io.PatchFileWriter;
import com.geo.conv.io.RawFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileConverter {
    public void convertFile() throws IOException {
        RawFileReader fr = new RawFileReader();
        File f = fr.readInputFile();
        PatchFileWriter writer = new PatchFileWriter();
        FileReader reader = new FileReader(f);
        BufferedReader br = new BufferedReader(reader);
        while (br.ready()) {
            String line = br.readLine();
            writer.writeToFile(line);
        }
    }

}
