package com.crio.qcalc;

import java.text.DecimalFormat;

public class StandardCalculator {

    public static void getVersion(){
        System.out.println("Standard Calculator 1.0");
    }
    protected double result;
    public final void add(double num1, double num2) throws ArithmeticException{
        double result =num1 + num2;

    if((result == Double.MAX_VALUE) || (result == Double.POSITIVE_INFINITY)){

        throw new ArithmeticException("Double overflow in addition");

    }

    this.result = result;
    }
    

    public final void subtract(double num1, double num2) throws ArithmeticException{
        double result = num1 - num2;

        if((result == -Double.MAX_VALUE) || (result == Double.NEGATIVE_INFINITY)){
    
            throw new ArithmeticException("Double overflow in subtraction");
    
        }
    
        this.result = result;
    }
    
    
    public final void multiply(double num1, double num2) throws ArithmeticException{
        result = num1*num2;
        if((result == Double.MAX_VALUE) || (result == Double.POSITIVE_INFINITY) || (result==Double.NEGATIVE_INFINITY)){

            throw new ArithmeticException("Double overflow in MULTIPLICATION");
    
        }
    }
    
    
    public final void divide(double num1, double num2) throws ArithmeticException{
        if(num2 ==0.0){
            throw new ArithmeticException("Divide By Zero");
        }
        result = num1/num2;
    }


	public final double getResult() {
		return result;
    }

    public final void setResult(double value) {
    if(value != 0){
        return ;
    }

    this.result = value;

 }


public final void clearResult() {

        result = 0;

}

public void printResult(){
    DecimalFormat format = new DecimalFormat("0.####");
    System.out.println("Standard Calculator Result:"+format.format(result));

}




}
