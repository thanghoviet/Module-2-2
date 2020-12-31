package com.company;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AppController {
    public static final String OPTION_INVALID_MESSAGE = "Invalid input. Please try again!";
    public static final String OPTION_SEPARATOR = "  ";
    private static boolean isRunning;

    // Private constructor to prevent object instantiation
    private AppController() {
    }

    public static void run() {
        FilesManager.printWorkingDirectory();
        isRunning = true;

        while (isRunning) {
            System.out.println("\nFile(F)  Close(C)");
            char option = AppUtils.getOptionChar();
            switch (option) {
                case 'F' -> handleFileMenu();
                case 'C' -> handleClose();
                default -> System.out.println(OPTION_INVALID_MESSAGE);
            }
        }
    }

    private static void handleFileMenu() {
        System.out.println("\nDir(D)  Search(S)  New(N)  Copy(C)  Delete(E)  Move(M)");
        char option = AppUtils.getOptionChar();
        switch (option) {
            case 'D' -> handleListFileInDir();
            case 'S' -> handleSearch();
            case 'N' -> handleNewFile();
            case 'C' -> handleCopyFile();
            case 'E' -> handleDeleteFile();
            case 'M' -> handleMoveFile();
            default -> System.out.println(OPTION_INVALID_MESSAGE);
        }
    }

    private static void handleClose() {
        System.out.println("\nDo you want to close?\nYes(Y)  No(N)");
        char option = AppUtils.getOptionChar();
        switch (option) {
            case 'Y' -> isRunning = false;
            case 'N' -> isRunning = true;
            default -> System.out.println(OPTION_INVALID_MESSAGE);
        }
    }

    private static void handleListFileInDir() {
        File[] listFiles = FilesManager.listFilesInBaseDir();
        if (listFiles == null || listFiles.length == 0) {
            System.out.println("Folder is empty");
            return;
        }

        StringBuilder files = new StringBuilder();
        for (File file : listFiles) {
            files.append(AppUtils.getFileNameFromFile(file)).append(OPTION_SEPARATOR);
        }
        System.out.println(files.toString());
    }

    private static void handleSearch() {
        System.out.println("Enter keywords:");
        String keywords = AppUtils.getString();
        List<String> searchResults = FilesManager.searchFilesInBaseDir(keywords);
        if (searchResults.size() == 0) {
            System.out.println("File not found!");
            return;
        }

        System.out.println("Search result:");
        searchResults.forEach(System.out::println);
    }

    private static void handleNewFile() {
        String filename = getFileName();
        FilesManager.createNewFile(filename);
    }

    private static void handleCopyFile() {
        String filename = getFileName();
        try {
            FilesManager.copyFile(filename);
            System.out.println("Copy file success.");
        } catch (IOException e) {
            System.out.println("Copy file failed: " + e.getMessage());
        }
    }


    private static void handleDeleteFile() {
        String filename = getFileName();
        boolean result = FilesManager.deleteFile(filename);
        if (result)
            System.out.println("Delete success");
        else
            System.out.println("Delete failed: " + filename + " doesn't exists.");
    }

    private static void handleMoveFile() {
        try {
            String filename = getFileName();
            if (!FilesManager.isFileExists(filename))
                throw new Exception("File " + filename + " does not exists.");

            System.out.println("Enter destination directory full path:");
            String destinationDirPath = AppUtils.getString();
            FilesManager.moveFile(filename, destinationDirPath);

            System.out.println("Move file " + filename + " success.");
        } catch (Exception e) {
            System.out.println("Move file failed: " + e.getMessage());
        }
    }

    private static String getFileName() {
        System.out.println("Enter filename:");
        return AppUtils.getFilename();
    }
}
