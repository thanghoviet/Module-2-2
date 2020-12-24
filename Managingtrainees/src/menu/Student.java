package menu;

import java.io.Serializable;

public class Student implements Serializable, Comparable<Student> {
    private int id;
    private String name;
    private int age;
    private double diemhs1thu1 = -1;
    private double diemhs1thu2 = -1;
    private double diemhs2 = -1;
    private double diemhs3 = -1;
    private double tongdiem = -1;
    private int counts = 0;

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getDiemhs1thu1() {
        return diemhs1thu1;
    }

    public void setDiemhs1thu1(double diemhs1thu1) {
        this.diemhs1thu1 = diemhs1thu1;
    }

    public double getDiemhs1thu2() {
        return diemhs1thu2;
    }

    public void setDiemhs1thu2(double diemhs1thu2) {
        this.diemhs1thu2 = diemhs1thu2;
    }

    public double getDiemhs2() {
        return diemhs2;
    }

    public void setDiemhs2(double diemhs2) {
        this.diemhs2 = diemhs2;
    }

    public double getDiemhs3() {
        return diemhs3;
    }

    public void setDiemhs3(double diemhs3) {
        this.diemhs3 = diemhs3;
    }

    public double getTongdiem() {
        return tongdiem;
    }

    public void setTongdiem(double tongdiem) {
        this.tongdiem = tongdiem;
    }


    public void tinhTrungbinhDiem() {
        if (getDiemhs1thu1() != -1 && getDiemhs1thu2() != -1 && getDiemhs2() != -1 && getDiemhs3() != -1) {
            tongdiem = (diemhs1thu1 + diemhs1thu2 + diemhs2 * 2 + diemhs3 * 3) / 7;
        }

    }


    @Override
    public String toString() {
        tinhTrungbinhDiem();
        return "\t" + id + " |" + name + "|" + diemhs1thu1 + "|"
                + diemhs1thu2 + "|"
                + diemhs2 + "|"
                + diemhs3 + "|"
                + tongdiem + "|";
    }

    @Override
    public int compareTo(Student o) {

        return Double.compare(o.getTongdiem(), tongdiem);
    }


}
