package com.refunits.dao;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.refunits.connection.ConnectionManager;
import com.refunits.enumeration.BoilingPoint;
import com.refunits.enumeration.UnitRange;
import com.refunits.model.QUnit;
import com.refunits.model.Unit;
import lombok.Cleanup;
import org.hibernate.Session;

import java.util.List;

public class UnitDao extends BaseDaoImpl<Integer, Unit> {

    private static final QUnit UNIT = QUnit.unit;

    private static final UnitDao INSTANCE = new UnitDao();

    public static UnitDao getInstance() {
        return INSTANCE;
    }

    public List<Unit> getFiltered(List<BoilingPoint> boilingPoints, List<UnitRange> ranges,
                                  Double minRefCapacity, Double maxRefCapacity, Integer limit) {
        @Cleanup Session session = ConnectionManager.getSession();
        return new JPAQuery<Tuple>(session)
                .select(UNIT)
                .from(UNIT)
                .where(UNIT.boilingPoint.in(boilingPoints)
                        .and(UNIT.range.in(ranges))
                        .and(UNIT.refCapacity.between(minRefCapacity, maxRefCapacity)))
                .limit(limit)
                .fetch();
    }
}