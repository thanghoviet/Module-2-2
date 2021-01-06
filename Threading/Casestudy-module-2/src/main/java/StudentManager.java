import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jdk.swing.interop.SwingInterOpUtils;
import org.json.simple.JSONValue;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class StudentManager {
    private static final Scanner sc = new Scanner(System.in);
    private static final String REGEX = "^[a-zA-Z ]{2,30}$";
    static HashMap<Integer, Student> studentTables;
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //try catch
    static {
//        try (ObjectInputStream read = new ObjectInputStream(new FileInputStream("Student.json"))) {
//            Object testFile = read.readObject();
//            studentTables = (HashMap<Integer, Student>) testFile;
//        } catch (Exception e) {
//            studentTables = new HashMap<>();
//        }
        try (FileReader fileReader = new FileReader("st.json")) {
            Type studentTable = new TypeToken<HashMap<Integer, Student>>() {}.getType();
            studentTables = gson.fromJson(fileReader, studentTable);
            if (studentTables == null) {
                studentTables = new HashMap<Integer, Student>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString() {
        return sc.nextLine().trim();
    }

    public void Menu() {
        System.out.println("********************************");
        System.out.println("1. Xem danh sách học viên");
        System.out.println("2. Thêm học viên");
        System.out.println("3. Sửa thông tin học viên theo id");
        System.out.println("4. Xóa học viên theo id");
        System.out.println("5. Nhập điểm học viên");
        System.out.println("6. Sửa nhập điểm học viên");
        System.out.println("7. Xếp loại học viên " +
                "(hiển thị danh sách học viên theo điểm GPA từ cao đến thấp)");
        System.out.println("Nhập 0 để thoát");
        System.out.println("Nhập lựa chọn của bạn:  ");
    }

    public static void listOfStudents() {
        System.out.format("||%-5s | ", "id");
        System.out.format("%-30s | ", "name");
        System.out.format("%-5s |", "sex");
        System.out.format("%-15s |", "birthday");
        System.out.format("%-8s | ", "15 Minutes");
        System.out.format("%-8s| ", "OralTest");
        System.out.format("%-8s | ", "HourTest");
        System.out.format("%-8s | ", "SemesterTest");
        System.out.format("%-8s || \n", "GPA");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Student student : studentTables.values()) {
            System.out.format("||%-5d | ", student.getId());
            System.out.format("%-30s | ", student.getName());
            System.out.format("%-5s | ", student.getSex());
            System.out.format("%-14s | ", student.getBirthday());
            System.out.format("%-9.2f | ", student.getPoint15Minutes());
            System.out.format("%-7.2f | ", student.getOralTestScores());
            System.out.format("%-8.2f | ", student.getHourTestScore());
            System.out.format("%-12.2f | ", student.getSemesterTestScores());
            System.out.format("%-8.2f || \n", student.getGPA());
        }
    }

    public static void addTrainees() throws IOException, ClassNotFoundException {
        Student student = new Student();
        StudentManagerTest repests = new StudentManagerTest();
        try {
            System.out.println("Nhập mã số sinh viên:");
            int checkId = Integer.parseInt(sc.nextLine());
            if (validateID(checkId) && checkId >= 0) {
                student.setId(checkId);
            } else {
                System.out.println("Đã có id này trong danh sách");
                repests.repeat();
            }
        } catch (Exception e) {
            System.out.println("Sai định dạng hoặc trùng");
            repests.repeat();
        }
        String name = getStudentName();
        student.setName(name);
        System.out.println("Nhập ngày tháng năm sinh của sinh viên (yy-mm-dd):");
        student.setBirthday(dateBirthday());
        boolean checkk = false;
        do {
            System.out.println("nhập giới tính của sinh viên:");
            System.out.println("giới tính Nam:nhập 'Nam' , giới tính Nữ:nhập 'Nữ'");
            student.setSex(sc.nextLine().trim().toLowerCase(Locale.ROOT));
            if (student.getSex().equals("nam") || student.getSex().equals("nữ")) {
                checkk = true;
                studentTables.put(student.getId(), student);
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);

                saveToFile();
            } else {
                System.out.println("bạn nhập sai rồi,nhập theo hướng dẫn");
                System.out.println("giới tính Nam:nhập 'Nam' , giới tính Nữ:nhập 'Nữ'");
            }
        } while (!checkk);
    }

    private static String getStudentName() {
        String name;
        System.out.println("Nhập tên sinh viên:");
        do {
            name = getString().trim().replaceAll("\\s+", " ");
            if (Pattern.matches(REGEX, name))
                break;
            else
                System.out.println("Nhap lai ten");
        } while (true);
        return name;
    }

    public void menuEditStudentInformation() {
        System.out.println("========================================");
        System.out.println("Menu");
        System.out.println("Ấn 1 : Để thay đổi tên cho sinh viên");
        System.out.println("Ấn 2 : Để thay đổi ngày sinh");
        System.out.println("Ấn 3 : Để thay đổi giới tính");
        System.out.println("Ấn 4 : Để quay lại menu chính");
        System.out.println("Ấn 0 : Để thoát");
    }

    public void editTheNameStudent() {
        try {
            boolean checkk = true;
            System.out.println("Nhập id học sinh để sửa tên sinh viên:");
            int fineid = Integer.parseInt(sc.nextLine());
            for (Student student : studentTables.values()) {
                if (fineid == student.getId()) {
                    checkk = false;

                    String name = getStudentName();
                    student.setName(name);
                    studentTables.put(student.getId(), student);
//                    ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                    saved.writeObject(studentTables);
                    saveToFile();
                    System.out.println("sửa thông tin học sinh thành công");
                }
            }
            if (checkk) {
                System.out.println("id này không tồn tại, vui lòng nhập lại");
            }
        } catch (Exception e) {
            System.out.println("không được nhập sai định dạng yêu cầu,nhập lại:");
            editTheNameStudent();
        }
    }

    public void editTheBirthDay() {
        try {
            boolean checkk = true;
            System.out.println("Nhập id học sinh để sửa tên sinh viên:");
            int fineid = Integer.parseInt(sc.nextLine());
            for (Student student : studentTables.values()) {
                if (fineid == student.getId()) {
                    checkk = false;
                    System.out.println("Nhập lại ngày sinh của sinh viên (yy-mm-dd):");
                    student.setBirthday(dateBirthday());
//                    ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                    saved.writeObject(studentTables);
                    saveToFile();
                    System.out.println("sửa thông tin học sinh thành công");
                }
            }
            if (checkk) {
                System.out.println("id này không tồn tại, vui lòng nhập lại");

            }
        } catch (Exception e) {
            System.out.println("không được nhập sai định dạng yêu cầu,nhập lại:");
            editTheBirthDay();
        }
    }

    public void editTheSex() {
        try {
            boolean checkk = true;
            System.out.println("Nhập id học sinh để sửa tên sinh viên:");
            int fineid = Integer.parseInt(sc.nextLine());
            for (Student student : studentTables.values()) {
                if (fineid == student.getId()) {
                    checkk = false;
                    do {
                        System.out.println("nhập lại giới tính của sinh viên:");
                        System.out.println("giới tính Nam:nhập 'Nam' , giới tính Nữ:nhập 'Nữ'");
                        student.setSex(sc.nextLine().trim().toLowerCase(Locale.ROOT));
                        if (student.getSex().equals("nam") || student.getSex().equals("nữ")) {
                            checkk = false;
                            studentTables.put(student.getId(), student);
//                            ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                            saved.writeObject(studentTables);
                            saveToFile();
                            System.out.println("sửa thông tin học sinh thành công");
                        } else {
                            System.out.println("bạn nhập sai rồi,nhập theo hướng dẫn");
                        }
                    } while (checkk);
                }
            }
            if (checkk) {
                System.out.println("id này không tồn tại, vui lòng nhập lại");
            }
        } catch (Exception e) {
            System.out.println("không được nhập sai định dạng yêu cầu,nhập lại:");
            editTheSex();
        }
    }

    public static void deleteStudent() throws IOException, ClassNotFoundException {
        int choise;
        StudentManagerTest studentManagerTest = new StudentManagerTest();
        try {
            System.out.println("Nhập id học sinh để xóa:");
            int fineid = Integer.parseInt(sc.nextLine());
            for (Student student : studentTables.values()) {
                if (fineid == student.getId()) {
                    System.out.println("bạn có thực sự muốn xóa học sinh :" + student.getName() + " Mã số : " + student.getId());
                    try {
                        System.out.println("--------------------");
                        System.out.println("Ấn 1:Để xóa");
                        System.out.println("Ấn 2:để hủy bước này và quay lại menu chính");
                        choise = Integer.parseInt(sc.nextLine());
                        switch (choise) {
                            case 1:
                                studentTables.remove(student.getId());
                                System.out.println("Đã xóa thành công");
//                                studentTables.put(student.getId(), student);
//                                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                                saved.writeObject(studentTables);
                                saveToFile();
                                break;
                            case 2:
                                studentManagerTest.manager();
                                break;
                            default:
                                System.out.println("Hãy lựa chọn theo yêu cầu");
                        }
                        studentManagerTest.manager();
                    } catch (Exception e) {
                        System.out.println("Hãy nhập đúng yêu cầu");
                        studentManagerTest.deleteStudentRepeat();
                    }
                }
            }
//            System.out.println("bạn đã nhập sai id vui lòng nhập lại id");
        } catch (Exception e) {
            System.out.println("Sai định dạng rồi,Nhập lại nhé!!!");
            studentManagerTest.deleteStudentRepeat();
        }
    }

    public void enterStudentScores() throws IOException, ClassNotFoundException {
        boolean check = true;
        try {
            System.out.println("Nhập id học sinh để nhập điểm cho sinh viên:");
            int fineid = Integer.parseInt(sc.nextLine());


            for (Student student : studentTables.values()) {
                if ((fineid == student.getId()) && student.getGPA() != -1) {
                    System.out.println("học sinh này đã nhập đủ 4 cột điểm , không thể nhập thêm");
                    enterScores();
                }
                if ((fineid == student.getId()) && student.getGPA() == -1) {
                    enterScores2(student);
                    check = false;
                }
            }
            if (check) {
                System.out.println("Bạn đã nhập sai id hoặc không có id này trong danh sách");
                enterScores();
            }

        } catch (Exception e) {
            System.out.println("Sai định dạng rồi,nhập lại nhé!!!");
            enterScores();
        }

    }

    public void repeatMenu() {
        System.out.println("========================================");
        System.out.println("Menu");
        System.out.println("Ấn 1 : nhập lại id");
        System.out.println("Ấn 2 : Để quay lại menu chính");
        System.out.println("Ấn 0 : Để thoát");
    }

    public static String dateBirthday() {
        try {
            Date date = Date.valueOf(sc.nextLine());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(date);
            String[] y = strDate.split("/");
            int year = Integer.parseInt(y[2]);
            if (year <= 1970 || year >= 2010)
                throw new Exception("so tuoi khong hop le");
            return strDate;
        } catch (Exception e) {
            System.out.println("Vui lòng nhập đúng định dạng (yy-mm-dd):");
            return dateBirthday();
        }
    }

    public static boolean validateID(int id) {
        for (Student student : studentTables.values()) {
            if (student.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public void enterStudentScores1(Student student) throws IOException, ClassNotFoundException {
        if (student.getPoint15Minutes() != -1) {
            System.out.println("Sinh viên đã được nhập điểm ở cột này");
            System.out.println("vui lòng nhập lại id học sinh khác");
            enterScores2(student);
        }
        if (student.getPoint15Minutes() == -1) {
            System.out.println("Nhập điểm hệ số 1 thứ 1:");
            student.setPoint15Minutes(Double.parseDouble(sc.nextLine()));
            if (student.getPoint15Minutes() >= 0 && student.getPoint15Minutes() <= 10) {
                studentTables.put(student.getId(), student);
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                saveToFile();
                System.out.println("Đã thêm điểm thành công");
                tinhTrungBinh(student);
                enterScores2(student);
            } else {
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                enterStudentScores1(student);
            }
        }
    }

    public void enterStudentScores2(Student student) throws IOException, ClassNotFoundException {
        if (student.getOralTestScores() != -1) {
            System.out.println("Sinh viên đã được nhập điểm  ở cột này");
            System.out.println("vui lòng nhập lại id học sinh khác");
            enterScores2(student);
        }
        if (student.getOralTestScores() == -1) {
            System.out.println("Nhập điểm hệ số 1 thứ 2:");
            student.setOralTestScores(Double.parseDouble(sc.nextLine()));
            if (student.getOralTestScores() >= 0 && student.getOralTestScores() <= 10) {
                studentTables.put(student.getId(), student);
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                saveToFile();
                System.out.println("Đã thêm điểm thành công");
                tinhTrungBinh(student);
                enterScores2(student);
            } else {
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                enterStudentScores2(student);
            }
        }
    }

    public void enterStudentScores3(Student student) throws IOException, ClassNotFoundException {
        if (student.getHourTestScore() != -1) {
            System.out.println("Sinh viên đã được nhập điểm ở cột này ");
            System.out.println("vui lòng nhập lại id học sinh khác");
            enterScores2(student);
        }
        if (student.getHourTestScore() == -1) {
            System.out.println("Nhập điểm hệ số 2:");
            student.setHourTestScore(Double.parseDouble(sc.nextLine()));
            if (student.getHourTestScore() >= 0 && student.getHourTestScore() <= 10) {
                studentTables.put(student.getId(), student);
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                saveToFile();
                System.out.println("Đã thêm điểm thành công");
                tinhTrungBinh(student);
                enterScores2(student);
            } else {
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                enterStudentScores3(student);
            }
        }
    }

    public void enterStudentScores4(Student student) throws IOException, ClassNotFoundException {
        if (student.getSemesterTestScores() != -1) {
            System.out.println("Sinh viên đã được nhập điểm ở cột này ");
            System.out.println("vui lòng nhập lại id học sinh khác");
            enterScores2(student);
        }
        if (student.getSemesterTestScores() == -1) {
            System.out.println("Nhập điểm hệ số 3:");
            student.setSemesterTestScores(Double.parseDouble(sc.nextLine()));
            if (student.getSemesterTestScores() >= 0 && student.getSemesterTestScores() <= 10) {
                studentTables.put(student.getId(), student);
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                saveToFile();
                System.out.println("Đã thêm điểm thành công");
                tinhTrungBinh(student);
                enterScores2(student);
            } else {
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                enterStudentScores4(student);
            }
        }
    }

    public void menuEnterAllScores() {
        System.out.println("========================================");
        System.out.println("Menu");
        System.out.println("Ấn 1 : Để nhập điểm 15 phút cho All học sinh");
        System.out.println("Ấn 2 : Để nhập điểm miệng cho All học sinh");
        System.out.println("Ấn 3 : Để nhập điểm 1 tiết  cho All học sinh");
        System.out.println("Ấn 4 : Để nhập điểm thi học kì cho All học sinh");
        System.out.println("Ấn 5 : Để quay lại menu chính");
        System.out.println("Ấn 0 : Để thoát");
    }

    public void enterAllStudentScoresPoint15Minutes() {
        boolean check = true;
        try {
            StudentManagerTest manafingTraineesTest = new StudentManagerTest();
            for (Student student : studentTables.values()) {
                if (student.getPoint15Minutes() == -1) {
                    check = false;
                    System.out.println("nhập điểm 15 phút cho học sinh :" + student.getName() + " mã số:" + student.getId());
                    student.setPoint15Minutes(Double.parseDouble(sc.nextLine()));
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                    saveToFile();
                    System.out.println("Đã thêm điểm thành công");
                    tinhTrungBinh(student);
                }
            }
            if (check) {
                System.out.println("toàn bộ học sinh ở cột này đã được thêm điểm");
                System.out.println("nếu muốn thay đổi điểm hãy đến phần sửa điểm");
            }
            manafingTraineesTest.manager();
        } catch (Exception e) {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            System.out.println("hoặc không được nhập sai định dạng");
            enterAllStudentScoresPoint15Minutes();
        }

    }

    public void enterAllStudentScoresOralTestScores() {
        boolean check = true;
        try {
            StudentManagerTest manafingTraineesTest = new StudentManagerTest();
            for (Student student : studentTables.values()) {
                if (student.getOralTestScores() == -1) {
                    check = false;
                    System.out.println("nhập điểm miệng cho học sinh :" + student.getName() + " mã số:" + student.getId());
                    student.setOralTestScores(Double.parseDouble(sc.nextLine()));
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                    saveToFile();
                    System.out.println("Đã thêm điểm thành công");
                    tinhTrungBinh(student);
                }
            }
            if (check) {
                System.out.println("toàn bộ học sinh ở cột này đã được thêm điểm");
                System.out.println("nếu muốn thay đổi điểm hãy đến phần sửa điểm");
            }
            manafingTraineesTest.manager();
        } catch (Exception e) {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            System.out.println("hoặc không được nhập sai định dạng");
            enterAllStudentScoresOralTestScores();
        }
    }

    public void enterAllStudentScoresHourTestScore() {
        boolean check = true;
        try {
            StudentManagerTest manafingTraineesTest = new StudentManagerTest();
            for (Student student : studentTables.values()) {
                if (student.getHourTestScore() == -1) {
                    check = false;
                    System.out.println("nhập điểm 1 tiết cho học sinh :" + student.getName() + " mã số:" + student.getId());
                    student.setHourTestScore(Double.parseDouble(sc.nextLine()));
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                    saveToFile();
                    System.out.println("Đã thêm điểm thành công");
                    tinhTrungBinh(student);
                }
            }
            if (check) {
                System.out.println("toàn bộ học sinh ở cột này đã được thêm điểm");
                System.out.println("nếu muốn thay đổi điểm hãy đến phần sửa điểm");
            }
            manafingTraineesTest.manager();
        } catch (Exception e) {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            System.out.println("hoặc không được nhập sai định dạng");
            enterAllStudentScoresHourTestScore();
        }
    }

    public void enterAllStudentScoresSemesterTestScores() {
        boolean check = true;
        try {
            StudentManagerTest manafingTraineesTest = new StudentManagerTest();
            for (Student student : studentTables.values()) {
                if (student.getSemesterTestScores() == -1) {
                    check = false;
                    System.out.println("nhập điểm học kì cho học sinh :" + student.getName() + " mã số:" + student.getId());
                    student.setSemesterTestScores(Double.parseDouble(sc.nextLine()));
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                    saveToFile();
                    System.out.println("Đã thêm điểm thành công");
                    tinhTrungBinh(student);
                }
            }
            if (check) {
                System.out.println("toàn bộ học sinh ở cột này đã được thêm điểm");
                System.out.println("nếu muốn thay đổi điểm hãy đến phần sửa điểm");
            }
            manafingTraineesTest.manager();
        } catch (Exception e) {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            System.out.println("hoặc không được nhập sai định dạng");
            enterAllStudentScoresSemesterTestScores();
        }
    }

    public void enterScores() throws IOException, ClassNotFoundException {
        StudentManagerTest manafingTraineesTest = new StudentManagerTest();
        System.out.println("========================================");
        System.out.println("Menu");
        System.out.println("Ấn 1 : Để nhập lại id học sinh");
        System.out.println("Ấn 2 : Để quay lại menu chính");
        System.out.println("Ấn 0 : Để thoát");
        manafingTraineesTest.suaDiem();
    }

    public void enterScores2(Student student) throws IOException, ClassNotFoundException {
        StudentManagerTest manafingTraineesTest = new StudentManagerTest();
        System.out.println("========================================");
        System.out.println("Menu");
        System.out.println("Ấn 1 : Để nhập điểm hệ số 1 cột 1");
        System.out.println("Ấn 2 : Để nhập điểm hệ số 1 cột 2");
        System.out.println("Ấn 3 : Để nhập điểm hệ số 2 cột 3");
        System.out.println("Ấn 4 : Để nhập điểm hệ số 3 cột 4");
        System.out.println("Ấn 5 : Để quay lại menu chính");
        System.out.println("Ấn 0 : Để thoát");
        manafingTraineesTest.inScoroes(student);
    }

    public void enterScores3() {
        System.out.println("========================================");
        System.out.println("Menu");
        System.out.println("Ấn 1:Để nhập điểm 1 cột cho toàn bộ học sinh");
        System.out.println("Ấn 2:Để nhập điểm cho 1 học sinh");
        System.out.println("Ấn 3:Để quay lại menu chính");
        System.out.println("Ấn 0 : Để thoát");
    }

    public void editterStudentScores() throws IOException, ClassNotFoundException {
        boolean check = false;

        try {
            System.out.println("Nhập id học sinh để sửa điểm cho sinh viên:");
            int fineid = Integer.parseInt(sc.nextLine());

            for (Student student : studentTables.values()) {
                if (fineid == student.getId() && student.getGPA() == -1.0) {
                    System.out.println("sinh viên chưa được nhập hết điểm");
                    enterScores();
                }
                if (fineid == student.getId() && student.getGPA() != -1.0) {
                    editScoresrun(student);
                    check = true;
                }
            }
            if (check == false) {
                System.out.println("Bạn đã nhập sai id hoặc không có id này trong danh sách");
                System.out.println("Vui lòng nhập lại id");
                enterScores();
            }
        } catch (Exception e) {
            System.out.println("Sai định dạng rồi,nhập lại nhé!!!");
            enterScores();
        }
    }

    public void editScores1(Student student) throws Exception {
        if (student.getPoint15Minutes() != -1) {
            System.out.println("Nhập điểm hệ số 1 thứ 1:");
            student.setPoint15Minutes(Double.parseDouble(sc.nextLine()));
            if (student.getPoint15Minutes() >= 0 && student.getPoint15Minutes() <= 10) {
                student.tinhTrungbinhDiem();
                studentTables.put(student.getId(), student);
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                saveToFile();
                System.out.println("Sửa điểm thành công");
                editScoresrun(student);
            } else {
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                editScores1(student);
            }
        }
    }

    public void editScores2(Student student) throws Exception {
        if (student.getOralTestScores() != -1) {

            System.out.println("Nhập điểm hệ số 1 thứ 2:");
            student.setOralTestScores(Double.parseDouble(sc.nextLine()));
            if (student.getOralTestScores() >= 0 && student.getOralTestScores() <= 10) {
                student.tinhTrungbinhDiem();
                studentTables.put(student.getId(), student);
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                saveToFile();
                System.out.println("Sửa điểm thành công");
                editScoresrun(student);
            } else {
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                editScores2(student);
            }
        }
    }

    public void editScores3(Student student) throws Exception {
        if (student.getHourTestScore() != -1) {
            System.out.println("Nhập điểm hệ số 2:");
            student.setHourTestScore(Double.parseDouble(sc.nextLine()));
            if (student.getHourTestScore() >= 0 && student.getHourTestScore() <= 10) {
                student.tinhTrungbinhDiem();
                studentTables.put(student.getId(), student);
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                saveToFile();
                System.out.println("Sửa điểm thành công");
                editScoresrun(student);
            } else {
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                editScores3(student);
            }
        }
    }

    public void editScores4(Student student) throws Exception {
        if (student.getSemesterTestScores() != -1) {
            System.out.println("Nhập điểm hệ số 3:");
            student.setSemesterTestScores(Double.parseDouble(sc.nextLine()));
            if (student.getSemesterTestScores() >= 0 && student.getSemesterTestScores() <= 10) {
                student.tinhTrungbinhDiem();
                studentTables.put(student.getId(), student);
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                saveToFile();
                System.out.println("Sửa điểm thành công");
                editScoresrun(student);
            } else {
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                editScores4(student);
            }
        }
    }

    public void menuEditAllScores() {
        System.out.println("========================================");
        System.out.println("Menu");
        System.out.println("Ấn 1 : Để sửa điểm 15 phút cho All học sinh");
        System.out.println("Ấn 2 : Để sửa điểm miệng cho All học sinh");
        System.out.println("Ấn 3 : Để sửa điểm 1 tiết  cho All học sinh");
        System.out.println("Ấn 4 : Để sửa điểm thi học kì cho All học sinh");
        System.out.println("Ấn 5 : Để quay lại menu chính");
        System.out.println("Ấn 0 : Để thoát");
    }

    public void editAllScoresMenu() {
        System.out.println("========================================");
        System.out.println("Menu");
        System.out.println("Ấn 1:Để sửa điểm 1 cột cho toàn bộ học sinh");
        System.out.println("Ấn 2:Để sửa điểm cho 1 học sinh");
        System.out.println("Ấn 3:Để quay lại menu chính");
        System.out.println("Ấn 0 : Để thoát");
    }

    public void editAllStudentScoresPoint15Minutes() {
        boolean check = true;
        try {
            StudentManagerTest manafingTraineesTest = new StudentManagerTest();
            for (Student student : studentTables.values()) {
                if (student.getPoint15Minutes() != -1) {
                    check = false;
                    System.out.println("sửa điểm 15 phút cho học sinh :" + student.getName() + " mã số:" + student.getId());
                    student.setPoint15Minutes(Double.parseDouble(sc.nextLine()));
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                    saveToFile();
                    System.out.println("Đã thêm điểm thành công");
                    tinhTrungBinh(student);
                }
            }
            if (check) {
                System.out.println("toàn bộ học sinh ở cột này đã được thêm điểm");
                System.out.println("nếu muốn thay đổi điểm hãy đến phần sửa điểm");
            }
            manafingTraineesTest.manager();
        } catch (Exception e) {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            System.out.println("hoặc không được nhập sai định dạng");
            enterAllStudentScoresPoint15Minutes();
        }

    }

    public void editAllStudentScoresOralTestScores() {
        boolean check = true;
        try {
            StudentManagerTest manafingTraineesTest = new StudentManagerTest();
            for (Student student : studentTables.values()) {
                if (student.getOralTestScores() != -1) {
                    check = false;
                    System.out.println("sửa điểm miệng cho học sinh :" + student.getName() + " mã số:" + student.getId());
                    student.setOralTestScores(Double.parseDouble(sc.nextLine()));
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                    saveToFile();
                    System.out.println("Đã thêm điểm thành công");
                    tinhTrungBinh(student);
                }
            }
            if (check) {
                System.out.println("toàn bộ học sinh ở cột này đã được thêm điểm");
                System.out.println("nếu muốn thay đổi điểm hãy đến phần sửa điểm");
            }
            manafingTraineesTest.manager();
        } catch (Exception e) {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            System.out.println("hoặc không được nhập sai định dạng");
            enterAllStudentScoresOralTestScores();
        }
    }

    public void editAllStudentScoresHourTestScore() {
        boolean check = true;
        try {
            StudentManagerTest manafingTraineesTest = new StudentManagerTest();
            for (Student student : studentTables.values()) {
                if (student.getHourTestScore() != -1) {
                    check = false;
                    System.out.println("sửa điểm 1 tiết cho học sinh :" + student.getName() + " mã số:" + student.getId());
                    student.setHourTestScore(Double.parseDouble(sc.nextLine()));
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                    saveToFile();
                    System.out.println("Đã thêm điểm thành công");
                    tinhTrungBinh(student);
                }
            }
            if (check) {
                System.out.println("toàn bộ học sinh ở cột này đã được thêm điểm");
                System.out.println("nếu muốn thay đổi điểm hãy đến phần sửa điểm");
            }
            manafingTraineesTest.manager();
        } catch (Exception e) {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            System.out.println("hoặc không được nhập sai định dạng");
            enterAllStudentScoresHourTestScore();
        }
    }

    public void editAllStudentScoresSemesterTestScores() {
        boolean check = true;
        try {
            StudentManagerTest manafingTraineesTest = new StudentManagerTest();
            for (Student student : studentTables.values()) {
                if (student.getSemesterTestScores() != -1) {
                    check = false;
                    System.out.println("sửa điểm học kì cho học sinh :" + student.getName() + " mã số:" + student.getId());
                    student.setSemesterTestScores(Double.parseDouble(sc.nextLine()));
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
                    saveToFile();
                    System.out.println("Đã thêm điểm thành công");
                    tinhTrungBinh(student);
                }
            }
            if (check) {
                System.out.println("toàn bộ học sinh ở cột này đã được thêm điểm");
                System.out.println("nếu muốn thay đổi điểm hãy đến phần sửa điểm");
            }
            manafingTraineesTest.manager();
        } catch (Exception e) {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            System.out.println("hoặc không được nhập sai định dạng");
            enterAllStudentScoresSemesterTestScores();
        }
    }

    public void editScoresrun(Student student) throws Exception {
        StudentManagerTest manafingTraineesTest = new StudentManagerTest();
        System.out.println("========================================");
        System.out.println("Menu");
        System.out.println("Ấn 1 : Để nhập điểm hệ số 1 cột 1");
        System.out.println("Ấn 2 : Để nhập điểm hệ số 1 cột 2");
        System.out.println("Ấn 3 : Để nhập điểm hệ số 2 cột 3");
        System.out.println("Ấn 4 : Để nhập điểm hệ số 3 cột 4");
        System.out.println("Ấn 5 : Để quay lại menu chính");
        System.out.println("Ấn 0 : Để thoát");
        manafingTraineesTest.editScores(student);
    }

    public void tinhTrungBinh(Student student) {
        student.tinhTrungbinhDiem();
        if (student.getGPA() != -1) {
            studentTables.put(student.getId(), student);
//                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                saved.writeObject(studentTables);
            saveToFile();
        }
    }

    public static void graded() throws IOException, ClassNotFoundException {
//        ObjectInputStream readding = new ObjectInputStream(new FileInputStream("st.json"));
//        Object testFile = readding.readObject();
//        studentTables = (HashMap<Integer, Student>) testFile;
        List<Student> list = new ArrayList<>(studentTables.values());
        Collections.sort(list);
        System.out.format("||%-5s | ", "id");
        System.out.format("%-30s | ", "name");
        System.out.format("%-5s |", "sex");
        System.out.format("%-15s |", "birthday");
        System.out.format("%-8s | ", "15 Minutes");
        System.out.format("%-8s| ", "OralTest");
        System.out.format("%-8s | ", "HourTest");
        System.out.format("%-8s | ", "SemesterTest");
        System.out.format("%-8s || \n", "GPA");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Student student : list) {
            System.out.format("||%-5d | ", student.getId());
            System.out.format("%-30s | ", student.getName());
            System.out.format("%-5s | ", student.getSex());
            System.out.format("%-14s | ", student.getBirthday());
            System.out.format("%-9.2f | ", student.getPoint15Minutes());
            System.out.format("%-7.2f | ", student.getOralTestScores());
            System.out.format("%-8.2f | ", student.getHourTestScore());
            System.out.format("%-12.2f | ", student.getSemesterTestScores());
            System.out.format("%-8.2f || \n", student.getGPA());
        }
    }

    public static void saveToFile() {
        try (FileWriter fileWriter = new FileWriter("st.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(studentTables, fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}