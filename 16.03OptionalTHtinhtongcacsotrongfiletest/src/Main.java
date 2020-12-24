import javax.script.ScriptException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScriptException {
        System.out.println("Nhập đường dẫn file: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        readFileText readfileEx = new readFileText();
        readfileEx.readFileText(path);


    }
}
