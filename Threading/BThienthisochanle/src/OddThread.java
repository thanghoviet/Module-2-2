public class OddThread extends Thread{
    int number;
    public OddThread(String name) {
        this.number=number;
    }

    @Override
    public void run() {
        for (int i = 0; i <=10 ; i++) {
            if (i%2==0) {
                System.out.println("chay " + number + " i=" + i);
            }
        }
    }
}
