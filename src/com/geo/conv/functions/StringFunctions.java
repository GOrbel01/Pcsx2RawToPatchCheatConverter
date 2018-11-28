package com.geo.conv.functions;

public class StringFunctions {
    public static boolean containsOnlyNumbersAndOneSpace(String line){
        if (line.length() == 0) {
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
}
