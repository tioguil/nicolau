package entrega02;

import javax.script.ScriptException;

public class Main {


    public static void main(String[] args) throws ScriptException, InterruptedException {
        double x = 1.;
        paralela(x);
        System.out.println("------------------------------------------------");
        sequencial(x);
    }

    static void paralela(Double x){
        Double result = 0.0;

//      3x^4 + 10x^3 -5x^2 + 7x + 10;

//      3x^4
        Funcao funcao = new Funcao('^', x,4., "Thread 1");
        Thread thread = new Thread(funcao);
        thread.start();

//      10x^3
        Funcao funcao3 = new Funcao('^', x,3., "Thread 2");
        Thread thread3 = new Thread(funcao3);
        thread3.start();

//      -5x^2
        Funcao funcao5 = new Funcao('^', x,2., "Thread 3");
        Thread thread5 = new Thread(funcao5);
        thread5.start();

//      7x
        Funcao funcao7 = new Funcao('*', 7.0,x, "Thread 4");
        Thread thread7 = new Thread(funcao7);
        thread7.start();

        try {
            thread.join();
            thread3.join();
            thread5.join();
            thread7.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result += funcao7.getResult();
        result += 10;

        Funcao funcao2 = new Funcao('*', funcao.getResult(),3., "Thread 5");
        Thread thread2 = new Thread(funcao2);

        Funcao funcao4 = new Funcao('*', funcao3.getResult(),10., "Thread 6");
        Thread thread4 = new Thread(funcao4);

        Funcao funcao6 = new Funcao('*', funcao5.getResult(),-5., "Thread 7");
        Thread thread6 = new Thread(funcao6);

        thread2.start();
        thread4.start();
        thread6.start();


        try {
            thread2.join();
            thread4.join();
            thread6.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();

        }

        result += funcao2.getResult();
        result += funcao4.getResult();
        result += funcao6.getResult();
        System.out.println("Resultado: " + result);

    }


    static void sequencial(double x){
        Double result = 0.0;

//      3x^4 + 10x^3 -5x^2 + 7x + 10;

//      3x^4
        Funcao funcao = new Funcao('^', x,4., "Thread 1");
        funcao.run();
        Funcao funcao2 = new Funcao('*', funcao.getResult(),3., "Thread 2");
        funcao2.run();
        result += funcao2.getResult();


//      10x^3
        Funcao funcao3 = new Funcao('^', x,3., "Thread 3");
        funcao3.run();
        Funcao funcao4 = new Funcao('*', funcao3.getResult(),10., "Thread 4");
        funcao4.run();
        result += funcao4.getResult();


//      -5x^2
        Funcao funcao5 = new Funcao('^', x,2., "Thread 5");
        funcao5.run();
        Funcao funcao6 = new Funcao('*', funcao5.getResult(),-5., "Thread 6");
        funcao6.run();
        result += funcao6.getResult();


//      7x
        Funcao funcao7 = new Funcao('*', 7.0,x, "Thread 7");
        funcao7.run();
        result += funcao7.getResult();
        result += 10;

        System.out.println("Resultado: " + result);
    }

}
