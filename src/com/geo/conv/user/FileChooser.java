package com.geo.conv.user;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileChooser {
    public File chooseFile(File[] files) {
        validateFiles(files);
        printFiles(files);
        return files[getUserChoice(files)];
    }

    public void printFiles(File[] files) {
        for (int i = 0; i < files.length; i++) {
            System.out.println(i + ". " + files[i].getName());
        }
    }

    public void validateFiles(File[] files) {
        if (files.length > 50) {
            throw new IllegalArgumentException("Directory has too many files.");
        }
        if (files.length <= 0) {
            System.out.println("No Files found. Program will End.");
            System.exit(0);
        }
    }

    public int getUserChoice(File[] files) {
        validateFiles(files);

        System.out.println("Enter the Number of the file you wish to open (-1 to Exit, -3 to print files again).");

        Scanner sc = new Scanner(System.in);
        int choice = 0;
        try {
            choice = sc.nextInt();
            if (choice < 0 || choice > (files.length-1)) {
                if (choice == -1) System.exit(0);
                else if (choice == -3) {
                    printFiles(files);
                    getUserChoice(files);
                } else {
                    System.out.println("Number must match the index of one of the discovred text files (or an option).");
                    getUserChoice(files);
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Incorrect input detected. Please enter a valid number.");
            getUserChoice(files);
        }

        return choice;
    }
}
