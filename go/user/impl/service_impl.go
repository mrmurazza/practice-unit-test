package impl

import "practice-unit-test/user"

type serviceImpl struct {
	repo user.Repository
}

func NewService(r user.Repository) user.Service {
	return &serviceImpl{
		repo: r,
	}
}

func (s *serviceImpl) CreateUser(u user.User) (*user.User, error) {
	err := u.Validate()
	if err != nil {
		return nil, err
	}

	u, err = s.repo.Persist(u)
	if err != nil {
		return nil, err
	}

	return &u, nil
}
