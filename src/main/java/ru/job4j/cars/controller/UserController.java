package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.SimpleUserService;
import ru.job4j.cars.utils.UserHttpSessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@AllArgsConstructor
@RequestMapping("/user")
@Controller
@ThreadSafe
public class UserController {
    private final SimpleUserService simpleUserServer;

    @GetMapping("/registration")
    public String registration() {
        return "user/registration";
    }

    @PostMapping("/create")
    public String add(@ModelAttribute User user, HttpServletRequest rq) {
        var optUser = simpleUserServer.create(user);
        UserHttpSessionUtil.setUser(rq, optUser.orElse(null));
        if (optUser.isEmpty()) {
            return "redirect:/user/addError";
        }
        return "redirect:/user/addSuccess";
    }

    @GetMapping("/addSuccess")
    public String addSuccess(Model model, HttpSession session) {
        model.addAttribute("user", UserHttpSessionUtil.getUser(session));
        return "user/addSuccess";
    }

    @GetMapping("/addError")
    public String addError(Model model, HttpSession session) {
        model.addAttribute("user", UserHttpSessionUtil.getUser(session));
        return "user/addError";
    }

    @GetMapping("/authorisation")
    public String authorisation() {
        return "user/authorisation";
    }

    @PostMapping("/logIn")
    public String logIn(@ModelAttribute User user, HttpServletRequest rq) {
        var optUser = simpleUserServer.findByLogin(user.getLogin());
        if (optUser.isEmpty()) {
            return "redirect:/user/loginError";
        }
        UserHttpSessionUtil.setUser(rq, optUser.get());
        return "redirect:/user/loginSuccess";
    }

    @GetMapping("/loginError")
    public String logInError(Model model, HttpSession session) {
        model.addAttribute("user", UserHttpSessionUtil.getUser(session));
        return "user/loginError";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Model model, HttpSession session) {
        model.addAttribute("user", UserHttpSessionUtil.getUser(session));
        return "user/loginSuccess";
    }


    @GetMapping("/edit")
    public String edit(Model model, HttpSession session) {
        model.addAttribute("user", UserHttpSessionUtil.getUser(session));
        return "user/edit";
    }

    @GetMapping("/logOut")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User user, HttpServletRequest rq) {
        var isUpdate = simpleUserServer.update(user);
        if (!isUpdate) {
            return "redirect:/user/editError";
        }
        UserHttpSessionUtil.setUser(rq, user);
        return "redirect:/user/editSuccess";
    }

    @GetMapping("/editSuccess")
    public String editSuccess(Model model, HttpSession session) {
        model.addAttribute("user", UserHttpSessionUtil.getUser(session));
        return "user/editSuccess";
    }

    @GetMapping("/editError")
    public String editError() {
        return "user/editError";
    }

    @GetMapping("/delete")
    public String delete(HttpSession session) {
        var user = UserHttpSessionUtil.getUser(session);
        var isDeleted = simpleUserServer.delete(user);
        if (!isDeleted) {
            return "redirect:/user/deleteError";
        }
        return "redirect:/";
    }

    @GetMapping("/deleteError")
    public String deleteError() {
        return "user/deleteError";
    }
}
