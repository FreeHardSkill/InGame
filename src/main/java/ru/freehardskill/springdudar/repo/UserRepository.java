package ru.freehardskill.springdudar.repo;

import org.springframework.data.repository.CrudRepository;
import ru.freehardskill.springdudar.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
