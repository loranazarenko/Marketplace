package com.project.marketplace.repository;

import com.project.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByFirstNameAndLastName(String firstName, String lastName);

    User save(User user);

    List<User> findAll();

    void delete(User user);

    User getById(Long id);
}
