package com.example.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
//jpa entity 클래스들이 BaseTimeEntity 를 상속할 경우 필드들도 칼럼으로 인식시킨다.
@MappedSuperclass
//BaseTimeEntity 클래스에 Auditing 기능(자동으로 시간을 매핑하여 테이블에 넣어주는 기능)을 포함시킨다.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    //entity가 생성되어 저장될 때 시간이 자동으로 저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate
    //조회한 entity의 값을 변경할 때 시간이 자동으로 저장된다.
    private LocalDateTime modifiedDate;
}
