import java.util.ArrayList;

public class PhoneBookController {
    ArrayList<PhoneBook> contactList=new ArrayList<>();
    ArrayList<PhoneBook> contactNumber = new ArrayList<>();
    ArrayList<PhoneBook> contactName = new ArrayList<>();
    CSVFile csvFile=new CSVFile();

    public boolean checkContactList(ArrayList<PhoneBook> phoneList) {
        return phoneList.size() == 0;
    }


    public ArrayList<PhoneBook> returnContactList(){
        return contactList;

    }

    public void showAllListContact(ArrayList<PhoneBook> contactList) {
        if (checkContactList(contactList)) {
            System.out.println("Chưa có SDT nào được thêm(Trống)");
        } else {
            for (PhoneBook phoneBook : contactList) {
                System.out.println(phoneBook);
            }
        }
    }

    public void addNewContactToList(String phoneNumber, String group, String fullName, String gender, String address, String dayOfBirth, String email) {
        contactList.add(new PhoneBook(phoneNumber, group, fullName, gender, address, dayOfBirth, email));
        System.out.println("---HẾT---");
    }

    public PhoneBook findContactWithNumberPhone(ArrayList<PhoneBook> contactList,String phoneNumberFind) {
        for (PhoneBook phoneBook : contactList) {
            if (phoneBook.getPhoneNumber().equals(phoneNumberFind)) {
                return phoneBook;
            }
        }
        return null;
    }

    public void editContact(PhoneBook phoneBook,String group, String fullName, String gender, String address, String dayOfBirth, String email){
        phoneBook.setGroup(group);
        phoneBook.setFullName(fullName);
        phoneBook.setGender(gender);
        phoneBook.setAddress(address);
        phoneBook.setDayOfBirth(dayOfBirth);
        phoneBook.setEmail(email);
        System.out.println("---HẾT---");
    }

    public void removeContactFromContactList(PhoneBook listRemove) {
        contactList.remove(listRemove);
        System.out.println("---HẾT---");
    }

    public ArrayList<PhoneBook> findListContactWithPhoneNumber(ArrayList<PhoneBook> phoneList,String phoneNumber) {
        if (checkContactList(phoneList)) {
            System.out.println("Chưa có SDT nào được thêm(Trống)");
        } else {
            for (PhoneBook phoneBook : phoneList) {
                if (phoneBook.getPhoneNumber().contains(phoneNumber)) {
                    contactNumber.add(phoneBook);
                }
            }
        }
        return contactNumber;
    }

    public ArrayList<PhoneBook> findListContactWithFullName(ArrayList<PhoneBook> phoneList,String fullName){
        if(checkContactList(phoneList)){
            System.out.println("Chưa có SDT nào được thêm(Trống)");
        } else {
            for (PhoneBook phoneBook : phoneList) {
                if (phoneBook.getFullName().toLowerCase().contains(fullName.toLowerCase())) {
                    contactName.add(phoneBook);
                }

            }
        }
        return contactName;
    }

    public void saveList(ArrayList<PhoneBook> phoneList){
        csvFile.writeCVS( phoneList,"data/contacts.csv");
        System.out.println("---HẾT---");
    }

    public void readList(){
        contactList= (ArrayList<PhoneBook>) csvFile.readCVS("data/contacts.csv");
        System.out.println("---HẾT---");
    }

}
