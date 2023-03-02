package utils.events;

import domain.Friendship;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static utils.events.EventType.ADD;
import static utils.events.EventType.UPDATE;

public class FriendshipChangeEvent implements UsersEvent<String>{
    private final Friendship oldFriendship;
    private final Friendship newFriendship;
    private final EventType typeOfEvent;

    public FriendshipChangeEvent(EventType typeOfEvent, Friendship oldFriendship, Friendship newFriendship){
        this.typeOfEvent = typeOfEvent;
        this.oldFriendship = oldFriendship;
        this.newFriendship = newFriendship;
    }

    public Friendship getOldFriendship() {
        return oldFriendship;
    }

    public Friendship getNewFriendship() {
        return newFriendship;
    }

    public EventType getTypeOfEvent() {
        return typeOfEvent;
    }

    @Override
    public Set<String> getInvolved() {
        if(typeOfEvent == ADD || typeOfEvent == UPDATE)
            return new HashSet<>(Arrays.asList(this.newFriendship.getIdFriend1(), this.newFriendship.getIdFriend2()));
        return new HashSet<>(Arrays.asList(this.oldFriendship.getIdFriend1(), this.oldFriendship.getIdFriend2()));
    }

    public String getOtherFriendInvolved(String username){
        if (typeOfEvent == EventType.ADD || typeOfEvent == EventType.UPDATE)
            return newFriendship.getOtherFriend(username);
        return oldFriendship.getOtherFriend(username);
    }
}
