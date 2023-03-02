package user_interface.scene_changer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.Services;
import user_interface.Runner;
import user_interface.homepage.*;
import user_interface.homepage.profile.EditProfileController;
import user_interface.homepage.profile.ProfileController;

import java.io.IOException;

public class HomepageSceneChanger {
    /**
     * Configure the controller for the search homepage
     * @param fxmlLoader - FXMLLoader - the loader of the homepage
     */
    private static void configureHomePageSearchController(String username, Services services, FXMLLoader fxmlLoader){
        SearchController homePageController = fxmlLoader.getController();
        homePageController.setServices(services);
        homePageController.setUsername(username);
        homePageController.setUp();
    }

    /**
     * Change current scene to the search homepage
     * @throws IOException - If there is a problem loading the page
     */
    public static void changeSceneToHomeSearch(Stage stage, String username, Services services) throws Exception {
        if(Runner.class.getResource("/user_interface/homepage/Search.fxml") == null)
            throw new Exception("Resource not found: Feed.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/user_interface/homepage/Search.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        configureHomePageSearchController(username, services, fxmlLoader);
        stage.setTitle("Search");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Configure the controller for the homepage
     * @param username - String - the id of the user that will be redirected to the homepage
     * @param fxmlLoader - FXMLLoader - the loader of the homepage
     */
    private static void configureHomeFeedController(String username, Services services, FXMLLoader fxmlLoader){
        FeedController homePageController = fxmlLoader.getController();
        homePageController.setServices(services);
        homePageController.setUsername(username);
        homePageController.setUp();
    }

    /**
     * Change current scene to the homepage
     * @param username - String - the user that will be redirected to the homepage
     * @throws IOException - If there is a problem loading the page
     */
    public static void changeSceneToHomeFeed(Stage stage, String username, Services services) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/user_interface/homepage/Feed.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        configureHomeFeedController(username, services, fxmlLoader);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Configure the controller for the homepage
     * @param username - String - the id of the user that will be redirected to the homepage
     * @param fxmlLoader - FXMLLoader - the loader of the homepage
     */
    private static void configureHomeNotificationsController(String username, Services services, FXMLLoader fxmlLoader){
        NotificationsController homePageController = fxmlLoader.getController();
        homePageController.setServices(services);
        homePageController.setUsername(username);
        homePageController.setUp();
    }

    /**
     * Change current scene to the homepage
     * @param username - String - the user that will be redirected to the homepage
     * @throws IOException - If there is a problem loading the page
     */
    public static void changeSceneToHomeNotifications(Stage stage, String username, Services services) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/user_interface/homepage/Notifications.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        configureHomeNotificationsController(username, services, fxmlLoader);
        stage.setTitle("Notifications");
        stage.setScene(scene);
        stage.show();
    }

    /** Configure the controller for the notifications homepage
     * @param username - String - the id of the user that will be redirected to the homepage
     * @param fxmlLoader - FXMLLoader - the loader of the homepage
     */
    private static void configureHomeInboxController(String username, Services services, String friend, FXMLLoader fxmlLoader){
        InboxController homePageController = fxmlLoader.getController();
        homePageController.setServices(services);
        homePageController.setUsername(username);
        homePageController.setUp(friend);
    }

    /**
     * Change current scene to the homepage with conversations
     * @param username - String - the user that will be redirected to the homepage
     * @throws IOException - If there is a problem loading the page
     */
    public static void changeSceneToHomeInbox(Stage stage, String username, String friend, Services services) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/user_interface/homepage/Inbox.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        configureHomeInboxController(username, services, friend, fxmlLoader);
        stage.setTitle("Inbox");
        stage.setScene(scene);
        stage.show();
    }

    /** Configure the controller for the notifications homepage
     * @param loggedUser - String - the id of the user that will be redirected to the homepage
     * @param fxmlLoader - FXMLLoader - the loader of the homepage
     */
    private static void configureProfileController(String loggedUser, String friend, Services services, FXMLLoader fxmlLoader) throws Exception {
        ProfileController profileController = fxmlLoader.getController();
        profileController.setServices(services);
        profileController.setUsername(loggedUser);
        profileController.setUp(friend);
    }

    /**
     * Change current scene to the homepage with conversations
     * @param loggedUser - String - the user that will be redirected to the homepage
     * @throws IOException - If there is a problem loading the page
     */
    public static void changeSceneToHomeProfile(Stage stage, String loggedUser, String friend, Services services) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/user_interface/homepage/profile/Profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Profile");
        stage.setScene(scene);
        configureProfileController(loggedUser, friend, services, fxmlLoader);
        stage.show();
    }

    /** Configure the controller for the notifications homepage
     * @param username - String - the id of the user that will be redirected to the homepage
     * @param fxmlLoader - FXMLLoader - the loader of the homepage
     */
    private static void configureEditProfileController(String username, Services services, FXMLLoader fxmlLoader) throws Exception {
        EditProfileController profileController = fxmlLoader.getController();
        profileController.setServices(services);
        profileController.setUsername(username);
        profileController.setUp();
    }

    /**
     * Change current scene to the homepage with conversations
     * @param username - String - the user that will be redirected to the homepage
     * @throws IOException - If there is a problem loading the page
     */
    public static void changeSceneToEditProfile(Stage stage, String username, Services services) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/user_interface/homepage/profile/EditProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        configureEditProfileController(username, services, fxmlLoader);
        stage.setTitle("Edit Profile");
        stage.setScene(scene);
        stage.show();
    }
}
