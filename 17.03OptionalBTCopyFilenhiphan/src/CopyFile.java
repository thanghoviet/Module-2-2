import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class CopyFile {
//    private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
//        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
//    }
    private static long copyFileUsingStream(File source, File dest) throws IOException {
        long bytes = 0;
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                bytes += length;
                os.write(buffer, 0, length);
            }
        }
        return bytes;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter source file:");
        String sourcePath = in.nextLine();
        System.out.print("Enter destination file:");
        String destPath = in.nextLine();

        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);

        try {
//            copyFileUsingJava7Files(sourceFile, destFile);
            long bytes = copyFileUsingStream(sourceFile, destFile);
            System.out.print("Copy completed " + bytes + " bytes");

        } catch (IOException ioe) {
            System.out.print("Can't copy that file");
            System.out.print(ioe.getMessage());
        }
    }
}
