package com.unittest.practice.user;

public interface IRepository {
    /* Save User to DB and return User with ID filled */
    User Persist(User user) throws Exception;
}
