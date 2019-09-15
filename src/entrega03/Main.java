package entrega03;

import java.util.Random;
import java.util.stream.IntStream;

//
//- Fazer um programa paralelo que calcule:
//        a soma dos valores contidos em um vetor do tipo inteiro de dimensão 10000
//        a) Fazer uma solução sequencial
//        b) Fazer uma solução paralela com 4 threads
public class Main {

    public static void main(String[] args) {

        int[] vector = new int[10000];
        Random random = new Random();
        for (int i = 0; i < vector.length; i++){
            vector[i] = random.nextInt(Integer.SIZE - 1);
            System.out.println(vector[i]);
        }
        long sum = IntStream.of(vector).sum();
        System.out.println("Total " + sum);

        //Sequencial
        long timeSequencial = System.currentTimeMillis();
        int totalSequencial = Sequencial.SumVector(vector);
        System.out.println("Total sequencial: " + totalSequencial);
        System.out.println("Tempo: " + (System.currentTimeMillis() - timeSequencial));

        //Paralela

        long timeParalela = System.currentTimeMillis();

        Paralela paralela1 = new Paralela(vector, 0, 2500);
        Paralela paralela2 = new Paralela(vector, 2500, 5000);
        Paralela paralela3 = new Paralela(vector, 5000, 7500);
        Paralela paralela4 = new Paralela(vector, 7500, 10000);

        Thread thread1 = new Thread(paralela1);
        thread1.start();

        Thread thread2 = new Thread(paralela2);
        thread2.start();

        Thread thread3 = new Thread(paralela3);
        thread3.start();

        Thread thread4 = new Thread(paralela4);
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int sumThread = 0;

        sumThread += paralela1.getSum();
        sumThread += paralela2.getSum();
        sumThread += paralela3.getSum();
        sumThread += paralela4.getSum();

        System.out.println("Total paralelo: " + sumThread);
        System.out.println("Tempo: " + (System.currentTimeMillis() - timeParalela));

    }
}
