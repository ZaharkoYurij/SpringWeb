package proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.entity.User;

/**
 * Created by SCIP on 20.09.2016.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}