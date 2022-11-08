package com.unittest.practice.user;

import com.unittest.practice.utils.CustomException;

public interface IService {
    /* Validate & Save User to DB */
    User CreateUser(User user) throws CustomException;
}
