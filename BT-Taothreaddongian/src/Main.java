public class Main {
    public static void main(String[] args) {
        NumberGenerator numberGenerator1 = new NumberGenerator(2);
        Thread thread1=new Thread(numberGenerator1);
        NumberGenerator numberGenerator2 = new NumberGenerator(5);
        Thread thread2=new Thread(numberGenerator2);

        thread1.start();
        thread2.start();
    }
}
