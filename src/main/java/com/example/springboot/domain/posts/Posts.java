package com.example.springboot.domain.posts;


import com.example.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//주요 어노테이션을 클래스에 가깝게 둔다. 새 언어 전환 시 쉽게 수정할 수 있게.

//Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
//해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분이 안되기 때문
//필드 값 변경 필요시 명확히 그 목적과 의도를 나타낼 수 있는 메소드 따로 추가

@Getter
//비어있는 기본 생성자 자동 추가, public Posts(){}와 같다.
@NoArgsConstructor
//테이블과 링크 될 클래스임을 나타내는 것. 카멜클래스 이름을 언더스코어 네이밍으로 변환시켜줌
//superMan.java -> super_man.table
@Entity
public class Posts extends BaseTimeEntity {

    //PK(Primary Key)필드를 나타내는 것
    @Id
    //PK의 생성 규칙을 나타내는 것.
    //스프링 부트 2.0에서부터 GenerationType.IDENTITY 옵션을 추가해야 auto_increment 발생
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //해당 클래스의 필드는 모두 칼럼이 됨. 기본값 이외에 추가로 변경이 필요한 옵션이 있을 때 사용.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌드에 포함
    //생성시점에 값을 채워주는 역할은 똑같으나 빌더 사용 시 어느 필드에 어떤값을 채워야할지 명확하게 인지 가능.
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
