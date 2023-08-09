package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddreRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.*;

@RestController//이 클래스를  API의 진입 지점으로 만들어 줌,주어진 class를 controller(API 입구)로 등록

public class CalculatorController {

    @GetMapping("/add")//아래 함수를 HTTP Method가 GET이고 HTTP path가 /add인 API로 지정
    public int addTwoNumbers(CalculatorAddreRequest request){//@RequestParam : 주어지는 쿼리를 함수 파라미터에 넣는다
        return request.getNumber1()+ request.getNumber2();
    }

    @PostMapping("/multiply")//Post/multiply 라는 API가 호출 될때  아래 함수를 실행 시켜줌
    public int multiplyTwoNumber(@RequestBody CalculatorMultiplyRequest request){//@RequstBody는 http body로 들어오는 json을 객체로 변경
        return request.getNumber1()* request.getNumber2();
    }

}
