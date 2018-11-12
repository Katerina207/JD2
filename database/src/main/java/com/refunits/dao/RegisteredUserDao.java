package com.refunits.dao;

import com.refunits.model.RegisteredUser;

public class RegisteredUserDao extends BaseDaoImpl<Integer, RegisteredUser> {

    private static final RegisteredUserDao INSTANCE = new RegisteredUserDao();

    public static RegisteredUserDao getInstance() {
        return INSTANCE;
    }
}
