package menu;

import java.util.HashMap;

public class StudentList {

    public static int lengthId = 2;
    public static int lengthName = 4;
    public static int lengthMark1 = 5;
    public static int lengthMark2 = 5;
    public static int lengthMark3 = 5;
    public static int lengthMark4 = 5;
    public static int lengthAvgMark = 7;
    public static final String SPACE = " ";

    public static void lengthColum(Student student){

        int id = student.getId();
        String idStudent = Integer.toString(id);
        if (idStudent.length() > lengthId) {
            lengthId = idStudent.length();
        }
        if (student.getName().length() > lengthName) {
            lengthName = student.getName().length();
        }
        double mark1 = student.getDiemhs1thu1();
        String mrk1 = Double.toString(mark1);
        if (mrk1.length() > lengthMark1) {
            lengthMark1 = mrk1.length();
        }
        double mark2 = student.getDiemhs1thu2();
        String mrk2 = Double.toString(mark2);
        if (mrk2.length() > lengthMark2) {
            lengthMark2 = mrk2.length();
        }
        double mark3 = student.getDiemhs2();
        String mrk3 = Double.toString(mark3);
        if (mrk3.length() > lengthMark3) {
            lengthMark3 = mrk3.length();
        }
        double mark4 = student.getDiemhs3();
        String mrk4 = Double.toString(mark4);
        if (mrk4.length() > lengthMark4) {
            lengthMark4 = mrk4.length();
        }
        double avg = student.getTongdiem();
        String avgm = Double.toString(avg);
        if (avgm.length() > lengthAvgMark) {
            lengthAvgMark = avgm.length();
        }
    }

    public static void tableShow(HashMap<Integer, Student> listStudent) {
        System.out.println(getString(68 + lengthId + lengthName
                +lengthMark1 + lengthMark2 + lengthMark3 + lengthMark4 + lengthAvgMark));
        System.out.print("|| Id" + getSpace(lengthId - 2 + 3) + "|");
        System.out.print("| Name" + getSpace(lengthName - 4 + 3) + "|");
        System.out.print("| Mark1" + getSpace(lengthMark1 - 5 + 3) + "|");
        System.out.print("| Mark2" + getSpace(lengthMark2 - 5 + 3) + "|");
        System.out.print("| Mark3" + getSpace(lengthMark3 - 5 + 3) + "|");
        System.out.print("| Mark4" + getSpace(lengthMark4 - 5 + 3) + "|");
        System.out.println("| AvgMark" + getSpace(lengthAvgMark - 7 + 3) + "||");
        System.out.print(getString(68 + lengthId + lengthName
                + lengthMark1 + lengthMark2 + lengthMark3 + lengthMark4 + lengthAvgMark));
        System.out.println();
        for (Student student : listStudent.values()) {
            int id = student.getId();
            String code = Integer.toString(id);
            System.out.print("||" + SPACE + code + getSpace(lengthId + 3 - code.length()) + "||");
            String name = student.getName();
            System.out.print(SPACE + name + getSpace(lengthName + 3 - name.length()) + "||");
            double mark1 = student.getDiemhs1thu1();
            String ark1 = Double.toString(mark1);
            System.out.print(SPACE + SPACE + ark1 + getSpace(lengthMark1 + 2 - ark1.length()) + "||");
            double mark2 = student.getDiemhs1thu2();
            String ark2 = Double.toString(mark2);
            System.out.print(SPACE + SPACE + ark2 + getSpace(lengthMark2 + 2 - ark2.length()) + "||");
            double mark3 = student.getDiemhs2();
            String ark3 = Double.toString(mark3);
            System.out.print(SPACE + SPACE + ark3 + getSpace(lengthMark3 + 2 - ark3.length()) + "||");
            double mark4 = student.getDiemhs3();
            String ark4 = Double.toString(mark4);
            System.out.print(SPACE + SPACE + ark4 + getSpace(lengthMark4 + 2 - ark4.length()) + "||");
            double avg = student.getTongdiem();
            String avgm = Double.toString(avg);
            System.out.print(SPACE + SPACE + avgm + getSpace(lengthAvgMark + 2 - avgm.length()) + "||");
            System.out.println();
        }
    }

    public static String getString(int i) {
        String result = "";
        for (int j = 0; j < i; j++) {
            result += "-";
        }
        return result;
    }

    public static String getSpace(int i) {
        String result = "";
        for (int j = 0; j < i; j++) {
            result += " ";
        }
        return result;
    }


}
