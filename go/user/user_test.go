package user_test

import (
	"errors"
	"testing"

	"practice-unit-test/user"
	"practice-unit-test/user/impl"
	"practice-unit-test/user/mocks"

	"github.com/stretchr/testify/assert"
)

// we put our mock classes & target classes in global var for reusablity
type TestData struct {
	MockRepo *mocks.Repository
	UserSvc  user.Service
}

var testData TestData

func init() {
	mockRepo := new(mocks.Repository)

	testData = TestData{
		MockRepo: mockRepo,
		UserSvc: impl.NewService(
			mockRepo,
		),
	}
}

func TestCreateUser(test *testing.T) {
	test.Run("postive case", func(t *testing.T) {
		// inputs & outputs
		validUser := user.User{
			Name:        "Name",
			Phonenumber: "0123456",
		}

		validUserWithID := user.User{
			ID:          "123",
			Name:        "Name",
			Phonenumber: "0123456",
		}

		// simulate mocks
		testData.MockRepo.On("Persist", validUser).Return(validUserWithID, nil).Once()

		result, err := testData.UserSvc.CreateUser(validUser)

		// assertion
		assert.Nil(t, err)
		assert.Equal(t, validUserWithID, *result)
		assert.True(t, testData.MockRepo.AssertExpectations(t), "mock method from mock repo not called as expected")
		assert.True(t, testData.MockRepo.AssertNumberOfCalls(t, "Persist", 1))

	})
}

func TestValidate(test *testing.T) {
	test.Run("postive case", func(t *testing.T) {
		validUser := user.User{
			Name:        "Name",
			Phonenumber: "0123456",
		}

		err := validUser.Validate()

		assert.Nil(t, err)
	})

	test.Run("negative case 1", func(t *testing.T) {
		invalidUser := user.User{
			Name:        "",
			Phonenumber: "0123456",
		}
		expectedErr := errors.New("name cannot be empty")

		err := invalidUser.Validate()

		assert.NotNil(t, err)
		assert.Equal(t, expectedErr, err)
	})
}
