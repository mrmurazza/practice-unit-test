package com.unittest.practice.user.impl;

import com.unittest.practice.user.IRepository;
import com.unittest.practice.user.User;
import com.unittest.practice.user.UserTestVariables;
import com.unittest.practice.utils.CustomException;
import com.unittest.practice.utils.OtherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import java.util.Calendar;

import static com.unittest.practice.user.UserTestVariables.MOCK_DATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceTest {
    private Service underTest;
    @Mock
    private IRepository mockRepo;
    @Mock
    private OtherService mockOtherService;

    @BeforeEach
    void setUp() throws MockitoException {
        MockitoAnnotations.openMocks(this);

        underTest = new Service(mockRepo, mockOtherService);
    }

    @Test
    void createUserValid() throws Exception {
        User input = UserTestVariables.VALID_USER_WITH_TS;

        Mockito.mockStatic(Calendar.class);
        Calendar calendarMock = Mockito.mock(Calendar.class);
        when(Calendar.getInstance()).thenReturn(calendarMock);
        when(calendarMock.getTime()).thenReturn(MOCK_DATE);

        User expected = UserTestVariables.VALID_USER_FULL;
        when(mockRepo.Persist(input)).thenReturn(expected);

        CustomException actualException = null;
        User actual = null;
        try {
            actual = underTest.CreateUser(input);
        } catch (CustomException exception) {
            actualException = exception;
        }

        assertNotNull(actual);
        assertNull(actualException);
        assertEquals(expected, actual);
        verify(mockRepo, times(1)).Persist(input);
    }

    @Test
    void createInvalidUser_1() throws Exception {
        User input = UserTestVariables.INVALID_USER_EMPTY_NAME;
        String expectedErrorMessage = "name cannot be empty";

        CustomException actualException = null;
        User actual = null;
        try {
            actual = underTest.CreateUser(input);
        } catch (CustomException exception) {
            actualException = exception;
        }

        assertNull(actual);
        assertNotNull(actualException);
        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(mockRepo, times(0)).Persist(input);
    }

    @Test
    void createInvalidUser_2() throws Exception {
        User input = UserTestVariables.INVALID_USER_NON_NUMERIC_PHONE;
        String expectedErrorMessage = "phoneNumber must be numeric only";

        CustomException actualException = null;
        User actual = null;
        try {
            actual = underTest.CreateUser(input);
        } catch (CustomException exception) {
            actualException = exception;
        }

        assertNull(actual);
        assertNotNull(actualException);
        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(mockRepo, times(0)).Persist(input);
    }

    @Test
    void createUser_ErrorRepo() throws Exception {
        User input = UserTestVariables.VALID_USER;
        String expectedErrorMessage = "Found repo error on persist";

        when(mockRepo.Persist(input)).thenThrow(new Exception());

        CustomException actualException = null;
        User actual = null;
        try {
            actual = underTest.CreateUser(input);
        } catch (CustomException exception) {
            actualException = exception;
        }

        assertNull(actual);
        assertNotNull(actualException);
        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(mockRepo, times(1)).Persist(input);
    }

    @Test
    void someFunction_test() throws Exception{
        User input = UserTestVariables.VALID_USER;
        String expected = "appending-string-some-string";

        Mockito.mockStatic(Calendar.class);
        Calendar calendarMock = Mockito.mock(Calendar.class);
        when(Calendar.getInstance()).thenReturn(calendarMock);
        when(calendarMock.getTime()).thenReturn(MOCK_DATE);

        when(mockRepo.Persist(UserTestVariables.VALID_USER_WITH_TS))
            .thenReturn(UserTestVariables.VALID_USER_FULL);

        when(mockOtherService.generateSomeString(UserTestVariables.VALID_USER_FULL))
            .thenReturn("some-string");

        Exception actualException = null;
        String actual = null;
        try {
            actual = underTest.someFunction(input);
        } catch (Exception exception) {
            actualException = exception;
        }

        assertNotNull(actual);
        assertNull(actualException);
        assertEquals(expected, actual);
        verify(mockRepo, times(1)).Persist(input);
        verify(mockOtherService, times(1))
            .generateSomeString(UserTestVariables.VALID_USER_FULL);
    }
}