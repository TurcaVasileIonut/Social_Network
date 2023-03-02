package service;

import config.Config;
import domain.User;
import domain.UserProfile;
import repository.Repository;
import repository.database_repository.UsersRepository;
import service.validators.UserValidator;
import utils.encryptors.StringEncryption;

import java.util.*;

public class ServiceUsers {

    private final Repository<String, User> usersRepo;
    private final UserValidator userValidator;
    /**
     * Constructor for creating a service
     * @param usersRepo - repository of users
     * @param userValidator - validator for users
     */
    public ServiceUsers(Repository<String, User> usersRepo, UserValidator userValidator){
        this.usersRepo = usersRepo;
        this.userValidator = userValidator;
    }

    /**
     * @param username - the username of the user we are looking for
     * @return a User object - the user with id
     */
    public User getUserByUsername(String username) throws Exception {
        User userId = usersRepo.findById(username);

        if(userId == null)
            throw new Exception("Id not found! ");

        return userId;
    }

    /**
     * @param username - the username of the user we are looking for
     * @return UserProfile
     */
    public UserProfile getUserProfileByUsername(String username) throws Exception {
        UserProfile userProfile = ((UsersRepository)this.usersRepo).getUserProfileById(username);
        if(userProfile == null)
            throw new Exception("User not found! ");
        return userProfile;
    }

    /**
     * Add a new user in the list
     * @param firstName of the user
     * @param lastName of the user
     * @throws Exception if the parameters are not suitable for a user
     */
    public void addUser(String username, String password, String firstName, String lastName, String email,
                        String gender) throws Exception{
        UserValidator.validate(firstName, lastName, email, username, password, gender);

        String securePassword = StringEncryption.encrypt(password,
                Config.getProperties().getProperty("salt"),
                Config.getProperties().getProperty("key"));

        User user = new User( username, securePassword,firstName, lastName, email, gender);
        usersRepo.add(user);
    }

    /** Transform an Iterable object into a collection
     * @param iter the iterable object we transform
     * @return the collection object we just created
     */
    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }

    /**
     * Return a list with all users from repository
     * @return the array with the users
     */
    public ArrayList<User> getAllUsers(){
        Collection<User> iterable = makeCollection(usersRepo.findAll());
        return new ArrayList<>(iterable);
    }

    /**
     * Returns a list with all users that have name in their firstname or in their lastname
     * @param name - String - the name we search
     * @return - ArrayList < User > - all users that have name
     */
    public ArrayList<User> getUsersByPartialName(String name){
        if(name.isEmpty())
            return new ArrayList<>();
        ArrayList <User> matchingUsers = new ArrayList<>();

        name = name.toLowerCase();
        Iterable <User> allUsers =this.getAllUsers();
        for(User user : allUsers){
            if(user.getFirstName().toLowerCase().contains(name) || user.getLastName().toLowerCase().contains(name))
                matchingUsers.add(user);
        }

        return matchingUsers;
    }

    public void updateDescription(String newDescription, String username){
        ((UsersRepository)this.usersRepo).updateDescription(newDescription, username);
    }

    public void changeProfilePicturePath(String newPath, String username) {
        ((UsersRepository)this.usersRepo).changeProfilePicturePath(newPath, username);
    }
}
