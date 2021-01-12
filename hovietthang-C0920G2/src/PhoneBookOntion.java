import java.util.Scanner;

public class PhoneBookOntion {
    static Scanner sc = new Scanner(System.in);

    private int getAndParseInteger() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Try it:");
            return -1;
        }
    }

    public int getOptionMenu(String messageMenu, int limitMenu, String title) {
        int optionTotalMenu;
        do {
            System.out.println(title);
            System.out.println("Chọn lựa chọn của bạn theo số (để tiếp tục):");
            System.out.println(messageMenu);
            System.out.println("-------Số bạn chọn là :-------");
            optionTotalMenu = getAndParseInteger();
        } while (optionTotalMenu < 0 || optionTotalMenu > limitMenu);
        return optionTotalMenu;
    }
}