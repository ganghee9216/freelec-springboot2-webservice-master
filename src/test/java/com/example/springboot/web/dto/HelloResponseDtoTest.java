package com.example.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
//Junit에도 assertThat이 있지만, assertj는 추가적인 라이브러리가 필요하지 않기 때문에 assertj사용

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //필드값 정의
        String name = "test";
        int amount = 1000;

        //객체 생성
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //검증
        assertThat(dto.getName()).isEqualTo(name);
        //assertThat : assertj라는 테스트 검증 라이브러리의 검증 메소드, 검증하고 싶은 대상을 메소드 인자로 받는다.
        //isEqualTo : assertj의 동등 비교 메소드. assertThat의 값과 같으면 성공
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
