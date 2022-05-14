package com.example.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//열거형, 상수의 집합임을 명시
public enum Role {

    //스프링 시큐리티에서 권한 코드에 항상 ROLE_이 앞에 붙어야함.
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
