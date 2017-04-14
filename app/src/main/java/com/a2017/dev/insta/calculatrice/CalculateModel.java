package com.a2017.dev.insta.calculatrice;

/**
 * Created by Telest on 10/04/2017.
 */

public class CalculateModel {
    private String firstValue = "";
    private String secondValue = "";
    private String operator = "";

    public void addFirstValueNumber(String number){
        this.firstValue += number;
    }

    public void addSecondValueNumber(String number){
        this.secondValue += number;
    }

    public double calcul() throws NumberFormatException{
        double result = 0;
        switch(this.operator){
            case Constant.OPERATOR_PLUS :
                result = Double.parseDouble(firstValue) + Double.parseDouble(secondValue);
                break;
            case Constant.OPERATOR_MOINS :
                result = Double.parseDouble(firstValue) - Double.parseDouble(secondValue);
                break;
            case Constant.OPERATOR_MULT :
                result = Double.parseDouble(firstValue) * Double.parseDouble(secondValue);
                break;
            case Constant.OPERATOR_DIV :
                result = Double.parseDouble(firstValue) / Double.parseDouble(secondValue);
                break;
        }
        return result;
    }

    public void clear(){
        this.firstValue = "";
        this.secondValue = "";
        this.operator = "";
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
