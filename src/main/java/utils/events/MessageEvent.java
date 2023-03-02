package utils.events;

import domain.Message;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MessageEvent implements UsersEvent<String>{
    private final EventType typeOfEvent;

    private final Message oldMessage;

    private final Message newMessage;

    public MessageEvent(EventType typeOfEvent, Message oldMessage, Message newMessage){
        this.typeOfEvent = typeOfEvent;
        this.oldMessage = oldMessage;
        this.newMessage = newMessage;
    }

    public EventType getTypeOfEvent() {
        return typeOfEvent;
    }

    public Message getOldMessage() {
        return oldMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }

    @Override
    public Set<String> getInvolved() {
        return new HashSet<>(Arrays.asList(this.newMessage.getSender(), this.getNewMessage().getReceiver()));
    }
}
