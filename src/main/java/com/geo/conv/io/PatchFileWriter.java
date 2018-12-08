package com.geo.conv.io;

import com.geo.conv.functions.StringFunctions;
import com.geo.conv.mode.Mode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatchFileWriter {
    private File tempOutput;
    private Mode mode;
    private String fileName;

    public PatchFileWriter(Mode mode, String fileName) {
        this.fileName = fileName;
        createFile();
        this.mode = mode;
    }

    public void createFile() {
        this.tempOutput = new File(AppDir.getFullAppDir() + File.separator + fileName + "_temp");
    }

    public String morphInputData(String toWrite) throws IOException {
        String result;
        if (mode == Mode.CONVERT) {
            result = writeConversionMode(toWrite);
        } else {
            result = writeCommentMode(toWrite);
        }
        return result;
    }

    private String writeConversionMode(String toWrite) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (StringFunctions.isRawCheatLine(toWrite)) {
            sb.append(morphLineToPcsx2CheatFormat(toWrite));
        } else {
            if (toWrite.equals("")) {
                sb.append(toWrite);
            } else {
                if (!toWrite.substring(0, 2).equals("//")) {
                    sb.append("//");
                    sb.append(toWrite);
                } else {
                    sb.append(toWrite);
                }
            }
        }
        return sb.toString();
    }

    private String writeCommentMode(String toWrite) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (mode == Mode.COMMENT) {
            if (StringFunctions.isFormattedPatchLine(toWrite)) {
                //Check isnt already commented
                if (!toWrite.substring(0, 2).equals("//")) {
                    sb.append("//");
                    sb.append(toWrite);
                } else {
                    sb.append(toWrite);
                }
            } else {
                sb.append(toWrite);
            }
        } else {
            if (StringFunctions.isFormattedPatchLine(toWrite)) {
                //Check is already commented
                if (toWrite.substring(0, 2).equals("//")) {
                    sb.append(toWrite.substring(2, toWrite.length()));
                } else {
                    sb.append(toWrite);
                }
            } else {
                sb.append(toWrite);
            }
        }
        return sb.toString();
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
