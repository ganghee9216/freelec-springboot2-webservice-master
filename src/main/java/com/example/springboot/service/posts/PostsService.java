package com.example.springboot.service.posts;

import com.example.springboot.domain.BaseTimeEntity;
import com.example.springboot.domain.posts.Posts;
import com.example.springboot.domain.posts.PostsRepository;
import com.example.springboot.web.dto.PostsListResponseDto;
import com.example.springboot.web.dto.PostsResponseDto;
import com.example.springboot.web.dto.PostsSaveRequestDto;
import com.example.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//@Autowired가 아닌 생성자로 Bean을 주입 받는다.
//롬복의 @RequiredArgsConstructor가 final필드의 생성자를 대신 생성해줌.
@RequiredArgsConstructor
//서비스레이어, 내부에서 자바로직을 처리
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    //트랜잭션 기능 적용, 메소드가 포함하고 있는 작업 중에 하나라도 실패할 경우 전체 작업을 취소한다.
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    //(readOnly = true) -> 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회속도 개선
    //등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        //stream : 많은 데이터의 흐름 속에서 각각의 원하는 값을 가공하여 최종적으로 제공한다.
        //중간중간 연산값 반환 가능, 데이터 소스를 변경시키지 않고, 일회용이며, 내부 반복으로 작업 처리
        return postsRepository.findAllDesc().stream()
                //.map(posts -> new PostsListResponseDto(posts))와 같은 기능
                //중간 연산, PostsListResponseDto로 변환 후 List로 반환
               .map(PostsListResponseDto::new)
                //최종 연산,
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        //JpaRepository에서 이미 delete메소드를 지원하기 때문에 활용.
        //엔티티를 파라미터로 삭제할 수도 있고, deleteById메소드를 이용하여 id로 삭제할 수도 있다.
        postsRepository.delete(posts);
    }
}
