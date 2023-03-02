package user_interface;

import service.ServiceFriendships;
import service.ServiceMessages;
import service.ServiceUsers;
import service.Services;
import user_interface.widgets_generators.VBoxesGenerator;

public class BaseController{
    protected ServiceUsers serviceUsers;

    protected ServiceFriendships serviceFriendships;

    protected ServiceMessages serviceMessages;

    protected VBoxesGenerator vBoxesGenerator;

    public void setServices(Services services){
        this.serviceUsers = services.getServiceUsers();
        this.serviceFriendships = services.getServiceFriendships();
        this.serviceMessages = services.getServiceMessages();
        vBoxesGenerator = new VBoxesGenerator(services);
    }

    /**
     * Set the serviceUsers for the current controller
     * @param serviceUsers - ServiceUser - the service for the users
     */
    public void setServiceUsers(ServiceUsers serviceUsers){
        this.serviceUsers = serviceUsers;
    }

    /**
     * Set the serviceFriendships for the current controller
     * @param serviceFriendships - ServiceFriendships - the service for the users
     */
    public void setServiceFriendships(ServiceFriendships serviceFriendships){
        this.serviceFriendships = serviceFriendships;
    }

}
