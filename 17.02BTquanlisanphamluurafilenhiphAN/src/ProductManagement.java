import javax.script.ScriptEngineFactory;
import java.io.*;
import java.util.concurrent.TimeoutException;

public class ProductManagement {
    public static void main(String[] args) throws TimeoutException {
        try{
            File dir = new File(
    "D:\\JAVA\\[BC-java-APJ-2]AdvancedPrograming\\17.02BTquanlisanphamluurafilenhiphAN\\hello");
            dir.mkdirs();
            OutputStream w = new FileOutputStream(
    "D:\\JAVA\\[BC-java-APJ-2]AdvancedPrograming\\17.02BTquanlisanphamluurafilenhiphAN\\hello\\dir.txt");
            byte[] by = new byte[] { 'H', 'e', 'l', 'l', 'o' };
            for (int i = 0; i < by.length; i++) {
                byte b = by[i];

                w.write(b);
            }
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
