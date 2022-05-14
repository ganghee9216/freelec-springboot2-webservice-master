package com.example.springboot.web;

import com.example.springboot.config.auth.SecurityConfig;
import com.example.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Junit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
//여기서는 SpringRunner를 사용하여 스프링부트 테스트와 JUnit 사이에 연결자 역할
@RunWith(SpringRunner.class)
//여러 스프링 테스트 어노테이션 중, Spring MVC에 집중할 수 있는 어노테이션
//웹상에서 요청과 응답에 대해 테스트 할 수 있다.
//@Controller, @ControllerAdvice 등을 사용할 수 있지만, @Service, @Component, @Repository 등은 사용할 수 없다.
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class HelloControllerTest {

    //스프링이 관리하는 빈을 주입받는다.
    @Autowired

    //웹 API를 테스트할 때 사용한다.
    //스프링 MVC테스트의 시작점이며, 이 클래스를 통해 HTTP GET, POST등에 대한 API를 테스트 할 수 있다.
    private MockMvc mvc;

    @WithMockUser(roles="USER")
    //테스트 대상으로 지정
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))//MockMvc를 통해 /hello 주소로 HTTP GET 요청
                //HTTP Header의 Status를 검증, 제대로 작동하는지 확인하는 부분.200이 정상
                //.isNotFound():404에러, .isInternalServerError():500에러, .is(int status) : status 상태 코드
                .andExpect(status().isOk())
                //응답 본문의 내용을 검증,
                .andExpect(content().string(hello));

    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        //API 테스트 시 사용될 요청 파라미터 설정, String값만 허용되기 때문에 등록할 때는 문자열로 변경해야만 한다.
        mvc.perform(
                get("/hello/dto").param("name",name).param("amount",String.valueOf(amount))
        )

                .andExpect(status().isOk())
                //json 응답값을 필드별로 검증할 수 있는 메소드
                //.andExpect(jsonPath("$.name", equalTo(name)) / .andExpect(jsonPath("$.name", is(equalTo(name)))) 와 같은 표현
                //$를 기준으로 필드명을 명시, $.name은 json 데이터 내에서 key가 name인 값.
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
