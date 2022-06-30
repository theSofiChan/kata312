package sof.crud.kata312.repositry;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sof.crud.kata312.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
