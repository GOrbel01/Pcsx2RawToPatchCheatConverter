package com.geo.conv.model;

import java.util.ArrayList;
import java.util.List;

public class PatchFile {
    public List<String> lines;

    public PatchFile() {
        this.lines = new ArrayList<>();
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public void addLine(String line) {
        this.lines.add(line);
    }
}
