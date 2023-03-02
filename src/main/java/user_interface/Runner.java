package user_interface;

import config.Config;
import domain.Friendship;
import domain.User;
import javafx.application.Application;
import javafx.stage.Stage;
import repository.Repository;
import repository.database_repository.FriendshipsRepository;
import repository.database_repository.MessagesRepository;
import repository.database_repository.UsersRepository;
import service.ServiceFriendships;
import service.ServiceMessages;
import service.ServiceUsers;
import service.Services;
import service.validators.FriendshipValidator;
import service.validators.MessageValidator;
import service.validators.UserValidator;
import user_interface.scene_changer.LoginRegisterSceneChanger;

import java.io.IOException;
import java.util.ArrayList;

public class Runner extends Application {

    private void openLoginPage(Services services) throws Exception {
        LoginRegisterSceneChanger.changeSceneToLogin(new Stage(), services);
        LoginRegisterSceneChanger.changeSceneToLogin(new Stage(), services);
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        UserValidator userValidator = new UserValidator();
        FriendshipValidator friendshipValidator = new FriendshipValidator();
        MessageValidator messageValidator = new MessageValidator();

        Repository<String, User> usersRepository = new UsersRepository(
                Config.getProperties().getProperty("databaseUrl"),
                Config.getProperties().getProperty("username"),
                Config.getProperties().getProperty("password")
        );
        Repository<ArrayList<String>, Friendship> friendshipsRepository = new FriendshipsRepository(
                Config.getProperties().getProperty("databaseUrl"),
                Config.getProperties().getProperty("username"),
                Config.getProperties().getProperty("password")
        );
        MessagesRepository messagesRepository = new MessagesRepository(
                Config.getProperties().getProperty("databaseUrl"),
                Config.getProperties().getProperty("username"),
                Config.getProperties().getProperty("password")
        );

        ServiceUsers serviceUsers = new ServiceUsers(usersRepository, userValidator);
        ServiceFriendships serviceFriendships = new ServiceFriendships(friendshipsRepository, friendshipValidator);
        ServiceMessages serviceMessages = new ServiceMessages(messagesRepository, messageValidator);

        Services services = new Services(serviceUsers, serviceFriendships, serviceMessages);
        try {
            openLoginPage(services);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
