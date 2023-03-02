package service.validators;

import domain.Friendship;

import java.util.Objects;

public class FriendshipValidator {

    /**
     * Validate if a friendship content is valid
     * @param entity The friendship we want to validate
     * @throws Exception if the friendship is not valid
     */
    public static void validate(Friendship entity) throws Exception{
        String message = new String();
        String usernameFriend1 = entity.getIdFriend1();
        String usernameFriend2 = entity.getIdFriend2();
        if(usernameFriend1.isEmpty())
            message = message + "Invalid first name! ";
        if(usernameFriend2.isEmpty())
            message = message + "Invalid last name! ";
        if(usernameFriend1.equals(usernameFriend2))
            message = message + "Invalid pair of ids! ";

        if (message.length() != 0)
            throw new Exception(message);
    }
}
