package com.example.springboot.web;

import com.example.springboot.config.auth.LoginUser;
import com.example.springboot.config.auth.dto.SessionUser;
import com.example.springboot.service.posts.PostsService;
import com.example.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    //기존에 httpSession.getAttribute("user")로 가져오던 세션 정보 값 개선. 어노테이션화 시킴
    //이제 어느 컨트롤러든 @LoginUser만 사용하면 세션 정보를 가져올 수 있다.
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        //세션에 저장된 값이 있을 때만 model에 userName으로 등록한다.
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
