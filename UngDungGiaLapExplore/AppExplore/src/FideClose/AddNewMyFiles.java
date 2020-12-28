package FideClose;

import java.io.File;
import java.util.Scanner;

public class AddNewMyFiles {
    public void createFileExample() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Nhập tên file bạn muốn thêm 'Ex: file.txt'");
            String createFile = scanner.nextLine();
            File file = new File("C:\\Baitap\\" + createFile);
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            createFileExample();
        }
    }
}
