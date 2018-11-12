package com.refunits.dao;

import com.refunits.model.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<P extends Serializable, E extends BaseEntity<P>> {

    P save(E entity);

    void update(E entity);

    void delete(E entity);

    E getById(P id);

    List<E> getAll();
}
