package com.unittest.practice.user.impl;

import com.unittest.practice.user.IService;
import com.unittest.practice.user.IRepository;
import com.unittest.practice.user.User;
import com.unittest.practice.utils.CustomException;
import com.unittest.practice.utils.OtherService;

import java.util.Calendar;
import java.util.Date;

public class Service implements IService {

    private final IRepository repo;
    private final OtherService otherService;

    protected Service(IRepository repo, OtherService otherService) {
        this.repo = repo;
        this.otherService = otherService;
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

    public String someFunction(User user) throws Exception {
        // some mutation on user object
        Date now = Calendar.getInstance().getTime();
        user.setTimestamp(now);

        // will save to DB & fill user id
        user = repo.Persist(user);

        String someString = otherService.generateSomeString(user);

        return String.format("appending-string-%s", someString);
    }
}
