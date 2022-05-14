package com.example.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//이 어노테이션이 생성될 수 있는 위치 지정
//parameter로 지정했기 때문에 메소드의 파라미터로 선언된 객체에서만 사용 가능.
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//세션값이 필요할때 값을 가져와주는 역할
//@interface : 이 파일을 어노테이션 클래스로 지정한다.
public @interface LoginUser {
}
