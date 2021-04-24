package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Date;
import java.util.Optional;

@Controller
public class PostControl {
    private final PostService posts;

    public PostControl(PostService posts) {
        this.posts = posts;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        post.setCreated(new Date(System.currentTimeMillis()));
        posts.add(post);
        return "redirect:/post?id=" + posts.findByName(post.getName()).get().getId();
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Post post) {
        Optional<Post> postFromMem = posts.findById(post.getId());
        if (postFromMem.isPresent()) {
            postFromMem.get().setDesc(post.getDesc());
            postFromMem.get().setName(post.getName());
        }
        return "redirect:/post?id=" + post.getId();
    }

    @GetMapping("/create")
    public String create() {
        return "/posts/create";
    }

    @GetMapping("/load")
    public String updateLoad(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", posts.findById(id).get());
        return "posts/update";
    }

    @GetMapping("/post")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", posts.findById(id).get());
        return "posts/post";
    }
}
