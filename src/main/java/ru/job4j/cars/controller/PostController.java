package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cars.service.PostService;

@Controller
@ThreadSafe
@AllArgsConstructor
@RequestMapping("/")
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public String allPosts() {
        System.out.println(postService.findAll());
        return "post/posts";
    }
}
