package entrega03;

public class Paralela implements Runnable {

    private int[] vector;
    private int start;
    private int end;
    private int sum;

    public Paralela(int[] vector, int start, int end){
        this.vector = vector;
        this.start = start;
        this.end = end;
        this.sum = 0;
    }

    public int getSum(){
        return sum;
    }

    @Override
    public void run() {

        for (int i = start; i < end; i++){
               sum += vector[i];
        }
    }
}
