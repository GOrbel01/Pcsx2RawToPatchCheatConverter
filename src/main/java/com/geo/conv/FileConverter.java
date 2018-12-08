package com.geo.conv;

import com.geo.conv.io.AppDir;
import com.geo.conv.io.PatchFileWriter;
import com.geo.conv.mode.Mode;
import com.geo.conv.model.PatchFile;
import com.geo.conv.user.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileConverter {

    public FileConverter() {

    }

    public PatchFile convertFile(File file, Mode mode) throws IOException {
        PatchFile patchFile = new PatchFile();
        PatchFileWriter writer = new PatchFileWriter(mode, file.getAbsolutePath());
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        while (br.ready()) {
            String line = br.readLine();
            patchFile.addLine(writer.morphInputData(line));
        }
        br.close();
        return patchFile;
    }
}
