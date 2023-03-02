module com.example.Social_Network {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.swing;

    exports user_interface;
    opens user_interface to javafx.fxml;
    exports user_interface.authentication;
    opens user_interface.authentication to javafx.fxml;
    exports user_interface.homepage;
    opens user_interface.homepage to javafx.fxml;
    exports user_interface.scene_changer;
    opens user_interface.scene_changer to javafx.fxml;
    exports user_interface.homepage.profile;
    opens user_interface.homepage.profile to javafx.fxml;
    exports utils;
    opens utils to javafx.fxml;
}