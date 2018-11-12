package com.refunits.dao;

import com.refunits.model.Product;

public class ProductDao extends BaseDaoImpl<Integer, Product> {

    private static final ProductDao INSTANCE = new ProductDao();

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}
