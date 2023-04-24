package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.SimpleCarService;
import ru.job4j.cars.service.SimpleEngineService;
import ru.job4j.cars.service.SimplePostService;
import ru.job4j.cars.utils.CollectCarBySameNameWithAllPropertyUtil;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@ThreadSafe
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final SimplePostService postService;
    private final SimpleCarService carService;
    private final SimpleEngineService engineService;

    @GetMapping("/add")
    public String add(Model model) {
        var carWithProperties = carService.getCarDividedByProperty(new CollectCarBySameNameWithAllPropertyUtil());
        if (carWithProperties.isEmpty()) {
            return "redirect:/post/addError";
        }
        model.addAttribute("properties", carWithProperties);
        return "post/add";
    }

    @PostMapping("/ajaxFindCarByProperty/{query}")
    public ResponseEntity<?> findProperty(@PathVariable("query") String query) {
        var carWithProperties = carService.ajaxFindProperty(query, new CollectCarBySameNameWithAllPropertyUtil());
        if (carWithProperties.isEmpty()) {
            return new ResponseEntity<>("Автомобиль отсутствует", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(carWithProperties, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public String postCar(Model model, @PathVariable("id") int id) {
        var post = postService.findById(id);
        if (post.isEmpty()) {
            return "redirect:/post/postError";
        }
        model.addAttribute("post", post.get());
        return "post/post";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute Post post,
            @ModelAttribute Car car,
            @ModelAttribute Owner owner,
            @RequestParam("carPhoto") MultipartFile preview,
            HttpSession session) throws IOException {
        var optCar = carService.findByProperty(car);
        if (optCar.isEmpty()) {
            return "redirect:/post/error";
        }
        post.setPhoto(preview.getBytes());
        post.setCar(optCar.get());
        postService.add(post);
        return "redirect:/post/success";
    }

    @GetMapping("/success")
    public String success() {
        return "post/success";
    }
}
