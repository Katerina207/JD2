package com.refunits.dao;

import com.refunits.model.PreOrder;

public class PreOrderDao extends BaseDaoImpl<Integer, PreOrder> {

    private static final PreOrderDao INSTANCE = new PreOrderDao();

    public static PreOrderDao getInstance() {
        return INSTANCE;
    }
}
