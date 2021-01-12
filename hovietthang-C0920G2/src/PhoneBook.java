public class PhoneBook {
    private String phoneNumber;
    private String group;
    private String fullName;
    private String gender;
    private String address;
    private String dayOfBirth;
    private String email;

    public PhoneBook(String phoneNumber, String group, String fullName, String gender, String address, String dayOfBirth, String email) {
        this.phoneNumber = phoneNumber;
        this.group = group;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGroup() {
        return group;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return phoneNumber +
                ", " + group +
                ", " + fullName +
                "," + gender +
                ", " + address +
                ", " + dayOfBirth +
                ", " + email
                ;
    }
}
