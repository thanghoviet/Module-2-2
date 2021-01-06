
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        StudentManagerTest run = new StudentManagerTest();
        run.manager();
    }
}
//    public static void editStudentInformation() {
//        boolean checkk = true;
//        try {
//            System.out.println("Nhập id học sinh để sửa thông tin:");
//            int fineid = Integer.parseInt(sc.nextLine());
//            for (Student student : studentTables.values()) {
//                if (fineid == student.getId()) {
//                    System.out.println("Nhập lại tên sinh viên:");
//                    student.setName(sc.nextLine().trim());
//                    System.out.println("Nhập lại ngày sinh của sinh viên (yy-mm-dd):");
//                    student.setBirthday(dateBirthday());
//                    do {
//                        System.out.println("nhập lại giới tính của sinh viên:");
//                        System.out.println("giới tính Nam:nhập 'Nam' , giới tính Nữ:nhập 'Nữ'");
//                        student.setSex(sc.nextLine().trim().toLowerCase(Locale.ROOT));
//                        if (student.getSex().equals("nam") || student.getSex().equals("nữ")) {
//                            checkk = false;
//                            studentTables.put(student.getId(), student);
//                            ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Student.json"));
//                            saved.writeObject(studentTables);
//                            System.out.println("sửa thông tin học sinh thành công");
//                        } else {
//                            System.out.println("bạn nhập sai rồi,nhập theo hướng dẫn");
//                        }
//                    } while (checkk);
//                }
//                if (checkk) {
//                    System.out.println("bạn đã nhập sai id vui lòng nhập lại");
//
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Sai định dạng rồi.nhập lại nhé!!!");
//            editStudentInformation();
//        }
//    }