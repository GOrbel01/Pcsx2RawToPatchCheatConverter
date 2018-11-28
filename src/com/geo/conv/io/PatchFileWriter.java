package com.geo.conv.io;

import com.geo.conv.functions.StringFunctions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatchFileWriter {
    private File output;
    private FileWriter fileWriter;

    public PatchFileWriter() {
        this.output = new File(AppDir.getFullAppDir() + File.separator + "output.txt");
        createFile();
    }

    public void createFile() {
        if (!output.exists()) {
            try {
                output.createNewFile();
            } catch (IOException ex) {
                System.out.println("File cannot be created.");
            }
        } else {
            System.out.println("Cleaning Existing File...");
            output.delete();
            createFile();
        }
    }

    public void writeToFile(String toWrite) throws IOException {
        if (fileWriter == null) {
            fileWriter = new FileWriter(output);
        }
        if (StringFunctions.containsOnlyNumbersAndOneSpace(toWrite)) {
            fileWriter.write(morphLineToPcsx2CheatFormat(toWrite));
        } else {
            if (toWrite.equals("")) {
                fileWriter.write(toWrite);
            } else {
                fileWriter.write("//" + toWrite);
            }
        }
        fileWriter.write(System.lineSeparator());
        fileWriter.flush();
    }

    private String morphLineToPcsx2CheatFormat(String line) {
        String[] text = line.split(" ");
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("patch=1,EE,");
            sb.append(text[0]);
            sb.append(",word,");
            sb.append(text[1]);
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArrayIndexOutOfBoundsException("Index out of Bounds At: " + line);

        }
        return sb.toString();
    }
}
