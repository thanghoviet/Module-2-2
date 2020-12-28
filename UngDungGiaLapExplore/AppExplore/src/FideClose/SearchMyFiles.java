package FideClose;

import java.io.File;
import java.util.Scanner;

public class SearchMyFiles {
    public void searchMyFiles() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Nhập tên file bạn muốn tìm 'Ex: file.txt'");
            String search = scanner.nextLine();
            File file = new File("C:\\Baitap\\" + search);
            if (file == null) {
                System.out.println("Ko tìm thấy file");
                return;
            } else {
                System.out.println(file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            searchMyFiles();
        }
    }
}
