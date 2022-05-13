package com.example.springboot.domain.posts;

import com.example.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//DB Layer 접근자, JPA에서는 Repository라고 부른다.
//단순히 인터페이스 생성 후 JpaRepository<Entity클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨.
//Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
public interface PostsRepository extends  JpaRepository<Posts, Long>{

    //SpringDataJpa에서 제공하지 않는 메소드는 이렇게 쿼리로도 작성 가능.
    //이 코드는 제공되는 기본 메소드로도 해결 가능하지만 @Query가 가독성이 좋아서 선택
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
