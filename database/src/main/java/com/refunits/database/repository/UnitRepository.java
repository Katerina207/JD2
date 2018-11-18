package com.refunits.database.repository;

import com.refunits.database.enumeration.BoilingPoint;
import com.refunits.database.enumeration.UnitRange;
import com.refunits.database.model.Unit;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public interface UnitRepository extends CrudRepository<Unit, Integer> {

    Optional<Unit> findById(Integer id);

    List<Unit> findAll();

    List<Unit> findAllByBoilingPointInAndRangeInAndRefCapacityBetween(
            List<BoilingPoint> boilingPoints,
            List<UnitRange> ranges,
            Double minRefCapacity,
            Double maxRefCapacity);

    Unit save(Unit unit);


}
