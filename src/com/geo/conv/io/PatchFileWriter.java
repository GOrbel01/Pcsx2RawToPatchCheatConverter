package com.geo.conv.io;

import com.geo.conv.functions.StringFunctions;
import com.geo.conv.mode.Mode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatchFileWriter {
    private File output;
    private File tempOutput;
    private FileWriter fileWriter;
    private Mode mode;
    private String fileName;

    public PatchFileWriter(Mode mode) {
        this.output = new File(AppDir.getFullAppDir() + File.separator + fileName);
        createFile();
        this.mode = mode;
    }

    public void createFile() {
        this.tempOutput = new File(AppDir.getFullAppDir() + File.separator + fileName + "_temp");
        if (tempOutput.exists()) {
            tempOutput.delete();
        } else {
            tempOutput.mkdir();
        }
    }

    public void writeToFile(String toWrite) throws IOException {
        if (fileWriter == null) {
            fileWriter = new FileWriter(tempOutput);
        }
        if (mode == Mode.CONVERT) {
            writeConversionMode(toWrite, fileWriter);
        } else {
            writeCommentMode(toWrite, fileWriter);
        }
        fileWriter.write(System.lineSeparator());
        fileWriter.flush();
    }

    private void writeConversionMode(String toWrite, FileWriter fileWriter) throws IOException {
        if (StringFunctions.isRawCheatLine(toWrite)) {
            fileWriter.write(morphLineToPcsx2CheatFormat(toWrite));
        } else {
            if (toWrite.equals("")) {
                fileWriter.write(toWrite);
            } else {
                if (!toWrite.substring(0, 2).equals("//")) {
                    fileWriter.write("//" + toWrite);
                } else {
                    fileWriter.write(toWrite);
                }
            }
        }
    }

    private void writeCommentMode(String toWrite, FileWriter fileWriter) throws IOException {
        if (mode == Mode.COMMENT) {
            if (StringFunctions.isFormattedPatchLine(toWrite)) {
                //Check isnt already commented
                if (!toWrite.substring(0, 2).equals("//")) {
                    fileWriter.write("//" + toWrite);
                }
            } else {
                fileWriter.write(toWrite);
            }
        } else {
            if (StringFunctions.isFormattedPatchLine(toWrite)) {
                //Check is already commented
                if (toWrite.substring(0, 2).equals("//")) {
                    fileWriter.write(toWrite.substring(2, toWrite.length()));
                }
            } else {
                fileWriter.write(toWrite);
            }
        }
    }

    public File getTempOutput() {
        return tempOutput;
    }

    public File getOutput() {
        return output;
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
