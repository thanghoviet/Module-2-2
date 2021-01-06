
package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesManager {
    private static String BASE_PATH;
    private static File BASE_DIRECTORY;

    static {
        boolean isBaseDirReady = false;
        while (!isBaseDirReady) {
            BASE_PATH = getBaseDirPath();
            BASE_DIRECTORY = new File(BASE_PATH);
            boolean isValidBaseDirExists = BASE_DIRECTORY.exists() && BASE_DIRECTORY.isDirectory();
            isBaseDirReady = isValidBaseDirExists || BASE_DIRECTORY.mkdirs();
        }
    }

    // Private constructor to prevent object instantiation
    private FilesManager() {
    }

    private static String getBaseDirPath() {
        try {
            return generateBaseDirPathBySystemOs();
        } catch (Exception e) {
            System.out.println("Generate base directory failed: " + e.getMessage());
            System.out.println("Enter directory's full path:");
            return formatDirectoryPath(AppUtils.getString());
        }
    }

    private static String generateBaseDirPathBySystemOs() throws Exception {
        boolean isWindow = System.getProperty("os.name").startsWith("Windows");
        if (isWindow) {
            return "C:\\BaiTap\\";
        } else {
            File homeFolder = new File("/home/");
            File[] homeFiles = homeFolder.listFiles();
            assert homeFiles != null;
            for (File file : homeFiles) {
                if (file.isDirectory())
                    return file.getAbsolutePath() + "/BaiTap/";
            }
            throw new Exception("No user directory available at /home/");
        }
    }

    public static void printWorkingDirectory() {
        System.out.println("Working at directory: " + BASE_PATH);
    }

    public static File[] listFilesInBaseDir() {
        return BASE_DIRECTORY.listFiles();
    }
    public static List<String> searchFilesInBaseDir(String keywords) {
        final String pattern = keywords.toLowerCase();
        File[] fileList = listFilesInBaseDir();


        return Stream.of(fileList)
                .map(AppUtils::getFileNameFromFile)
                .filter(filename -> filename.toLowerCase().contains(pattern))
                .collect(Collectors.toList());
    }

    public static void createNewFile(String filename) {
        try {
            String validFilename = generateNewFilename(BASE_DIRECTORY, filename);
            File newFile = new File(BASE_PATH + validFilename);
            boolean result = newFile.createNewFile();
            if (result)
                System.out.println("New file created: " + validFilename);
            else
                throw new Exception("parse file name: " + validFilename);
        } catch (Exception e) {
            System.out.println("Create new file failed: " + e.getMessage());
        }
    }

    private static String generateNewFilename(File directory, String baseFilename) {
        File[] fileList = directory.listFiles();
        if (fileList == null)
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory!");

        Set<String> filenameSet = Stream.of(fileList)
                .map(AppUtils::getFileNameFromFile)
                .collect(Collectors.toSet());

        FileParts fileParts = separateFilenameIntoParts(baseFilename);

        int duplicates = 0;
        while (true) {
            String newName = generateFilenameWithDuplicateCounter(fileParts, duplicates);
            if (filenameSet.contains(newName))
                duplicates++;
            else
                return newName;
        }
    }

    private static String generateFilenameWithDuplicateCounter(FileParts fileParts, int duplicates) {
        String duplicatePart = duplicates == 0 ? "" : "(" + duplicates + ")";
        return fileParts.name + duplicatePart + fileParts.extension;
    }

    public static void copyFile(String filename) throws IOException {
        File originalFile = getBaseDirFileFromFilename(filename);

        FileParts parts = separateFilenameIntoParts(filename);
        String newName = parts.name + "_copy" + parts.extension;
        File copyingFile = getBaseDirFileFromFilename(newName);

        tryCopyFile(originalFile, copyingFile);
    }

    private static FileParts separateFilenameIntoParts(String filename) {
        String name, extension;

        int separatorIndex = filename.lastIndexOf(".");
        boolean hasFileExtension = separatorIndex != -1;
        if (hasFileExtension) {
            name = filename.substring(0, separatorIndex);
            extension = filename.substring(separatorIndex);
        } else {
            name = filename;
            extension = "";
        }

        return new FileParts(name, extension);
    }

    public static boolean deleteFile(String filename) {
        File file = getBaseDirFileFromFilename(filename);
        return file.delete();
    }

    public static void moveFile(String originalFilename, String destinationDirPath) throws IOException {
        destinationDirPath = formatDirectoryPath(destinationDirPath);

        File destinationDir = new File(destinationDirPath);
        if (!destinationDir.exists())
            throw new IllegalArgumentException("Directory " + destinationDirPath + " is not exists!");
        String newFileName = generateNewFilename(destinationDir, originalFilename);

        File destinationFile = new File(destinationDirPath + newFileName);
        File originalFile = getBaseDirFileFromFilename(originalFilename);

        tryCopyFile(originalFile, destinationFile);

        if (!originalFile.delete())
            throw new IOException("Remove original file failed!");
    }

    private static void tryCopyFile(File originalFile, File copyingFile) throws IOException {
        try (
                FileInputStream fis = new FileInputStream(originalFile);
                FileOutputStream fos = new FileOutputStream(copyingFile)
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }

    public static boolean isFileExists(String filename) {
        return getBaseDirFileFromFilename(filename).exists();
    }

    private static String formatDirectoryPath(String dirPath) {
        if (!dirPath.endsWith(File.separator))
            dirPath += File.separator;
        return dirPath;
    }

    private static File getBaseDirFileFromFilename(String filename) {
        String filePath = BASE_PATH + filename;
        return new File(filePath);
    }

    private static class FileParts {
        private final String name;
        private final String extension;

        public FileParts(String name, String extension) {
            this.name = name;
            this.extension = extension;
        }
    }
}
