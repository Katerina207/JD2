package com.refunits.database.repository;

import com.refunits.database.model.PreOrder;
import com.refunits.database.model.RegisteredUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PreOrderRepository extends CrudRepository<PreOrder, Integer> {

    Optional<PreOrder> findById(Integer id);

    List<PreOrder> findAll();

    List<PreOrder> findAllByRegisteredUser(RegisteredUser registeredUser);

    PreOrder save(PreOrder preOrder);
}
