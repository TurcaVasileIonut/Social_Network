package user_interface.homepage.profile;

import domain.UserProfile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.Services;
import user_interface.NotificationPopups;
import user_interface.homepage.MainController;
import user_interface.scene_changer.HomepageSceneChanger;
import user_interface.widgets_generators.VBoxesGenerator;
import utils.events.FriendshipChangeEvent;
import utils.events.UsersEvent;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class ProfileController extends MainController {

    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label description;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView editProfileButtonImageView;
    @FXML
    private VBox friendVBox;

    private VBoxesGenerator.FriendVBox friendshipStateButtonsVBox;

    /**
     * A method that loads friend profile components
     * @param profileOwner - String
     * @throws Exception
     */
    public void setUpFriendProfile(String profileOwner) throws Exception {
        editProfileButtonImageView.setStyle("-fx-opacity: 0");
        Stage stage = (Stage) friendVBox.getScene().getWindow();
        friendshipStateButtonsVBox = vBoxesGenerator.makeFriendshipChangeVBox(stage, this.username, profileOwner);
        friendVBox.getChildren().add(friendshipStateButtonsVBox);
    }

    /**
     * Method that loads our own profile
     */
    public void setUpOwnProfile(){
        editProfileButtonImageView.setOnMouseClicked(e->{
            clickEditProfile();
        });
    }

    /**
     * A method that loads the current user profile picture
     * @param user - UserProfile
     */
    public void loadProfilePicture(UserProfile user){
        System.out.println(user.getProfilePicturePath());
        if(user.getProfilePicturePath() == null){
            this.imageView.setImage(new Image("defaultProfilePicture.png"));
        }else {
            try {
                InputStream stream = new FileInputStream(user.getProfilePicturePath());
                Image image = new Image(stream);
                imageView.setImage(image);
            }catch (Exception e){
                e.printStackTrace();
                NotificationPopups.errorPopup("Your image is being changed. The changes will appear in short time");
                this.imageView.setImage(new Image("defaultProfilePicture.png"));
            }
        }
    }

    /**
     * A method that makes configurations for current profile
     * @param profileOwnerUsername - String
     * @throws Exception
     */
    public void setUp(String profileOwnerUsername) throws Exception {
        UserProfile user = serviceUsers.getUserProfileByUsername(profileOwnerUsername);
        this.firstnameLabel.setText(user.getFirstName());
        this.lastnameLabel.setText(user.getLastName());
        this.usernameLabel.setText("@" + user.getUsername());
        this.description.setText(user.getDescription());
        loadProfilePicture(user);
        if(Objects.equals(profileOwnerUsername, this.username))
            setUpOwnProfile();
        else
            setUpFriendProfile(profileOwnerUsername);
        this.serviceFriendships.addObserver(this, this.username);
        this.serviceMessages.addObserver(this, this.username);
    }

    /**
     * A method that will open editProfile page for logged user
     */
    @FXML
    void clickEditProfile(){
        try {
            Stage stage = (Stage)buttonSearch.getScene().getWindow();
            Services services = new Services(serviceUsers, serviceFriendships, serviceMessages);
            HomepageSceneChanger.changeSceneToEditProfile(stage, username, services);
        }catch (Exception e){
            NotificationPopups.errorPopup(e.getMessage());
        }
    }

    @Override
    public void updateFriendships(UsersEvent<String> event) {
        if(friendshipStateButtonsVBox == null)
            return;
        friendshipStateButtonsVBox.updateFriendStateButtons(((FriendshipChangeEvent)event).getNewFriendship());
    }
}
