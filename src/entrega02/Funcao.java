package entrega02;

import javax.xml.ws.handler.Handler;

public class Funcao implements Runnable{


    private Character operation;
    private Double numberA, numberB, result;
    private String name;

    public Funcao(Character operation, Double numberA, Double numberB, String name){
        this.operation = operation;
        this.numberA = numberA;
        this.numberB = numberB;
        this.name = name;
    }

    private Double sum(Double a, double b){
        return (a+b);
    }

    private Double subtraction(Double a, double b){
        return (a-b);
    }

    private Double multiplication(Double a, double b){
        return (a*b);
    }

    private Double division(Double a, double b){
        return (a/b);
    }

    private Double exponentiation(Double a, double b){
        return Math.pow(a,b);
    }

    public Double getResult(){
        return this.result;
    }


    private void step1(){

        switch (operation){

            case '+':
                this.result= sum(this.numberA, this.numberB);
                break;
            case '-':
                this.result= subtraction(this.numberA, this.numberB);
                break;
            case '*':
                this.result= multiplication(this.numberA, this.numberB);
                break;
            case '/':
                this.result= division(this.numberA, this.numberB);
                break;
            case '^':
                this.result= exponentiation(this.numberA, this.numberB);
                break;
        }

    }


    @Override
    public void run() {

        step1();
        System.out.println(name);
    }
}
