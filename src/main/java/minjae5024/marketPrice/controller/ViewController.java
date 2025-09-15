package minjae5024.marketPrice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login.html")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register.html")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/community.html")
    public String communityPage() {
        return "community";
    }

    @GetMapping("/post-write.html")
    public String postWritePage() {
        return "post-write";
    }

    @GetMapping("/post-detail.html")
    public String postDetailPage() {
        return "post-detail";
    }
}
