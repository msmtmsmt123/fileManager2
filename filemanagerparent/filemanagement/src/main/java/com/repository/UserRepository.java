package com.repository;

import com.entity.User;

public interface UserRepository {
    Long createUser(User user);
    User read(User user);
    User update(User user);
    User delete(User user);
}
