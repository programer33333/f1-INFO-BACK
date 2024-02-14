package com.f1info.repository;

import com.f1info.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    Optional<User> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    User save(User user);

    @Override
    List<User> findAll();

    boolean existsByLogin(String login);
}
