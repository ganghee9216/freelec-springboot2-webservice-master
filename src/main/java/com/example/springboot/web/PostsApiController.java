package com.example.springboot.web;

import com.example.springboot.service.posts.PostsService;
import com.example.springboot.web.dto.PostsResponseDto;
import com.example.springboot.web.dto.PostsSaveRequestDto;
import com.example.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//생성자로 Bean을 주입받는것이 가장 권장되는 방식
//롬복의 이 어노테이션이 생성자를 대신 생성해준다.
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("api/v1/posts")
    //바디 내용을 통째로 자바 객체로 변환해서 매핑된 메소드 파라미터로 전달해준다.
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    //@PathVariable : 메소드 인자에 사용되어 URL템플릿 변수의 값을 메소드 인자로 할당하는데 사용됨
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
