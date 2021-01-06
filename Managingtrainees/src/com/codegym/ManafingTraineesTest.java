package com.codegym;

import java.io.IOException;
import java.util.Scanner;

public class ManafingTraineesTest {
    static Scanner scanner = new Scanner(System.in);
    ManagingTrainees check = new ManagingTrainees();
    public void manager() throws IOException, ClassNotFoundException {
        int choice;
        do {
            check.Menu();
            choice = getFormatChoice();
            switch (choice) {
                case 1:
                    check.listOfStudents();
                    break;
                case 2:
                    check.addTrainees();
                    break;
                case 3:
                    check.editStudentInformation();
                    break;
                case 4:
                    check.deleteStudent();
                    break;
                case 5:
                    check.enterStudentScores();
                    break;
                case 6:
                    check.editterStudentScores();
                    break;
                case 7:
                    check.graded();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Ban nhap sai chuc nang" +
                            "Bam nut theo menu de tiep tuc giao dich");
            }
        } while (true);
    }

    public static int getFormatChoice(){
        String choice= scanner.nextLine();
        try{
            return Integer.parseInt(choice);
        }catch (Exception e){
            System.out.println("Chon lai");
            return getFormatChoice();
        }
    }

    public void suaDiem() throws IOException, ClassNotFoundException {
        int choise=scanner.nextInt();
        switch (choise){
            case 1:
                manager();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Ban nhap sai chuc nang" +
                        "Bam nut theo menu de tiep tuc");
        }
    }
    public void inScoroes(Student student) throws IOException, ClassNotFoundException {
        int choise=scanner.nextInt();
        switch (choise){
            case 1:
                check.enterStudentScores1(student);
                break;
            case 2:
                check.enterStudentScores2(student);
                break;
            case 3:
                check.enterStudentScores3(student);
                break;
            case 4:
                check.enterStudentScores4(student);
                break;
            case 5:
                manager();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Ban nhap sai chuc nang" +
                        "Bam nut theo menu de tiep tuc");
        }
    }
    public void editScores(Student student) throws Exception {
        int choise=scanner.nextInt();
        switch (choise){
            case 1:
                check.editScores1(student);
                break;
            case 2:
                check.editScores2(student);
                break;
            case 3:
                check.editScores3(student);
                break;
            case 4:
                check.editScores4(student);
                break;
            case 5:
                manager();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Ban nhap sai chuc nang" +
                        "Bam nut theo menu de tiep tuc");
        }
    }
}
