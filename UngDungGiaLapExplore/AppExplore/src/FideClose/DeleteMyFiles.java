package FideClose;

import java.io.File;
import java.util.Scanner;

public class DeleteMyFiles {
    public void deleteMyFiles() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Nhập tên file bạn muốn xóa");
            String deleteFiles = scanner.nextLine();
            File file = new File("C:\\Baitap\\" + deleteFiles);
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            deleteMyFiles();
        }
    }
}
