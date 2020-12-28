public class NumberGenerator implements Runnable{
    int hashCode;

    public NumberGenerator(int hashCode) {
        this.hashCode=hashCode;
    }

    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println(hashCode+":"+  i);
            try {
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
