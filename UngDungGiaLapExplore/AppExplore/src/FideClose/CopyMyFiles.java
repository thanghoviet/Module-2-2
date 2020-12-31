package FideClose;

import java.io.*;
import java.util.Scanner;

public class CopyMyFiles {
    Scanner scanner = new Scanner(System.in);
    InputStream inStream = null;
    OutputStream outStream = null;

    public void copyFiles() throws IOException {
        try {
            System.out.println("Nhập tên file bạn muốn copy");
            String copy = scanner.nextLine();
            inStream = new FileInputStream(new File("C:\\Baitap\\" + copy));
            outStream = new FileOutputStream(new File("C:\\Baitap\\" + copy + "copy"));
            int length;
            byte[] buffer = new byte[1024];
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            System.out.println("File is copied successful!");
        } catch (Exception e) {
            e.printStackTrace();
            copyFiles();
        } finally {
            inStream.close();
            outStream.close();
        }
    }
}
