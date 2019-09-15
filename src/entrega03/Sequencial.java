package entrega03;

public class Sequencial {

    public static int SumVector(int [] vector){
        int sum = 0;
        for (int i : vector){
            sum += i;
        }
        return sum;
    }

}
