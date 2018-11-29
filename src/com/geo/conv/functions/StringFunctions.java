package com.geo.conv.functions;

public class StringFunctions {
    //containsOnlyNumbersAndOneSpace
    public static boolean isRawCheatLine(String line){
        if (isEmptyLine(line)) {
            return false;
        }
        String[] res = line.split(" ");
        if (res.length != 2) {
            return false;
        }
        if (res[0].length() != 8 || res[1].length() != 8) {
            return false;
        }
        /*
            Handle case of [8 char word] [8 char word] for description.
            Still not fullproof. E.g. 1AAAAAAA 2BBBBBBB would fool this logic.
            However this is the best the code can do, as there is no
            more accurate definitive difference.

            From observation the code:
                Must be 8 Chars followed by a space, followed by another 8 chars
                The first character of each 8 chars must be a number
            The description;
                Can come from anywhere and be anything, in any format
                with no prefix required (would solve the issue if it was)
         */
        if (!Character.isDigit(res[0].charAt(0)) && !Character.isDigit(res[1].charAt(0))) {
            System.out.println("Line " + line + ", R0: " + res[0].charAt(0));
            System.out.println("Line " + line + ", R1: " + res[1].charAt(0));
            return false;
        }
        return true;
    }

    private static boolean isEmptyLine(String line) {
        if (line.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isFormattedPatchLine(String line) {
        if (isEmptyLine(line)) {
            return false;
        } else if (line.length() < 16) {
            return false;
        } else {
            return line.substring(0, 7).equals("patch=1") || line.substring(0, 9).equals("//patch=1");
        }
    }
}
