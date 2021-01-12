package com.codegym;

import java.util.Scanner;

public class DirectoryTest {
    Scanner scanner = new Scanner(System.in);
    DirectoryManagementProgram check = new DirectoryManagementProgram();
    DirectoryProgram in = new DirectoryProgram();

    public void starMenu() {
        int choice;
        try {
            do {
                in.menu();
                choice = getFormatChoice();
                switch (choice) {
                    case 1:
                        check.seeList();
                        break;
                    case 2:
                        check.addNewThePhoneNumber();
                        break;
                    case 3:
                        check.editTheNumber();
                        break;
                    case 4:
                        check.deleteNumberPhone();
                        break;
                    case 5:
                        check.speach();
                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:
                    System.exit(0);
                    default:
                        System.out.println("Ban nhap sai chuc nang" +
                                "Bam nut theo menu de tiep tuc giao dich");
                }
            } while (true);
        }catch (Exception e){
            System.out.println("chon sai roi,chon lai");
            starMenu();
    }

}

    public int getFormatChoice() {
        String choice = scanner.nextLine();
        try {
            return Integer.parseInt(choice);
        } catch (Exception e) {
            System.out.println("Chon lai");
            return getFormatChoice();
        }
    }
}
