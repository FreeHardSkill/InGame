package ru.freehardskill.springdudar.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.freehardskill.springdudar.models.User;
import ru.freehardskill.springdudar.repo.UserRepository;

@ControllerAdvice
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @ModelAttribute
    public void addUserAttributes(Model model) {
         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            User user = userRepository.findByUsername(username).orElse(null);
            if (user != null) {
                model.addAttribute("username", user.getUsername());
                model.addAttribute("userPhotoPath", user.getImgUrl() != null ? user.getImgUrl() : "/img/user/user-9.png");
            }
        }
    }
}
