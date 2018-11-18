package com.refunits.database.repository;

import com.refunits.database.model.RegisteredUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RegisteredUserRepository extends CrudRepository<RegisteredUser, Integer> {

    Optional<RegisteredUser> findById(Integer id);

    List<RegisteredUser> findAll();

//    RegisteredUser save(RegisteredUser registeredUser);
}
