package in.bushansirgur.expensetrackerapi.repository;

import in.bushansirgur.expensetrackerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByEmail(String email);
    User findByEmail(String email);


}
