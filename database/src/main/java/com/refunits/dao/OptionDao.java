package com.refunits.dao;

import com.refunits.model.Option;

public class OptionDao extends BaseDaoImpl<Integer, Option> {

    private static final OptionDao INSTANCE = new OptionDao();

    public static OptionDao getInstance() {
        return INSTANCE;
    }
}