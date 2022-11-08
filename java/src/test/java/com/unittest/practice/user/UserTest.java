package com.unittest.practice.user;

import com.unittest.practice.utils.CustomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTest {

    @Test
    void validateValidUser(){
        User input = new User(null, "Razza", "01234");

        CustomException actualException = null;
        try {
            input.validate();
        } catch (CustomException exception) {
            actualException = exception;
        }

        assertNull(actualException);
    }

    @Test
    void validateInValidUser(){
        User input = new User(null, "", "01234");
        String expectedErrorMsg = "name cannot be empty";

        CustomException actualException = null;
        try {
            input.validate();
        } catch (CustomException exception) {
            actualException = exception;
        }

        assertNotNull(actualException);
        assertEquals(expectedErrorMsg, actualException.getMessage());
    }

    @Test
    void validateInValidUser_2(){
        User input = new User(null, "Razza", "");
        String expectedErrorMsg = "phoneNumber cannot be empty";

        CustomException actualException = null;
        try {
            input.validate();
        } catch (CustomException exception) {
            actualException = exception;
        }

        assertNotNull(actualException);
        assertEquals(expectedErrorMsg, actualException.getMessage());
    }

    @Test
    void validateInValidUser_3(){
        User input = new User(null, "Razza", "asd");
        String expectedErrorMsg = "phoneNumber must be numeric only";

        CustomException actualException = null;
        try {
            input.validate();
        } catch (CustomException exception) {
            actualException = exception;
        }

        assertNotNull(actualException);
        assertEquals(expectedErrorMsg, actualException.getMessage());
    }
}