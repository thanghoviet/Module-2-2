package com.company;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AppUtils {
    private static final Pattern FILENAME_PATTERN = Pattern.compile("^[^<>:;,?\"*|/\\\\]+$");
    private static final Scanner scanner = new Scanner(System.in);

    // Private constructor to prevent object instantiation
    private AppUtils() {
    }

    public static char getOptionChar() {
        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1)
                return input.toUpperCase().charAt(0);
        }
    }

    public static String getString() {
        return scanner.nextLine().trim();
    }

    public static String getFilename() {
        while (true) {
            String filename = getString();
            boolean validateResult = AppUtils.validateFilename(filename);
            if (validateResult)
                return filename;
            else
                System.out.println("Invalid filename: Empty or contains illegal characters.");
        }
    }

    public static String getFileNameFromFile(File file) {
        String name = file.getName();
        return name.substring(name.lastIndexOf(File.separator) + 1);
    }

    public static boolean validateFilename(String fileName) {
        return FILENAME_PATTERN.matcher(fileName).matches();
    }

}
