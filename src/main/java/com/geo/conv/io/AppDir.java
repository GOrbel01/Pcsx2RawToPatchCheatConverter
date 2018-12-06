package com.geo.conv.io;

import java.io.File;

public final class AppDir {
    private static final String APP_DIR = "rawToPcsx2Conv";

    public static String getFullAppDir() {
        return System.getProperty("user.home") + File.separator + APP_DIR;
    }
}
