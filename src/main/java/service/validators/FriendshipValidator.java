package service.validators;

import domain.Friendship;
import service.validators.exceptions.FriendshipInvalidException;
import service.validators.exceptions.SecurityFaultException;

public class FriendshipValidator {

    /**
     * Validate if a friendship content is valid
     * @throws Exception if the friendship is not valid
     */
    public static void validate(String usernameFriend1, String usernameFriend2) throws FriendshipInvalidException, SecurityFaultException {
        String message = new String();
        if(usernameFriend1.isEmpty())
            message = message + "Invalid first name! ";
        if(usernameFriend2.isEmpty())
            message = message + "Invalid last name! ";
        if(usernameFriend1.equals(usernameFriend2))
            message = message + "Invalid pair of ids! ";
        if(usernameFriend1.equals(usernameFriend2))
            message = message + "Users must be different! ";
        SqlInjectionValidator.validate(usernameFriend1, usernameFriend2);


        if (message.length() != 0)
            throw new FriendshipInvalidException(message);
    }
}
