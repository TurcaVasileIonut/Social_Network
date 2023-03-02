package user_interface.scene_changer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.Services;
import user_interface.Runner;
import user_interface.authentication.LoginPageController;
import user_interface.authentication.RegisterPageController;

public class LoginRegisterSceneChanger {
    /**
     * Configure the controller of the register page
     * @param fxmlLoader - FXMLoader - the loader of the homepage
     */
    public static void configureRegisterPageController(Services services, FXMLLoader fxmlLoader){
        RegisterPageController registerPageController = fxmlLoader.getController();
        registerPageController.setServices(services);
    }

    /**
     * Change current scene to register page
     * @throws Exception - if the page change has failed
     */
    public static void changeSceneToRegister(Stage stage, Services services) throws Exception {
        if (Runner.class.getResource("/user_interface/authentication/RegisterPage.fxml") == null)
            throw new Exception("Resource not found: RegisterPage.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/user_interface/authentication/RegisterPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        configureRegisterPageController(services, fxmlLoader);
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Configure the login page controller
     * @param fxmlLoader - FXMLLoader - the loader of the login page
     */
    public static void configureLoginPageController(Services services, FXMLLoader fxmlLoader){
        LoginPageController loginPageController = fxmlLoader.getController();
        loginPageController.setServices(services);
    }

    /**
     * Change scene to login page
     * @throws Exception - if the change scene event has failed
     */
    public static void changeSceneToLogin(Stage stage, Services services) throws Exception {
        if(Runner.class.getResource("/user_interface/authentication/LoginPage.fxml") == null)
            throw new Exception("Resource not found: LoginPage.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/user_interface/authentication/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        configureLoginPageController(services, fxmlLoader);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

}
