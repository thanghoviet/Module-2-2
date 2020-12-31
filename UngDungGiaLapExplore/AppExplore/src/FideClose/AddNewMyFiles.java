package FideClose;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class AddNewMyFiles {
    public void createFileExample() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Nhập tên file bạn muốn thêm:");
            String createFile = scanner.nextLine();
            File file = new File("C:\\Baitap\\" + createFile);
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                for (int i = 1; ; i++) {
                    file = new File("C:\\Baitap\\" + createFile + i);
                    if (file.createNewFile()) {
                        System.out.println("File is created!!! There is overlap and has been changed");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            createFileExample();
        }
    }
}
//else {
//        File file1 = new File("C:\\Baitap\\" + createFile);
//        if (file1.createNewFile()) {
//        System.out.println("file đã được thêm thành công");
//        return;
//        }
//        }