package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.form.CommentDto;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping("/post/{id}")
    public String showPost(@PathVariable("id") String id,
                           HttpSession httpSession,
                           Model model) {
        if (!model.containsAttribute("comment")) {
            model.addAttribute("comment", new CommentDto());
        }

        try {
            Optional<Post> postServiceById = postService.findById(Long.parseLong(id));

            postServiceById.ifPresent(post -> model.addAttribute("post", post));
        } catch (NumberFormatException ignored) {
        }

        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String writeComment(@PathVariable String id,
                               @Valid @ModelAttribute("comment") CommentDto comment,
                               BindingResult bindingResult,
                               HttpSession httpSession,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return showPost(id, httpSession, model);
        }

        try {
            Optional<Post> postServiceById = postService.findById(Long.parseLong(id));

            postServiceById.ifPresent(post ->
                    postService.writeComment(post, getUser(httpSession), comment.getText()));

            putMessage(httpSession, "Comment added");
        } catch (NumberFormatException ignored) {
        }

        return "redirect:/post/{id}";
    }
}
