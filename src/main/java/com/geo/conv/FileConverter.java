package com.geo.conv;

import com.geo.conv.io.AppDir;
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
        PatchFileWriter writer = new PatchFileWriter(fileChooser.getMode(), fileChooser.getFileName());
        FileReader reader = new FileReader(f);
        BufferedReader br = new BufferedReader(reader);
        while (br.ready()) {
            String line = br.readLine();
            writer.writeToFile(line);
        }
        br.close();
        writer.closeWriter();
        swapTempAndClean(fileChooser);
    }

    private void swapTempAndClean(FileChooser chooser) {
        String fName = AppDir.getFullAppDir() + File.separator + chooser.getFileName();
        File f = new File(fName);
        System.out.println("Deleted: " + f.getAbsolutePath() + " " + f.delete());
        File tempFile = new File(fName + "_temp");
        boolean renamed = tempFile.renameTo(new File(fName));
        System.out.println("Renamed: " + tempFile.getAbsolutePath() + " to " + fName + " " + renamed);
    }
}
