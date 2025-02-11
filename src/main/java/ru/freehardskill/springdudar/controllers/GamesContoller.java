package ru.freehardskill.springdudar.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.freehardskill.springdudar.models.Game;
import ru.freehardskill.springdudar.repo.GameRepository;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Controller
public class GamesContoller {

    @Autowired
    private GameRepository gamesRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/games")
    public String gamesPage(Model model) {
        Iterable<Game> games = gamesRepository.findAll();
        model.addAttribute("games", games);
        model.addAttribute("filePath", uploadPath);
        return "games";
    }

    @GetMapping("/games/add")
    public String gameAdd(Model model){
        return "game-add";
    }

    @PostMapping("/games/add")
    public String gamePostAdd(@RequestParam String name, @RequestParam String annotation,
                              @RequestParam String fullText, @RequestParam("file") MultipartFile file, Model model) throws IOException {

        Game game = new Game(name, annotation, fullText);
        if(file != null) {
            File uploadDir = new File(uploadPath + "images/games/");
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = "images/games/" + uuidFile + "-" + file.getOriginalFilename();
            game.setImgUrl(resultFileName);

            file.transferTo(new File(uploadPath + resultFileName));
        }
        gamesRepository.save(game);
        return "redirect:/games";
    }

    @GetMapping("games/{id}")
    public String gameDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Game> gameOptional = gamesRepository.findById(id);
        if(gameOptional.isEmpty()) {
            return "404";

        }
        Game game = gameOptional.get();
        model.addAttribute("game",game);
        return "game-detail";
    }
}
