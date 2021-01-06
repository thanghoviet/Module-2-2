public class NameClassExampletest {
    public static String[] nameClass = new String[] {"C0235P","H5852K"};

    public static void main(String[] args) {
        for (String account : nameClass) {
            boolean isvalid = NameClassExample.validate(account);
            System.out.println("Account is " + account +" is valid: "+ isvalid);
        }
    }
}
