package com.example.springboot.web;

import com.example.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//컨트롤러를 json을 반환하는 컨트롤러로 만들어준다.
public class HelloController {

    @GetMapping("/hello")
    //HTTP 메소드인 Get의 요청을 받을 수 있는 API를 만들어준다.
    // /hello로 요청이 오면 문자열 hello를 반환하는 기능
    //메소드에만 적용,
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
    //외부에서 API로 넘긴 파라미터를 가져오는 어노테이션.
    //name(@RequestParam("name"))이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장한다.
        return new HelloResponseDto(name, amount);
    }
}
