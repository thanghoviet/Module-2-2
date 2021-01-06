import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {
    private int id;
    private String name;
    private String birthday;
    private String sex;
    private double point15Minutes = -1;
    private double oralTestScores = -1;
    private double HourTestScore = -1;
    private double semesterTestScores = -1;
    private double GPA = -1;
    public Student() {}

    public Student(int id, String name, String birthday, String sex, double point15Minutes, double oralTestScores, double hourTestScore, double semesterTestScores, double GPA) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.point15Minutes = point15Minutes;
        this.oralTestScores = oralTestScores;
        HourTestScore = hourTestScore;
        this.semesterTestScores = semesterTestScores;
        this.GPA = GPA;
    }

//    public String toJSONString() {
//        StringBuffer sb = new StringBuffer();
//        sb.append("{"); // Bắt đầu một đối tượng JSON là dấu mở ngoặc nhọn
//        sb.append("\"id\":\"" + getId() + "\""); // dòng này có nghĩa là
//        // "id":"Giá_Trị"
//        sb.append(","); // sau mỗi cặp key/value là một dấu phẩy
//        sb.append("\"name\":\"" + getName() + "\"");
//        sb.append(",");
//        sb.append("\"birthday\":\"" + getBirthday() + "\"");
//        sb.append(",");
//        sb.append("\"sex\":\"" + getSex() + "\"");
//        sb.append(",");
//        sb.append("\"point15Minutes\":\"" + getPoint15Minutes() + "\"");
//        sb.append(",");
//        sb.append("\"oralTestScores\":\"" + getOralTestScores() + "\"");
//        sb.append(",");
//        sb.append("\"HourTestScore\":\"" + getHourTestScore() + "\"");
//        sb.append(",");
//        sb.append("\"semesterTestScores\":\"" + getSemesterTestScores() + "\"");
//        sb.append(",");
//        sb.append("\"GPA\":\"" + getGPA() + "\"");
//        sb.append("}"); // Kết thúc một đối tượng JSON là dấu đóng ngoặc nhọn
//
//        return sb.toString();
//    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getPoint15Minutes() {
        return point15Minutes;
    }

    public double getOralTestScores() {
        return oralTestScores;
    }

    public double getHourTestScore() {
        return HourTestScore;
    }

    public double getSemesterTestScores() {
        return semesterTestScores;
    }

    public void setPoint15Minutes(double point15Minutes) {
        this.point15Minutes = point15Minutes;
    }

    public void setOralTestScores(double oralTestScores) {
        this.oralTestScores = oralTestScores;
    }

    public void setHourTestScore(double hourTestScore) {
        HourTestScore = hourTestScore;
    }

    public void setSemesterTestScores(double semesterTestScores) {
        this.semesterTestScores = semesterTestScores;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public double getGPA() {
        return GPA;
    }
    public void tinhTrungbinhDiem() {
        if (getPoint15Minutes() != -1 && getOralTestScores() != -1 && getHourTestScore() != -1 && getSemesterTestScores() != -1) {
            GPA = (point15Minutes + oralTestScores + HourTestScore * 2 + semesterTestScores * 3) / 7;
        }

    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(o.getGPA(), GPA);
    }

}
