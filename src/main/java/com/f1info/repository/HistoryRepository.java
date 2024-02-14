package com.f1info.repository;

import com.f1info.domain.history.History;
import com.f1info.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {

    @Override
    Optional<History> findById(Long id);

    @Override
    List<History> findAll();

    @Override
    History save(History history);

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();

    List<History> findByUserId(User user);
}