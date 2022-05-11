package com.jojoldu.book.Controller;

import com.jojoldu.book.Dto.PostsResponseDto;
import com.jojoldu.book.config.auth.LoginUser;
import com.jojoldu.book.config.auth.dto.SessionUser;
import com.jojoldu.book.service.posts.PostsService;
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
    public String index(Model model, @LoginUser SessionUser user) {

        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("userName" , user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, @LoginUser SessionUser user, Model model) {

        PostsResponseDto dto = postsService.findById(id);

        if(user != null) {
            model.addAttribute("userName" , user.getName());
        }

        model.addAttribute("post", dto);

        return "posts-update";
    }
}
