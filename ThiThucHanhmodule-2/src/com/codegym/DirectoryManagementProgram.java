package com.codegym;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class DirectoryManagementProgram {
    Scanner sc = new Scanner(System.in);
    static ArrayList<Directory> directory;
    Directory list = new Directory();
    private static final String REGEX = "^[0-9\\-+]{9,15}$";
    private static final String NAME_REGEX = "^[a-zA-Z ]{2,30}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    static {
        try {
            ObjectInputStream read = new ObjectInputStream(new FileInputStream("Memory.txt"));
            Object testFile = read.readObject();
            directory = (ArrayList<Directory>) testFile;
        } catch (Exception e) {
            directory = new ArrayList();
        }
    }


    public void seeList() {
        System.out.format("%-30s | ", "name");
        System.out.format("%-5s |", "sex");
        System.out.format("%-15s |", "birthday");
        System.out.format("%-15s | ", "Phone Number");
        System.out.format("%-10s| ", "Group");
        System.out.format("%-15s | ", "Address");
        System.out.format("%-15s | ", "Email");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < directory.size(); i++) {
            System.out.format("||%-30d | ", list.getName());
            System.out.format("%-5s | ", list.getSex());
            System.out.format("%-15s | ", list.getBirthday());
            System.out.format("%-15s | ", list.getBirthday());
            System.out.format("%-15f | ", list.getPhoneNumber());
            System.out.format("%-15f | ", list.getGroup());
            System.out.format("%-15f | ", list.getAddress());
            System.out.format("%-15f | ", list.getEmail());
        }
    }

    public void addNewThePhoneNumber() {
        boolean test = true;
        try {
            System.out.println("Nhập số điện thoại bạn muốn thêm:");
            String check = sc.nextLine();
//            double checkNumber = Double.parseDouble(check);
            if (Pattern.matches(REGEX, check)) {
                for (int i = 0; i < directory.size(); i++) {
                    if (check.equals(list.getPhoneNumber())) {
                        list.setPhoneNumber(check);
                        System.out.println("Nhập nhóm bạn muốn thêm vào");
                        String groupAdd = sc.nextLine();
                        boolean test2 = true;
                        for (int j = 0; j < directory.size(); j++) {
                            if (groupAdd.equals(list.getGroup())) {
                                System.out.println("bạn đã thêm số điện thoại vào nhóm :" + list.getGroup() + "thành công");
                                list.setGroup(groupAdd);
                                test2 = false;
                            }
                        }
                        if (test2) {
                            System.out.println("bạn đã tạo 1 nhóm mới");
                            list.setGroup(groupAdd);
                        }
                        String name = enterTheName();
                        list.setName(name);
                        System.out.println("Nhập ngày tháng năm sinh của sinh viên (yy-mm-dd):");
                        list.setBirthday(dateBirthday());

                        boolean test3 = true;
                        do {
                            System.out.println("nhập Email");
                            String email = sc.nextLine();
                            if (Pattern.matches(REGEX, email) || email == null) {
                                list.getEmail();
                                test3 = false;
                            } else {
                                System.out.println("Nhập đúng email hoặc bỏ trống");
                            }
                        } while (test3);
                        boolean checkk = false;
                        do {
                            System.out.println("nhập giới tính của sinh viên:");
                            System.out.println("giới tính Nam:nhập 'Nam' , giới tính Nữ:nhập 'Nu'");
                            list.setSex(sc.nextLine().trim().toLowerCase(Locale.ROOT));
                            if (list.getSex().equals("nam") || list.getSex().equals("nu")) {
                                checkk = true;
                                directory.add(list);
                                ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Memory.txt"));
                                saved.writeObject(directory);
                            } else {
                                System.out.println("bạn nhập sai rồi,nhập theo hướng dẫn");
                                System.out.println("giới tính Nam:nhập 'Nam' , giới tính Nữ:nhập 'Nữ'");
                            }
                        } while (!checkk);
                    }
                }
            }
            else {
                System.out.println("nhập lại");
                addNewThePhoneNumber();
            }

        } catch (Exception e) {
            System.out.println("Sai định dạng hoặc trùng");
            addNewThePhoneNumber();
        }

    }


    public void editTheNumber() {
        boolean check = false;
        do {
            try {
                System.out.println("nhập số điện thoại để sửa");
                String numberPhone = sc.nextLine();
                if (Pattern.matches(REGEX, numberPhone)) {
                    for (int i = 0; i < directory.size(); i++) {
                        if (numberPhone.equals(list.getName())) {
                            System.out.println("nhập tên mới");
                            String name = sc.nextLine().trim();
                            list.setName(name);
                            System.out.println("nhập địa chỉ mới");
                            String diaChi = sc.nextLine().trim();
                            list.getAddress();
                            System.out.println("nhập lại ngày sinh");
                            list.setBirthday(dateBirthday());
                            boolean test4 = true;
                            do {
                                System.out.println("nhập Email");
                                String email = sc.nextLine();
                                if (Pattern.matches(REGEX, email) || email == null) {
                                    list.getEmail();
                                    test4 = false;
                                } else {
                                    System.out.println("Nhập đúng email hoặc bỏ trống");
                                }
                            } while (test4);
                            check = true;
                            boolean checkk = false;
                            do {
                                System.out.println("nhập giới tính của sinh viên:");
                                System.out.println("giới tính Nam:nhập 'Nam' , giới tính Nữ:nhập 'Nu'");
                                list.setSex(sc.nextLine().trim().toLowerCase(Locale.ROOT));
                                if (list.getSex().equals("nam") || list.getSex().equals("nu")) {
                                    checkk = true;
                                    directory.add(list);
                                    ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Memory.txt"));
                                    saved.writeObject(directory);
                                } else {
                                    System.out.println("bạn nhập sai rồi,nhập theo hướng dẫn");
                                    System.out.println("giới tính Nam:nhập 'Nam' , giới tính Nữ:nhập 'Nữ'");
                                }
                            } while (!checkk);
                        }
                    }
                }
                else {
                    System.out.println("nhập sai nhập lại");
                    editTheNumber();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (check);
    }

    public String dateBirthday() {
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

    public String enterTheName() {
        String name;
        System.out.println("Nhập tên sinh viên:");
        do {
            name = sc.nextLine().trim().replaceAll("\\s+", " ");
            if (Pattern.matches(NAME_REGEX, name))
                break;
            else
                System.out.println("Nhap lai ten");
        } while (true);
        return name;
    }

    public void deleteNumberPhone() {
        boolean check = false;
        do {
            try {
                System.out.println("Nhập sdt để xóa:");
                String finenumber = sc.nextLine();
                if (Pattern.matches(REGEX, finenumber)){
                    for (int i = 0; i < directory.size(); i++) {
                        if (finenumber.equals(list.getPhoneNumber())) {
                            directory.remove(list);
                            directory.add(list);
                            ObjectOutputStream saved = new ObjectOutputStream(new FileOutputStream("Memory.txt"));
                            saved.writeObject(directory);
                            System.out.println("Đã xóa thành công");
                            check = true;
                        }
                    }
                    if (check = false) {
                        System.out.println("bạn đã nhập sai sdt vui lòng nhập lại sdt");
                        deleteNumberPhone();
                    }
                }else {
                    System.out.println("nhập sai,nhập lại");
                    deleteNumberPhone();
                }
            } catch (Exception e) {
                System.out.println("Sai định dạng rồi,Nhập lại nhé!!!");
               deleteNumberPhone();
            }
        } while (check != true);
    }

    public  void speach(){
        System.out.println(" nhập số điện thoại bạn muốn tìm");
        String speach = sc.nextLine();
        boolean check1=true;
        if (Pattern.matches(REGEX, speach)){
            for (int i = 0; i <directory.size() ; i++) {
                if (speach.equals(list.getPhoneNumber())){
                    System.out.println(list);
                    check1=false;
                }if (check1){
                    System.out.println("không có số dt này trong danh sách");
                }
            }
        }else {
            System.out.println("nhập lại");
            speach();
        }
    }
}