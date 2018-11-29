package com.geo.conv;

import com.geo.conv.io.PatchFileWriter;
import com.geo.conv.user.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileConverter {
    public void convertFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.chooseFile();
        PatchFileWriter writer = new PatchFileWriter(fileChooser.getMode());
        FileReader reader = new FileReader(f);
        BufferedReader br = new BufferedReader(reader);
        while (br.ready()) {
            String line = br.readLine();
            writer.writeToFile(line);
        }
        swapTempAndClean(writer, fileChooser);
    }

    private void swapTempAndClean(PatchFileWriter writer, FileChooser chooser) {
        new File(chooser.getFileName()).delete();
        File tempFile = new File(chooser.getFileName() + "_temp");
        tempFile.renameTo(new File(chooser.getFileName()));
    }
}
