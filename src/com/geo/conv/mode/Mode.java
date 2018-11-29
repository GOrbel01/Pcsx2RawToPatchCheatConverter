package com.geo.conv.mode;

import java.util.ArrayList;
import java.util.List;

public enum Mode {
    CONVERT(1, "Convert"), COMMENT(2, "Comment"), UNCOMMENT(3, "Uncomment");

    private int id;
    private String text;

    private static List<Mode> modes = new ArrayList<>();

    static {
        modes.add(CONVERT);
        modes.add(COMMENT);
        modes.add(UNCOMMENT);
    }

    Mode(int id, String text) {
       this.id = id;
       this.text = text;
    }

    public static void printModes() {
        for (Mode m : modes) {
            System.out.println(m);
        }
    }

    public static Mode getMode(int id) {
        switch (id) {
            case 1: return CONVERT;
            case 2: return COMMENT;
            case 3: return UNCOMMENT;
            default: return null;
        }
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return id + ". " + text;
    }
}
