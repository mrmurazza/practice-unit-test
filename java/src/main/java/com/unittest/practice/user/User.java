package com.unittest.practice.user;

import com.unittest.practice.utils.CustomException;

import java.util.Date;

public class User {
    private String id;
    private String name;
    private String phoneNumber;

    private Date timestamp;

    protected User(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    protected User(String id, String name, String phoneNumber, Date timestamp) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void validate() throws CustomException {
        if (name == null || name.isEmpty()) {
            throw new CustomException("name cannot be empty");
        }

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new CustomException("phoneNumber cannot be empty");
        }

        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            throw new CustomException("phoneNumber must be numeric only");
        }
    }
}
