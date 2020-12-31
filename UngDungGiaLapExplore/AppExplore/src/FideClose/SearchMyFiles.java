package FideClose;

import java.io.*;
import java.util.Scanner;

public class SearchMyFiles {

    public void searchMyFiles() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Nhập tên file bạn muốn tìm");
            String search = scanner.nextLine().trim();
            File file = new File("C:\\Baitap\\" + search);
            if (file.exists()) {
                System.out.println(file.getAbsolutePath());
                return;
            } else {
                System.out.println("file ko tồn tại");
            }
        } catch (Exception e) {
            e.printStackTrace();
            searchMyFiles();
        }
    }

}



