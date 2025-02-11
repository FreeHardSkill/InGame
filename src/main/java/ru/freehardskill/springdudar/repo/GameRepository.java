package ru.freehardskill.springdudar.repo;

import org.springframework.data.repository.CrudRepository;
import ru.freehardskill.springdudar.models.Game;

public interface GameRepository extends CrudRepository<Game, Long> {
}
