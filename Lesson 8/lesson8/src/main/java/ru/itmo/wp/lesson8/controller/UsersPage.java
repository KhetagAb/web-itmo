package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.domain.model.User;
import ru.itmo.wp.lesson8.form.UsersActivity;
import ru.itmo.wp.lesson8.service.UserService;

import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable("id") String parameterId) {
        User user = null;
        try {
            long id = Long.parseLong(parameterId);
            user = userService.findById(id);
        } catch (NumberFormatException ignored) {
        }

        model.addAttribute("userInfo", user);
        return "UserPage";
    }

    @GetMapping("/users/all")
    public String usersGet(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users")
    public String usersPost(@Valid @ModelAttribute("usersForm") UsersActivity usersActivity) {
        userService.setActivity(usersActivity.getUserLogin(), usersActivity.getActive().equals("Enable"));
        return "redirect:/users/all";
    }

}
