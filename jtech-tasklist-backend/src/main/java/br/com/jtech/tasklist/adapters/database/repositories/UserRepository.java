package br.com.jtech.tasklist.adapters.database.repositories;

import br.com.jtech.tasklist.adapters.database.repositories.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * class UseRepository
 *
 * @author rafael.zanetti
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
  Optional<UserModel> findByUserName(String userName);

}
