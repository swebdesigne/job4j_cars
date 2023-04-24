package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cars.repository.HibernateCarRepository;
import ru.job4j.cars.repository.HibernatePostRepository;
import ru.job4j.cars.service.SimplePostService;

@ThreadSafe
@AllArgsConstructor
@Controller
public class IndexController {
    private final HibernatePostRepository hibernatePostRepository;

    @GetMapping("/")
    public String allPosts(Model model) {
        model.addAttribute("posts", hibernatePostRepository.findAll());
        return "post/posts";
    }

}
