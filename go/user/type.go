package user

type Service interface {
	// Validate User and save to DB
	CreateUser(u User) (*User, error)
}

type Repository interface {
	// Save Entity User to DB and return the object with ID filled
	Persist(u User) (User, error)
}
