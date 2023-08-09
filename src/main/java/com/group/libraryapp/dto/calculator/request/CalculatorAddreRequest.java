package com.group.libraryapp.dto.calculator.request;

public class CalculatorAddreRequest {
    private final int number1;//final 선언-> 초기화 필수(여기서는 생성자로 초기화)
    private final int number2;

    public CalculatorAddreRequest(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }
}
