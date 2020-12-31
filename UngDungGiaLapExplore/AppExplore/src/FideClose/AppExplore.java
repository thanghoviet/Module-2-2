package FideClose;
import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class AppExplore {
    Scanner scanner = new Scanner(System.in);
    File fileOrDir = new File("C:\\Baitap\\");
    Menu menu = new Menu();
    ShowMyFiles dir = new ShowMyFiles();
    SearchMyFiles search = new SearchMyFiles();
    AddNewMyFiles add = new AddNewMyFiles();
    CopyMyFiles copy = new CopyMyFiles();
    DeleteMyFiles delete = new DeleteMyFiles();
    MovieMyFiles movie = new MovieMyFiles();


    public void appExplore(){
        String choise;
        try {
            menu.menu();
            choise = scanner.nextLine().toUpperCase(Locale.ROOT);
            switch (choise) {
                case "F" -> {
                    menu.menuFile();
                    file();
                }
                case "C" -> {
                    close();
                }
                default -> {
                    System.out.println("Bạn nhập sai chức năng rồi");

                    System.out.println("Nhập theo menu để sủ dụng ứng dụng");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Bạn nhập sai chức năng rồi");
            appExplore();
        }
    }

    public void file(){
        do {
            try {
                String choise2;
                choise2 = scanner.nextLine().toUpperCase(Locale.ROOT);
                switch (choise2) {
                    case "D" -> {
                        dir.traverseDepthFiles(fileOrDir);
                        System.out.println("_________________________________");
                        System.out.println("Nhập sự lựa chọn tiếp theo của bạn");
//                        menu.menuFile();
                    }
                    case "S" -> {
                        search.searchMyFiles();
                        System.out.println("_________________________________");
                        System.out.println("Nhập sự lựa chọn tiếp theo của bạn");
//                        menu.menuFile();
                    }
                    case "N" -> {
                        add.createFileExample();
                        System.out.println("_________________________________");
                        System.out.println("Nhập sự lựa chọn tiếp theo của bạn");
//                        menu.menuFile();
                    }
                    case "C" -> {
                        copy.copyFiles();
                        System.out.println("_________________________________");
                        System.out.println("Nhập sự lựa chọn tiếp theo của bạn");
//                        menu.menuFile();
                    }
                    case "M" -> {
                        movie.movieMyFiles();
                        System.out.println("_________________________________");
                        System.out.println("Nhập sự lựa chọn tiếp theo của bạn");
//                        menu.menuFile();
                    }
                    case "E" -> {
                        delete.deleteMyFiles();
                        System.out.println("_________________________________");
                        System.out.println("Nhập sự lựa chọn tiếp theo của bạn");
//                        menu.menuFile();
                    }
                    case "O" -> close();
                    default -> {
                        System.out.println("Bạn nhập sai chức năng rồi");
                        System.out.println("Nhập theo menu để sủ dụng ứng dụng");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Bạn nhập sai chức năng rồi");
                file();
            }
        }while (true);
    }
    public void close(){
        menu.menuClose();
        String choise3;
        try {
            choise3 = scanner.nextLine().toUpperCase(Locale.ROOT);
            switch (choise3) {
                case "Y":
                    System.exit(0);
                case "N":
                    appExplore();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng rồi");
                    System.out.println("Nhập theo menu để sủ dụng ứng dụng");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Bạn nhập sai chức năng rồi");
            close();
        }
    }
}


