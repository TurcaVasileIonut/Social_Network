package service;

import domain.Message;
import repository.database_repository.MessagesRepository;
import service.validators.MessageValidator;
import utils.events.EventType;
import utils.events.MessageEvent;
import utils.events.UsersEvent;
import utils.observer.ObservableUsers;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ServiceMessages extends ObservableUsers<UsersEvent<String>, String> {
    private final MessagesRepository messageRepository;
    private final MessageValidator messageValidator;

    /**
     * Constructor for ServiceMessage
     * @param messageRepository - the repository for messages
     */
    public ServiceMessages(MessagesRepository messageRepository, MessageValidator messageValidator) {
        this.messageRepository = messageRepository;
        this.messageValidator = messageValidator;
    }

    /**
     * Returns all messages from repository
     * @return  ArrayList < Message >
     */
    public ArrayList< Message > getAllMessages(){
        return (ArrayList<Message>) messageRepository.findAll();
    }

    /**
     * Returns the message with chosen id
     * @param idMessage - Long
     * @return Message
     */
    public Message getById(Long idMessage){
        return messageRepository.findById(idMessage);
    }

    /**
     * Add a new message in the repository
     * @param senderUsername - String - the username of the person who sent the message
     * @param receiverUsername - String - the username of the person expected to receive the message
     * @param messageContent - String - the content of the message
     * @throws Exception - if the message content violates the security rules
     */
    public void addNewMessage(String senderUsername, String receiverUsername, String messageContent) throws Exception{
        this.messageValidator.validate(messageContent);
        Message message = new Message(this.messageRepository.generateAvailableId(),
                senderUsername, receiverUsername, messageContent);
        messageRepository.add(message);
        super.notifyObserversMessage(new MessageEvent(EventType.ADD, null, message));
    }

    /**
     * Removes the message with the chosen id from repository
     * @param idMessage - Long
     */
    public void remove(Long idMessage){
        messageRepository.remove(idMessage);
    }

    public static ArrayList<Message> getArraySortedDESCByDateTime(ArrayList<Message> messages){
        return (ArrayList<Message>) messages.stream().sorted( (message1, message2) ->{
            if(message1.getMessageTime().isAfter(message2.getMessageTime()))
                return -1;
            if(message1.getMessageTime().isBefore(message2.getMessageTime()))
                return 1;
            return 0;
        }).collect(Collectors.toList());
    }

    /**
     * Returns a list with all messages from user A to user B sorted chronological
     * @param username1 - String
     * @param username2 - String
     * @return - ArrayList < Message > -
     */
    public ArrayList<Message> getAllMessagesBetween2Users(String username1, String username2){
        return (ArrayList<Message>) this.messageRepository.getMessagesBetween2User(username1, username2);
    }

    /**
     * Every message that has sender = message.Sender and receiver = message.Receiver and sending time before 
     * message.sendingTime, will be set as received
     * @param message - Message
     */
    public void setMessagesReceived(Message message){
        this.messageRepository.setMessagesReceived(message);
        message.setReceived(true);
        super.notifyObserversMessage(new MessageEvent(EventType.UPDATE, null, message));
    }

    /**
     * Sets all messages from sender to receiver as seen
     * @param message - Message
     */
    public void setMessageSeen(Message message){
        this.messageRepository.setMessageSeen(message.getId());
        message.setSeen(true);
        message.setReceived(true);
        super.notifyObserversMessage(new MessageEvent(EventType.UPDATE, null, message));
    }

    /**
     * Returns the last message from every conversation descending by the moment of sending
     * @param username - String
     * @return - Iterable < Message >
     */
    public Iterable < Message > getLastMessageFromEveryConversation(String username) {
        return getArraySortedDESCByDateTime((ArrayList<Message>) this.messageRepository.getLastMessageFromEveryConversationOfUser(username));
    }
}