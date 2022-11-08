package com.unittest.practice.user;

import java.util.Calendar;
import java.util.Date;

public class UserTestVariables {
    //  January 1, 2022 12:00:00 AM
    public static final Date MOCK_DATE = new Date(1640995200000L);
    public static final User VALID_USER = new User(null, "Razza", "01234");
    public static final User VALID_USER_WITH_TS = new User(null, "Razza", "01234", MOCK_DATE);

    public static final User VALID_USER_WITH_ID = new User("ID", "Razza", "01234");
    public static final User VALID_USER_FULL = new User("ID", "Razza", "01234", MOCK_DATE);

    public static final User INVALID_USER_EMPTY_NAME = new User(null, "", "01234");

    public static final User INVALID_USER_EMPTY_PHONE = new User(null, "Razza", "");

    public static final User INVALID_USER_NON_NUMERIC_PHONE = new User(null, "Razza", "asd");

}
