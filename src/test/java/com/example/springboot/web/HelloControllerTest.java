package com.example.springboot.web;

import com.example.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//스프링부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class)
//여러 스프링 테스트 어노테이션 중, Spring MVC에 집중할 수 있는 어노테이션
//웹상에서 요청과 응답에 대해 테스트 할 수 있다.
//@Controller, @ControllerAdvice 등을 사용할 수 있지만, @Service, @Component, @Repository 등은 사용할 수 없다.
public class HelloControllerTest {

    @Autowired
    //스프링이 관리하는 빈을 주입받는다.
    private MockMvc mvc;
    //웹 API를 테스트할 때 사용한다.
    //스프링 MVC테스트의 시작점이며, 이 클래스를 통해 HTTP GET, POST등에 대한 API를 테스트 할 수 있다.

    @Test
    //테스트 대상으로 지정
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))//MockMvc를 통해 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk())
                //HTTP Header의 Status를 검증, 제대로 작동하는지 확인하는 부분.200이 정상
                //.isNotFound():404에러, .isInternalServerError():500에러, .is(int status) : status 상태 코드
                .andExpect(content().string(hello));
                //응답 본문의 내용을 검증,
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto").param("name",name).param("amount",String.valueOf(amount))
        )
        //API 테스트 시 사용될 요청 파라미터 설정, String값만 허용되기 때문에 등록할 때는 문자열로 변경해야만 한다.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                //.andExpect(jsonPath("$.name", equalTo(name)) / .andExpect(jsonPath("$.name", is(equalTo(name)))) 와 같은 표현
                //$를 기준으로 필드명을 명시, $.name은 json 데이터 내에서 key가 name인 값.
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
