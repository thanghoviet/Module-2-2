import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameClassExample {
    private static final String ACCOUNT_EXAMPLE="^[CAP]+\\d{4}+[GHIKLM]$";
    private static final Pattern pattern = Pattern.compile(ACCOUNT_EXAMPLE);

    public static boolean validate(String regex) {
        return pattern.matcher(regex).matches();
    }
}
