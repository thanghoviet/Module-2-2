package FideClose;

import java.io.File;
import java.util.Scanner;

public class MovieMyFiles {
    public void movieMyFiles(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Nhập tên file bạn muốn movie");
            String deleteFiles = scanner.nextLine();
            File file = new File("C:\\Baitap\\"+deleteFiles);
            if (file.renameTo(new File("C:\\Baitap2\\"+file.getName()))){
                System.out.println("File is moved successful!");
            } else {
                System.out.println("File is failed to move!");
            }
            }catch (Exception e){
        e.printStackTrace();
            movieMyFiles();
        }
    }
}
