import java.util.ArrayList;

public class PhoneBookScanner {
    PhoneBookFormat contactFormat=new PhoneBookFormat();
    PhoneBookController contactController=new PhoneBookController();


    public void handleShowAllContactList(){
        ArrayList<PhoneBook> phoneList =contactController.returnContactList();
        contactController.showAllListContact(phoneList);
    }

    public void handleAddContactToList(){
        String phoneNumber=contactFormat.getFormatPhoneNumber("Viết số điện thoại của bạn muốn thêm:");
        String group=contactFormat.getLine("Viết nhóm bạn muốn thêm:");
        String fullName= contactFormat.getName("Viết tên bạn muốn thêm co SDT trên");
        String gender=contactFormat.getFormatGender("Giới tính:");
        String address= contactFormat.getLine("Địa chỉ:");
        String dOB= contactFormat.getFormatDOB("Ngày sinh:");
        String email=contactFormat.getFormatEmail("Email:");
        contactController.addNewContactToList(phoneNumber,group,fullName,gender,address,dOB,email);
    }

    public void handleEditContactFromList() {
        ArrayList<PhoneBook> phoneList = contactController.returnContactList();
        if (phoneList.size() == 0) {
            System.out.println("Danh sách liên hệ trống!");
        } else {
            String phoneNumberEdit = contactFormat.getFormatPhoneNumber("Nhập số điện thoại bạn muốn thêm:");
            PhoneBook contactEdit = contactController.findContactWithNumberPhone(phoneList, phoneNumberEdit);
            editContact(contactEdit);
        }
    }

    public void editContact(PhoneBook PhoneBookEdit) {
        if (PhoneBookEdit == null) {
            System.out.println("Không tìm thấy số điện thoại trên");
            System.out.println("Có thể bạn nhập sai SDT");
        } else {
            String group = contactFormat.getLine("bạn muốn sửa thành nhóm gì:");
            String fullName = contactFormat.getName("nhập lại tên mới:");
            String gender = contactFormat.getFormatGender("nhập lại giới tính mới:");
            String address = contactFormat.getLine("nhập lại địa chỉ mới:");
            String dOB = contactFormat.getFormatDOB("nhập lại ngày sinh mới:");
            String email = contactFormat.getFormatEmail("nhập lại Email mới:");
            contactController.editContact(PhoneBookEdit, group, fullName, gender, address, dOB, email);
        }
    }

    public void handleRemoveContactFromList(){
        ArrayList<PhoneBook> contactList=contactController.returnContactList();
        if (contactList.size()==0){
            System.out.println("Danh bạ trống");
        }else {
            String phoneNumberRemove = contactFormat.getFormatPhoneNumber("Nhập SDT bạn muốn xóa:");
            PhoneBook phoneList = contactController.findContactWithNumberPhone(contactList, phoneNumberRemove);
            removeContactFromList(phoneList);
        }
    }

    public void removeContactFromList(PhoneBook phoneBookRemove){
        if(phoneBookRemove==null){
            System.out.println("Không tìm thấy SDT bạn muốn xóa");
        }else {
            contactController.removeContactFromContactList(phoneBookRemove);
        }
    }

    public void showListFind(ArrayList<PhoneBook> listFind){
        for (PhoneBook phoneBook:listFind){
            System.out.println(phoneBook);
        }
    }

    public void handleFindListWithPhoneNumber(){
        ArrayList<PhoneBook> contactList=contactController.returnContactList();
        String phoneNumberFind=contactFormat.getFormatPhoneNumberFind("Nhập SDT bạn muốn tìm:");
        ArrayList<PhoneBook> contactPhoneNumberList=contactController.findListContactWithPhoneNumber(contactList,phoneNumberFind);
        if(contactPhoneNumberList.size()==0){
            System.out.println("Không tìm thấy với số điện thoại!");
        }else {
            showListFind(contactPhoneNumberList);
        }
    }

    public void handleFindListWithName(){
        ArrayList<PhoneBook> contactList=contactController.returnContactList();
        String nameFind=contactFormat.getNameFind("Nhập tên bạn muốn tìm");
        ArrayList<PhoneBook> contactNameList=contactController.findListContactWithFullName(contactList,nameFind);
        if(contactNameList.size()==0){
            System.out.println("Không tìm thấy với số điện thoại");
        }else {
            showListFind(contactNameList);
        }
    }

    public void handleReadFile(){
        contactController.readList();
    }

    public void handleWriteFile(){
        ArrayList<PhoneBook> phoneList=contactController.returnContactList();
        contactController.saveList(phoneList);
    }
}

