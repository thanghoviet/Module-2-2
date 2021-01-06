package FideClose;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class SearchMyFiles {

    public void searchMyFiles() {

        try ( Scanner scanner = new Scanner(System.in)) {
            File dir = new File("C:\\Baitap\\");
            File[] files = dir.listFiles();

            ArrayList<String> filenames = new ArrayList<>();
            for (File file : files) {
                filenames.add(file.getName());
            }

            System.out.println("Nhập tên file bạn muốn tìm");
            String keyword = scanner.nextLine().trim().toLowerCase();

            ArrayList<String> result = new ArrayList<>();
            for (String filename : filenames) {
                if (filename.toLowerCase().contains(keyword))
                    result.add(filename);
            }
            System.out.println(result);

//            File file = new File("C:\\Baitap\\" + search);
//            if (file.exists()) {
//                System.out.println(file.getAbsolutePath());
//            } else {
//                System.out.println("file ko tồn tại");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            searchMyFiles();
        }
    }

}



