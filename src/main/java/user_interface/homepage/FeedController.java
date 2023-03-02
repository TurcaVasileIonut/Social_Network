package user_interface.homepage;

import utils.events.UsersEvent;

public class FeedController extends MainController {


    public void setUp(){
        serviceMessages.addObserver(this, this.username);
        serviceFriendships.addObserver(this, this.username);
    }

}
