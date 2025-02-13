package ru.freehardskill.springdudar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.freehardskill.springdudar.models.User;
import ru.freehardskill.springdudar.repo.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String userLogin() {
        return "login";
    }


    @GetMapping("/error")
    public String errorPage(@RequestParam(value = "continue", required = false) String continueParam, Model model) {
        model.addAttribute("errorMessage", "Произошла ошибка при аутентификации.");
        return "error"; // Показываем страницу error.html
    }

    @GetMapping("/signup")
    public String userSignup(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Пользователь с таким именем уже существует!");
        }
        return "signup";
    }

    @PostMapping("/signup")
    public String userRegistration(@RequestParam String username, @RequestParam String email,
                                   @RequestParam String password, @RequestParam String firstName,
                                   @RequestParam String lastName, @RequestParam String sex,
                                   @RequestParam("file") MultipartFile file,  Model model) throws IOException {

        if(userRepository.findByUsername(username).isPresent()) {
            return "redirect:/signup?error=true";
        }

        User user = new User(username, password, firstName, lastName, email, sex);
        if(file != null) {
            File uploadDir = new File(uploadPath + "images/profiles/");
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = "images/profiles/" + uuidFile + "-" + file.getOriginalFilename();
            user.setImgUrl(resultFileName);

            file.transferTo(new File(uploadPath + resultFileName));
        }

        userRepository.save(user);

        return "redirect:/login";
    }
}
