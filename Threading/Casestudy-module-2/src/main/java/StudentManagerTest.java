import java.io.IOException;
import java.util.Scanner;

public class StudentManagerTest {
    static Scanner scanner = new Scanner(System.in);
    StudentManager check = new StudentManager();

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
                    editStudentInformationMenu();
                    break;
                case 4:
                    check.deleteStudent();
                    break;
                case 5:
                    enterScortmenu();
                    break;
                case 6:
                    editScortmenu();
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

    public void deleteStudentRepeat() throws IOException, ClassNotFoundException {
        int choice;
        check.repeatMenu();
        choice = getFormatChoice();
        switch (choice) {
            case 1:
                check.deleteStudent();
                break;
            case 2:
                manager();
            case 0:
                System.exit(0);
            default:
                System.out.println("Ban nhap sai chuc nang" +
                        "Bam nut theo menu de tiep tuc giao dich");
        }
    }

    public void editAllStudentScoresMenu() {
        int choice;
        check.menuEditAllScores();
        choice = getFormatChoice();
        switch (choice) {
            case 1:
                check.editAllStudentScoresPoint15Minutes();
                break;
            case 2:
                check.editAllStudentScoresOralTestScores();
                break;
            case 3:
                check.editAllStudentScoresHourTestScore();
                break;
            case 4:
                check.editAllStudentScoresSemesterTestScores();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Ban nhap sai chuc nang" +
                        "Bam nut theo menu de tiep tuc giao dich");
        }
    }

    public void editStudentInformationMenu() throws IOException, ClassNotFoundException {
        int choice;
        check.menuEditStudentInformation();
        choice = getFormatChoice();
        switch (choice) {
            case 1:
                check.editTheNameStudent();
                break;
            case 2:
                check.editTheBirthDay();
                break;
            case 3:
                check.editTheSex();
                break;
            case 4:
                manager();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Ban nhap sai chuc nang" +
                        "Bam nut theo menu de tiep tuc giao dich");
        }
    }

    public void editScortmenu() throws IOException, ClassNotFoundException {
        int choice;
        check.editAllScoresMenu();
        choice = getFormatChoice();
        switch (choice) {
            case 1:
                editAllStudentScoresMenu();
                break;
            case 2:
                check.editterStudentScores();
                break;
            case 3:
                manager();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Ban nhap sai chuc nang" +
                        "Bam nut theo menu de tiep tuc giao dich");

        }
    }

    public void enterScortmenu() throws IOException, ClassNotFoundException {
        int choice;
        check.enterScores3();
        choice = getFormatChoice();
        switch (choice) {
            case 1:
                enterAllScortMenu();
                break;
            case 2:
                check.enterStudentScores();
                break;
            case 3:
                manager();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Ban nhap sai chuc nang" +
                        "Bam nut theo menu de tiep tuc giao dich");

        }
    }

    public void repeat() throws IOException, ClassNotFoundException {
        int choice;
        check.repeatMenu();
        choice = getFormatChoice();
        switch (choice) {
            case 1:
                check.addTrainees();
                break;
            case 2:
                manager();
            case 0:
                System.exit(0);
            default:
                System.out.println("Ban nhap sai chuc nang" +
                        "Bam nut theo menu de tiep tuc giao dich");
        }
    }


    public void enterAllScortMenu() {
        int choice;
        check.menuEnterAllScores();
        choice = getFormatChoice();
        switch (choice) {
            case 1:
                check.enterAllStudentScoresPoint15Minutes();
                break;
            case 2:
                check.enterAllStudentScoresOralTestScores();
                break;
            case 3:
                check.enterAllStudentScoresHourTestScore();
                break;
            case 4:
                check.enterAllStudentScoresSemesterTestScores();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Ban nhap sai chuc nang" +
                        "Bam nut theo menu de tiep tuc giao dich");
        }
    }

    public void inScoroes(Student student) throws IOException, ClassNotFoundException {
        int choise = scanner.nextInt();
        switch (choise) {
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

    public void suaDiem() throws IOException, ClassNotFoundException {
        int choise = scanner.nextInt();
        switch (choise) {
            case 1:
                check.enterStudentScores();
                break;
            case 2:
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
        int choise = scanner.nextInt();
        switch (choise) {
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

    public static int getFormatChoice() {
        String choice = scanner.nextLine();
        try {
            return Integer.parseInt(choice);
        } catch (Exception e) {
            System.out.println("Chon lai");
            return getFormatChoice();
        }
    }
}
