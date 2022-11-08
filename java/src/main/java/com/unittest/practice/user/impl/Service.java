package com.unittest.practice.user.impl;

import com.unittest.practice.user.IService;
import com.unittest.practice.user.IRepository;
import com.unittest.practice.user.User;
import com.unittest.practice.utils.CustomException;

import java.util.Calendar;
import java.util.Date;

public class Service implements IService {

    private final IRepository repo;

    protected Service(IRepository repo) {
        this.repo = repo;
    }

    @Override
    public User CreateUser(User user) throws CustomException {
        user.validate();

        try {
            Date now = Calendar.getInstance().getTime();
            user.setTimestamp(now);

            return repo.Persist(user);
        } catch (Exception e) {
            throw new CustomException("Found repo error on persist");
        }
    }
}
