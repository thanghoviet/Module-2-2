import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFile {

    private static final String CSV_SEPARATOR = ",";
    static {
        new File("data/").mkdirs();
    }

    public void writeCVS(ArrayList<PhoneBook> contactList,String fileName) {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));
            for (PhoneBook contact : contactList)
            {
                String oneLine = contact.getPhoneNumber() +
                        CSV_SEPARATOR +
                        contact.getGroup() +
                        CSV_SEPARATOR +
                        contact.getFullName() +
                        CSV_SEPARATOR +
                        contact.getAddress() +
                        CSV_SEPARATOR +
                        contact.getGender() +
                        CSV_SEPARATOR +
                        contact.getDayOfBirth() +
                        CSV_SEPARATOR +
                        contact.getEmail();
                bw.write(oneLine);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException ignored) {}
    }

    public List<PhoneBook> readCVS(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            ArrayList<PhoneBook> list = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                String phoneNumber = parseField(fields[0]);
                String group = parseField(fields[1]);
                String fullName = parseField(fields[2]);
                String gender = parseField(fields[3]);
                String address = parseField(fields[4]);
                String email = parseField(fields[5]);
                String dob = parseField(fields[6]);
                PhoneBook phoneBook =  new PhoneBook(phoneNumber, group, fullName, gender, address, email, dob);
                list.add(phoneBook);
            }
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("Doc CVS that bai: " + e.getMessage());
            return null;
        }
    }

    private static String parseField(String field) {
        return field.substring(0, field.length());
    }
}

