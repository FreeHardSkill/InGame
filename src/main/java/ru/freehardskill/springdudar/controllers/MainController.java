package ru.freehardskill.springdudar.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.freehardskill.springdudar.models.User;
import ru.freehardskill.springdudar.repo.UserRepository;

@Controller
public class MainController {


    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
}
