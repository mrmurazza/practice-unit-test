package user

import (
	"errors"
	"fmt"
	"strconv"
)

type User struct {
	ID          string
	Name        string
	Phonenumber string
}

func (u *User) Validate() error {
	if u.Name == "" {
		return errors.New("name cannot be empty")
	}

	if u.Phonenumber == "" {
		return errors.New("phonenumber cannot be empty")
	}

	_, err := strconv.Atoi(u.Phonenumber)
	if err != nil {
		return fmt.Errorf("phonenumber value %s is filled with non numeric value", u.Phonenumber)
	}

	return nil
}
