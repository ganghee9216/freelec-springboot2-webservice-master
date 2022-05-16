package com.example.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    //MockTesting과 달리 컨테이너를 직접 실행한다.
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩(){
        //객체 생성
        // get방식으로 "/"로 호출했을 때 String형태로 반환
        String body = this.restTemplate.getForObject("/", String.class);

        //검증
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}
