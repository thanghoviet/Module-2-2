package menu;

import java.io.*;
import java.util.*;

public class ManagingTrainees {
    Scanner sc = new Scanner(System.in);
    HashMap<Integer, Student> studentTables;

    //try catch
    {
        try (ObjectInputStream read = new ObjectInputStream(new FileInputStream("Student.dat"))) {
            Object testFile = read.readObject();
            studentTables = (HashMap<Integer, Student>) testFile;
        } catch (Exception e) {
            studentTables = new HashMap<>();
        }
    }

    public void Menu() {
        System.out.println("1. Xem danh sách học viên");
        System.out.println("2. Thêm học viên");
        System.out.println("3. Sửa thông tin học viên");
        System.out.println("4. Xoá học viên");
        System.out.println("5. Nhập điểm học viên");
        System.out.println("6. Sửa nhập điểm học viên");
        System.out.println("7. Xếp loại học viên " +
                "(hiển thị danh sách học viên theo điểm từ cao đến thấp)");
        System.out.println("Nhập 0 để thoát");
        System.out.println("Nhập lựa chọn của bạn:  ");
    }


    public void listOfStudents() {
        System.out.format("||%-5s | ", "id");
        System.out.format("%-25s | ", "name");
        System.out.format("%-8s | ", "Điểm hs1");
        System.out.format("%-8s| ", "Điểm hs1");
        System.out.format("%-8s | ", "Điểm hs2");
        System.out.format("%-8s | ", "Điểm hs3");
        System.out.format("%-8s || \n", "Điểm TB");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Student student : studentTables.values()) {
            System.out.format("||%-5d | ", student.getId());
            System.out.format("%-25s | ", student.getName());
            System.out.format("%-8.1f | ", student.getDiemhs1thu1());
            System.out.format("%-8.1f | ", student.getDiemhs1thu2());
            System.out.format("%-8.1f | ", student.getDiemhs2());
            System.out.format("%-8.1f | ", student.getDiemhs3());
            System.out.format("%-7.1f || \n", student.getTongdiem());
        }
    }

    public void addTrainees() throws IOException {
        Student student = new Student();
        boolean check = false;
        do {
            try {
                System.out.println("Nhập mã số sinh viên:");
                int checkId = Integer.parseInt(sc.nextLine());
                if (validateID(checkId) && checkId >= 0) {
                    student.setId(checkId);
                    check = true;
                } else {
                    System.out.println("trùng id roi");
                }
            } catch (Exception e) {
                System.out.println("Sai định dạng hoặc trùng");
            }
        } while (check != true);

        System.out.println("Nhập tên sinh viên:");
        student.setName(sc.nextLine());
        boolean checkk = false;
        do {
            try {
                System.out.println("Nhập tuổi sinh viên:");
                int age = Integer.parseInt(sc.nextLine());
                if (age > 0 && age <= 120) {
                    student.setAge(age);
                    checkk = true;
                } else {
                    System.out.println("bạn đã nhập sai.nhập lại nhé!!");
                }
            } catch (Exception e) {
                System.out.println("Sai định dạng,nhập lại nhé");
            }
        } while (checkk != true);
        studentTables.put(student.getId(), student);
        ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
        saved.writeObject(studentTables);

    }

    public boolean validateID(int id) {
        for (Student student : studentTables.values()) {
            if (student.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public void editStudentInformation() {
        boolean check = false;
        do {
            try {
                System.out.println("Nhập id học sinh để sửa thông tin:");
                int fineid = Integer.parseInt(sc.nextLine());
                for (Student student : studentTables.values()) {
                    if (fineid == student.getId()) {
                        System.out.println("Nhập lại tên sinh viên:");
                        student.setName(sc.nextLine());
                        System.out.println("Nhập lại tuổi sinh viên:");
                        student.setAge(Integer.parseInt(sc.nextLine()));
                        studentTables.put(student.getId(), student);
                        ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
                        saved.writeObject(studentTables);
                        check = true;
                    }
                }
                System.out.println("bạn đã nhập sai id vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println("Sai định dạng rồi.nhập lại nhé!!!");
            }
        } while (check != true);
    }

    public void deleteStudent() {
        boolean check = false;
        do {
            try {
                System.out.println("Nhập id học sinh để xóa:");
                int fineid = Integer.parseInt(sc.nextLine());
                for (Student student : studentTables.values()) {
                    if (fineid == student.getId()) {
                        studentTables.remove(student.getId());
                        System.out.println("Đã xóa thành công");
                        check = true;
                    }
                }
                System.out.println("bạn đã nhập sai id vui lòng nhập lại id");
            } catch (Exception e) {
                System.out.println("Sai định dạng rồi,Nhập lại nhé!!!");
            }
        } while (check != true);
    }

    public void enterStudentScores() throws IOException, ClassNotFoundException {
        boolean check = true;
        try {
            System.out.println("Nhập id học sinh để nhập điểm cho sinh viên:");
            int fineid = Integer.parseInt(sc.nextLine());


            for (Student student : studentTables.values()) {
                if ((fineid == student.getId()) && student.getTongdiem() != -1) {
                    System.out.println("học sinh này đã nhập đủ 4 cột điểm , không thể nhập thêm");
                    enterScores();
                }
                if ((fineid == student.getId()) && student.getTongdiem() == -1) {
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


    public void editterStudentScores() throws IOException, ClassNotFoundException {
        boolean check = false;

        try {
            System.out.println("Nhập id học sinh để sửa điểm cho sinh viên:");
            int fineid = Integer.parseInt(sc.nextLine());

            for (Student student : studentTables.values()) {
                if (fineid == student.getId() &&student.getTongdiem() == -1.0) {
                    System.out.println("sinh viên chưa được nhập hết điểm");
                    enterScores();
                }
                if (fineid == student.getId() && student.getTongdiem() != -1.0) {
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

    public void graded() throws IOException, ClassNotFoundException {
        ObjectInputStream readding = new ObjectInputStream(new FileInputStream("Student.dat"));
        Object testFile = readding.readObject();
        HashMap<Integer, Student> studentTabless = (HashMap<Integer, Student>) testFile;
        List<Student> list = new ArrayList<Student>(studentTabless.values());
        Collections.sort(list);
        System.out.format("||%-3s | ", "id");
        System.out.format("%-25s | ", "name");
        System.out.format("%-8s | ", "Điểm hs1");
        System.out.format("%-8s| ", "Điểm hs1");
        System.out.format("%-8s | ", "Điểm hs2");
        System.out.format("%-8s | ", "Điểm hs3");
        System.out.format("%-8s || \n", "Điểm TB");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Student st : list) {
            System.out.format("||%-3d | ", st.getId());
            System.out.format("%-25s | ", st.getName());
            System.out.format("%-8.1f | ", st.getDiemhs1thu1());
            System.out.format("%-8.1f | ", st.getDiemhs1thu2());
            System.out.format("%-8.1f | ", st.getDiemhs2());
            System.out.format("%-8.1f | ", st.getDiemhs3());
            System.out.format("%-7.1f || \n", st.getTongdiem());
        }
    }

    public void enterScores() throws IOException, ClassNotFoundException {
        ManafingTraineesTest manafingTraineesTest = new ManafingTraineesTest();
        System.out.println("========================================");
        System.out.println("Menu");
        System.out.println("Ấn 1 : Để quay lại menu chính");
        System.out.println("Ấn 0 : Để thoát");
        manafingTraineesTest.suaDiem();
    }

    public void enterScores2(Student student) throws IOException, ClassNotFoundException {
        ManafingTraineesTest manafingTraineesTest = new ManafingTraineesTest();
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

    public void editScoresrun(Student student) throws Exception {
        ManafingTraineesTest manafingTraineesTest = new ManafingTraineesTest();
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

    public void tinhTrungBinh(Student student) throws IOException {
        student.tinhTrungbinhDiem();
        if (student.getTongdiem() != -1) {
            studentTables.put(student.getId(), student);
            ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
            saved.writeObject(studentTables);
        }
    }

    public void enterStudentScores1(Student student) throws IOException, ClassNotFoundException {
        if (student.getDiemhs1thu1() != -1) {
            System.out.println("Sinh viên đã được nhập điểm ở cột này");
            System.out.println("vui lòng nhập lại id học sinh khác");
            enterScores();
        }
        if (student.getDiemhs1thu1() == -1) {
            System.out.println("Nhập điểm hệ số 1 thứ 1:");
            student.setDiemhs1thu1(Double.parseDouble(sc.nextLine()));
            if (student.getDiemhs1thu1() >= 0 && student.getDiemhs1thu1() <= 10) {
                studentTables.put(student.getId(), student);
                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
                saved.writeObject(studentTables);
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
        if (student.getDiemhs1thu2() == -1) {
            System.out.println("Nhập điểm hệ số 1 thứ 2:");
            student.setDiemhs1thu2(Double.parseDouble(sc.nextLine()));
            if (student.getDiemhs1thu2() >= 0 && student.getDiemhs1thu2() <= 10) {
                studentTables.put(student.getId(), student);
                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
                saved.writeObject(studentTables);
                System.out.println("Đã thêm điểm thành công");
                tinhTrungBinh(student);
                enterScores2(student);
            }
        } else {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            enterStudentScores2(student);
        }
        if (student.getDiemhs1thu2() != -1) {
            System.out.println("Sinh viên đã được nhập điểm  ở cột này");
            System.out.println("vui lòng nhập lại id học sinh khác");
            enterScores();
        }
    }

    public void enterStudentScores3(Student student) throws IOException, ClassNotFoundException {
        if (student.getDiemhs2() == -1) {
            System.out.println("Nhập điểm hệ số 2:");
            student.setDiemhs2(Double.parseDouble(sc.nextLine()));
            if (student.getDiemhs2() >= 0 && student.getDiemhs2() <= 10) {
                studentTables.put(student.getId(), student);
                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
                saved.writeObject(studentTables);
                System.out.println("Đã thêm điểm thành công");
                tinhTrungBinh(student);
                enterScores2(student);
            }
        } else {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            enterStudentScores3(student);
        }
        if (student.getDiemhs2() != -1) {
            System.out.println("Sinh viên đã được nhập điểm ở cột này ");
            System.out.println("vui lòng nhập lại id học sinh khác");
            enterScores();
        }
    }

    public void enterStudentScores4(Student student) throws IOException, ClassNotFoundException {
        if (student.getDiemhs3() == -1) {
            System.out.println("Nhập điểm hệ số 3:");
            student.setDiemhs3(Double.parseDouble(sc.nextLine()));
            if (student.getDiemhs3() >= 0 && student.getDiemhs3() <= 10) {
                studentTables.put(student.getId(), student);
                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
                saved.writeObject(studentTables);
                System.out.println("Đã thêm điểm thành công");
                tinhTrungBinh(student);
                enterScores2(student);
            }
        } else {
            System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
            enterStudentScores4(student);
        }
        if (student.getDiemhs3() != -1) {
            System.out.println("Sinh viên đã được nhập điểm ở cột này ");
            System.out.println("vui lòng nhập lại id học sinh khác");
            enterScores();
        }
    }

    public void editScores1(Student student) throws Exception {
        if (student.getDiemhs1thu1() != -1) {
            System.out.println("Nhập điểm hệ số 1 thứ 1:");
            student.setDiemhs1thu1(Double.parseDouble(sc.nextLine()));
            if (student.getDiemhs1thu1() >= 0 && student.getDiemhs1thu1() <= 10) {
                student.tinhTrungbinhDiem();
                studentTables.put(student.getId(), student);
                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
                saved.writeObject(studentTables);
                System.out.println("Sửa điểm thành công");
                editScoresrun(student);
            }else{
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                editScores1(student);
            }
        }
    }

    public void editScores2(Student student) throws Exception {
        if (student.getDiemhs1thu2() != -1) {

            System.out.println("Nhập điểm hệ số 1 thứ 2:");
            student.setDiemhs1thu2(Double.parseDouble(sc.nextLine()));
            if (student.getDiemhs1thu2() >= 0 && student.getDiemhs1thu2() <= 10) {
                student.tinhTrungbinhDiem();
                studentTables.put(student.getId(), student);
                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
                saved.writeObject(studentTables);
                System.out.println("Sửa điểm thành công");
                editScoresrun(student);
            }else{
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                editScores2(student);
            }
        }
    }

    public void editScores3(Student student) throws Exception {
        if (student.getDiemhs2() != -1) {
            System.out.println("Nhập điểm hệ số 2:");
            student.setDiemhs2(Double.parseDouble(sc.nextLine()));
            if (student.getDiemhs2() >= 0 && student.getDiemhs2() <= 10) {
            student.tinhTrungbinhDiem();
                studentTables.put(student.getId(), student);
                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
                saved.writeObject(studentTables);
                System.out.println("Sửa điểm thành công");
            editScoresrun(student);
            }else{
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                editScores3(student);
            }
        }
    }

    public void editScores4(Student student) throws Exception {
        if (student.getDiemhs3() != -1) {
            System.out.println("Nhập điểm hệ số 3:");
            student.setDiemhs3(Double.parseDouble(sc.nextLine()));
            if (student.getDiemhs3() >= 0 && student.getDiemhs3() <= 10) {
            student.tinhTrungbinhDiem();
                studentTables.put(student.getId(), student);
                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.dat"));
                saved.writeObject(studentTables);
                System.out.println("Sửa điểm thành công");
                editScoresrun(student);
            }else{
                System.out.println("không được nhập số âm hoặc vượt quá 10 điểm");
                editScores4(student);
            }
        }
    }
}