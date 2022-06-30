package sof.crud.kata312.repositry;


import org.springframework.data.jpa.repository.JpaRepository;
import sof.crud.kata312.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}

