package user_interface.widgets_generators;

import domain.Friendship;
import domain.User;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ServiceFriendships;
import service.ServiceUsers;
import service.Services;
import utils.DataTypeConvertors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static user_interface.widgets_generators.HBoxesGenerator.create2StringsHBox;

public class VBoxesGenerator {
    private ServiceUsers serviceUsers;
    private ServiceFriendships serviceFriendships;
    private ButtonsGenerator buttonsGenerator;

    public VBoxesGenerator(Services services){
        this.serviceUsers = services.getServiceUsers();
        this.serviceFriendships = services.getServiceFriendships();
        this.buttonsGenerator = new ButtonsGenerator(services);
    }

    public static class FriendVBox extends VBox{
        private String username1;
        private String username2;
        private Label labelFirstname = new Label();
        private Label labelLastname = new Label();
        private HBox nameHBox;
        private HBox hBoxFriendStateButtons;
        private Button friendProfileButton;
        private final ButtonsGenerator buttonsGenerator;
        private boolean friendStateButtons;
        private boolean userData;
        private boolean profileButton;

        private HBox buttonsHBox;

        private void populateNameHBox(User user){
            this.labelFirstname = new Label(" " + user.getFirstName() + " ");
            this.labelLastname = new Label(" " + user.getLastName() + " ");
            nameHBox = new HBox(labelFirstname, labelLastname);
        }

        public FriendVBox(ServiceUsers serviceUsers, ServiceFriendships serviceFriendships, ButtonsGenerator buttonsGenerator,
                        Stage stage, String username1, String username2, boolean friendStateButtons, boolean userData,
                          boolean profileButton) throws Exception {
            this.username1 = username1;
            this.username2 = username2;
            this.friendStateButtons = friendStateButtons;
            this.userData = userData;
            this.profileButton = profileButton;
            this.buttonsGenerator = buttonsGenerator;
            Friendship friendship = serviceFriendships.getFriendshipById(username1, username2);

            buttonsHBox = new HBox();
            if(userData) {
                populateNameHBox(serviceUsers.getUserByUsername(username2));
                this.getChildren().add(this.nameHBox);
            }
            if(profileButton) {
                this.friendProfileButton = this.buttonsGenerator.makeButtonFriendProfile(stage, username1, username2);
                buttonsHBox.getChildren().add(this.friendProfileButton);
            }
            if(friendStateButtons) {
                this.hBoxFriendStateButtons = getButtonsFriendshipState(username1, username2, friendship);
                buttonsHBox.getChildren().add(this.hBoxFriendStateButtons);
            }

            if(profileButton || friendStateButtons)
                this.getChildren().add(buttonsHBox);
        }

        private HBox getButtonsFriendshipState(String username1, String username2, Friendship friendship) {
            if(friendship == null)
                return new HBox(this.buttonsGenerator.makeButtonAddFriend(username1, username2));
            if(!friendship.getIsPending())
                return new HBox(this.buttonsGenerator.makeButtonDeleteFriend(username1, username2));
            if(friendship.isFromUsername(username1))
                return new HBox(this.buttonsGenerator.makeButtonCancelFriendRequest(username1, username2));

            return new HBox(
                    this.buttonsGenerator.makeButtonAcceptFriendRequest(username1, username2),
                    this.buttonsGenerator.makeButtonDeclineFriendRequest(username1, username2)
            );
        }

        public String getUsername2(){
            return this.username2;
        }

        public void updateFriendStateButtons(Friendship friendship){
            this.hBoxFriendStateButtons = getButtonsFriendshipState(this.username1, this.username2, friendship);
            this.buttonsHBox.getChildren().clear();
            if(profileButton)
                this.buttonsHBox.getChildren().add(this.friendProfileButton);
            if(friendStateButtons) {
                buttonsHBox.getChildren().add(this.hBoxFriendStateButtons);
            }
        }

    }

    /**
     * A method that creates a vbox with friend2 data. If the users are not friends, there will be an add
     * friend button. If there is a pending friend request from user1 to user2, there will be a cancel friend request
     * button. If there is a pending friend request from user2 to user1, there will be an accept button and a decline
     * button. If the users are friends, there will be a delete friend button
     * @param username1 - String
     * @param username2 - String
     * @return
     */
    public FriendVBox makeUserVBox(Stage stage, String username1, String username2) throws Exception {
        return new FriendVBox(serviceUsers, serviceFriendships, buttonsGenerator,
                stage, username1, username2, true, true, true);
    }

    public FriendVBox makeFriendshipChangeVBox(Stage stage, String username1, String username2) throws Exception{
        return new FriendVBox(serviceUsers, serviceFriendships, buttonsGenerator,
                stage, username1, username2, true, false, false);
    }

    public VBox makePendingFriendRequestBox(Stage stage, String username1, String username2) throws Exception {
        return new FriendVBox(serviceUsers, serviceFriendships, buttonsGenerator,
                stage, username1, username2, true, true, true);
    }

    public static VBox createUserNameAndUsernameVBox(User user){
        VBox vBox = new VBox();
        vBox.getChildren().add(create2StringsHBox(user.getFirstName(), user.getLastName()));
        vBox.getChildren().add(new Label(user.getUsername()));
        return vBox;
    }

    public static VBox createMessageTimeVBox(LocalDateTime messageTime){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.BOTTOM_RIGHT);
        if(messageTime.isBefore(LocalDateTime.of(LocalDate.now(), LocalTime.parse("00:00"))))
            vBox.getChildren().add(new Label(DataTypeConvertors.getDateStringFromDateTimeCompressed(messageTime)));
        else
            vBox.getChildren().add(new Label(DataTypeConvertors.getCompressedTimeStringFromDateTime(messageTime)));
        return vBox;
    }
}
