package com.geo.conv.user;

import com.geo.conv.io.AppDir;
import com.geo.conv.mode.Mode;

import java.io.File;
import java.io.FilenameFilter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileChooser {
    private File[] files;
    private Mode mode;
    private String fileName;

    public File chooseFile() {
        File f = setupDirectory();
        this.mode = chooseMode();
        setupFiles(mode, f);
        validateFiles(files);
        printFiles(files);
        File res = files[getUserChoice(files)];
        this.fileName = res.getName();
        return res;
    }

    private File setupDirectory() {
        File f = new File(AppDir.getFullAppDir());
        if (!f.exists()) {
            f.mkdir();
        }
        return f;
    }

    private void setupFiles(Mode mode, File f) {
        if (mode == Mode.CONVERT) {
            this.files = f.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return !name.equals("output.txt");
                }
            });
        } else {
            this.files = f.listFiles();
        }
    }

    private Mode chooseMode() {
        System.out.println("Choose an execution mode. (enter the number matching your choice)");
        Mode.printModes();
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("You must enter an execution mode.");
            chooseMode();
        }
        return Mode.getMode(choice);
    }

    public Mode getMode() {
        return mode;
    }

    public String getFileName() {
        return fileName;
    }

    private void printFiles(File[] files) {
        for (int i = 0; i < files.length; i++) {
            System.out.println(i + ". " + files[i].getName());
        }
    }

    private void validateFiles(File[] files) {
        if (files.length > 50) {
            throw new IllegalArgumentException("Directory has too many files.");
        }
        if (files.length <= 0) {
            System.out.println("No Files found. Program will End.");
            System.exit(0);
        }
    }

    private int getUserChoice(File[] files) {
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
