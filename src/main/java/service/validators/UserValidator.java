package service.validators;

public class UserValidator {

    /**
     * Validate if the user potential content is valid.
     * Every string should be nonempty
     *
     * @param firstName - String - the firstname we validate
     * @param lastName - String - the lastname we validate
     * @param email - String - the email we check to be valid
     * @param username - String - the username we check to be valid
     * @param password - String - the password we check to be valid
     * @param gender - String - the gender we check to be valid
     */
    public static void validate(String firstName, String lastName, String email,
                                String username, String password, String gender) throws Exception{
        String message = new String();
        if(firstName.length() == 0)
            message = message + "Invalid first name! ";
        if(lastName.length() == 0)
            message = message + "Invalid last name! ";
        if(email.length() == 0)
            message = message + "Invalid email! ";
        if (username.length() == 0)
            message = message + "Invalid username! ";
        if(password.length() == 0)
            message = message + "Invalid password! ";
        if(gender == null)
            message = message + "You have to choose an option for gender! ";
        else
        if(gender.length() == 0)
            message = message + "Invalid gender! ";

        if (message.length() != 0)
            throw new Exception(message);
    }
}
