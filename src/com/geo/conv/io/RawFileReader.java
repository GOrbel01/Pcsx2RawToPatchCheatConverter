package com.geo.conv.io;

import com.geo.conv.user.FileChooser;

import java.io.File;
import java.io.FilenameFilter;

public class RawFileReader {
    private FileChooser fileChooser;

    public RawFileReader() {
        this.fileChooser = new FileChooser();
    }

    public File readInputFile() {
        File f = new File(AppDir.getFullAppDir());
        if (!f.exists()) {
            f.mkdir();
        }
        return fileChooser.chooseFile(f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.equals("output.txt");
            }
        }));
    }
}
