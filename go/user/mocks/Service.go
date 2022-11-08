// Code generated by mockery 2.9.0. DO NOT EDIT.

package mocks

import (
	user "practice-unit-test/user"

	mock "github.com/stretchr/testify/mock"
)

// Service is an autogenerated mock type for the Service type
type Service struct {
	mock.Mock
}

// CreateUser provides a mock function with given fields: u
func (_m *Service) CreateUser(u user.User) (*user.User, error) {
	ret := _m.Called(u)

	var r0 *user.User
	if rf, ok := ret.Get(0).(func(user.User) *user.User); ok {
		r0 = rf(u)
	} else {
		if ret.Get(0) != nil {
			r0 = ret.Get(0).(*user.User)
		}
	}

	var r1 error
	if rf, ok := ret.Get(1).(func(user.User) error); ok {
		r1 = rf(u)
	} else {
		r1 = ret.Error(1)
	}

	return r0, r1
}