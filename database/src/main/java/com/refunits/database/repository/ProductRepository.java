package com.refunits.database.repository;

import com.refunits.database.model.PreOrder;
import com.refunits.database.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Optional<Product> findById(Integer id);

    List<Product> findAll();

    List<Product> findAllByPreOrder(PreOrder preOrder);

    Product save(Product product);
}
