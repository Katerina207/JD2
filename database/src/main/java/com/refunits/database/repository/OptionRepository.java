package com.refunits.database.repository;

import com.refunits.database.model.Option;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OptionRepository extends CrudRepository<Option, Integer> {

    Optional<Option> findById(Integer id);

    List<Option> findAll();

    Optional<Option> findByNameAndPrice(String name, Integer price);

    @Query(value = "select option.* " +
            "from refunits_storage.option " +
            "join refunits_storage.unit_option " +
            "where unit_id = :unit", nativeQuery = true)
    List<Option> findAllByUnit(@Param("unit") Integer unitId);

    @Query(value = "select option.* " +
            "from refunits_storage.option " +
            "join refunits_storage.product_option " +
            "where product_id = :product", nativeQuery = true)
    List<Option> findAllByProduct(@Param("product") Integer productId);

    Option save(Option option);
}
